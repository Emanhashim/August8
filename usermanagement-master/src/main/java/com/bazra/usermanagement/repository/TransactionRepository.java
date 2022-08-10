package com.bazra.usermanagement.repository;


import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bazra.usermanagement.model.Account;
import com.bazra.usermanagement.model.Transaction;


/**
 * Returns list of transaction of an account
 * @author Bemnet
 * @version 5/2022
 */

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findByUserID(Integer id);
    List<Transaction> findByaccountNumberEquals(String accountNumber);
    List<Transaction> findByfromAccountNumberEquals(String fromAccountNumber);
//    List<Transaction> findByTransactionDateTimeGreaterThanEqualTransactionDateTimeLessThanEqual(Date transactionDateTime,Date tansactionDatetimeend);
    List<Transaction> findByTransactionDateTimeBetween(LocalDateTime todayMidnight,LocalDateTime tomorrowMidnight);
    Page<Transaction> findByAccountNumberContaining(String accountNumber,Pageable pageable);
    Page<Transaction> findByfromAccountNumberEquals(String fromAccountNumber,Pageable pageable);
    Page<Transaction> findByaccountNumberEquals(String fromAccountNumber,Pageable pageable);
    List<Transaction> findByTransactionDateTime(LocalDate today);
    
   
	
}

