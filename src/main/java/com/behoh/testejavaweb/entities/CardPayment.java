package com.behoh.testejavaweb.entities;

import javax.persistence.Entity;

import com.behoh.testejavaweb.entities.enums.PaymentStatus;

@Entity
public class CardPayment extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Integer numberOfInstallments;

	public CardPayment(Long id, PaymentStatus status, Event event, Integer numberOfInstallments) {
		super(id, status, event);
		this.numberOfInstallments = numberOfInstallments;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}	
}
