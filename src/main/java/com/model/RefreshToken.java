package com.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.math.BigInteger;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
	
	@Id
	@SequenceGenerator(name = "REFRESHTOKEN_SEQ", sequenceName = "REFRESHTOKEN_SEQ", initialValue = 1, allocationSize=10)
	@GeneratedValue(strategy = SEQUENCE, generator = "REFRESHTOKEN_SEQ")
	private BigInteger RefreshId;
	private String token;
	private Instant createdDate;
}
