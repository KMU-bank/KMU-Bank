package mainsequence;

import java.io.IOException;
import java.util.Scanner;
import account.Account;
import bank.*;
import client.Client;
import client.Clients;
import view.View;

public class MainSequence {
	View view = new View();
	Clients clients = Clients.getInstance();
	Scanner sc = new Scanner(System.in);

	Client selectedClient;
	Bank selectedBank;
	Account selectedAccount;

	public void firstSequence() {
		while (true) {
			view.screenClear();
			view.title();
			view.initialPage();

			try {
				int select = sc.nextInt();

				switch (select) {
				case 1:
					view.screenClear();
					view.title();
					view.createUser();
					clients.createClient(sc.next());
					sc.nextLine();
					break;
				case 2:
					view.screenClear();
					view.title();
					view.deleteUser(clients.clientList);
					clients.deleteClient(sc.nextInt());
					break;
				case 3:
					clientSelectSequance();
				}
			} catch (Exception e) {
				sc.nextLine();
				view.intError();
			}
		} // while
	}

	// ----------------------------------------------------- 시작화면 -> 3.사용자 선택화면
	public void clientSelectSequance() {
		view.screenClear();
		view.title();
		view.selectUser(clients.clientList);

		try {
			int select = sc.nextInt();

			if (select == 0) // 0은 뒤로가기
				firstSequence();
			else {
				selectedClient = clients.clientList.get(select);
				bankSelectSeq();
			}
		} catch (Exception e) {
			sc.nextLine();
			view.intError();
			clientSelectSequance();
		}
	}

	// ----------------------------------------------------- 시작화면 -> 3.사용자 선택 ->
	// 은행선택 화면
	public void bankSelectSeq() {
		view.screenClear();
		view.title(selectedClient);
		view.selectBank();

		try {
			int select = sc.nextInt();

			if (select == 0)
				clientSelectSequance();

			selectedBank = findBank(select);

			if (clients.haveAccount(selectedBank, selectedClient))
				bankingSeq();
			else
				accountErrorSeq();

		} catch (Exception e) {
			sc.nextLine();
			view.intError();
			bankSelectSeq();
		}
	}

	// ----------------------------------------------------- 계좌 생성시 y/n 선택
	public void accountErrorSeq() {
		view.screenClear();
		view.title(selectedClient);
		view.noAccount();
		String select = sc.next();

		if (select.equals("n")) {
			bankSelectSeq();
		} else if (select.equals("y")) {
			view.createAccount(selectedClient.openAccount(selectedBank));
			bankingSeq();
		} else {
			sc.nextLine();
			System.out.println("n 또는 y 를 입력하세요.");
			view.pressEnter();
			accountErrorSeq();
		}
	}

	// ----------------------------------------------------- 시작화면 -> 3.사용자 선택 ->
	// 은행선택 -> 뱅킹 매뉴
	public void bankingSeq() {
		while (true) {
			view.screenClear();
			view.title(selectedClient, selectedBank);
			view.banking();
			try {
				int select = Integer.parseInt(sc.next());
				switch (select) {
				case 0:
					bankSelectSeq();
					break;
				case 1:
					depositSeq();
					break;
				case 2:
					withdrawSeq();
					break;
				case 3:
					transferSeq();
					break;
				case 4:
					printStateSeq();
					break;
				case 5:
					loanSeq();
					break;
				case 6:
					repaySeq();
					break;
				case 7:
					deleteBank();
				case 8:
					timeLeapSeq();
				}
			} catch (Exception e) {
				sc.nextLine();
				view.intError();
				bankingSeq();
			}
		}
	}

	public void depositSeq() {
		view.screenClear();
		view.title(selectedClient, selectedBank);
		view.deposit();
		try {
			int money = sc.nextInt();
			if (!selectedClient.deposit(selectedBank, money))
				view.notEnoughAssetError();
			view.currentBalance(selectedClient.getBalance(selectedBank));
			view.pressEnter();
		} catch (Exception e) {
			sc.nextLine();
			view.intError();
			depositSeq();
		}
	}

	public void withdrawSeq() {
		view.screenClear();
		view.title(selectedClient, selectedBank);
		view.withdraw();
		try {
			int money = sc.nextInt();
			if (!selectedClient.withdraw(selectedBank, money))
				view.notEnoughBalanceError();

			view.currentBalance(selectedClient.getBalance(selectedBank));
			view.pressEnter();
		} catch (Exception e) {
			sc.nextLine();
			view.intError();
			withdrawSeq();
		}
	}

	public void transferSeq() {
		while (true) {
			view.screenClear();
			view.title(selectedClient, selectedBank);
			view.transferAccountNumber();

			String accountNumber = sc.next();
			if (accountNumber.equals(selectedClient.getAccountNumber(selectedBank))) {
				System.out.println("자신의 계좌로 입금할 수 없습니다.");
				view.pressEnter();
				break;
			}

			try {
				view.transferMoney();
				int money = sc.nextInt();
				if (!selectedClient.transfer(selectedBank, accountNumber, money))
					view.notEnoughBalanceError();

				view.currentBalance(selectedClient.getBalance(selectedBank));
				view.pressEnter();
				break;
			} catch (Exception e) {
				sc.nextLine();
				view.intError();
			}
		}
	}

	public void printStateSeq() {
		System.out.println("계좌번호 : " + selectedClient.getAccountNumber(selectedBank));
		view.printStateList(selectedClient.getStateList(selectedBank));
		view.pressEnter();
	}

	public void loanSeq() {
		view.screenClear();
		view.title(selectedClient, selectedBank);
		view.loan();

		try {
			int money = sc.nextInt();
			selectedClient.loan(selectedBank, money);
			view.currentDebt(selectedClient.getDebt(selectedBank));
			view.pressEnter();
		} catch (Exception e) {
			sc.nextLine();
			view.intError();
			loanSeq();
		}
	}

	public void repaySeq() {
		view.screenClear();
		view.title(selectedClient, selectedBank);
		view.repay();

		try {
			int money = sc.nextInt();
			selectedClient.repay(selectedBank, money);
			view.currentDebt(selectedClient.getDebt(selectedBank));
			view.pressEnter();
		} catch (Exception e) {
			sc.nextLine();
			view.intError();
			repaySeq();
		}
	}

	public void timeLeapSeq() {
		view.screenClear();
		view.title(selectedClient, selectedBank);
		view.timeLeap();

		selectedClient.timeLeap(selectedBank);
		view.pressEnter();
	}

	public void deleteBank() {
		view.deleteAccount();

		selectedClient.closeAccount(selectedBank);
		view.pressEnter();
		bankSelectSeq();
	}

	public Bank findBank(int select) {
		if (select == 1)
			return new KBBank();
		else if (select == 2)
			return new NHBank();
		else
			return new WooriBank();
	}

	public static void main(String args[]) {
		MainSequence seq = new MainSequence();
		seq.firstSequence();
	}
}
