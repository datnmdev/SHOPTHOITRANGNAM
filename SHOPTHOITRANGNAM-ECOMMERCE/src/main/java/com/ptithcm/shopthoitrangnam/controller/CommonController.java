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

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ptithcm.shopthoitrangnam.controller.ProductController.SellingPriceComparator;
import com.ptithcm.shopthoitrangnam.entity.Customer;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.entity.FlashSaleDetail;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSaleDetail;
import com.ptithcm.shopthoitrangnam.entity.OrderDetail;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
import com.ptithcm.shopthoitrangnam.entity.SaleOffDetail;
import com.ptithcm.shopthoitrangnam.entity.SellingPrice;
import com.ptithcm.shopthoitrangnam.service.AccountService;
import com.ptithcm.shopthoitrangnam.service.CustomerService;
import com.ptithcm.shopthoitrangnam.service.FlashSaleService;
import com.ptithcm.shopthoitrangnam.service.FlatRateSaleService;
import com.ptithcm.shopthoitrangnam.service.OrderDetailService;
import com.ptithcm.shopthoitrangnam.service.ProductDetailService;
import com.ptithcm.shopthoitrangnam.service.SaleOffService;

@Controller
public class CommonController {
	@Autowired
	FlashSaleService flashSaleService;
	
	@Autowired
	SaleOffService saleOffService;
	
	@Autowired
	FlatRateSaleService flatRateSaleService;
	
	@Autowired
	ProductDetailService productDetailService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AccountService accountService;
	
	@Value("${shopthoitrangnam-manage.domain}")
	private String mainHost;
	
	@GetMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("mainHost", mainHost);
		
		Date now = new Date();
		Optional<FlashSale> flashSale = flashSaleService.findAll().stream().filter(flashSale1 -> now.getTime() >= flashSale1.getStartTime().getTime() && now.getTime() <= flashSale1.getEndTime().getTime()).findFirst();
		if (flashSale.isPresent()) {
			model.addAttribute("flashSale", flashSale.get());
			model.addAttribute("timeDown", (flashSale.get().getEndTime().getTime()-new Date().getTime()) / 1000);
			
			List<FlashSaleDetail> flashSaleDetails = flashSale.get().getFlashSaleDetails();
			List<ProductDetail> productDetailInFlashSale = flashSaleDetails.stream().map(FlashSaleDetail::getProductDetail).toList();
			List<Float> ratingOfProductDetailInFlashSale = productDetailInFlashSale.stream().map(productDetail -> ratingOfProduct(productDetail)).toList();
			List<BigDecimal> oldPriceOfProductDetailInFlashSale = productDetailInFlashSale.stream().map(productDetail -> productDetail.getSellingPrices().stream().filter(sellingPrice -> sellingPrice.getEffectiveTime().getTime() <= now.getTime()).sorted(new SellingPriceComparator()).findFirst().get().getPrice()).toList();
			List<BigDecimal> newPriceOfProductDetailInFlashSale = new ArrayList<>();
			for (int i = 0; i < productDetailInFlashSale.size(); ++i) {
				newPriceOfProductDetailInFlashSale.add(oldPriceOfProductDetailInFlashSale.get(i).multiply(new BigDecimal(100 - flashSaleDetails.get(i).getFlashSalePercentage())).divide(new BigDecimal(100)));
			}
			
			model.addAttribute("flashSaleDetails", flashSaleDetails);
			model.addAttribute("ratingOfProductDetailInFlashSale", ratingOfProductDetailInFlashSale);
			model.addAttribute("oldPriceOfProductDetailInFlashSale", oldPriceOfProductDetailInFlashSale);
			model.addAttribute("newPriceOfProductDetailInFlashSale", newPriceOfProductDetailInFlashSale);
		} else {
			model.addAttribute("flashSale", null);
		}
		
