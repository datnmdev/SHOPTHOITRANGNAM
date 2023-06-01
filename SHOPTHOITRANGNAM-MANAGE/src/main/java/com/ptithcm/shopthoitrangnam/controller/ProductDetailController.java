package com.ptithcm.shopthoitrangnam.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptithcm.shopthoitrangnam.dto.ProductDetailDto;
import com.ptithcm.shopthoitrangnam.entity.Color;
import com.ptithcm.shopthoitrangnam.entity.Product;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.entity.SellingPrice;
import com.ptithcm.shopthoitrangnam.entity.Size;
import com.ptithcm.shopthoitrangnam.mapper.ProductDetailMapper;
import com.ptithcm.shopthoitrangnam.service.ColorService;
import com.ptithcm.shopthoitrangnam.service.ProductDetailService;
import com.ptithcm.shopthoitrangnam.service.ProductService;
import com.ptithcm.shopthoitrangnam.service.SellingPriceService;
import com.ptithcm.shopthoitrangnam.service.SizeService;

import jakarta.validation.Valid;

@Controller
public class ProductDetailController {
	@Autowired
	ProductDetailService productDetailService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	SizeService sizeService;
	
	@Autowired
	ColorService colorService;
	
	@Autowired
	SellingPriceService sellingPriceService;
	
	@Autowired
	@Qualifier("createProductDetailFormValidator")
	Validator createProductDetailFormValidator;
	
	@Autowired
	@Qualifier("updateProductDetailFormValidator")
	Validator updateProductDetailFormValidator;
	
	@Value("${multipart.saveProductDetailImagePath}")
	private String saveProductDetailImagePath;
	
	@Value("${image.productDefaultImage}")
	private String productDefaultImage;
	
	@GetMapping("/owner/product-details")
	public String productDetailPage(Model model, RedirectAttributes redirectAttributes) {
		List<ProductDetail> productDetails = productDetailService.findAll();
		model.addAttribute("productDetails", productDetails);
		model.addAttribute("pageNumber", 0);
		Boolean isDeletedProductDetail = (Boolean) redirectAttributes.getFlashAttributes().get("isDeletedProductDetail");
		if (isDeletedProductDetail != null) {
			model.addAttribute("isDeletedProductDetail", isDeletedProductDetail);
		}
		return "owner-product-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/product-details", params = "search")
	public String searchProductDetail(Model model, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/product-details";
		}
		
		Set<ProductDetail> productDetails = new LinkedHashSet<>();
		
		search = search.trim();
		if (NumberUtils.isCreatable(search)) {
			Optional<ProductDetail> productDetailById = productDetailService.findByProductDetailId(NumberUtils.createInteger(search));
			if (productDetailById.isPresent()) {
				productDetails.add(productDetailById.get());
			}
		}
		
		String pattern = "%" + search.replaceAll(" ", "%") + "%";
		List<ProductDetail> productDetailsByProductCode = productDetailService.findByProductCodeUsingRegex(pattern);
		List<ProductDetail> productDetailsBySizeCode = productDetailService.findBySizeCodeUsingRegex(pattern);
		List<ProductDetail> productDetailsByColorCode = productDetailService.findByColorCodeUsingRegex(pattern);
		
		productDetails.addAll(productDetailsByProductCode);
		productDetails.addAll(productDetailsBySizeCode);
		productDetails.addAll(productDetailsByColorCode);
		
