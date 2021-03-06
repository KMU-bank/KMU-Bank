/*********************************************************
 * BanKMU - Bank class
 * Description: 은행의 기능들(계좌개설/폐쇠, 입금, 출금, 대출 등)이 정의되어 
 * 있는 클래스로서 MainSequece에서 요청을 받아 명령을 수행한다.
 **********************************************************/
package bank;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import account.*;

public class Bank {

	private HashMap<String, Account> account = Accounts.getInstance().getAccountList();
	public String INIT = ""; // initial account number - 은행 고유 번호
	public double positiveInterest = 0.0;		// 은행별 이자율
	public double negativeInterest = 0.0;		// 은행별 대출 이자율

	public Bank(String INIT, double positiveInterest, double negativeInterest) {
		this.INIT = INIT;
		this.positiveInterest = positiveInterest;
		this.negativeInterest = negativeInterest;
	}

	public String openAccount(String name) {			// 은행 계좌 생성 ( 고유번호 + 난수 )
		String accountNumber = INIT + (new Random().nextInt(89999) + 10000);

		while (true)
			if (account.containsKey(accountNumber))
				accountNumber = INIT + (new Random().nextInt(89999) + 10000);
			else
				break;

		account.put(accountNumber, new Account(accountNumber, name));
		return accountNumber;
	}

	public int closeAccount(String accountNumber) {		// 계좌 삭제
		if (account.get(accountNumber) != null) {
			int restBalance = account.get(accountNumber).getBalance();
			int restDebt = account.get(accountNumber).getDebt();
			account.remove(accountNumber);
			return restBalance - restDebt; // 대출이 더 많을 경우 음수 반환
		}
		return -1; // if no account number
	}

	public boolean deposit(String accountNumber, int money) {		// 예금
		Account selectedAccount = account.get(accountNumber);
		boolean isDone = selectedAccount.deposit(money);
		if (isDone && money > 0)
			selectedAccount.addStateList("입금 : " + money + " 잔액 : " + selectedAccount.getBalance());
		return isDone;
	}

	public boolean withdraw(String accountNumber, int money) {		// 출금
		Account selectedAccount = account.get(accountNumber);
		boolean isDone = selectedAccount.withdraw(money);

		if (isDone)
			selectedAccount.addStateList("출금 : " + money + " 잔액 : " + selectedAccount.getBalance());

		return isDone;
	}// false -> stay client money & print out on console

	public boolean transfer(String from, String to, int money) {	// 이체
		try {
			Account fromAccount = account.get(from);	// 보내는 계좌
			Account toAccount = account.get(to);		// 받는 계좌

			boolean isDone = fromAccount.withdraw(money);	// 보내는 계좌에서 금액이 감소합니다.
			
			if (!isDone)
				return false;
			
			toAccount.deposit(money);		// 받는 계좌의 금액이 증가합니다.

			// 거래 내역을 기록합니다.
			fromAccount.addStateList("이체한 금액 : " + money + " 이체 계좌번호 : " + to + " 잔액 : " + fromAccount.getBalance());
			toAccount.addStateList("이체된 금액 : " + money + " 이체 계좌번호 : " + from + " 잔액 : " + toAccount.getBalance());

		} catch (Exception e) {		// ERROR
			System.out.println("없는 계좌번호 입니다.");
			return false;
		}
		return true;
	}

	public boolean loan(String accountNumber, int money) {			// 대출
		if (!account.get(accountNumber).loan(money))				// 대출 조건(양수의 대출금액)을 충족시키지 못했을때 FALSE
			return false;
		account.get(accountNumber)
				.addStateList("대출 금액 : " + money + " 남은 대출금 : " + account.get(accountNumber).getDebt());
		return true;
	}

	public boolean repayOnAccount(String accountNumber, int money) {	// 걔좌를 통한 대출 상환
		Account selectedAccount = account.get(accountNumber);

		if(!selectedAccount.repayOnAccount(money))
			return false;
		
		selectedAccount.addStateList("대출상환금액 : " + money + " 남은 대출금 : " + selectedAccount.getDebt() + "은행잔고 : "
				+ selectedAccount.getBalance());
		return true;
	}

	public boolean repay(String accountNumber, int money) {				// 현금 대출 상환
		Account selectedAccount = account.get(accountNumber);
		boolean isDone = selectedAccount.repay(money);
		if(!isDone)
			return false;
		
		selectedAccount.addStateList("대출상환금 : " + money + " 남은 대출금 : " + selectedAccount.getDebt());
		return true;
	}

	//
	//
	// ------------------- getter & setter --------------------------
	public int getBalance(String accountNumber) {
		return account.get(accountNumber).getBalance();
	}

	public int getDebt(String accountNumber) {
		return account.get(accountNumber).getDebt();
	}
	
	public LinkedList<String> getStateList(String accountNumber) {
		return account.get(accountNumber).getStateList();
	}

	public void timeLeapYear() {		// 해당 은행의 모든 계좌의 예금, 대출에 대한 이자를 계산합니다.
		for (String key : account.keySet()) {
			Account selectedAccount = account.get(key);

			if (selectedAccount.getBalance() != 0) {
				int interest = (int) (selectedAccount.getBalance() * positiveInterest);
				selectedAccount.deposit(interest);
				selectedAccount.addStateList("예금 이자 : " + interest + " 잔액 : " + selectedAccount.getBalance());
			}

			if (selectedAccount.getDebt() != 0) {
				int debt = (int) (selectedAccount.getDebt() * negativeInterest);
				selectedAccount.loan(debt);
				if (selectedAccount.getDebt() != 0)
					selectedAccount.addStateList("대출 이자 : " + debt + " 남은 대출 : " + selectedAccount.getDebt());
			}
		}
	}
}