		Optional<SaleOff> saleOff = saleOffService.findAll().stream().filter(saleOff1 -> now.getTime() >= saleOff1.getStartTime().getTime() && now.getTime() <= saleOff1.getEndTime().getTime()).findFirst();
		if (saleOff.isPresent()) {
			model.addAttribute("saleOff", saleOff.get());
			
			List<SaleOffDetail> saleOffDetails = saleOff.get().getSaleOffDetails();
			List<ProductDetail> productDetailInSaleOff = saleOffDetails.stream().map(SaleOffDetail::getProductDetail).toList();
			List<Float> ratingOfProductDetailInSaleOff = productDetailInSaleOff.stream().map(productDetail -> ratingOfProduct(productDetail)).toList();
			List<BigDecimal> oldPriceOfProductDetailInSaleOff = productDetailInSaleOff.stream().map(productDetail -> productDetail.getSellingPrices().stream().filter(sellingPrice -> sellingPrice.getEffectiveTime().getTime() <= now.getTime()).sorted(new SellingPriceComparator()).findFirst().get().getPrice()).toList();
			List<BigDecimal> newPriceOfProductDetailInSaleOff = new ArrayList<>();
			for (int i = 0; i < productDetailInSaleOff.size(); ++i) {
				newPriceOfProductDetailInSaleOff.add(oldPriceOfProductDetailInSaleOff.get(i).multiply(new BigDecimal(100 - saleOffDetails.get(i).getSaleOffPercentage())).divide(new BigDecimal(100)));
			}
			
			model.addAttribute("saleOffDetails", saleOffDetails);
			model.addAttribute("ratingOfProductDetailInSaleOff", ratingOfProductDetailInSaleOff);
			model.addAttribute("oldPriceOfProductDetailInSaleOff", oldPriceOfProductDetailInSaleOff);
			model.addAttribute("newPriceOfProductDetailInSaleOff", newPriceOfProductDetailInSaleOff);
		} else {
			model.addAttribute("saleOff", null);
		}
		
		List<FlatRateSale> flatRateSales = flatRateSaleService.findAll().stream().filter(flatRateSale1 -> now.getTime() >= flatRateSale1.getStartTime().getTime() && now.getTime() <= flatRateSale1.getEndTime().getTime()).toList();
		if (!flatRateSales.isEmpty()) {
			model.addAttribute("flatRateSales", flatRateSales);
			
			List<FlatRateSaleDetail> flatRateSaleDetails = new ArrayList<>();
			for (FlatRateSale flatRateSale : flatRateSales) {
				flatRateSaleDetails.addAll(flatRateSale.getFlatRateSaleDetails());
			}
			List<ProductDetail> productDetailInFlatRateSale = flatRateSaleDetails.stream().map(FlatRateSaleDetail::getProductDetail).toList();
			List<Float> ratingOfProductDetailInFlatRateSale = productDetailInFlatRateSale.stream().map(productDetail -> ratingOfProduct(productDetail)).toList();
			List<BigDecimal> oldPriceOfProductDetailInFlatRateSale = productDetailInFlatRateSale.stream().map(productDetail -> productDetail.getSellingPrices().stream().filter(sellingPrice -> sellingPrice.getEffectiveTime().getTime() <= now.getTime()).sorted(new SellingPriceComparator()).findFirst().get().getPrice()).toList();
			List<BigDecimal> newPriceOfProductDetailInFlatRateSale = new ArrayList<>();
			for (FlatRateSale flatRateSale : flatRateSales) {
				flatRateSale.getFlatRateSaleDetails().forEach(flatRateSaleDetail -> {
					newPriceOfProductDetailInFlatRateSale.add(flatRateSale.getPrice());
				});
			}
			
			model.addAttribute("flatRateSaleDetails", flatRateSaleDetails);
			model.addAttribute("ratingOfProductDetailInFlatRateSale", ratingOfProductDetailInFlatRateSale);
			model.addAttribute("oldPriceOfProductDetailInFlatRateSale", oldPriceOfProductDetailInFlatRateSale);
			model.addAttribute("newPriceOfProductDetailInFlatRateSale", newPriceOfProductDetailInFlatRateSale);
		} else {
			model.addAttribute("flatRateSales", null);
		}
		
