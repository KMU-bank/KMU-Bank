package mainsequence;

import java.io.IOException;
import java.util.Scanner;

import client.Clients;
import view.View;

public class MainSequence {
	View view = new View();
	Clients clients = Clients.getInstance();
	Scanner sc = new Scanner(System.in);

	public void firstSequence() {
		while (true) {
			view.title();
			view.initialPage();
			try {
				int select = sc.nextInt();
				
				switch (select) {
				case 1:
					view.createUser();
					clients.createClient(sc.next());
					break;
				case 2:
					view.deleteUser(clients.clientsList);
					clients.deleteClient(sc.nextInt());
					break;
				case 3:
					clientSelectSequance();
				}
			} catch (Exception e) {
				System.out.println("숫자만 입력하세요.");
				sc.next();
			}
		} // while
	}

	public void clientSelectSequance() {
		view.selectUser(clients.clientsList);
		try {
			int select = sc.nextInt();

			if (select == 0) // 0은 뒤로가기
				firstSequence();
			else {
				clients.selectClient(select);
				bankSelectSeq();
			}
		} catch (Exception e) {
			System.out.println("숫자만 입력하세요.");
			sc.next();
			clientSelectSequance();
		}
	}

	public void bankSelectSeq() {
		view.selectBank();
		try {
			int selectedBank = sc.nextInt();

			if (selectedBank == 0)
				clientSelectSequance();
			else
				clients.selectBank(selectedBank);

			if (clients.haveAccount())
				bankingSeq();
			else
				accountErrorSeq();
		} catch (Exception e) {
			System.out.println("숫자만 입력하세요.");
			sc.next();
			bankSelectSeq();
		}
	}

	public void accountErrorSeq() {
		view.noAccount();
		String select = sc.next();

		if (select.equals("n"))
			bankSelectSeq();
		else if (select.equals("y")) {
			view.createAccount();
			clients.selectedClient.openAccount();
			bankingSeq();
		} else {
			System.out.println("n 또는 y 를 입력하세요.");
			accountErrorSeq();
		}
	}

	public void bankingSeq() {
		while (true) {
			view.banking();
			try {
				int select = sc.nextInt();

				switch (select) {
				case 0:
					firstSequence();
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
				System.out.println("숫자만 입력하세요.");
				sc.next();
				bankingSeq();
			}
		}
	}

	public void depositSeq() {
		view.deposit();
		try {
			int money = sc.nextInt();
			clients.selectedClient.deposit(money);
			view.currentBalance(clients.selectedClient.getBalance());
		} catch (Exception e) {
			System.out.println("숫자만 입력하세요.");
			sc.next();
			depositSeq();
		}
	}

	public void withdrawSeq() {
		view.withdraw();
		try {
			int money = sc.nextInt();
			clients.selectedClient.withdraw(money);
			view.currentBalance(clients.selectedClient.getBalance());
		} catch (Exception e) {
			System.out.println("숫자만 입력하세요.");
			sc.next();
			withdrawSeq();
		}
	}

	public void transferSeq() {
		while (true) {
				view.transferAccountNumber();
				String accountNumber = sc.next();
				try {
				view.transferMoney();
				int money = sc.nextInt();
				clients.selectedClient.transfer(accountNumber, money);
				break;
			} catch (Exception e) {
				System.out.println("숫자만 입력하세요.");
				sc.next();
			}
		}
	}

	public void printStateSeq() {
		view.printStateList(clients.selectedClient.getStateList());
		System.out.println("확인 후 아무 키나 누르시오");
		try {
			System.in.read();
		} catch (IOException e) {}
	}

	public void loanSeq() {
		view.loan();
		try {
			int money = sc.nextInt();
			clients.selectedClient.loan(money);
			view.currentDebt(clients.selectedClient.getDebt());
		} catch (Exception e) {
			System.out.println("숫자만 입력하세요.");
			sc.next();
			loanSeq();
		}
	}

	public void repaySeq() {
		view.repay();
		try {
			int money = sc.nextInt();
			clients.selectedClient.repay(money);
			view.currentDebt(clients.selectedClient.getDebt());
		} catch (Exception e) {
			System.out.println("숫자만 입력하세요.");
			sc.next();
			repaySeq();
		}
	}

	public void timeLeapSeq() {
		view.timeLeap();
		clients.selectedClient.timeLeap();
	}
	public void deleteBank(){
		clients.selectedClient.deleteBank();
		System.out.println("해당 은행의 계좌가 삭제되었습니다");
		bankSelectSeq();
	}
}
