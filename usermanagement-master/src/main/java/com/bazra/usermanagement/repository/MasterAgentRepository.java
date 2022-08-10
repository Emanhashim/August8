package com.bazra.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bazra.usermanagement.model.AgentInfo;
import com.bazra.usermanagement.model.MasterAgentInfo;
@Repository
public interface MasterAgentRepository extends JpaRepository<MasterAgentInfo, Long> {
	Optional<MasterAgentInfo> findByuserName(String username);
	Optional<MasterAgentInfo> findByphoneNumber(String phoneNumber);

}