		List<ProductDetail> clothes = productDetailService.findAll().stream().filter(productDetail -> productDetail.getProduct().getProductCategory().getProductCategoryCode().equals("AO")).toList();
		model.addAttribute("clothes", clothes);
		List<Float> ratingOfClothes = new ArrayList<>();
		List<BigDecimal> newPriceOfClothes = new ArrayList<>();
		List<BigDecimal> oldPriceOfClothes = new ArrayList<>();
		List<Float> percentageOfClothes = new ArrayList<>();
		clothes.forEach(productDetail -> {
			List<OrderDetail> orderDetails1 = orderDetailService.findByProductDetail(productDetail).stream().filter(orderDetail1_ -> orderDetail1_.getProductRating() != null).toList();
			if (orderDetails1.isEmpty()) {
				ratingOfClothes.add((float) 0);
			} else {
				int totalStars = orderDetails1.stream().mapToInt(orderDetail1_ -> orderDetail1_.getProductRating().getStarRating()).sum();
				ratingOfClothes.add((float) totalStars / (orderDetails1.size()*5) * 100);
			}
					
			BigDecimal oldPrice = productDetail.getSellingPrices().stream().filter(sellingPrice -> now.getTime() >= sellingPrice.getEffectiveTime().getTime()).sorted(Comparator.comparing(SellingPrice::getEffectiveTime).reversed()).findFirst().get().getPrice();
			oldPriceOfClothes.add(oldPrice);
			
			percentageOfClothes.add(null);
			newPriceOfClothes.add(null);
		});
		
		model.addAttribute("ratingOfClothes", ratingOfClothes);
		model.addAttribute("newPriceOfClothes", newPriceOfClothes);
		model.addAttribute("oldPriceOfClothes", oldPriceOfClothes);
		model.addAttribute("percentageOfClothes", percentageOfClothes);

		List<ProductDetail> pants = productDetailService.findAll().stream().filter(productDetail -> productDetail.getProduct().getProductCategory().getProductCategoryCode().equals("QUAN")).toList();
		model.addAttribute("pants", pants);
		List<Float> ratingOfPants = new ArrayList<>();
		List<BigDecimal> newPriceOfPants = new ArrayList<>();
		List<BigDecimal> oldPriceOfPants = new ArrayList<>();
		List<Float> percentageOfPants = new ArrayList<>();
		pants.forEach(productDetail -> {
			List<OrderDetail> orderDetails1 = orderDetailService.findByProductDetail(productDetail).stream().filter(orderDetail1_ -> orderDetail1_.getProductRating() != null).toList();
			if (orderDetails1.isEmpty()) {
				ratingOfPants.add((float) 0);
			} else {
				int totalStars = orderDetails1.stream().mapToInt(orderDetail1_ -> orderDetail1_.getProductRating().getStarRating()).sum();
				ratingOfPants.add((float) totalStars / (orderDetails1.size()*5) * 100);
			}
					
			BigDecimal oldPrice = productDetail.getSellingPrices().stream().filter(sellingPrice -> now.getTime() >= sellingPrice.getEffectiveTime().getTime()).sorted(Comparator.comparing(SellingPrice::getEffectiveTime).reversed()).findFirst().get().getPrice();
			oldPriceOfPants.add(oldPrice);
			
			percentageOfPants.add(null);
			newPriceOfPants.add(null);
		});
		model.addAttribute("ratingOfPants", ratingOfPants);
		model.addAttribute("newPriceOfPants", newPriceOfPants);
		model.addAttribute("oldPriceOfPants", oldPriceOfPants);
		model.addAttribute("percentageOfPants", percentageOfPants);
		
		List<ProductDetail> accessories = productDetailService.findAll().stream().filter(productDetail -> productDetail.getProduct().getProductCategory().getProductCategoryCode().equals("PHUKIEN")).toList();
		model.addAttribute("accessories", accessories);
		List<Float> ratingOfAccessories = new ArrayList<>();
		List<BigDecimal> newPriceOfAccessories = new ArrayList<>();
		List<BigDecimal> oldPriceOfAccessories = new ArrayList<>();
		List<Float> percentageOfAccessories = new ArrayList<>();
		pants.forEach(productDetail -> {
			List<OrderDetail> orderDetails1 = orderDetailService.findByProductDetail(productDetail).stream().filter(orderDetail1_ -> orderDetail1_.getProductRating() != null).toList();
			if (orderDetails1.isEmpty()) {
				ratingOfAccessories.add((float) 0);
			} else {
				int totalStars = orderDetails1.stream().mapToInt(orderDetail1_ -> orderDetail1_.getProductRating().getStarRating()).sum();
				ratingOfAccessories.add((float) totalStars / (orderDetails1.size()*5) * 100);
			}
					
			BigDecimal oldPrice = productDetail.getSellingPrices().stream().filter(sellingPrice -> now.getTime() >= sellingPrice.getEffectiveTime().getTime()).sorted(Comparator.comparing(SellingPrice::getEffectiveTime).reversed()).findFirst().get().getPrice();
			oldPriceOfAccessories.add(oldPrice);
			
			percentageOfAccessories.add(null);
			newPriceOfAccessories.add(null);
		});
		model.addAttribute("ratingOfAccessories", ratingOfAccessories);
		model.addAttribute("newPriceOfAccessories", newPriceOfAccessories);
		model.addAttribute("oldPriceOfAccessories", oldPriceOfAccessories);
		model.addAttribute("percentageOfAccessories", percentageOfAccessories);
		
