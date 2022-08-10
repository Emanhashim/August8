package com.bazra.usermanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bazra.usermanagement.model.Account;



@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
   Page<Account> findByAccountNumberEquals(String accountnumber,Pageable pageable);
   Page<Account> findByusername(String username,Pageable pageable);
   Optional<Account> findByusername(String username);
   Optional<Account> findByuserid(Integer userid);
   Optional<Account> findByAccountNumberEquals(String accountnumber);
//   List<Account> findAll(String accountNumber,Sort sort);
}
