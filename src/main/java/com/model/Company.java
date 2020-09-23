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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {
	
	@Id
	@SequenceGenerator(name = "COMPANY_SEQ", sequenceName = "COMPANY_SEQ", initialValue = 1, allocationSize=10)
	@GeneratedValue(strategy = SEQUENCE, generator = "COMPANY_SEQ")
	private BigInteger CompanyId;
	private String description;
	private boolean enabled; 
	private Instant createdate;
	
	@Transient
	private UpdateType updateType;
	
}