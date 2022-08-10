package com.bazra.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bazra.usermanagement.model.Promotion;


@Repository
@Transactional(readOnly = true)
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
	Optional<Promotion> findByTitle(String title);
	Optional<Promotion> findById(Long id);

}
