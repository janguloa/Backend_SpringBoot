package com.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, BigInteger> {
	
	Optional<Users> findByUsername(String username);
	
	Optional<Users> findById(BigInteger id);

}