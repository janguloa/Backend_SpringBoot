package com.model;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PurchasesDetails {
	
	@Id
	@SequenceGenerator(name = "PURCHASESDETAILS_SEQ", sequenceName = "PURCHASESDETAILS_SEQ", initialValue = 1, allocationSize=10)
	@GeneratedValue(strategy = SEQUENCE, generator = "PURCHASESDETAILS_SEQ")
	private Long id;
	private String description;
	private int quantity;
	private Double unitaryCost;
	private Double unitaryShippingCost;
	private Double taxesCost;
	private Instant createdate;
	private boolean enabled;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "IdPurchases", referencedColumnName = "Id")
	private Purchases purchases;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "IdCompany", referencedColumnName = "Id")
	private Company company;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "IdProduct", referencedColumnName = "Id")
	private Products products;
	
	@Transient
	private UpdateType updateType; 
	
	public Double fetchCost() {
		
		return 	getUnitaryCost() + getTaxesCost() + getUnitaryShippingCost();
	}
}