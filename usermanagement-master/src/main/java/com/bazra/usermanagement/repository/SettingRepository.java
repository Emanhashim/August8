package com.bazra.usermanagement.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bazra.usermanagement.model.Account;
import com.bazra.usermanagement.model.Levels;
import com.bazra.usermanagement.model.Settings;



@Repository
public interface SettingRepository extends JpaRepository<Settings, Integer> {
   
   Optional<Settings> findBysettingName(String settingName);
   Optional<Settings> findBylevels(Levels levels);
}
