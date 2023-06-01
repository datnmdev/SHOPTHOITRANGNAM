package com.ptithcm.shopthoitrangnam.controller;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ptithcm.shopthoitrangnam.dto.PurchaseNoteDetailDto;
import com.ptithcm.shopthoitrangnam.embeddable.PurchaseNoteDetailPK;
import com.ptithcm.shopthoitrangnam.entity.PurchaseNoteDetail;
import com.ptithcm.shopthoitrangnam.entity.PurchaseOrderDetail;
import com.ptithcm.shopthoitrangnam.mapper.PurchaseNoteDetailMapper;
import com.ptithcm.shopthoitrangnam.service.PurchaseNoteDetailService;
import com.ptithcm.shopthoitrangnam.service.PurchaseNoteService;

@Controller
public class PurchaseNoteDetailController {
	@Autowired
	PurchaseNoteDetailService purchaseNoteDetailService;
	
	@Autowired
	PurchaseNoteService purchaseNoteService;
	
	@GetMapping(value = "/owner/purchase-note-details", params = "purchase-note-code")
	public String purchaseNoteDetailPage(Model model, @RequestParam("purchase-note-code") String purchaseNoteCode, RedirectAttributes redirectAttributes) {
		List<PurchaseNoteDetail> purchaseNoteDetails = purchaseNoteDetailService.findByPurchaseNote(purchaseNoteService.findByPurchaseNoteCode(purchaseNoteCode).get());
		model.addAttribute("purchaseNoteDetails", purchaseNoteDetails);
		model.addAttribute("pageNumber", 0);
		model.addAttribute("purchaseNoteCode", purchaseNoteCode);
		
		List<PurchaseOrderDetail> purchaseOrderDetailsOrigin = purchaseNoteService.findByPurchaseNoteCode(purchaseNoteCode).get().getPurchaseOrder().getPurchaseOrderDetails();
		List<PurchaseOrderDetail> purchaseOrderDetails = new ArrayList<>();
		purchaseNoteDetails.forEach(purchaseNoteDetail -> {
			for (PurchaseOrderDetail purchaseOrderDetailOrigin : purchaseOrderDetailsOrigin) {
				if (purchaseOrderDetailOrigin.getPurchaseOrderDetailPK().getProductDetailId().equals(purchaseNoteDetail.getPurchaseNoteDetailPK().getProductDetailId())) {
					purchaseOrderDetails.add(purchaseOrderDetailOrigin);
					break;
				}
			}
		});
		model.addAttribute("purchaseOrderDetails", purchaseOrderDetails);
		
		return "owner-purchase-note-detail.html";
	}
	
