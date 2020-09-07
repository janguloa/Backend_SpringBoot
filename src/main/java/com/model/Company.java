package com.model;

import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {
	
	@Id
	@SequenceGenerator(name = "COMPANY_SEQ", sequenceName = "COMPANY_SEQ", initialValue = 1, allocationSize=100)
	@GeneratedValue(strategy = SEQUENCE, generator = "COMPANY_SEQ")
	private long Id;
	private String description;
	private boolean enabled;
	
}