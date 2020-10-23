package com.behoh.testejavaweb.entities;

import java.util.Date;

import javax.persistence.Entity;

import com.behoh.testejavaweb.entities.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class BoletoPayment extends Payment{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dueDate;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date paymentDate;
	
	public BoletoPayment(Long id, PaymentStatus status, Event event, Date dueDate, Date paymentDate) {
		super(id, status, event);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
}
