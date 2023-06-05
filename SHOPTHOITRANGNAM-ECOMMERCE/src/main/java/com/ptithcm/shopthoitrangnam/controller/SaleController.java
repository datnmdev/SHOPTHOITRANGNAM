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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ptithcm.shopthoitrangnam.entity.FlashSale;
import com.ptithcm.shopthoitrangnam.entity.FlashSaleDetail;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSale;
import com.ptithcm.shopthoitrangnam.entity.FlatRateSaleDetail;
import com.ptithcm.shopthoitrangnam.entity.OrderDetail;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.SaleOff;
import com.ptithcm.shopthoitrangnam.entity.SaleOffDetail;
import com.ptithcm.shopthoitrangnam.entity.SellingPrice;
import com.ptithcm.shopthoitrangnam.service.FlashSaleService;
import com.ptithcm.shopthoitrangnam.service.FlatRateSaleService;
import com.ptithcm.shopthoitrangnam.service.OrderDetailService;
import com.ptithcm.shopthoitrangnam.service.SaleOffService;

@Controller
public class SaleController {
	@Autowired
	FlashSaleService flashSaleService;
	
	@Autowired
	SaleOffService saleOffService;
	
	@Autowired
	FlatRateSaleService flatRateSaleService;
	
	@Value("${shopthoitrangnam-manage.domain}")
	private String mainHost;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@GetMapping(value = "/sales", params = "type")
	public String salePage(Model model, @RequestParam("type") String type) {
		Date now = new Date();
		model.addAttribute("now", now.getTime());
		model.addAttribute("mainHost", mainHost);
		if (type.equals("flashSales")) {
			@SuppressWarnings("deprecation")
			List<FlashSale> flashSales = flashSaleService.findAll().stream().filter(flashSale1 -> now.getTime() >= flashSale1.getStartTime().getTime() && now.getTime() <= flashSale1.getEndTime().getTime() || (now.getTime() < flashSale1.getStartTime().getTime() && (flashSale1.getStartTime().getYear() == now.getYear() && flashSale1.getStartTime().getMonth() == now.getMonth() && flashSale1.getStartTime().getDate() == now.getDate()))).toList();
			model.addAttribute("flashSales", flashSales);
			
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
			
			return "flash-sale.html";
		} else if (type.equals("saleOffs")) {
			@SuppressWarnings("deprecation")
			List<SaleOff> saleOffs = saleOffService.findAll().stream().filter(saleOff1 -> now.getTime() >= saleOff1.getStartTime().getTime() && now.getTime() <= saleOff1.getEndTime().getTime() || (now.getTime() < saleOff1.getStartTime().getTime() && (saleOff1.getStartTime().getYear() == now.getYear() && saleOff1.getStartTime().getMonth() == now.getMonth() && saleOff1.getStartTime().getDate() == now.getDate()))).toList();
			model.addAttribute("saleOffs", saleOffs);
			
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
			
			return "sale-off.html";
		} else {
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
			return "flat-rate-sale.html";
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
