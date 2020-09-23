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

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sales {
	
	@Id
	@SequenceGenerator(name = "SALES_SEQ", sequenceName = "SALES_SEQ", initialValue = 1, allocationSize=10)
	@GeneratedValue(strategy = SEQUENCE, generator = "SALES_SEQ")
	private BigInteger salesId;
	private Instant createdDate;
	private String receipt;
	private int quantity;
	private Double unitaryPrice;
	private Double totalprice;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "IdCompany", referencedColumnName = "CompanyId")
	private Company company;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "IdProduct", referencedColumnName = "ProductId")
	private Products products;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "IdCostumer", referencedColumnName = "customerId")
	private Customer customer;
	
}