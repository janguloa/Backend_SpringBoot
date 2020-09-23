package com.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.math.BigInteger;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	@Id
	@SequenceGenerator(name = "CUSTOMER_SEQ", sequenceName = "CUSTOMER_SEQ", initialValue = 1, allocationSize=10)
	@GeneratedValue(strategy = SEQUENCE, generator = "CUSTOMER_SEQ")
	private BigInteger customerId;
	private String customerIdent;
	private String names;
	private String email;
	private String telephone;
	private Instant createdDate;
	private Instant lastPurchasesDate;
	private boolean enabled;
	
	@Transient
	private UpdateType updateType;

}