		model.addAttribute("productDetails", productDetails);
		model.addAttribute("isSearched", true);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		return "owner-product-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/product-details", params = "product-code")
	public String filterProductDetail(Model model, @RequestParam("product-code") String productCode) {
		List<ProductDetail> productDetails = productService.findByProductCode(productCode).get().getProductDetails();
		model.addAttribute("productDetails", productDetails);
		model.addAttribute("productCode", productCode);
		model.addAttribute("pageNumber", 0);
		return "owner-product-detail-manage.html";
	}
	
	@PostMapping(value = "/owner/product-details", params = {"product-code", "create-page"})
	public String filterAndCreateProductDetailPage(Model model, @RequestParam("product-code") String productCode) {
		List<Size> sizes = sizeService.findAll();
		List<Color> colors = colorService.findAll();
		
		model.addAttribute("sizes", sizes);
		model.addAttribute("colors", colors);
		
		ProductDetailDto productDetailDto = new ProductDetailDto();
		productDetailDto.setProductCode(productCode);
		productDetailDto.setQuantity(0);
		productDetailDto.setImage("/img/products/default.jpg");
		model.addAttribute("productDetailDto", productDetailDto);
		model.addAttribute("productCode", productCode);
		return "owner-create-product-detail.html";
	}
	
	@PostMapping(value = "/owner/product-details", params = "create-page")
	public String createProductDetailPage(Model model) {
		List<Product> products = productService.findAll();
		List<Size> sizes = sizeService.findAll();
		List<Color> colors = colorService.findAll();
		
		model.addAttribute("products", products);
		model.addAttribute("sizes", sizes);
		model.addAttribute("colors", colors);
		
		ProductDetailDto productDetailDto = new ProductDetailDto();
		productDetailDto.setQuantity(0);
		productDetailDto.setImage(productDefaultImage);
		model.addAttribute("productDetailDto", productDetailDto);
		return "owner-create-product-detail.html";
	}
	
	@PostMapping(value = "/owner/product-details", params = "create")
	public String createProductDetail(@Valid @ModelAttribute(name = "productDetailDto") ProductDetailDto productDetailDto,
			BindingResult bindingResult, @RequestPart("productDetailImage") MultipartFile file,  Model model) throws IllegalStateException, IOException {
		List<Product> products = productService.findAll();
		List<Size> sizes = sizeService.findAll();
		List<Color> colors = colorService.findAll();
		
		model.addAttribute("products", products);
		model.addAttribute("sizes", sizes);
		model.addAttribute("colors", colors);
		
		createProductDetailFormValidator.validate(productDetailDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			productDetailDto.setImage(productDefaultImage);
			return "owner-create-product-detail.html";
		}
		
		model.addAttribute("hasError", false);
		
//		Save image
		if (!file.isEmpty()) {
			String fileExtention = productDetailDto.getImage().substring(productDetailDto.getImage().lastIndexOf("."));
			String newFileName = UUID.randomUUID().toString() + fileExtention;
			Path destPath = Paths.get(saveProductDetailImagePath + "/" + newFileName);
			file.transferTo(destPath);
			productDetailDto.setImage("/img/products/" + newFileName);
		}
		productDetailService.insert(productDetailDto);
		productDetailDto.setImage(productDefaultImage);
		return "owner-create-product-detail.html";
	}
	
	@PostMapping(value = "/owner/product-details/{productDetailId}", params = "update-page")
	public String updateProductDetailPage(Model model, @PathVariable(name = "productDetailId") Integer productDetailId) {
		ProductDetailDto productDetailDto = ProductDetailMapper.toProductDetailDto(productDetailService.findByProductDetailId(productDetailId).get());
		model.addAttribute("productDetailDto", productDetailDto);
		return "owner-update-product-detail.html";
	}
	
	@PostMapping(value = "/owner/product-details/{productDetailId}", params = "update")
	public String updateProductDetail(@Valid @ModelAttribute(name = "productDetailDto") ProductDetailDto productDetailDto, 
			@RequestPart("productDetailImage") MultipartFile file, BindingResult bindingResult, Model model) throws IllegalStateException, IOException {
		updateProductDetailFormValidator.validate(productDetailDto, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("hasError", true);
			return "owner-update-product-detail.html";
		}
		
//		Save image
		if (!file.isEmpty()) {
			String fileExtention = productDetailDto.getImage().substring(productDetailDto.getImage().lastIndexOf("."));
			String newFileName = UUID.randomUUID().toString() + fileExtention;
			Path destPath = Paths.get(saveProductDetailImagePath + "/" + newFileName);
			file.transferTo(destPath);
			productDetailDto.setImage("/img/products/" + newFileName);
		}
		productDetailService.update(productDetailDto);
		model.addAttribute("hasError", false);
		return "owner-update-product-detail.html";
	}
	
	@PostMapping(value = "/owner/product-details/{productDetailId}", params = "delete")
	public String deleteProductDetail(@PathVariable(name = "productDetailId") Integer productDetailId, RedirectAttributes redirectAttributes) {
		
		ProductDetail productDetail = productDetailService.findByProductDetailId(productDetailId).get();
		System.out.println("size: "+productDetail.getSupplyDetails().size());
		if (!productDetail.getOrderDetails().isEmpty() || !productDetail.getShoppingCarts().isEmpty() || 
				!productDetail.getFlashSaleDetails().isEmpty() || !productDetail.getSaleOffDetails().isEmpty() ||
				!productDetail.getPurchaseNoteDetails().isEmpty() || !productDetail.getFlatRateSaleDetails().isEmpty() ||
				!productDetail.getPurchaseOrderDetails().isEmpty() || !productDetail.getSupplyDetails().isEmpty()) {
			redirectAttributes.addFlashAttribute("isDeletedProductDetail", false);
			return "redirect:/owner/product-details"; 
		}
		
		List<SellingPrice> sellingPrices = productDetailService.findByProductDetailId(productDetailId).get().getSellingPrices();
		sellingPriceService.deleteAll(sellingPrices);
		productDetailService.deleteByProductDetailId(productDetailId);
		redirectAttributes.addFlashAttribute("isDeletedProductDetail", true);
		return "redirect:/owner/product-details";
	}
	
	@GetMapping(value = "/owner/product-details", params = "page-number")
	public String sizePageNumber(@RequestParam("page-number") Integer pageNumber, Model model) {
		List<ProductDetail> productDetails = productDetailService.findAll();
		model.addAttribute("productDetails", productDetails);
		model.addAttribute("pageNumber", pageNumber-1);
		return "owner-product-detail-manage.html";
	}
	
	@GetMapping(value = "/owner/product-details", params = "export")
	@ResponseBody
	public List<ProductDetailDto> getProductDetailDtos() {
		List<ProductDetail> productDetails = productDetailService.findAll();
		return ProductDetailMapper.productDetailDtos(productDetails);
	}
}
