package mainsequence;

import java.util.Scanner;

import account.*;
import bank.*;
import client.*;
import data.FileIO;
import view.View;

public class MainSequence {
	private final Scanner sc = new Scanner(System.in);

	Client selectedClient;
	Bank selectedBank;
	Account selectedAccount;

	public void firstSequence() {
		while (true) {
			FileIO.backUpClientsOnFile(Clients.getInstance().getClientList());
			FileIO.backUpAccountsOnFile(Accounts.getInstance().getAccountList());
			View.screenClear();
			View.title();
			View.initialPage();

			try {
				int select = sc.nextInt();

				switch (select) {
				case 1:																// 사용자 생성
					View.screenClear();
					View.title();
					View.createUser();
					Clients.getInstance().createClient(sc.next());
					sc.nextLine();
					break;
				case 2:																// 사용자 삭제
					View.screenClear();
					View.title();
					View.deleteUser(Clients.getInstance().getClientList());
					Clients.getInstance().deleteClient(sc.nextInt());
					break;
				case 3:																// 사용자 선택
					clientSelectSequance();
				}
			} catch (Exception e) {
				sc.nextLine();
				View.intError();
			}
		} // while
	}

	// ----------------------------------------------------- 시작화면 -> 3.사용자 선택화면
	public void clientSelectSequance() {
		while (true) {
			View.screenClear();
			View.title();
			View.selectUser(Clients.getInstance().getClientList());

			try {
				int select = sc.nextInt();

				if (select == 0)	// 0은 뒤로가기
					break;
				else {				// 사용자 번호 선택
					selectedClient = Clients.getInstance().getClientList().get(select);
					bankSelectSeq();
				}
				break;
			} catch (Exception e) {
				sc.nextLine();
				View.intError();
			}
		} // while
	}

	// ----------------------------------------------------- 시작화면 -> 3.사용자 선택 ->
	// 은행선택 화면
	public void bankSelectSeq() {
		while (true) {
			View.screenClear();
			View.title(selectedClient);
			View.selectBank();

			try {
				int select = sc.nextInt();

				if (select == 0)
					break;

				selectedBank = findBank(select);
				if (Clients.getInstance().haveAccount(selectedBank, selectedClient))	// 은행 선택
					bankingSeq();
				else																	// 1,2,3,0 외의 잘못된 입력시 ERROR 출력
					accountErrorSeq();

			} catch (Exception e) {
				sc.nextLine();
				View.intError();
			}
		} // while
	}

	// ----------------------------------------------------- 계좌 생성시 y/n 선택
	public void accountErrorSeq() {

		while (true) {
			View.screenClear();
			View.title(selectedClient);
			View.noAccount();

			String select = sc.next();

			if (!select.equals("n") && !select.equals("y")) {					// y/n 외 다른 입력시 경고 메시지 출력
				sc.nextLine();
				System.out.println("n 또는 y 를 입력하세요.");
				View.pressEnter();
			} else if (select.equals("n")) {									// n 입력, 뒤로 돌아감
				break;
			} else if (select.equals("y")) {									// y 입력, 계좌 생성 후 뱅킹 메뉴로 이동
				View.createAccount(selectedClient.openAccount(selectedBank));
				bankingSeq();
				break;
			}
		} // while
	}

	// ----------------------------------------------------- 시작화면 -> 3.사용자 선택 ->
	// 은행선택 -> 뱅킹 매뉴
	public void bankingSeq() {
		loop: while (true) {
			FileIO.backUpClientsOnFile(Clients.getInstance().getClientList());
			FileIO.backUpAccountsOnFile(Accounts.getInstance().getAccountList());

			View.screenClear();
			View.title(selectedClient, selectedBank);
			View.banking();

			try {
				int select = Integer.parseInt(sc.next());

				switch (select) {
				case 0:
					break loop;
				case 1:						// 입금
					depositSeq();
					break;
				case 2:						// 출금
					withdrawSeq();
					break;
				case 3:						// 이체
					transferSeq();
					break;
				case 4:						// 조회
					printStateSeq();
					break;
				case 5:						// 대출
					loanSeq();
					break;
				case 6:						// 대출 상환
					repaySeq();
					break;
				case 7:						// 계좌 삭제
					deleteBankSeq();
					break loop;
				case 8:						// 1년 시간이동
					timeLeapSeq();
				}
			} catch (Exception e) {
				sc.nextLine();
				View.intError();
				bankingSeq();
			}
		} // while

		FileIO.backUpClientsOnFile(Clients.getInstance().getClientList());
		FileIO.backUpAccountsOnFile(Accounts.getInstance().getAccountList());

	}

