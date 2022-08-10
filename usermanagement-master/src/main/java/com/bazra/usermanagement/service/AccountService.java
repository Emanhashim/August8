package com.bazra.usermanagement.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bazra.usermanagement.model.Account;
import com.bazra.usermanagement.model.Levels;
import com.bazra.usermanagement.model.Transaction;
import com.bazra.usermanagement.repository.AccountRepository;
import com.bazra.usermanagement.repository.SettingRepository;
import com.bazra.usermanagement.repository.TransactionRepository;
import com.bazra.usermanagement.repository.UserRepository;
import com.bazra.usermanagement.request.AdminTransferRequest;
import com.bazra.usermanagement.request.DepositRequest;
import com.bazra.usermanagement.request.TransferRequest;
import com.bazra.usermanagement.request.WithdrawRequest;
import com.bazra.usermanagement.response.DepositResponse;
import com.bazra.usermanagement.response.ResponseError;
import com.bazra.usermanagement.response.TransferResponse;
import com.bazra.usermanagement.response.WithdrawResponse;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	SettingRepository settingRepository;

//	@Value("${withdrawfee}")
//	private BigDecimal withdrawfee;

	Account toAccount;
	Account agAccount;
	Account fromAccount;
	Account frAccount;
	Account merAccount;
	Account bazraAccount;
	BigDecimal transactionfee;
	BigDecimal commissionfee;
	BigDecimal dailyTransferLimit;
	BigDecimal agentDepositLimit;
	BigDecimal maxBalanceL1;
	BigDecimal maxBalanceL2;
	BigDecimal maxBalanceL3;