		return "index.html";
	}
	
	@GetMapping(value = "/products", params = "category")
	public String productCategoryPage(Model model, @RequestParam("category") String category) {
		Date now = new Date();
		model.addAttribute("mainHost", mainHost);
		List<ProductDetail> productDetails = productDetailService.findAll().stream().filter(productDetail -> productDetail.getProduct().getProductCategory().getProductCategoryCode().equals(category)).toList();
		List<Float> ratings = new ArrayList<>();
		List<BigDecimal> newPrices = new ArrayList<>();
		List<BigDecimal> oldPrices = new ArrayList<>();
		List<Float> percentages = new ArrayList<>();
		productDetails.forEach(productDetail -> {
			List<OrderDetail> orderDetails1 = orderDetailService.findByProductDetail(productDetail).stream().filter(orderDetail1_ -> orderDetail1_.getProductRating() != null).toList();
			if (orderDetails1.isEmpty()) {
				ratings.add((float) 0);
			} else {
				int totalStars = orderDetails1.stream().mapToInt(orderDetail1_ -> orderDetail1_.getProductRating().getStarRating()).sum();
				ratings.add((float) totalStars / (orderDetails1.size()*5) * 100);
			}
					
			BigDecimal oldPrice = productDetail.getSellingPrices().stream().filter(sellingPrice -> now.getTime() >= sellingPrice.getEffectiveTime().getTime()).sorted(Comparator.comparing(SellingPrice::getEffectiveTime).reversed()).findFirst().get().getPrice();
			oldPrices.add(oldPrice);
			
			percentages.add(null);
			newPrices.add(null);
		});
		switch (category) {
			case "AO": 
				model.addAttribute("clothes", productDetails);
				model.addAttribute("ratingOfClothes", ratings);
				model.addAttribute("newPriceOfClothes", newPrices);
				model.addAttribute("oldPriceOfClothes", oldPrices);
				model.addAttribute("percentageOfClothes", percentages);
				return "clothing-product.html";
			case "QUAN": 
				model.addAttribute("pants", productDetails);
				model.addAttribute("ratingOfPants", ratings);
				model.addAttribute("newPriceOfPants", newPrices);
				model.addAttribute("oldPriceOfPants", oldPrices);
				model.addAttribute("percentageOfPants", percentages);
				return "pant-product.html";
			case "PHUKIEN": 
				model.addAttribute("accessories", productDetails);
				model.addAttribute("ratingOfAccessories", ratings);
				model.addAttribute("newPriceOfAccessories", newPrices);
				model.addAttribute("oldPriceOfAccessories", oldPrices);
				model.addAttribute("percentageOfAccessories", percentages);
				return "accessory-product.html";
			default:
				throw new IllegalArgumentException("Unexpected value: " + category);
		}
	}
	
	public Float ratingOfProduct(ProductDetail productDetail) {
		List<OrderDetail> orderDetails = orderDetailService.findAll().stream().filter(orderDetail -> orderDetail.getProductDetail().getProduct().getProductCode().equals(productDetail.getProductDetailPK().getProductCode())).toList();
		
		float productRatingNumber = (float) 0.0;
		if (orderDetails.size() > 0) {
			productRatingNumber = orderDetails.stream().mapToInt(od -> od.getProductRating().getStarRating()).sum() / ((float) (orderDetails.size())*5);
		}
		
		return productRatingNumber;
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
