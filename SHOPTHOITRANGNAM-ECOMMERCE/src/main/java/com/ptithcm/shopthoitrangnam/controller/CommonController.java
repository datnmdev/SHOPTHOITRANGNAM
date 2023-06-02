package com.ptithcm.shopthoitrangnam.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ptithcm.shopthoitrangnam.entity.Customer;
import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;
import com.ptithcm.shopthoitrangnam.entity.OrderDetail;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
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
//		
//		Optional<FlashSale> flashSale = flashSaleService.findAll().stream().filter(flashSale_ -> now.getTime() >= flashSale_.getStartTime().getTime() && now.getTime() <= flashSale_.getEndTime().getTime()).findFirst();
//		model.addAttribute("flashSale", flashSale.get());
//		List<ProductDetail> productDetailInFlashSale = null;
//		List<Float> ratingOfProductDetailInFlashSale = new ArrayList<>();
//		List<BigDecimal> newPriceOfProductDetailInFlashSale = new ArrayList<>();
//		List<BigDecimal> oldPriceOfProductDetailInFlashSale = new ArrayList<>();
//		List<Float> percentageOfProductDetailInFlashSale = new ArrayList<>();
//		if (flashSale.isPresent()) {
//			productDetailInFlashSale = flashSale.get().getFlashSaleDetails().stream().map(flashSaleDetail -> flashSaleDetail.getProductDetail()).toList();
//			productDetailInFlashSale.forEach(productDetail -> {
//				List<OrderDetail> orderDetails1 = orderDetailService.findByProductDetail(productDetail).stream().filter(orderDetail1_ -> orderDetail1_.getProductRating() != null).toList();
//				if (orderDetails1 == null) {
//					ratingOfProductDetailInFlashSale.add((float) 0);
//				} else {
//					int totalStars = orderDetails1.stream().mapToInt(orderDetail1_ -> orderDetail1_.getProductRating().getStarRating()).sum();
//					ratingOfProductDetailInFlashSale.add((float) totalStars / (orderDetails1.size()*5));
//				}
//				
//				BigDecimal oldPrice = productDetail.getSellingPrices().stream().filter(sellingPrice -> now.getTime() >= sellingPrice.getEffectiveTime().getTime()).sorted(Comparator.comparing(SellingPrice::getEffectiveTime).reversed()).findFirst().get().getPrice();
//				oldPriceOfProductDetailInFlashSale.add(oldPrice);
//				
//				newPriceOfProductDetailInFlashSale.add(oldPrice.multiply(new BigDecimal(100 - flashSale.get().getFlashSaleDetails().stream().filter(flashSaleDetail -> flashSaleDetail.getProductDetail().getProductDetailId().equals(productDetail.getProductDetailId())).findFirst().get().getFlashSalePercentage())));
//				
//				percentageOfProductDetailInFlashSale.add(flashSale.get().getFlashSaleDetails().stream().filter(flashSaleDetail -> flashSaleDetail.getProductDetail().getProductDetailId().equals(productDetail.getProductDetailId())).findFirst().get().getFlashSalePercentage());
//			});
//		}
//		model.addAttribute("productDetailInFlashSale", productDetailInFlashSale);
//		model.addAttribute("ratingOfProductDetailInFlashSale", ratingOfProductDetailInFlashSale);
//		model.addAttribute("newPriceOfProductDetailInFlashSale", newPriceOfProductDetailInFlashSale);
//		model.addAttribute("newPriceOfProductDetailInFlashSale", newPriceOfProductDetailInFlashSale);
//		model.addAttribute("percentageOfProductDetailInFlashSale", percentageOfProductDetailInFlashSale);
//		
//		Optional<SaleOff> saleOff = saleOffService.findAll().stream().filter(saleOff1 -> now.getTime() >= saleOff1.getStartTime().getTime() && now.getTime() <= saleOff1.getEndTime().getTime()).findFirst();
//		model.addAttribute("saleOff", saleOff.get());
//		List<ProductDetail> productDetailInSaleOff = null;
//		List<Float> ratingOfProductDetailInSaleOff = new ArrayList<>();
//		List<BigDecimal> newPriceOfProductDetailInSaleOff = new ArrayList<>();
//		List<BigDecimal> oldPriceOfProductDetailInSaleOff = new ArrayList<>();
//		List<Float> percentageOfProductDetailInSaleOff = new ArrayList<>();
//		if (saleOff != null) {
//			productDetailInSaleOff = saleOff.get().getSaleOffDetails().stream().map(saleOffDetail -> saleOffDetail.getProductDetail()).toList();
//			productDetailInSaleOff.forEach(productDetail -> {
//				List<OrderDetail> orderDetails1 = orderDetailService.findByProductDetail(productDetail).stream().filter(orderDetail1_ -> orderDetail1_.getProductRating() != null).toList();
//				if (orderDetails1 == null) {
//					ratingOfProductDetailInSaleOff.add((float) 0);
//				} else {
//					int totalStars = orderDetails1.stream().mapToInt(orderDetail1_ -> orderDetail1_.getProductRating().getStarRating()).sum();
//					ratingOfProductDetailInSaleOff.add((float) totalStars / (orderDetails1.size()*5));
//				}
//				
//				BigDecimal oldPrice = productDetail.getSellingPrices().stream().filter(sellingPrice -> now.getTime() >= sellingPrice.getEffectiveTime().getTime()).sorted(Comparator.comparing(SellingPrice::getEffectiveTime).reversed()).findFirst().get().getPrice();
//				oldPriceOfProductDetailInSaleOff.add(oldPrice);
//				
//				newPriceOfProductDetailInSaleOff.add(oldPrice.multiply(new BigDecimal(100 - saleOff.get().getSaleOffDetails().stream().filter(saleOffDetail -> saleOffDetail.getProductDetail().getProductDetailId().equals(productDetail.getProductDetailId())).findFirst().get().getSaleOffPercentage())));
//				
//				percentageOfProductDetailInSaleOff.add(saleOff.get().getSaleOffDetails().stream().filter(saleOffDetail -> saleOffDetail.getProductDetail().getProductDetailId().equals(productDetail.getProductDetailId())).findFirst().get().getSaleOffPercentage());
//			});
//		}
//		model.addAttribute("productDetailInSaleOff", productDetailInSaleOff);
//		model.addAttribute("ratingOfProductDetailInSaleOff", ratingOfProductDetailInSaleOff);
//		model.addAttribute("newPriceOfProductDetailInSaleOff", newPriceOfProductDetailInSaleOff);
//		model.addAttribute("oldPriceOfProductDetailInSaleOff", oldPriceOfProductDetailInSaleOff);
//		model.addAttribute("percentageOfProductDetailInSaleOff", percentageOfProductDetailInSaleOff);
//		
//		Optional<FlatRateSale> flatRateSale = flatRateSaleService.findAll().stream().filter(saleOff1 -> now.getTime() >= saleOff1.getStartTime().getTime() && now.getTime() <= saleOff1.getEndTime().getTime()).findFirst();
//		model.addAttribute("flatRateSale", flatRateSale.get());
//		List<ProductDetail> productDetailInFlatRateSale = null;
//		List<Float> ratingOfProductDetailInFlatRateSale = new ArrayList<>();
//		List<BigDecimal> oldPriceOfProductDetailInFlatRateSale = new ArrayList<>();
//		if (flatRateSale.isPresent()) {
//			productDetailInFlatRateSale = flatRateSale.get().getFlatRateSaleDetails().stream().map(flatRateSaleDetail -> flatRateSaleDetail.getProductDetail()).toList();
//			productDetailInFlatRateSale.forEach(productDetail -> {
//				List<OrderDetail> orderDetails1 = orderDetailService.findByProductDetail(productDetail).stream().filter(orderDetail1_ -> orderDetail1_.getProductRating() != null).toList();
//				if (orderDetails1 == null) {
//					ratingOfProductDetailInSaleOff.add((float) 0);
//				} else {
//					int totalStars = orderDetails1.stream().mapToInt(orderDetail1_ -> orderDetail1_.getProductRating().getStarRating()).sum();
//					ratingOfProductDetailInFlatRateSale.add((float) totalStars / (orderDetails1.size()*5));
//				}
//				
//				BigDecimal oldPrice = productDetail.getSellingPrices().stream().filter(sellingPrice -> now.getTime() >= sellingPrice.getEffectiveTime().getTime()).sorted(Comparator.comparing(SellingPrice::getEffectiveTime).reversed()).findFirst().get().getPrice();
//				oldPriceOfProductDetailInFlatRateSale.add(oldPrice);
//			});
//		}
//		model.addAttribute("productDetailInFlatRateSale", productDetailInFlatRateSale);
//		model.addAttribute("ratingOfProductDetailInFlatRateSale", ratingOfProductDetailInFlatRateSale);
//		model.addAttribute("oldPriceOfProductDetailInFlatRateSale", oldPriceOfProductDetailInFlatRateSale);
		
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
	
	@GetMapping(value = "/customer/home")
	public String home_(@AuthenticationPrincipal UserDetails userDetail, Model model) {
		Customer customer = customerService.findByAccount(accountService.findByUsername(userDetail.getUsername()).get()).get();
		model.addAttribute("customer", customer);
		return "index.html";
	}
}
