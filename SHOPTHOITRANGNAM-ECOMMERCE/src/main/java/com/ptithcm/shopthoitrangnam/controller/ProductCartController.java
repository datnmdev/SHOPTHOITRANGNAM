package com.ptithcm.shopthoitrangnam.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptithcm.shopthoitrangnam.dto.ShoppingCartDto;
import com.ptithcm.shopthoitrangnam.entity.Account;
import com.ptithcm.shopthoitrangnam.entity.Color;
import com.ptithcm.shopthoitrangnam.entity.Customer;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.entity.FlashSaleDetail;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSaleDetail;
import com.ptithcm.shopthoitrangnam.entity.Product;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
import com.ptithcm.shopthoitrangnam.entity.SaleOffDetail;
import com.ptithcm.shopthoitrangnam.entity.SellingPrice;
import com.ptithcm.shopthoitrangnam.entity.ShoppingCart;
import com.ptithcm.shopthoitrangnam.entity.Size;
import com.ptithcm.shopthoitrangnam.service.AccountService;
import com.ptithcm.shopthoitrangnam.service.CustomerService;
import com.ptithcm.shopthoitrangnam.service.FlashSaleService;
import com.ptithcm.shopthoitrangnam.service.FlatRateSaleService;
import com.ptithcm.shopthoitrangnam.service.SaleOffService;
import com.ptithcm.shopthoitrangnam.service.ShoppingCartService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProductCartController {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	FlashSaleService flashSaleService;
	
	@Autowired
	SaleOffService saleOffService;
	
	@Autowired
	FlatRateSaleService flatRateSaleService;
	
	@Value("${shopthoitrangnam-manage.domain}")
	private String mainHost;
	
	@GetMapping(value = "/product-carts", params = "add")
	public String addProductInCart(Model model, HttpServletRequest httpServletRequest,
			@RequestParam("add") Integer productDetailId, RedirectAttributes redirectAttributes) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<Account> account = accountService.findByUsername(authentication.getName());
		Customer customer = null;
		if (account.isPresent()) {
			customer = customerService.findByAccount(account.get()).get();
		}
		model.addAttribute("customer", customer);
		
		ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
		shoppingCartDto.setCustomerCode(customer.getCustomerCode());
		shoppingCartDto.setProductDetailId(productDetailId);
		shoppingCartDto.setQuantity(1);
		shoppingCartService.save(shoppingCartDto);
		
		redirectAttributes.addFlashAttribute("hasError", false);
		model.addAttribute("hasError", false);
		
		return "redirect:/";
	}
	
	@GetMapping(value = "/product-carts")
	public String productCart(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = customerService.findByAccount(accountService.findByUsername(authentication.getName()).get()).get();
		model.addAttribute("customer", customer);
		
		List<ShoppingCart> shoppingCarts = customer.getShoppingCarts();
		model.addAttribute("shoppingCarts", shoppingCarts);
		
		List<List<Size>> sizes = shoppingCarts.stream().map(ShoppingCart::getProductDetail).map(ProductDetail::getProduct).map(Product::getProductDetails).map(productDetails -> productDetails.stream().map(ProductDetail::getSize).filter(distinctByKey(Size::getSizeCode)).toList()).toList();
		model.addAttribute("sizes", sizes);
		
		List<List<Color>> colors = shoppingCarts.stream().map(ShoppingCart::getProductDetail).map(ProductDetail::getProduct).map(Product::getProductDetails).map(productDetails -> productDetails.stream().map(ProductDetail::getColor).filter(distinctByKey(Color::getColorCode)).toList()).toList();
		model.addAttribute("colors", colors);
		
		List<BigDecimal> oldPrices =  shoppingCarts.stream().map(ShoppingCart::getProductDetail).map(productDetail -> productDetail.getSellingPrices().stream().sorted(new SellingPriceComparator()).findFirst().get().getPrice()).toList();
		model.addAttribute("oldPrices", oldPrices);
		
		Date now = new Date();
		Optional<FlashSale> flashSale = flashSaleService.findAll().stream().filter(flashSale1 -> now.getTime() >= flashSale1.getStartTime().getTime() && now.getTime() <= flashSale1.getEndTime().getTime()).findFirst();
		Optional<SaleOff> saleOff = saleOffService.findAll().stream().filter(saleOff1 -> now.getTime() >= saleOff1.getStartTime().getTime() && now.getTime() <= saleOff1.getEndTime().getTime()).findFirst();
		List<FlatRateSale> flatRateSales = flatRateSaleService.findAll().stream().filter(flatRateSale -> now.getTime() >= flatRateSale.getStartTime().getTime() && now.getTime() <= flatRateSale.getEndTime().getTime()).toList();
		List<BigDecimal> newPrices = new ArrayList<>();
		List<ProductDetail> productDetails = shoppingCarts.stream().map(ShoppingCart::getProductDetail).toList();
		for (int i = 0; i < productDetails.size(); ++i) {
			if (flashSale.isPresent()) {
				for (FlashSaleDetail flashSaleDetail : flashSale.get().getFlashSaleDetails()) {
					if (flashSaleDetail.getProductDetail().getProductDetailId().equals(productDetails.get(i).getProductDetailId())) {
						newPrices.add(oldPrices.get(i).multiply(new BigDecimal(100 - flashSaleDetail.getFlashSalePercentage())).divide(new BigDecimal(100)));
						break;
					}
				}
			} else if (saleOff.isPresent()) {
				for (SaleOffDetail saleOffDetail : saleOff.get().getSaleOffDetails()) {
					if (saleOffDetail.getProductDetail().getProductDetailId().equals(productDetails.get(i).getProductDetailId())) {
						newPrices.add(oldPrices.get(i).multiply(new BigDecimal(100 - saleOffDetail.getSaleOffPercentage())).divide(new BigDecimal(100)));
						break;
					}
				}
			} else if (!flatRateSales.isEmpty()) {
				for (FlatRateSale flatRateSale : flatRateSales) {
					for (FlatRateSaleDetail flatRateSaleDetail : flatRateSale.getFlatRateSaleDetails()) {
						if (flatRateSaleDetail.getProductDetail().getProductDetailId().equals(productDetails.get(i).getProductDetailId())) {
							newPrices.add(flatRateSale.getPrice());
							break;
						}
					}
				}
			} else {
				newPrices.add(oldPrices.get(i));
			}
		}
		model.addAttribute("newPrices", newPrices);
		
		List<BigDecimal> totalPrices = new ArrayList<>();
		for (int i = 0; i < shoppingCarts.size(); ++i) {
			totalPrices.add(newPrices.get(i).multiply(new BigDecimal(shoppingCarts.get(i).getQuantity())));
		}
		
		model.addAttribute("totalPrices", totalPrices);
		
		model.addAttribute("mainHost", mainHost);
		
		return "product-cart.html";
	}
	
	public <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
	    Set<Object> seen = new HashSet<>();
	    return t -> seen.add(keyExtractor.apply(t));
	}
	
	public class SellingPriceComparator implements Comparator<SellingPrice> {
		@Override
		public int compare(SellingPrice o1, SellingPrice o2) {
			Long ef1 = o1.getEffectiveTime().getTime();
			Long ef2 = o2.getEffectiveTime().getTime();
			return ef2.compareTo(ef1);
		}
	}
}
