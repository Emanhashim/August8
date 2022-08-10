package com.bazra.usermanagement.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class DepositResponse {
    private BigDecimal amount;
    private LocalDate time;
    private String message;
    
    
    public DepositResponse( BigDecimal amount, LocalDate localDate, String message) {
        
    
        this.amount = amount;
        this.time = localDate;
        this.message= message;
       }


    public BigDecimal getAmount() {
        return amount;
    }


    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public LocalDate getTime() {
        return time;
    }


    public void setTime(LocalDate time) {
        this.time = time;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

}
