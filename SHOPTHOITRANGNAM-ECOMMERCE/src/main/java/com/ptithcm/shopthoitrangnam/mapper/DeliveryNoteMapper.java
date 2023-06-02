package com.ptithcm.shopthoitrangnam.mapper;

import java.util.List;

import com.ptithcm.shopthoitrangnam.dto.DeliveryNoteDto;
import com.ptithcm.shopthoitrangnam.entity.DeliveryNote;

public class DeliveryNoteMapper {
	public static DeliveryNoteDto toDeliveryNoteDto(DeliveryNote deliveryNote) {
		return new DeliveryNoteDto(deliveryNote.getDeliveryNoteCode(), deliveryNote.getDeliveryNoteName(), deliveryNote.getDescription(), deliveryNote.getCreationTime(), deliveryNote.getEmployee().getEmployeeCode(), deliveryNote.getDeliveryStaff().getEmployeeCode());
	}
	
	public static List<DeliveryNoteDto> toDeliveryNoteDtos(List<DeliveryNote> deliveryNotes) {
		return deliveryNotes.stream().map(deliveryNote -> toDeliveryNoteDto(deliveryNote)).toList();
	}
}
