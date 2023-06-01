package com.ptithcm.shopthoitrangnam.validate;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ptithcm.shopthoitrangnam.dto.ProductDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.ProductDetailPK;
import com.ptithcm.shopthoitrangnam.entity.ProductDetail;
import com.ptithcm.shopthoitrangnam.service.ProductDetailService;

@Component("createProductDetailFormValidator")
public class CreateProductDetailFormValidatorImpl implements Validator {
	@Autowired
	ProductDetailService productDetailService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ProductDetailDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		ProductDetailDto productDetailDto = (ProductDetailDto) target;
		
		ProductDetailPK productDetailPK = new ProductDetailPK();
		productDetailPK.setProductCode(productDetailDto.getProductCode());
		productDetailPK.setSizeCode(productDetailDto.getSizeCode());
		productDetailPK.setColorCode(productDetailDto.getColorCode());
		Optional<ProductDetail> productDetail = productDetailService.findByProductDetailPK(productDetailPK);
		if (productDetail.isPresent()) {
			errors.rejectValue("primaryKeyError", "primaryKeyError", "Chi tiết sản phẩm này đã tồn tại. Vui lòng kiểm tra lại 3 thông tin: mã sản phẩm, mã kích thước và mã màu sắc");
		}
		
		String fileExtention = productDetailDto.getImage().substring(productDetailDto.getImage().lastIndexOf(".") + 1);
		if (!fileExtention.equals("jpeg") && !fileExtention.equals("jpg") && 
				!fileExtention.equals("png") && !fileExtention.equals("webp") && !fileExtention.equals("jfif")) {
			errors.rejectValue("image", "image" ,"(*) File tải lên phải có định dạng .png, .jpg, .jpeg, .jfif hoặc webp");
		}
	}
}
