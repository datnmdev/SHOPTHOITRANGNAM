package com.ptithcm.shopthoitrangnam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptithcm.shopthoitrangnam.embeddable.PurchaseNoteDetailPK;
import com.ptithcm.shopthoitrangnam.entity.PurchaseNoteDetail;

@Repository
public interface PurchaseNoteDetailRepository extends JpaRepository<PurchaseNoteDetail, PurchaseNoteDetailPK> {

}
