/*********************************************************
 * BanKMU - Client class
 * Description: 사용자의 정보(이름 등)을 저장하고 Client의 행동이 정의된 클래스.
 * 콘솔로 입력한 행동을 Client가 Bank에 요청한다는 개념이기에 은행의 각 기능마다 함수가
 * 정의되더 있다.
 **********************************************************/
package client;

import java.io.Serializable;
import java.util.LinkedList;

import bank.*;

@SuppressWarnings("serial")
public class Client implements Serializable{
	private int asset; 							// 은행에 입금하지 않은 소지금
	private String name;						// client name
	private String KBBankAccountNumber;			// each bank's account number
	private String NHBankAccountNumber;
	private String WooriBankAccountNumber;

	public Client(String Name) { 				// 생성자
		name = Name;
		asset = 3000000;						// 기본 소지금
		KBBankAccountNumber = "0";
		NHBankAccountNumber = "0";
		WooriBankAccountNumber = "0";
	}

	public String openAccount(Bank bank) {		// 은행별 계좌 생성
		if (bank.getClass() == KBBank.class)
			return KBBankAccountNumber = bank.openAccount(name);
		else if (bank.getClass() == NHBank.class)
			return NHBankAccountNumber = bank.openAccount(name);
		else
			return WooriBankAccountNumber = bank.openAccount(name);
	}

	public int closeAccount(Bank bank) {		// 은행별 계좌 삭제
		int rest = bank.closeAccount(getAccountNumber(bank));
		asset += rest;
		
		if (bank.getClass() == KBBank.class)
			KBBankAccountNumber = "0";
		else if (bank.getClass() == NHBank.class)
			NHBankAccountNumber = "0";
		else
			WooriBankAccountNumber = "0";
		
		return rest;
	}

	public boolean deposit(Bank bank, int money) {		// 예금
		if (money > asset)
			return false;
		asset -= money;
		if (!bank.deposit(getAccountNumber(bank), money))
			System.out.println("입금 실패!");
		return true;
	}

	public boolean withdraw(Bank bank, int money) {		// 출금
		if (money <= 0) {
			System.out.println("출금 실패!");
			return true;
		} else if (!bank.withdraw(getAccountNumber(bank), money))
			return false;

		asset += money; // 출금에 성공했을때만 현재자산이 변화함
		return true;
	}

	public boolean loan(Bank bank, int money) { 			// 대출
		if(bank.loan(getAccountNumber(bank), money)){
			this.asset += money;
			return true;
		}
		return false;
	}

	public boolean repay(Bank bank, int money) {			// 대출 상환 - 현금
		if (asset < money)
			return false;
		bank.repay(getAccountNumber(bank), money);
		asset -= money;
		return true;
	}

	public boolean repayOnAccount(Bank bank, int money) {	// 대출 상환 - 계좌
		return bank.repayOnAccount(getAccountNumber(bank), money);
	}

	public boolean transfer(Bank bank, String toAccount, int money) { // 자기 계좌에서 송금
		return bank.transfer(getAccountNumber(bank), toAccount, money);
	}

	public void timeLeap(Bank bank) {						// 시간이동! 도와줘! 돈데크만!
		bank.timeLeapYear();
	}

	//
	//
	// ------------------------ getter & setter ---------------------------
	public String getAccountNumber(Bank bank) {
		if (bank.getClass() == KBBank.class)
			return KBBankAccountNumber;
		else if (bank.getClass() == NHBank.class)
			return NHBankAccountNumber;
		return WooriBankAccountNumber;
	}

	public int getBalance(Bank bank) {
		return bank.getBalance(getAccountNumber(bank));
	}

	public String getName() {
		return name;
	}

	public int getAsset() {
		return asset;
	}

	public int getDebt(Bank bank) {
		return bank.getDebt(getAccountNumber(bank));
	}

	public LinkedList<String> getStateList(Bank bank) {
		return bank.getStateList(getAccountNumber(bank));
	}

}