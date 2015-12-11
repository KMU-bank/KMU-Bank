/*********************************************************
 * BanKMU - Account class
 * Description: account클래스는 Bank클래스가 관리하는 고객의 계좌정보를 담고있다.
 * 계좌내의 정보를 수정/반환할 수 있으며 상환/대출 시 Account정보의 변화가 정의되어 있다 
 **********************************************************/
package account;

import java.io.Serializable;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Account implements Serializable {
	private String accountNumber;
	private String name;
	private int balance;
	private LinkedList<String> stateList = new LinkedList<String>();
	private int debt = 0;

	public Account(String accountNumber, String name) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = 0;
	}

	//
	//
	// getter & setter...
	public String getAccountNumber() {
		return accountNumber;
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}

	public int getDebt() {
		return debt;
	}

	public LinkedList<String> getStateList() {
		return stateList;
	}

	public void addStateList(String state) {
		stateList.add(state);
	}

	//
	//
	// basic function...
	public boolean deposit(int money) {
		if (money <= 0)
			return false;
		balance += money;
		return true;
	}

	public boolean withdraw(int money) {
		if (balance < money || money <= 0)
			return false;
		balance -= money;
		return true;
	}

	public boolean transfer(String accountNumber, int money) {
		return withdraw(money);
	}

	//
	//
	// loan function
	public boolean loan(int money) {
		if(money <= 0) 
			return false;
		debt += money;
		return true;
	}

	public boolean repay(int money) {
		if (debt < money || money <= 0)
			return false;
		debt -= money;
		return true;
	}

	public boolean repayOnAccount(int money) {
		if(debt < money)
			return false;
		boolean isDone = withdraw(money);
		if (isDone)
			debt -= money;
		return isDone;
	}
}
