package com.ptithcm.shopthoitrangnam.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ptithcm.shopthoitrangnam.entity.Account;
import com.ptithcm.shopthoitrangnam.entity.Color;
import com.ptithcm.shopthoitrangnam.entity.Customer;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.entity.FlashSaleDetail;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSaleDetail;
import com.ptithcm.shopthoitrangnam.entity.OrderDetail;
import com.ptithcm.shopthoitrangnam.entity.Product;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
import com.ptithcm.shopthoitrangnam.entity.SaleOffDetail;
import com.ptithcm.shopthoitrangnam.entity.SellingPrice;
import com.ptithcm.shopthoitrangnam.entity.Size;
import com.ptithcm.shopthoitrangnam.service.AccountService;
import com.ptithcm.shopthoitrangnam.service.CustomerService;
import com.ptithcm.shopthoitrangnam.service.FlashSaleService;
import com.ptithcm.shopthoitrangnam.service.FlatRateSaleService;
import com.ptithcm.shopthoitrangnam.service.OrderDetailService;
import com.ptithcm.shopthoitrangnam.service.ProductService;
import com.ptithcm.shopthoitrangnam.service.SaleOffService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	FlashSaleService flashSaleService;
	
	@Autowired
	SaleOffService saleOffService;
	
	@Autowired
	FlatRateSaleService flatRateSaleService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AccountService accountService;
	
	@Value("${shopthoitrangnam-manage.domain}")
	private String mainHost;
	
	@GetMapping(value = "/products/{productCode}")
	public String productDetailPage(Model model, @PathVariable("productCode") String productCode) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<Account> account = accountService.findByUsername(authentication.getName());
		Customer customer = null;
		if (account.isPresent()) {
			customer = customerService.findByAccount(account.get()).get();
		}
		model.addAttribute("customer", customer);
		
		Product product = productService.findByProductCode(productCode).get();
		model.addAttribute("product", product);
		
		List<ProductDetail> productDetails = product.getProductDetails();
		
		List<ProductDetail> productDetailsWithColorNameDistinct = productDetails.stream().filter(distinctByKey(ProductDetail::getColor)).collect(Collectors.toList());;
		model.addAttribute("productDetailsWithColorNameDistinct", productDetailsWithColorNameDistinct);
		
		List<OrderDetail> orderDetails = orderDetailService.findAll().stream().filter(orderDetail -> orderDetail.getProductDetail().getProduct().getProductCode().equals(productCode)).toList();
		model.addAttribute("orderDetails", orderDetails);
		
		if (orderDetails.size() > 0) {
			float productRatingNumber = orderDetails.stream().mapToInt(od -> od.getProductRating().getStarRating()).sum() / ((float) (orderDetails.size())*5);
			model.addAttribute("productRatingNumber", productRatingNumber);
		} else {
			model.addAttribute("productRatingNumber", 0.0);
		}
		
		
		Date now = new Date();
		List<BigDecimal> oldPrices = productDetails.stream().map(productDetail -> productDetail.getSellingPrices().stream().filter(sellingPrice -> sellingPrice.getEffectiveTime().getTime() <= now.getTime()).sorted(new SellingPriceComparator()).findFirst().get().getPrice()).toList();
		List<BigDecimal> sortedOldPrices = oldPrices.stream().sorted().toList();
		if (sortedOldPrices.get(0).compareTo(sortedOldPrices.get(sortedOldPrices.size()-1)) == 0) {
			model.addAttribute("oldPrice", sortedOldPrices.get(0));
		} else {
			model.addAttribute("minOldPrice", sortedOldPrices.get(0));
			model.addAttribute("maxOldPrice", sortedOldPrices.get(sortedOldPrices.size()-1));
		}
		
		Optional<FlashSale> flashSale = flashSaleService.findAll().stream().filter(flashSale1 -> now.getTime() >= flashSale1.getStartTime().getTime() && now.getTime() <= flashSale1.getEndTime().getTime()).findFirst();
		Optional<SaleOff> saleOff = saleOffService.findAll().stream().filter(saleOff1 -> now.getTime() >= saleOff1.getStartTime().getTime() && now.getTime() <= saleOff1.getEndTime().getTime()).findFirst();
		List<FlatRateSale> flatRateSales = flatRateSaleService.findAll().stream().filter(flatRateSale -> now.getTime() >= flatRateSale.getStartTime().getTime() && now.getTime() <= flatRateSale.getEndTime().getTime()).toList();
		List<BigDecimal> newPrices = new ArrayList<>();
		for (int i = 0; i < productDetails.size(); ++i) {
			boolean isFinded = false;
			
			if (flashSale.isPresent()) {
				for (FlashSaleDetail flashSaleDetail : flashSale.get().getFlashSaleDetails()) {
					if (flashSaleDetail.getProductDetail().getProductDetailId().equals(productDetails.get(i).getProductDetailId())) {
						newPrices.add(oldPrices.get(i).multiply(new BigDecimal(100 - flashSaleDetail.getFlashSalePercentage())).divide(new BigDecimal(100)));
						isFinded = true;
						break;
					}
				}
			}
			
			if (saleOff.isPresent() && !isFinded) {
				for (SaleOffDetail saleOffDetail : saleOff.get().getSaleOffDetails()) {
					if (saleOffDetail.getProductDetail().getProductDetailId().equals(productDetails.get(i).getProductDetailId())) {
						newPrices.add(oldPrices.get(i).multiply(new BigDecimal(100 - saleOffDetail.getSaleOffPercentage())).divide(new BigDecimal(100)));
						isFinded = true;
						break;
					}
				}
			}
			
			if (!flatRateSales.isEmpty() && !isFinded) {
				for (FlatRateSale flatRateSale : flatRateSales) {
					for (FlatRateSaleDetail flatRateSaleDetail : flatRateSale.getFlatRateSaleDetails()) {
						if (flatRateSaleDetail.getProductDetail().getProductDetailId().equals(productDetails.get(i).getProductDetailId())) {
							newPrices.add(flatRateSale.getPrice());
							isFinded = true;
							break;
						}
					}
					if (isFinded) {
						break;
					}
				}
			}
			
			if (!isFinded) {
				newPrices.add(oldPrices.get(i));
			}
		}
		List<BigDecimal> sortedNewPrices = newPrices.stream().sorted().toList();
		if (sortedNewPrices.get(0).compareTo(sortedNewPrices.get(sortedNewPrices.size()-1)) == 0) {
			model.addAttribute("newPrice", sortedNewPrices.get(0));
		} else {
			model.addAttribute("minNewPrice", sortedNewPrices.get(0));
			model.addAttribute("maxNewPrice", sortedNewPrices.get(sortedNewPrices.size()-1));
		}
		
		List<Color> colors = productDetails.stream().map(ProductDetail::getColor).filter(distinctByKey(Color::getColorCode)).collect(Collectors.toList());;
		model.addAttribute("colors", colors);
		
		List<String> images = productDetails.stream().map(ProductDetail::getImage).toList();
		model.addAttribute("images", images);
		
		List<Size> sizes = productDetails.stream().map(ProductDetail::getSize).filter(distinctByKey(Size::getSizeCode)).collect(Collectors.toList());;
		model.addAttribute("sizes", sizes);
		
//		Tính số lượng tồn
		model.addAttribute("inventory", 100);
		
		model.addAttribute("fiveStarRating", orderDetails.stream().filter(orderDetail -> orderDetail.getProductRating().getStarRating().intValue() == 5).toList().size());
		model.addAttribute("fourStarRating", orderDetails.stream().filter(orderDetail -> orderDetail.getProductRating().getStarRating().intValue() == 5).toList().size());;
		model.addAttribute("threeStarRating", orderDetails.stream().filter(orderDetail -> orderDetail.getProductRating().getStarRating().intValue() == 5).toList().size());;
		model.addAttribute("twoStarRating", orderDetails.stream().filter(orderDetail -> orderDetail.getProductRating().getStarRating().intValue() == 5).toList().size());;
		model.addAttribute("oneStarRating", orderDetails.stream().filter(orderDetail -> orderDetail.getProductRating().getStarRating().intValue() == 5).toList().size());;
		
		model.addAttribute("mainHost", mainHost);
		
		return "product-detail.html";
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
