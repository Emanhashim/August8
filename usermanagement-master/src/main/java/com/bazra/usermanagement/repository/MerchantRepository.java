package com.bazra.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bazra.usermanagement.model.AgentInfo;
import com.bazra.usermanagement.model.MerchantInfo;

public interface MerchantRepository extends JpaRepository<MerchantInfo, Integer>{
	Optional<MerchantInfo> findByUsername(String username);
	Optional<MerchantInfo> findById(Integer i);
}