	@GetMapping(value = "/owner/purchase-note-details", params = {"purchase-note-code", "search"})
	public String searchPurchaseNoteDetail(Model model, @RequestParam("purchase-note-code") String purchaseNoteCode, @RequestParam("search") String search) {
		if (search.isEmpty()) {
			return "redirect:/owner/purchase-note-details?purchase-note-code=" + purchaseNoteCode;
		}
		
		Set<PurchaseNoteDetail> purchaseNoteDetails = new LinkedHashSet<>();
		if (NumberUtils.isCreatable(search)) {
			PurchaseNoteDetailPK purchaseNoteDetailPK = new PurchaseNoteDetailPK();
			purchaseNoteDetailPK.setPurchaseNoteCode(purchaseNoteCode);
			purchaseNoteDetailPK.setProductDetailId(NumberUtils.createInteger(search));
			Optional<PurchaseNoteDetail> purchaseNoteDetail = purchaseNoteDetailService.findByPurchaseNoteDetailPK(purchaseNoteDetailPK);
			if (purchaseNoteDetail.isPresent()) {
				purchaseNoteDetails.add(purchaseNoteDetail.get());
			}
		}
		String pattern = ".*" + search.replaceAll(" ", ".*") + ".*";
		List<PurchaseNoteDetail> purchaseNoteDetailsByProductName = purchaseNoteDetailService.findAll().stream().filter(purchaseNoteDetail -> purchaseNoteDetail.getProductDetail().getProduct().getProductName().matches(pattern)).toList();
		purchaseNoteDetails.addAll(purchaseNoteDetailsByProductName);
		 
		model.addAttribute("purchaseNoteDetails", purchaseNoteDetails);
		model.addAttribute("isSearched", true);
		model.addAttribute("purchaseNoteCode", purchaseNoteCode);
		model.addAttribute("search", search);
		model.addAttribute("pageNumber", 0);
		
		List<PurchaseOrderDetail> purchaseOrderDetailsOrigin = purchaseNoteService.findByPurchaseNoteCode(purchaseNoteCode).get().getPurchaseOrder().getPurchaseOrderDetails();
		List<PurchaseOrderDetail> purchaseOrderDetails = new ArrayList<>();
		purchaseNoteDetails.forEach(purchaseNoteDetail -> {
			for (PurchaseOrderDetail purchaseOrderDetailOrigin : purchaseOrderDetailsOrigin) {
				if (purchaseOrderDetailOrigin.getPurchaseOrderDetailPK().getProductDetailId().equals(purchaseNoteDetail.getPurchaseNoteDetailPK().getProductDetailId())) {
					purchaseOrderDetails.add(purchaseOrderDetailOrigin);
					break;
				}
			}
		});
		model.addAttribute("purchaseOrderDetails", purchaseOrderDetails);
		
		return "owner-purchase-note-detail.html";
	}
	
	@GetMapping(value = "/owner/purchase-note-details", params = {"purchase-note-code", "page-number"})
	public String purchaseNoteDetailPageNumber(@RequestParam("page-number") Integer pageNumber, @RequestParam("purchase-note-code") String purchaseNoteCode, Model model) {
		List<PurchaseNoteDetail> purchaseNoteDetails = purchaseNoteDetailService.findByPurchaseNote(purchaseNoteService.findByPurchaseNoteCode(purchaseNoteCode).get());
		model.addAttribute("purchaseNoteDetails", purchaseNoteDetails);
		model.addAttribute("pageNumber", pageNumber-1);
		model.addAttribute("purchaseNoteCode", purchaseNoteCode);
		
		List<PurchaseOrderDetail> purchaseOrderDetailsOrigin = purchaseNoteService.findByPurchaseNoteCode(purchaseNoteCode).get().getPurchaseOrder().getPurchaseOrderDetails();
		List<PurchaseOrderDetail> purchaseOrderDetails = new ArrayList<>();
		purchaseNoteDetails.forEach(purchaseNoteDetail -> {
			for (PurchaseOrderDetail purchaseOrderDetailOrigin : purchaseOrderDetailsOrigin) {
				if (purchaseOrderDetailOrigin.getPurchaseOrderDetailPK().getProductDetailId().equals(purchaseNoteDetail.getPurchaseNoteDetailPK().getProductDetailId())) {
					purchaseOrderDetails.add(purchaseOrderDetailOrigin);
					break;
				}
			}
		});
		model.addAttribute("purchaseOrderDetails", purchaseOrderDetails);
		
		return "owner-purchase-note-detail.html";
	}
	
	@GetMapping(value = "/owner/purchase-note-details", params = {"purchase-note-code", "export"})
	@ResponseBody
	public List<PurchaseNoteDetailDto> getPurchaseNoteDetailDtos(@RequestParam("purchase-note-code") String purchaseNoteCode) {
		List<PurchaseNoteDetailDto> purchaseNoteDetailDtos = PurchaseNoteDetailMapper.toPurchaseNoteDetailDtos(purchaseNoteDetailService.findByPurchaseNote(purchaseNoteService.findByPurchaseNoteCode(purchaseNoteCode).get()));
		return purchaseNoteDetailDtos;
	}
}
