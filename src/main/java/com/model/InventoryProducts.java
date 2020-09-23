package com.model;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

import java.math.BigInteger;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryProducts {
	
	@Id
	@SequenceGenerator(name = "INVPRODUCTS_SEQ", sequenceName = "INVPRODUCTS_SEQ", initialValue = 1, allocationSize=10)
	@GeneratedValue(strategy = SEQUENCE, generator = "INVPRODUCTS_SEQ")
	private BigInteger InventoryId;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "IdProduct", referencedColumnName = "ProductId")
	private Products products;
	
	private int count;
	private int totalStock;
	private Double priceSale;
	private Double cost;
	private boolean state;
	private int totalFaulty;
	private Double discountPercent;
	private Instant createdDate;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "createdUser", referencedColumnName = "UserId")
	private Users user;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "IdCompany", referencedColumnName = "CompanyId")
	private Company company;

}