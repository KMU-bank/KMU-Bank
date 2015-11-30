package client;

import java.util.LinkedList;

import bank.Bank;

public class Client {
	int asset; // 은행에 입금하지 않은 소지금
	String name;
	Bank bank;
	String accountNumber;
	
	public Client(String Name){ // 생성자
		name = Name;
		accountNumber = "0";
	}
	
	public void setAccountNumber(String Num){
		accountNumber = Num;
	}
	public String getAccountNumber(){ // 계좌번호 반환
		return accountNumber;
	}
	public void openAccount(){
		accountNumber = bank.openAccount(name);
	}
	public void closeAccount(){
		asset += bank.closeAccount(accountNumber); // 계좌를 폐쇄하면 
	}
	public void deposit(int money){ // 예금
		if(money < asset){
			System.out.println("금액이 부족합니다.");
			return;
		}
		asset -= money;
		bank.deposit(accountNumber, money);
	}
	public void withdraw(int money){
		if(bank.withdraw(accountNumber, money)){
			asset += money;
		} // 출금에 성공했을때만 현재자산이 변화함
	}
	public int getBalance(){
		return bank.getBalance(accountNumber);
	}
	public void transfer(int money, String accountNumber){ // 자기 계좌에서 송금
		bank.transfer(this.accountNumber, accountNumber, money);
	}
	public void loan(int money){ // money : 빌릴 돈
		bank.loan(accountNumber, money);
	}
	
	public void repayOnAccount(int money){
		bank.repayOnAccount(accountNumber, money);
	}
	public void repay(int money){
		bank.repay(accountNumber, money);
	}
	
	public void selectBank(Bank banks){ // 은행 선택
		bank = banks;
	}
	public String getName(){
		return name;
	}
	public int getAsset(){
		return asset;
	}
	public LinkedList<String> getStateList(){
		return bank.getStateList(accountNumber);
	}
	public int getDebt(){
		return bank.getDebt(accountNumber);
	}
	public void timeLeap(){
		bank.timeLeapYear();
	}
}