package com.bazra.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bazra.usermanagement.model.AdminInfo;
@Repository
@Transactional(readOnly = true)
public interface AdminRepository extends JpaRepository<AdminInfo, Integer> {
	Optional<AdminInfo> findByUsername(String username);
}