	// ----------------------------------------------------- 시작화면 -> 3.사용자 선택 -> 은행선택 ->
	// 뱅킹 매뉴 -> 입금
	public void depositSeq() {
		while (true) {
			View.screenClear();
			View.title(selectedClient, selectedBank);
			View.deposit();

			try {
				int money = sc.nextInt();
				if (money == 0)		// 0은 언제나 취소
					break;
				
				int errorCode = selectedClient.deposit(selectedBank, money); 
				if (errorCode == 1)	// 현금이 입금액보다 부족할 시 ERROR
					View.notEnoughAssetError();
				else if(errorCode == 2)
					System.out.println("입금 실패!");

				View.currentBalance(selectedClient.getBalance(selectedBank));
				View.pressEnter();
				break;
			} catch (Exception e) {
				sc.nextLine();
				View.intError();
			}
		} // while
	}

	// ----------------------------------------------------- 시작화면 -> 3.사용자 선택 -> 은행선택 ->
	// 뱅킹 매뉴 -> 출금
	public void withdrawSeq() {
		while (true) {
			View.screenClear();
			View.title(selectedClient, selectedBank);
			View.withdraw();

			try {
				int money = sc.nextInt();
				if (money == 0)		// 0은 언제나 취소
					break;
				
				int errorCode = selectedClient.withdraw(selectedBank, money);
				
				if (errorCode == 1)		// 출금할 계좌 잔금이 출금액보다 적을시 ERROR
					View.notEnoughBalanceError();
				else if(errorCode == 2)
					System.out.println("출금 실패!");

				View.currentBalance(selectedClient.getBalance(selectedBank));
				View.pressEnter();
				break;
			} catch (Exception e) {
				sc.nextLine();
				View.intError();
			}
		} // while
	}

	// ----------------------------------------------------- 시작화면 -> 3.사용자 선택 -> 은행선택 ->
	// 뱅킹 매뉴 -> 이체
	public void transferSeq() {
		while (true) {
			View.screenClear();
			View.title(selectedClient, selectedBank);
			View.transferAccountNumber();

			String accountNumber = sc.next();

			if (accountNumber.equals(selectedClient.getAccountNumber(selectedBank))) {		// 이체시 자기 계좌에서 자기 계좌로의 이체는 불가능 합니다.
				System.out.println("자신의 계좌로 입금할 수 없습니다.");
				View.pressEnter();
				break;
			} else if (accountNumber.equals("-1")) {		// 본래는 계좌번호를 따로 알아야 하지만, 편의상 현제 개설된 사용자-계좌 를 확인 할 수 있는 커멘드입니다.
				View.printAccountNumber();
				accountNumber = sc.next();
			} // 계좌번호를 모를때 모든 client의 계좌번호를 출력한다

			if (accountNumber.equals("0"))		// 0은 언제나 취소
				break;

			try {
				View.transferMoney();

				int money = sc.nextInt();
				if (money == 0)			// 0은 언제나 취소
					break;

				if (!selectedClient.transfer(selectedBank, accountNumber, money))	// 이체시 자신의 계좌 잔금이 송금할 금액보다 적을시 ERROR
					View.notEnoughBalanceError();

				View.currentBalance(selectedClient.getBalance(selectedBank));
				View.pressEnter();
				break;
			} catch (Exception e) {
				sc.nextLine();
				View.intError();
			}
		} // while
	}

	// ----------------------------------------------------- 시작화면 -> 3.사용자 선택 -> 은행선택 ->
	// 뱅킹 매뉴 -> 조회
	public void printStateSeq() {
		View.printStateList(selectedClient.getStateList(selectedBank));
		View.pressEnter();
	}

