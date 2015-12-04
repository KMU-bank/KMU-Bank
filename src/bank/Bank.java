package bank;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import account.*;

public class Bank {
	
	private HashMap<String, Account> account = Accounts.getInstance().accountList;
	public String INIT = "";	//initial account number
	public double positiveInterest = 0.0;
	public double negativeInterest = 0.0;
	
	public Bank(String INIT, double positiveInterest, double negativeInterest){
		this.INIT = INIT;
		this.positiveInterest = positiveInterest;
		this.negativeInterest = negativeInterest;
	}
	
	public String openAccount(String name){
		String accountNumber = INIT + new Random().nextInt(99999);
		while(true)
			if(account.containsKey(accountNumber))
				accountNumber = INIT + new Random().nextInt(99999);
			else
				break;
		
		account.put(accountNumber, new Account(accountNumber, name));
		return accountNumber;
	}
	
	public int closeAccount(String accountNumber){
		if(account.get(accountNumber) != null){
			int restBalance = account.get(accountNumber).getBalance();
			account.remove(accountNumber);
			return restBalance;
		}
		return 0; // if no account number
	}
	
	public boolean deposit(String accountNumber, int money){
		Account selectedAccount = account.get(accountNumber);
		boolean isDone =  selectedAccount.deposit(money);
		if(isDone)
			selectedAccount.addStateList("입급 : " + money + " 잔액 : " + selectedAccount.getBalance());
		return isDone;
	}
	
	public boolean withdraw(String accountNumber, int money){
		Account selectedAccount = account.get(accountNumber);
		boolean isDone =  selectedAccount.withdraw(money);
		if(isDone)
			selectedAccount.addStateList("출금 : " + money + " 잔액 : " + selectedAccount.getBalance());
		return isDone;
	}//false -> stay client money & print out on console
	
	public boolean transfer(String from, String to, int money){
		try{
		Account fromAccount = account.get(from);
		Account toAccount = account.get(to);
		
		boolean isDone = fromAccount.withdraw(money);
		if(isDone)
			toAccount.deposit(money);
		else
			return false;
		
		fromAccount.addStateList("이체한 금액 : " + money + " 이체 계좌번호 : " + from + "잔액 : " + fromAccount.getBalance());
		fromAccount.addStateList("이체된 금액 : " + money + " 이체 계좌번호 : " + to + "잔액 : "  + toAccount.getBalance());
		
		} catch(Exception e){
			System.out.println("없는 계좌번호 입니다.");
		}
		return true;
	}
	
	public void loan(String accountNumber, int money){
		account.get(accountNumber).loan(money);
	}
	
	public int getBalance(String accountNumber){
		return account.get(accountNumber).getBalance();
	}
	
	public int getDebt(String accountNumber){
		return account.get(accountNumber).getDebt();
	}
	
	public boolean repayOnAccount(String accountNumber, int money){
		Account selectedAccount = account.get(accountNumber);
		
		boolean isDone = selectedAccount.repayOnAccount(money);
		selectedAccount.addStateList("대출상환금액 : " + money 
										+ " 남은 대출금 : " + selectedAccount.getDebt() 
											+ "은행잔고 : " + selectedAccount.getBalance());
		return isDone;
	}
	
	public void repay(String accountNumber, int money){
		Account selectedAccount = account.get(accountNumber);
		selectedAccount.repay(money);
		selectedAccount.addStateList("대출상환금액 : " + money + "남은 대출금 : " + selectedAccount.getDebt());
	}
	
	public LinkedList<String> getStateList(String accountNumber){
		return account.get(accountNumber).getStateList();
	}
	
	public void timeLeapYear(){
		for(String key: account.keySet()){
			account.get(key).deposit((int)(account.get(key).getBalance() * positiveInterest));
			account.get(key).loan((int)(account.get(key).getDebt() * negativeInterest));
		}
	}
}
