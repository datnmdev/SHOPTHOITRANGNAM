package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.PurchaseNoteDto;
import com.ptithcm.shopthoitrangnam.entity.PurchaseNote;

public class PurchaseNoteMapper {
	public static PurchaseNoteDto toPurchaseNoteDto(PurchaseNote purchaseNote) {
		return new PurchaseNoteDto(purchaseNote.getPurchaseNoteCode(), purchaseNote.getPurchaseNoteName(), purchaseNote.getDescription(), purchaseNote.getCreationTime(), purchaseNote.getEmployee().getEmployeeCode(), purchaseNote.getPurchaseOrder().getPurchaseOrderCode());
	}
	
	public static List<PurchaseNoteDto> toPurchaseNoteDtos(List<PurchaseNote> purchaseNotes) {
		return purchaseNotes.stream().map(purchaseNote -> toPurchaseNoteDto(purchaseNote)).toList();
	}
}
