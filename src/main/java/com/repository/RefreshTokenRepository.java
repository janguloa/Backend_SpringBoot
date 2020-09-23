package com.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, BigInteger> {
	
	Optional <RefreshToken> findByToken(String token);
	
	void deleteByToken(String token); 

}
