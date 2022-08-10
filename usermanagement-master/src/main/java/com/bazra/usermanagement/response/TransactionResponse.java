package com.bazra.usermanagement.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.bazra.usermanagement.model.*;
import com.fasterxml.jackson.annotation.JsonProperty;
public class TransactionResponse {
	@JsonProperty(value = "summary", required = true)
    private List<Account> accounts;
	private List<Transaction> transaction;
	
	
   
    public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public List<Transaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}
	public TransactionResponse(List<Transaction> transaction,List<Transaction> transaction2) {
    	List<Transaction> stringArrayList = new ArrayList<Transaction>();
    	stringArrayList.addAll(transaction);
        stringArrayList.addAll(transaction2);
        
        this.transaction= stringArrayList;
        
        
    }
    public TransactionResponse(List<Account> accounts) {
    	List<Account> stringArrayList = new ArrayList<Account>();
    	stringArrayList.addAll(accounts);
//        stringArrayList.addAll(transaction2);
        
        this.accounts= stringArrayList;
        
        
    }

}
