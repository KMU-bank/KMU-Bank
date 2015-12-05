package client;

import java.util.LinkedList;

import bank.*;

public class Client {
	private int asset; // 은행에 입금하지 않은 소지금
	private String name;
	private String KBBankAccountNumber;
	private String NHBankAccountNumber;
	private String WooriBankAccountNumber;
	
	public Client(String Name){ // 생성자
		name = Name;
		asset = 2000000;	//	기본 소지금
		KBBankAccountNumber = "0";
		NHBankAccountNumber = "0";
		WooriBankAccountNumber = "0";
	}
	
	public String openAccount(Bank bank){
		if(bank.getClass() == KBBank.class)
			return KBBankAccountNumber = bank.openAccount(name);
		else if(bank.getClass() == NHBank.class)
			return NHBankAccountNumber = bank.openAccount(name);
		else
			return WooriBankAccountNumber = bank.openAccount(name);
	}
	
	public void closeAccount(Bank bank){
			asset += bank.closeAccount(getAccountNumber(bank));
	}
	
	public boolean deposit(Bank bank, int money){ // 예금
		if(money > asset)
			return false;
		asset -= money;
		if(!bank.deposit(getAccountNumber(bank), money))
			System.out.println("입금 실패!");
		return true;
	}
	
	public boolean withdraw(Bank bank, int money){
		if(money <= 0){
			System.out.println("출금 실패!");
			return true;
		}
		else if(!bank.withdraw(getAccountNumber(bank), money))
			return false;
		
		asset += money; // 출금에 성공했을때만 현재자산이 변화함
		return true;
	}
	
	public void loan(Bank bank, int money){ // money : 빌릴 돈
		bank.loan(getAccountNumber(bank), money);
		this.asset += money;
	}
	
	public boolean repay(Bank bank, int money){
		if(asset < money)
			return false;
		bank.repay(getAccountNumber(bank), money);
		asset -= money;
		return true;
	}
	
	public boolean repayOnAccount(Bank bank, int money){
		return bank.repayOnAccount(getAccountNumber(bank), money);
	}
	
	public boolean transfer(Bank bank, String toAccount, int money){ // 자기 계좌에서 송금
		return bank.transfer(getAccountNumber(bank), toAccount, money);
	}
	
	public void timeLeap(Bank bank){
		bank.timeLeapYear();
	}
	
	/*			getter & setter			*/
	public String getAccountNumber(Bank bank){
		if(bank.getClass() == KBBank.class)
			return KBBankAccountNumber;
		else if(bank.getClass() == NHBank.class)
			return NHBankAccountNumber;
		return WooriBankAccountNumber;
	}
	
	public int getBalance(Bank bank){
		return bank.getBalance(getAccountNumber(bank));
	}
	
	public String getName(){
		return name;
	}
	
	public int getAsset(){
		return asset;
	}
	
	public int getDebt(Bank bank){
		return bank.getDebt(getAccountNumber(bank));
	}
	
	public LinkedList<String> getStateList(Bank bank){
		return bank.getStateList(getAccountNumber(bank));
	}
	
}