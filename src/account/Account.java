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
	private String accountNumber;										// 계좌번호
	private String name;												// 사용자 이름
	private int balance;												// 잔고
	private LinkedList<String> stateList = new LinkedList<String>();	// 거래 내역
	private int debt = 0;												// 부체

	public Account(String accountNumber, String name) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = 0;
	}

	//
	//
	// ----------------------- getter & setter... ------------------------
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
	// --------------------- basic function... ------------------------
	
	public boolean deposit(int money) {		// 입금
		if (money <= 0)
			return false;
		balance += money;
		return true;
	}

	public boolean withdraw(int money) {	// 출금
		if (balance < money || money <= 0)
			return false;
		balance -= money;
		return true;
	}

	public boolean transfer(String accountNumber, int money) {		// 이체
		return withdraw(money);										// 계좌상에서 이체는 출금과 같습니다.
	}

	//
	//
	// ------------------------ loan function --------------------------
	public boolean loan(int money) {		// 대출
		if(money <= 0) 
			return false;
		debt += money;
		return true;
	}

	public boolean repay(int money) {		// 대출 상환 - 현금
		if (debt < money || money <= 0)
			return false;
		debt -= money;
		return true;
	}

	public boolean repayOnAccount(int money) {		// 대출 상환 - 계좌
		if(debt < money)
			return false;
		boolean isDone = withdraw(money);
		if (isDone) 
			debt -= money;
		return isDone;
	}
}
