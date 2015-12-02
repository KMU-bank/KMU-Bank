package account;

import java.util.LinkedList;

public class Account {
	private String accountNumber;
	private String name;
	private int balance;
	private boolean haveCard = false;
	private LinkedList<String> stateList = new LinkedList<String>();
	private int debt = 0;
	private boolean stolen = false;

	public Account(String accountNumber, String name){
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = 0;
	}
	
	public String getName(){
		return name;
	}
	
	public int getBalance(){
		return balance;
	}
	
	public int getDebt(){
		return debt;
	}
	
	public LinkedList<String> getStateList(){
		return stateList;
	}
	
	public void deposit(int money){
		balance += money;
		stateList.add("입금 : " +  money + " 잔고 : " + balance);
	}
	
	public boolean withdraw(int money){
		if(balance < money)
			return false;
		balance -= money;
		stateList.add("출금 : " +  money + " 잔고 : " + balance);
		return true;
	}
	
	public void transfer(String accountNumber, int money){
		withdraw(money);
		stateList.add("계좌번호 : " + accountNumber + "  " + money + " 잔고 : " + balance);
	}
	
	public void loan(int money){
		debt += money;
	}      
	
	public void repay(int money){
			debt -= money;
	}
	
	public void repayOnAccount(int money){
		if(withdraw(money))
			debt -= money;
	}
	
	public boolean makeCard(){
		if(haveCard)
			return false;	//	if already have card
		
		haveCard = true;
		return true;
	}
	
	public void lostReport(){
		stolen = true;
	}
	
	public void findReport(){
		stolen = false;
	}
	
	public boolean useCard(String state, int money){
		if(!haveCard || stolen)
			return false;
		
		balance -= money;
		stateList.add(state + " : " + money);
		return true;
	}
}