//	BigDecimal daily = new BigDecimal(0);
	LocalDate daterepo;
	
	public Account save(Account account) {
		accountRepository.save(account);
		return accountRepository.findByAccountNumberEquals(account.getAccountNumber()).get();
	}

	public Account getAccount(String account) {
		Account accounts = accountRepository.findByAccountNumberEquals(account).get();
		return accounts;
	}

	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	public List<Transaction> findall(String accountnumber) {
		List<Transaction> transactions = transactionRepository.findByaccountNumberEquals(accountnumber);
		return transactions;
	}

	public Account findByAccountNumber(String accountnumber) {
		Account account = accountRepository.findByAccountNumberEquals(accountnumber).get();
		return account;
	}

	public ResponseEntity<?> sendMoney(TransferRequest transferBalanceRequest, String name) {
		Account fromAccount = accountRepository.findByAccountNumberEquals(name).get();
		BigDecimal daily =new BigDecimal(0);
	
		LocalDate today = LocalDate.now(ZoneId.systemDefault());
		
		Levels levels = userRepository.findById(fromAccount.getUserid()).get().getLevels();
		
		if(levels==Levels.LEVEL_1) {
			transactionfee = settingRepository.findBysettingName("Transaction Fee(LEVEL_1)").get().getValue();
			dailyTransferLimit = settingRepository.findBysettingName("Daily Transfer Limit").get().getValue();
		}
		else if (levels==Levels.LEVEL_2) {
			transactionfee = settingRepository.findBysettingName("Transaction Fee(LEVEL_2)").get().getValue();
			dailyTransferLimit = settingRepository.findBysettingName("Daily Transfer Limit(LEVEL_2)").get().getValue();
		}
		else if (levels==Levels.LEVEL_3) {
			transactionfee = settingRepository.findBysettingName("Transaction Fee(LEVEL_3)").get().getValue();
			dailyTransferLimit = settingRepository.findBysettingName("Daily Transfer Limit(LEVEL_3)").get().getValue();
		}
		String toAccountNumber = transferBalanceRequest.getToAccountNumber();
		BigDecimal amount = transferBalanceRequest.getAmount();
		bazraAccount = accountRepository.findByAccountNumberEquals("091122334455").get();
		try {
			toAccount = accountRepository.findByAccountNumberEquals(toAccountNumber).get();
			if(toAccount.equals(fromAccount)) {
				return ResponseEntity.badRequest().body(new ResponseError("Invalid receipient"));
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError("Invalid receipient"));
		}

		if (toAccount.isBlocked()) {
			return ResponseEntity.badRequest().body(new ResponseError("Account Blocked"));
		}

		if (toAccount == null) {
			return ResponseEntity.badRequest().body(new ResponseError("Invalid receipient"));
		}
		if (transferBalanceRequest.getAmount() == null) {
			return ResponseEntity.badRequest().body(new ResponseError("Enter Valid Amount"));
		}

		if (amount.compareTo(new BigDecimal(5)) == -1) {
			return ResponseEntity.badRequest().body(new ResponseError("Minimum amount to transfer is 5"));
		}
		if (transferBalanceRequest.getMessage() == null) {
			return ResponseEntity.badRequest().body(new ResponseError("Enter your remark"));
		}
		Levels toAccountLevel = userRepository.findByUsername(toAccount.getUsername()).get().getLevels();
		if(toAccountLevel.equals(Levels.LEVEL_1)) {
			maxBalanceL1 = settingRepository.findBysettingName("Balance Limit (LEVEL_1)").get().getValue();
		}
		else if (toAccountLevel.equals(Levels.LEVEL_2)) {
			maxBalanceL1 = settingRepository.findBysettingName("Balance Limit (LEVEL_2)").get().getValue();
		}
		else if (toAccountLevel.equals(Levels.LEVEL_3)) {
			maxBalanceL1 = settingRepository.findBysettingName("Balance Limit (LEVEL_3)").get().getValue();
		}
		List<Transaction> transactionscurr =new ArrayList<Transaction>();
		List<Transaction> transactions=transactionRepository.findByTransactionDateTime(today);
		
		for (int i = 0; i < transactions.size(); i++) {
			if (transactions.get(i).getFromAccountNumber().matches(name)) {
				transactionscurr.add(transactions.get(i));

			}

		}
		BigDecimal minbalance = amount.add(transactionfee);
		
		if(transactionscurr.isEmpty()) {
			if (fromAccount.getBalance().compareTo(BigDecimal.ONE) == 1
					&& fromAccount.getBalance().compareTo(minbalance) == 1) {
				

				daily = daily.add(transferBalanceRequest.getAmount());

				
				if (maxBalanceL1.compareTo(toAccount.getBalance().add(transferBalanceRequest.getAmount()))==-1) {
					return ResponseEntity.badRequest().body(new ResponseError("Receiver not able to accept"));
				}
				if (dailyTransferLimit.compareTo(daily.negate()) == 1) {
					fromAccount.setBalance(fromAccount.getBalance().subtract(minbalance));
					fromAccount.setDaily(daily.negate().add(transferBalanceRequest.getAmount()));
					accountRepository.save(fromAccount);
					bazraAccount.setBalance(bazraAccount.getBalance().add(transactionfee.multiply(amount)));
					toAccount.setBalance(toAccount.getBalance().add(amount));
					accountRepository.save(toAccount);
					accountRepository.save(bazraAccount);
					Transaction transaction = transactionRepository
							.save(new Transaction(fromAccount.getUser_id(), fromAccount.getAccountNumber(),
									transferBalanceRequest.getToAccountNumber(), amount.negate(), today, "Transfer"));
					return ResponseEntity.ok(new TransferResponse(transaction.getaccountNumber(),
							transaction.getTransactionAmount(), transaction.getTransactionDateTime(),
							"Transfer successful! " + amount + "$ has been sent to " + toAccountNumber));
				}
				else {
					
					return ResponseEntity.badRequest().body(new ResponseError("Daily Limit overpassed"));
				}
			}
		}	
		else {
			daterepo= transactions.get(transactions.size()-1).getTransactionDateTime();

			if (fromAccount.getBalance().compareTo(BigDecimal.ONE) == 1
					&& fromAccount.getBalance().compareTo(minbalance) == 1) {
			

				
				for (int i = 0; i < transactionscurr.size(); i++) {
					
						daily = daily.add(transactionscurr.get(i).getTransactionAmount());
				
				}
				
				if (maxBalanceL1.compareTo(toAccount.getBalance().add(transferBalanceRequest.getAmount()))==-1) {
					return ResponseEntity.badRequest().body(new ResponseError("Receiver not able to accept"));
				}
				if (dailyTransferLimit.compareTo(daily.negate().add(minbalance)) == 1) {
					fromAccount.setBalance(fromAccount.getBalance().subtract(minbalance));
					fromAccount.setDaily(daily.negate().add(transferBalanceRequest.getAmount()));
					accountRepository.save(fromAccount);
					bazraAccount.setBalance(bazraAccount.getBalance().add(transactionfee.multiply(amount)));
					toAccount.setBalance(toAccount.getBalance().add(amount));
					accountRepository.save(toAccount);
					accountRepository.save(bazraAccount);
					Transaction transaction = transactionRepository
							.save(new Transaction(fromAccount.getUser_id(), fromAccount.getAccountNumber(),
									transferBalanceRequest.getToAccountNumber(), amount.negate(), today, "Transfer"));
					return ResponseEntity.ok(new TransferResponse(transaction.getaccountNumber(),
							transaction.getTransactionAmount(), transaction.getTransactionDateTime(),
							"Transfer successful! " + amount + "$ has been sent to " + toAccountNumber));
				} else {

					return ResponseEntity.badRequest().body(new ResponseError("Daily Limit overpassed"));
				}

			}
		}
		
		return ResponseEntity.badRequest().body(new ResponseError("Insufficient balance"));
	}

	public ResponseEntity<?> adminSendMoney(AdminTransferRequest adminTransferRequest,String adminusername) {
		Account fromAccount = accountRepository.findByAccountNumberEquals(adminTransferRequest.getFromAccountNumber()).get();
		BigDecimal daily =new BigDecimal(0);
	
		LocalDate today = LocalDate.now(ZoneId.systemDefault());
		
		Levels levels = userRepository.findById(fromAccount.getUserid()).get().getLevels();
		
		if(levels==Levels.LEVEL_1) {
			transactionfee = settingRepository.findBysettingName("Transaction Fee(LEVEL_1)").get().getValue();
			dailyTransferLimit = settingRepository.findBysettingName("Daily Transfer Limit").get().getValue();
		}
		else if (levels==Levels.LEVEL_2) {
			transactionfee = settingRepository.findBysettingName("Transaction Fee(LEVEL_2)").get().getValue();
			dailyTransferLimit = settingRepository.findBysettingName("Daily Transfer Limit(LEVEL_2)").get().getValue();
		}
		else if (levels==Levels.LEVEL_3) {
			transactionfee = settingRepository.findBysettingName("Transaction Fee(LEVEL_3)").get().getValue();
			dailyTransferLimit = settingRepository.findBysettingName("Daily Transfer Limit(LEVEL_3)").get().getValue();
		}
		String toAccountNumber = adminTransferRequest.getToAccountNumber();
		BigDecimal amount = adminTransferRequest.getAmount();
		bazraAccount = accountRepository.findByAccountNumberEquals(adminusername).get();
		try {
			toAccount = accountRepository.findByAccountNumberEquals(toAccountNumber).get();
			if(toAccount.equals(fromAccount)) {
				return ResponseEntity.badRequest().body(new ResponseError("Invalid receipient"));
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError("Invalid receipient"));
		}

		if (toAccount.isBlocked()) {
			return ResponseEntity.badRequest().body(new ResponseError("Account Blocked"));
		}

		if (toAccount == null) {
			return ResponseEntity.badRequest().body(new ResponseError("Invalid receipient"));
		}
		if (adminTransferRequest.getAmount() == null) {
			return ResponseEntity.badRequest().body(new ResponseError("Enter Valid Amount"));
		}

		if (amount.compareTo(new BigDecimal(5)) == -1) {
			return ResponseEntity.badRequest().body(new ResponseError("Minimum amount to transfer is 5"));
		}
		if (adminTransferRequest.getMessage() == null) {
			return ResponseEntity.badRequest().body(new ResponseError("Enter your remark"));
		}
		Levels toAccountLevel = userRepository.findByUsername(toAccount.getUsername()).get().getLevels();
		if(toAccountLevel.equals(Levels.LEVEL_1)) {
			maxBalanceL1 = settingRepository.findBysettingName("Balance Limit (LEVEL_1)").get().getValue();
		}
		else if (toAccountLevel.equals(Levels.LEVEL_2)) {
			maxBalanceL1 = settingRepository.findBysettingName("Balance Limit (LEVEL_2)").get().getValue();
		}
		else if (toAccountLevel.equals(Levels.LEVEL_3)) {
			maxBalanceL1 = settingRepository.findBysettingName("Balance Limit (LEVEL_3)").get().getValue();
		}
		List<Transaction> transactionscurr =new ArrayList<Transaction>();
		List<Transaction> transactions=transactionRepository.findByTransactionDateTime(today);
		
		for (int i = 0; i < transactions.size(); i++) {
			if (transactions.get(i).getFromAccountNumber().matches(adminTransferRequest.getFromAccountNumber())) {
				transactionscurr.add(transactions.get(i));

			}

		}
		BigDecimal minbalance = amount.add(transactionfee);
		
		if(transactionscurr.isEmpty()) {
			if (fromAccount.getBalance().compareTo(BigDecimal.ONE) == 1
					&& fromAccount.getBalance().compareTo(minbalance) == 1) {
				

				daily = daily.add(adminTransferRequest.getAmount());

				
				if (maxBalanceL1.compareTo(toAccount.getBalance().add(adminTransferRequest.getAmount()))==-1) {
					return ResponseEntity.badRequest().body(new ResponseError("Receiver not able to accept"));
				}
				if (dailyTransferLimit.compareTo(daily.negate()) == 1) {
					fromAccount.setBalance(fromAccount.getBalance().subtract(minbalance));
					fromAccount.setDaily(daily.negate().add(adminTransferRequest.getAmount()));
					accountRepository.save(fromAccount);
					bazraAccount.setBalance(bazraAccount.getBalance().add(transactionfee.multiply(amount)));
					toAccount.setBalance(toAccount.getBalance().add(amount));
					accountRepository.save(toAccount);
					accountRepository.save(bazraAccount);
					Transaction transaction = transactionRepository
							.save(new Transaction(fromAccount.getUser_id(), fromAccount.getAccountNumber(),
									adminTransferRequest.getToAccountNumber(), amount.negate(), today, "Transfer"));
					return ResponseEntity.ok(new TransferResponse(transaction.getaccountNumber(),
							transaction.getTransactionAmount(), transaction.getTransactionDateTime(),
							"Transfer successful! " + amount + "$ has been sent to " + toAccountNumber));
				}
				else {
					
					return ResponseEntity.badRequest().body(new ResponseError("Daily Limit overpassed"));
				}
			}
		}	
		else {
			daterepo= transactions.get(transactions.size()-1).getTransactionDateTime();

			if (fromAccount.getBalance().compareTo(BigDecimal.ONE) == 1
					&& fromAccount.getBalance().compareTo(minbalance) == 1) {
			

				
				for (int i = 0; i < transactionscurr.size(); i++) {
					
						daily = daily.add(transactionscurr.get(i).getTransactionAmount());
				
				}
				
				if (maxBalanceL1.compareTo(toAccount.getBalance().add(adminTransferRequest.getAmount()))==-1) {
					return ResponseEntity.badRequest().body(new ResponseError("Receiver not able to accept"));
				}
				if (dailyTransferLimit.compareTo(daily.negate().add(minbalance)) == 1) {
					fromAccount.setBalance(fromAccount.getBalance().subtract(minbalance));
					fromAccount.setDaily(daily.negate().add(adminTransferRequest.getAmount()));
					accountRepository.save(fromAccount);
					bazraAccount.setBalance(bazraAccount.getBalance().add(transactionfee.multiply(amount)));
					toAccount.setBalance(toAccount.getBalance().add(amount));
					accountRepository.save(toAccount);
					accountRepository.save(bazraAccount);
					Transaction transaction = transactionRepository
							.save(new Transaction(fromAccount.getUser_id(), fromAccount.getAccountNumber(),
									adminTransferRequest.getToAccountNumber(), amount.negate(), today, "Transfer"));
					return ResponseEntity.ok(new TransferResponse(transaction.getaccountNumber(),
							transaction.getTransactionAmount(), transaction.getTransactionDateTime(),
							"Transfer successful! " + amount + "$ has been sent from "+ adminTransferRequest.getFromAccountNumber()+" to " + toAccountNumber));
				} else {

					return ResponseEntity.badRequest().body(new ResponseError("Daily Limit overpassed"));
				}

			}
		}
		
		return ResponseEntity.badRequest().body(new ResponseError("Insufficient balance"));
	}

	public ResponseEntity<?> withdraw(WithdrawRequest withdraw, String name) {
		LocalDate today = LocalDate.now(ZoneId.systemDefault());
		BigDecimal daily =new BigDecimal(0);
		frAccount = accountRepository.findByAccountNumberEquals(name).get();
		Levels levels = userRepository.findById(frAccount.getUser_id()).get().getLevels();
		if(levels==Levels.LEVEL_1) {
			commissionfee = settingRepository.findBysettingName("commission fee(LEVEL_1)").get().getValue();
			transactionfee = settingRepository.findBysettingName("Transaction Fee(LEVEL_1)").get().getValue();
			dailyTransferLimit = settingRepository.findBysettingName("Daily Transfer Limit").get().getValue();
		}
		else if (levels==Levels.LEVEL_2) {
			commissionfee = settingRepository.findBysettingName("commission fee(LEVEL_2)").get().getValue();
			transactionfee = settingRepository.findBysettingName("Transaction Fee(LEVEL_2)").get().getValue();
			dailyTransferLimit = settingRepository.findBysettingName("Daily Transfer Limit(LEVEL_2)").get().getValue();
		}
		else if (levels==Levels.LEVEL_3) {
			commissionfee = settingRepository.findBysettingName("commission fee(LEVEL_3)").get().getValue();
			transactionfee = settingRepository.findBysettingName("Transaction Fee(LEVEL_3)").get().getValue();
			dailyTransferLimit = settingRepository.findBysettingName("Daily Transfer Limit(LEVEL_3)").get().getValue();
		}
		try {
			agAccount = accountRepository.findByAccountNumberEquals(withdraw.getFromAccountNumber()).get();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError("Invalid account"));
		}
		if (agAccount.isBlocked()) {
			return ResponseEntity.badRequest().body(new ResponseError("Account Blocked"));
		}
		if (agAccount == null) {
			return ResponseEntity.badRequest().body(new ResponseError("Invalid account"));
		}
		if (!agAccount.getType().matches("AGENT")) {
			return ResponseEntity.badRequest().body(new ResponseError("Not an agent account"));
		}
		if (withdraw.getAmount() == null) {
			return ResponseEntity.badRequest().body(new ResponseError("Enter Valid Amount"));
		}
		if(withdraw.getAmount().compareTo(agAccount.getBalance())==1) {
			return ResponseEntity.badRequest().body(new ResponseError("Agent has insufficient balance for your request"));
		}
		List<Transaction> transactionscurr =new ArrayList<Transaction>();
		List<Transaction> transactions=transactionRepository.findByTransactionDateTime(today);
		bazraAccount = accountRepository.findByAccountNumberEquals("091122334455").get();
		System.out.println(transactions);
		for (int i = 0; i < transactions.size(); i++) {
			if (transactions.get(i).getFromAccountNumber().matches(name)) {
				transactionscurr.add(transactions.get(i));

			}

		}
		BigDecimal balance = frAccount.getBalance();
		BigDecimal minbalance = withdraw.getAmount().add(commissionfee).add(transactionfee);
		if(transactionscurr.isEmpty()) {
			
			
			if (balance.compareTo(minbalance) == 1) {
				daily = daily.add(withdraw.getAmount());
				if (dailyTransferLimit.compareTo(daily.negate().add(minbalance)) == 1) {
					System.out.println("daily"+daily);
					frAccount.setBalance(balance.subtract(minbalance));
					frAccount.setDaily(daily.negate().add(withdraw.getAmount()));
					accountRepository.save(frAccount);
					agAccount.setBalance(agAccount.getBalance().add(withdraw.getAmount()));
					agAccount.setCommission(agAccount.getCommission().add(commissionfee.multiply(withdraw.getAmount())));
					accountRepository.save(agAccount);
					bazraAccount.setBalance(bazraAccount.getBalance().add(transactionfee.multiply(withdraw.getAmount())));
					accountRepository.save(bazraAccount);
					Transaction transaction = transactionRepository.save(new Transaction(frAccount.getUser_id(),
							 name,withdraw.getFromAccountNumber(), withdraw.getAmount().negate(), today, "withdraw"));
	
					return ResponseEntity
							.ok(new WithdrawResponse(transaction.getTransactionAmount(), transaction.getTransactionDateTime(),
									"Amount " + withdraw.getAmount()
											+ "$ has been debited from your account. Your current balance is "
											+ balance.subtract(withdraw.getAmount())));
				
				}
				else {
					return ResponseEntity.badRequest().body(new ResponseError("Daily Limit overpassed"));
				}
			}
			else {
				return ResponseEntity.badRequest().body(new ResponseError("Insufficient balance"));
			}
	
		}
		else {
			if (balance.compareTo(minbalance) == 1) {
				for (int i = 0; i < transactionscurr.size(); i++) {
					
					daily = daily.add(transactionscurr.get(i).getTransactionAmount());
					System.out.println("dddd"+daily);
			}
				if (dailyTransferLimit.compareTo(daily.negate().add(minbalance)) == 1) {
					System.out.println("daaaiiillllyyy"+daily);
					frAccount.setBalance(balance.subtract(minbalance));
					System.out.println(balance.subtract(minbalance));
					frAccount.setDaily(daily.negate().add(withdraw.getAmount()));
					accountRepository.save(frAccount);
					agAccount.setBalance(agAccount.getBalance().add(withdraw.getAmount()));
					agAccount.setCommission(agAccount.getCommission().add(commissionfee.multiply(withdraw.getAmount())));
					accountRepository.save(agAccount);
					bazraAccount.setBalance(bazraAccount.getBalance().add(transactionfee.multiply(withdraw.getAmount())));
					accountRepository.save(bazraAccount);
					Transaction transaction = transactionRepository.save(new Transaction(frAccount.getUser_id(),
							 name,withdraw.getFromAccountNumber(), withdraw.getAmount().negate(), today, "withdraw"));

					return ResponseEntity
							.ok(new WithdrawResponse(transaction.getTransactionAmount(), transaction.getTransactionDateTime(),
									"Amount " + withdraw.getAmount()
											+ "$ has been debited from your account. Your current balance is "
											+ balance.subtract(withdraw.getAmount())));
				}
				else {
					return ResponseEntity.badRequest().body(new ResponseError("Daily Limit overpassed! Available transfer amount is "+ (dailyTransferLimit.subtract(frAccount.getDaily()))));
				}
				
				
			}
			else {
				return ResponseEntity.badRequest().body(new ResponseError("Insufficient balance"));
			}

			
		}
		
	}

	public ResponseEntity<?> Deposit(DepositRequest depositRequest, String name) {
		
		LocalDate today = LocalDate.now(ZoneId.systemDefault());
		agentDepositLimit = settingRepository.findBysettingName("Agent Deposit Limit").get().getValue();
		try {
			agAccount = accountRepository.findByAccountNumberEquals(name).get();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError("Invalid account"));
		}
		if (!agAccount.getType().matches("AGENT")) {
			return ResponseEntity.badRequest().body(new ResponseError("Not an agent account"));
		}
		try {
			toAccount = accountRepository.findByAccountNumberEquals(depositRequest.getToAccountNumber()).get();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError("Invalid account"));
		}
		if (toAccount == null) {
			return ResponseEntity.badRequest().body(new ResponseError("Invalid account"));
		}
		if (toAccount.isBlocked()) {
			return ResponseEntity.badRequest().body(new ResponseError("Account Blocked"));
		}

		if (depositRequest.getAmount() == null) {
			return ResponseEntity.badRequest().body(new ResponseError("Enter Valid Amount"));
		}
		Levels toAccountLevel = userRepository.findByUsername(toAccount.getUsername()).get().getLevels();
		if(toAccountLevel.equals(Levels.LEVEL_1)) {
			maxBalanceL1 = settingRepository.findBysettingName("Balance Limit (LEVEL_1)").get().getValue();
		}
		else if (toAccountLevel.equals(Levels.LEVEL_2)) {
			maxBalanceL1 = settingRepository.findBysettingName("Balance Limit (LEVEL_2)").get().getValue();
		}
		else if (toAccountLevel.equals(Levels.LEVEL_3)) {
			maxBalanceL1 = settingRepository.findBysettingName("Balance Limit (LEVEL_3)").get().getValue();
		}
		BigDecimal balance = agAccount.getBalance();
		if(agentDepositLimit.compareTo(depositRequest.getAmount())==-1) {
			return ResponseEntity.badRequest().body(new ResponseError("Deposit Limit overpassed"));
		}
		if (maxBalanceL1.compareTo(toAccount.getBalance().add(depositRequest.getAmount()))==-1) {
			return ResponseEntity.badRequest().body(new ResponseError("User Account not able to accept"));
		}
		if (balance.compareTo(depositRequest.getAmount()) == 1) {
			agAccount.setBalance(balance.subtract(depositRequest.getAmount()));
			accountRepository.save(agAccount);
			toAccount.setBalance(toAccount.getBalance().add(depositRequest.getAmount()));
			accountRepository.save(toAccount);
			Transaction transaction = transactionRepository.save(new Transaction(agAccount.getUser_id(), name,
					depositRequest.getToAccountNumber(), depositRequest.getAmount(), today, "Deposit"));
			return ResponseEntity
					.ok(new DepositResponse(transaction.getTransactionAmount(), transaction.getTransactionDateTime(),
							"Amount " + depositRequest.getAmount()
									+ "$ has been debited from your account. Your current balance is "
									+ agAccount.getBalance().add(depositRequest.getAmount())));
		}

		return ResponseEntity.badRequest().body(new ResponseError("Agent has Insufficient balance"));
	}

	public ResponseEntity<?> pay(DepositRequest depositRequest, String name) {
		LocalDate today = LocalDate.now(ZoneId.systemDefault());
		frAccount = accountRepository.findByAccountNumberEquals(name).get();
		try {
			merAccount = accountRepository.findByAccountNumberEquals(depositRequest.getToAccountNumber()).get();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError("Invalid account"));
		}
		if (merAccount == null) {
			return ResponseEntity.badRequest().body(new ResponseError("Invalid account"));
		}
		if (!merAccount.getType().matches("MERCHANT")) {
			return ResponseEntity.badRequest().body(new ResponseError("Not a merchant account"));
		}
		if (depositRequest.getAmount() == null) {
			return ResponseEntity.badRequest().body(new ResponseError("Enter Valid Amount"));
		}
		BigDecimal balance = frAccount.getBalance();
		if (balance.compareTo(depositRequest.getAmount()) == 1) {
			frAccount.setBalance(balance.subtract(depositRequest.getAmount()));
			accountRepository.save(frAccount);
			merAccount.setBalance(merAccount.getBalance().add(depositRequest.getAmount()));
			accountRepository.save(merAccount);
			Transaction transaction = transactionRepository.save(new Transaction(frAccount.getUser_id(), name,
					depositRequest.getToAccountNumber(), depositRequest.getAmount(), today, "Deposit"));
			return ResponseEntity
					.ok(new DepositResponse(transaction.getTransactionAmount(), transaction.getTransactionDateTime(),
							"Amount " + depositRequest.getAmount()
									+ "$ has been debited from your account. Your current balance is "
									+ frAccount.getBalance().add(depositRequest.getAmount())));
		}

		return ResponseEntity.badRequest().body(new ResponseError("Insufficient balance"));
	}


}