	// ----------------------------------------------------- 시작화면 -> 3.사용자 선택 -> 은행선택 ->
	// 뱅킹 매뉴 -> 대출
	public void loanSeq() {
		while (true) {
			View.screenClear();
			View.title(selectedClient, selectedBank);
			View.loan();

			try {
				int money = sc.nextInt();
				if (money == 0)			// 0은 언제나 취소
					break;

				if (selectedClient.loan(selectedBank, money)) {			// 대출시 대출 금 만큼 현금과 부체가 증가합니다.
					View.currentDebt(selectedClient.getDebt(selectedBank));
					View.pressEnter();
					break;
				}

				View.unvalidInput();

			} catch (Exception e) {
				sc.nextLine();
				View.intError();
			}
		} // while
	}

	// ----------------------------------------------------- 시작화면 -> 3.사용자 선택 -> 은행선택 ->
	// 뱅킹 매뉴 -> 대출 상환
	public void repaySeq() {
		while (true) {
			View.screenClear();
			View.title(selectedClient, selectedBank);
			View.repay();

			try {
				int money = sc.nextInt();
				if (money == 0)			// 0은 언제나 취소
					break;

				View.selectAssetOrAccount();
				int select = sc.nextInt();
				if (select == 0)		// 0은 언제나 취소
					break;
				else if (select == 1 && !selectedClient.repay(selectedBank, money))				// 부체상환 방법으로 현금을 선택합니다.
					View.notEnoughAssetError();
				else if (select == 2 && !selectedClient.repayOnAccount(selectedBank, money))	// 부체상환 방법으로 계좌를 선택합니다.
					View.notEnoughBalanceError();												// 상환시 해당 방법의 잔액이 상환금액보다 적을시 ERROR
				else{
					View.unvalidInput();		// 상환한 금액을 확인 시켜주고 Enter를 칠때까지 대기합니다.
					View.pressEnter();
					break;
				}

				View.currentDebt(selectedClient.getDebt(selectedBank));
				View.pressEnter();
				break;
			} catch (Exception e) {
				sc.nextLine();
				View.intError();
			}
		} // while
	}

	// ----------------------------------------------------- 시작화면 -> 3.사용자 선택 -> 은행선택 ->
	// 뱅킹 매뉴 -> 1년 이동
	public void timeLeapSeq() {
		View.screenClear();
		View.title(selectedClient, selectedBank);		// 현실시간과 연동 돼진 않았지만, 시간의 흐름에 따른 이자도 구현해냈습니다.
		View.timeLeap();

		selectedClient.timeLeap(selectedBank);
		View.pressEnter();
	}

	// ----------------------------------------------------- 시작화면 -> 3.사용자 선택 -> 은행선택 ->
	// 뱅킹 매뉴 -> 계좌 삭제
	public void deleteBankSeq() {
		View.deleteAccount(selectedClient.closeAccount(selectedBank));		// 계좌를 삭제합니다. 계좌 잔액과 미상환 대출금이 있다면 계산되어 현금으로 들어갑니다.
		View.pressEnter();
	}

	// ----------------------------------------------------- 시작화면 ->
	// 사용자 선택 -> 은행선택
	public Bank findBank(int select) {
		if (select == 1)				// 은행 KB 를 선택합니다
			return new KBBank();
		else if (select == 2)			// 은행 NH 를 선택합니다
			return new NHBank();
		else							// 은행 Woori 를 선택합니다
			return new WooriBank();
	}

	// ----------------------------------------------------- 시작화면 - 메인 시퀀스 시작
	public static void main(String args[]) {
		FileIO.restoreClientsFromFile(Clients.getInstance().getClientList());
		FileIO.restoreAccountsFromFile(Accounts.getInstance().getAccountList());

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				FileIO.backUpClientsOnFile(Clients.getInstance().getClientList());
				FileIO.backUpAccountsOnFile(Accounts.getInstance().getAccountList());
			}
		}); // 비정상적인 프로그램 종료가 일어날 때 DB에 write한 후 종료한다.

		MainSequence seq = new MainSequence();
		seq.firstSequence();
	}
}