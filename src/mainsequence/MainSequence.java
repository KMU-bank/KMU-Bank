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
				case 1:
					View.screenClear();
					View.title();
					View.createUser();
					Clients.getInstance().createClient(sc.next());
					sc.nextLine();
					break;
				case 2:
					View.screenClear();
					View.title();
					View.deleteUser(Clients.getInstance().getClientList());
					Clients.getInstance().deleteClient(sc.nextInt());
					break;
				case 3:
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

				if (select == 0) // 0은 뒤로가기
					break;
				else {
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
				if (Clients.getInstance().haveAccount(selectedBank, selectedClient))
					bankingSeq();
				else
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

			if (!select.equals("n") && !select.equals("y")) {
				sc.nextLine();
				System.out.println("n 또는 y 를 입력하세요.");
				View.pressEnter();
			} else if (select.equals("n")) {
				break;
			} else if (select.equals("y")) {
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
					deleteBankSeq();
					break loop;
				case 8:
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

	public void depositSeq() {
		while (true) {
			View.screenClear();
			View.title(selectedClient, selectedBank);
			View.deposit();

			try {
				int money = sc.nextInt();
				if (money == 0)
					break;
				if (!selectedClient.deposit(selectedBank, money))
					View.notEnoughAssetError();

				View.currentBalance(selectedClient.getBalance(selectedBank));
				View.pressEnter();
				break;
			} catch (Exception e) {
				sc.nextLine();
				View.intError();
			}
		} // while
	}

	public void withdrawSeq() {
		while (true) {
			View.screenClear();
			View.title(selectedClient, selectedBank);
			View.withdraw();

			try {
				int money = sc.nextInt();
				if (money == 0)
					break;
				if (!selectedClient.withdraw(selectedBank, money))
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

	public void transferSeq() {
		while (true) {
			View.screenClear();
			View.title(selectedClient, selectedBank);
			View.transferAccountNumber();

			String accountNumber = sc.next();

			if (accountNumber.equals(selectedClient.getAccountNumber(selectedBank))) {
				System.out.println("자신의 계좌로 입금할 수 없습니다.");
				View.pressEnter();
				break;
			} else if (accountNumber.equals("-1")) {
				View.printAccountNumber();
				accountNumber = sc.next();
			} // 계좌번호를 모를때 모든 client의 계좌번호를 출력한다

			if (accountNumber.equals("0"))
				break;

			try {
				View.transferMoney();

				int money = sc.nextInt();
				if (money == 0)
					break;

				if (!selectedClient.transfer(selectedBank, accountNumber, money))
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

	public void printStateSeq() {
		View.printStateList(selectedClient.getStateList(selectedBank));
		View.pressEnter();
	}

	public void loanSeq() {
		while (true) {
			View.screenClear();
			View.title(selectedClient, selectedBank);
			View.loan();

			try {
				int money = sc.nextInt();
				if (money == 0)
					break;

				if (selectedClient.loan(selectedBank, money)) {
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

	public void repaySeq() {
		while (true) {
			View.screenClear();
			View.title(selectedClient, selectedBank);
			View.repay();

			try {
				int money = sc.nextInt();
				if (money == 0)
					break;

				View.selectAssetOrAccount();
				int select = sc.nextInt();
				if (select == 0)
					break;
				else if (select == 1 && !selectedClient.repay(selectedBank, money))
					View.notEnoughAssetError();
				else if (select == 2 && !selectedClient.repayOnAccount(selectedBank, money))
					View.notEnoughBalanceError();
				else{
					View.unvalidInput();
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

	public void timeLeapSeq() {
		View.screenClear();
		View.title(selectedClient, selectedBank);
		View.timeLeap();

		selectedClient.timeLeap(selectedBank);
		View.pressEnter();
	}

	public void deleteBankSeq() {
		View.deleteAccount(selectedClient.closeAccount(selectedBank));
		View.pressEnter();
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