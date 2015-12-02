package client;

import java.util.LinkedList;

import bank.Bank;

public class Client {
	private int asset; // 은행에 입금하지 않은 소지금
	private String name;
	private Bank bank;
	private String accountNumber;
	
	public Client(String Name){ // 생성자
		name = Name;
		asset = 2000000;	//	기본 소지금
		accountNumber = "0";
	}
		
	public void selectBank(Bank banks){ // 은행 선택
		bank = banks;
	}
	
	public void openAccount(){
		if(bank != null)
			accountNumber = bank.openAccount(name);
	}
	
	public void closeAccount(){
		asset += bank.closeAccount(accountNumber); // 계좌를 폐쇄하면 
	}
	
	public boolean deposit(int money){ // 예금
		if(money > asset)
			return false;
		asset -= money;
		bank.deposit(accountNumber, money);
		return true;
	}
	
	public void withdraw(int money){
		if(bank.withdraw(accountNumber, money)){
			asset += money;
		} // 출금에 성공했을때만 현재자산이 변화함
	}
	
	public void loan(int money){ // money : 빌릴 돈
		bank.loan(accountNumber, money);
		this.asset += money;
	}
	
	public void repay(int money){
		bank.repay(accountNumber, money);
		asset -= money;
	}
	
	public void repayOnAccount(int money){
		bank.repayOnAccount(accountNumber, money);
	}
	
	public void transfer(String accountNumber, int money){ // 자기 계좌에서 송금
		bank.transfer(this.accountNumber, accountNumber, money);
	}
	
	public void timeLeap(){
		bank.timeLeapYear();
	}
	
	/*			getter & setter			*/
	public String getAccountNumber(){ // 계좌번호 반환
		return accountNumber;
	}
	
	public int getBalance(){
		return bank.getBalance(accountNumber);
	}
	
	public String getName(){
		return name;
	}
	
	public int getAsset(){
		return asset;
	}
	
	public int getDebt(){
		return bank.getDebt(accountNumber);
	}
	
	public LinkedList<String> getStateList(){
		return bank.getStateList(accountNumber);
	}

}