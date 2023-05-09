package com.ptithcm.shopthoitrangnam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.embeddable.DeliveryNoteDetailPK;
import com.ptithcm.shopthoitrangnam.entity.DeliveryNoteDetail;

@Repository
public interface DeliveryNoteDetailRepository extends JpaRepository<DeliveryNoteDetail, DeliveryNoteDetailPK> {

}
