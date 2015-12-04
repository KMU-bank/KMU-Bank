package client;

import java.util.LinkedList;

import bank.*;

public class Client {
	private int asset; // 은행에 입금하지 않은 소지금
	private String name;
	private Bank bank;
	private String selectedAccount;
	private String[] accountNumbers = new String[3];
	
	public Client(String Name){ // 생성자
		name = Name;
		asset = 2000000;	//	기본 소지금
		selectedAccount = "0";
		accountNumbers[0] = "0";
		accountNumbers[1] = "0";
		accountNumbers[2] = "0";
	}
	
	public void selectBank(Bank banks){ // 은행 선택
		bank = banks;
		
		if(bank.getClass() == KBBank.class)
			selectedAccount = accountNumbers[0];
		else if(bank.getClass() == NHBank.class)
			selectedAccount = accountNumbers[1];
		else if(bank.getClass() == WooriBank.class)
			selectedAccount = accountNumbers[2];
	}
	
	public void openAccount(){
		selectedAccount = bank.openAccount(name);
		
		if(bank.getClass() == KBBank.class)
			accountNumbers[0] = selectedAccount;
		else if(bank.getClass() == NHBank.class)
			accountNumbers[1] = selectedAccount;
		else if(bank.getClass() == NHBank.class)
			accountNumbers[2] = selectedAccount;
	}
	
	public void closeAccount(){
		asset += bank.closeAccount(selectedAccount); // 계좌를 폐쇄하면 
	}
	
	public boolean deposit(int money){ // 예금
		if(money > asset)
			return false;
		asset -= money;
		bank.deposit(selectedAccount, money);
		return true;
	}
	
	public boolean withdraw(int money){
		if(bank.withdraw(selectedAccount, money)){
			asset += money; // 출금에 성공했을때만 현재자산이 변화함
			return true;
		} else{
			return false;
		}
	}
	
	public void loan(int money){ // money : 빌릴 돈
		bank.loan(selectedAccount, money);
		this.asset += money;
	}
	
	public void repay(int money){
		bank.repay(selectedAccount, money);
		asset -= money;
	}
	
	public void repayOnAccount(int money){
		bank.repayOnAccount(selectedAccount, money);
	}
	
	public void transfer(String selectedAccount, int money){ // 자기 계좌에서 송금
		bank.transfer(this.selectedAccount, selectedAccount, money);
	}
	
	public void timeLeap(){
		bank.timeLeapYear();
	}
	
	/*			getter & setter			*/
	public String getAccountNumber(){ // 계좌번호 반환
		return selectedAccount;
	}
	
	public int getBalance(){
		return bank.getBalance(selectedAccount);
	}
	
	public String getName(){
		return name;
	}
	
	public int getAsset(){
		return asset;
	}
	
	public int getDebt(){
		return bank.getDebt(selectedAccount);
	}
	
	public LinkedList<String> getStateList(){
		return bank.getStateList(selectedAccount);
	}
	
	public void deleteBank(){
		bank.closeAccount(selectedAccount);
		selectedAccount = "0";
	}

}