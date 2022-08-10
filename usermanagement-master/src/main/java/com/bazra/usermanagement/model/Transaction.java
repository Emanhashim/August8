package com.bazra.usermanagement.model;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Transaction")
public class Transaction {
   

    @Id
    @GeneratedValue
    private Long transactionId;
    private int userID;
    private String accountNumber;
    private String fromAccountNumber;

    private BigDecimal transactionAmount;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate transactionDateTime;
    
    private String transaction_type;

    

//    public Transaction() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

    public int getUserID() {
		return userID;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

    


	public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public String getaccountNumber() {
		return accountNumber;
	}

	public void setaccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalDate getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDate transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public Transaction(  String accountNumber, BigDecimal amount, LocalDate timestamp) {
    
     this.accountNumber = accountNumber;
     this.transactionAmount = amount;
     this.transactionDateTime = timestamp;
    }
    public Transaction(int userID, String fromAccountNumber,  String accountNumber, BigDecimal amount, LocalDate timestamp,String transaction_type) {
    	this.userID=userID;
    	this.fromAccountNumber=fromAccountNumber;
        this.accountNumber = accountNumber;
        this.transactionAmount = amount;
        this.transactionDateTime = timestamp;
        this.transaction_type=transaction_type;
       }
 
    public Transaction(BigDecimal amount, LocalDate timestamp) {
        this.transactionAmount=amount;
        this.transactionDateTime=timestamp;
        
    }
    
    
}
