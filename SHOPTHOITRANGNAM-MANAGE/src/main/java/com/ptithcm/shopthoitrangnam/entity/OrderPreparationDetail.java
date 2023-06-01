package com.ptithcm.shopthoitrangnam.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ptithcm.shopthoitrangnam.embeddable.OrderPreparationDetailPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "CHITIETCHUANBIDONHANG")
public class OrderPreparationDetail {
	@EmbeddedId
	private OrderPreparationDetailPK orderPreparationDetailPK;
	
	@ManyToOne
	@JoinColumn(name = "MANV", insertable=false, updatable=false)
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "MADONHANG", insertable=false, updatable=false)
	private Order order;
	
	@Column(name = "THOIDIEMGIAONHIEMVU")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date taskDeliveryTime;

//	Getter and setter methods
	public OrderPreparationDetailPK getOrderPreparationDetailPK() {
		return orderPreparationDetailPK;
	}

	public void setOrderPreparationDetailPK(OrderPreparationDetailPK orderPreparationDetailPK) {
		this.orderPreparationDetailPK = orderPreparationDetailPK;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Date getTaskDeliveryTime() {
		return taskDeliveryTime;
	}

	public void setTaskDeliveryTime(Date taskDeliveryTime) {
		this.taskDeliveryTime = taskDeliveryTime;
	}
}
