package com.model;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

import java.math.BigInteger;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Products {
	
	@Id
	@SequenceGenerator(name = "PRODUCTS_SEQ", sequenceName = "PRODUCTS_SEQ", initialValue = 1, allocationSize=10)
	@GeneratedValue(strategy = SEQUENCE, generator = "PRODUCTS_SEQ")
	private BigInteger ProductId;
	private String codproduct;
	private String description;
	private int totalStock;
	private int reOrderStock;
	private Double priceSale;
	private Double cost;
	private int totalFaulty;
	private Double discountPercent;
	@Lob
	private byte[] imagen;
	private boolean enabled;
	private Instant createdDate;
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "createdUser", referencedColumnName = "UserId")
	private Users user;
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "IdCompany", referencedColumnName = "CompanyId")
	private Company company;
	
	@Transient
	private UpdateType updateType; 
}