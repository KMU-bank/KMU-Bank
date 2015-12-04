package mainsequence;

import java.io.IOException;
import java.util.Scanner;

import client.Clients;
import view.View;

public class MainSequence {
	View view = new View();
	Clients clients = Clients.getInstance();
	Scanner sc = new Scanner(System.in);

	public static void main(String args[]){
		MainSequence seq = new MainSequence();
		seq.firstSequence();
	}
	
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
					break;
				case 2:
					view.screenClear();
					view.title();
					view.deleteUser(clients.clientsList);
					clients.deleteClient(sc.nextInt());
					break;
				case 3:
					view.screenClear();
					view.title();
					clientSelectSequance();
				}
			} catch (Exception e) {
				System.out.println("숫자만 입력하세요.");
				sc.next();
			}
		} // while
	}

//----------------------------------------------------- 시작화면 -> 3.사용자 선택화면
	public void clientSelectSequance() {
		view.selectUser(clients.clientsList);
		try {
			int select = sc.nextInt();

			if (select == 0) // 0은 뒤로가기
				firstSequence();
			else {
				view.screenClear();
				view.title();				
				clients.selectClient(select);
				bankSelectSeq();
			}
		} catch (Exception e) {
			System.out.println("숫자만 입력하세요.");
			sc.next();
			clientSelectSequance();
		}
	}

//----------------------------------------------------- 시작화면 -> 3.사용자 선택 -> 은행선택 화면
	public void bankSelectSeq() {
		view.selectBank();
		try {
			int selectedBank = sc.nextInt();

			if (selectedBank == 0){
				view.screenClear();
				view.title();
				clientSelectSequance();
			}
			else{
				view.screenClear();
				view.title();
				clients.selectBank(selectedBank);
			}

			if (clients.haveAccount()){
				view.screenClear();
				view.title();
				bankingSeq();
			}
			else{
				view.screenClear();
				view.title();
				accountErrorSeq();
			}
		} catch (Exception e) {
			System.out.println("숫자만 입력하세요.");
			sc.next();
			bankSelectSeq();
		}
	}

//----------------------------------------------------- 계좌 생성시 y/n 선택
	public void accountErrorSeq() {
		view.noAccount();
		String select = sc.next();

		if (select.equals("n")){
			view.screenClear();
			view.title();
			bankSelectSeq();
		}
		else if (select.equals("y")) {
			view.screenClear();
			view.title();
			view.createAccount();
			clients.selectedClient.openAccount();
			bankingSeq();
		} else {
			System.out.println("n 또는 y 를 입력하세요.");
			accountErrorSeq();
		}
	}

//----------------------------------------------------- 시작화면 -> 3.사용자 선택 -> 은행선택 -> 뱅킹 매뉴
	public void bankingSeq() {
		while (true) {
			view.banking();
			try {
				int select = Integer.parseInt(sc.next());
				switch (select) {
				case 0:
					view.screenClear();
					view.title();
					bankSelectSeq();
					break;
				case 1:
					view.screenClear();
					view.title();
					depositSeq();
					break;
				case 2:
					view.screenClear();
					view.title();
					withdrawSeq();
					break;
				case 3:
					view.screenClear();
					view.title();
					transferSeq();
					break;
				case 4:
					view.screenClear();
					view.title();
					printStateSeq();
					break;
				case 5:
					view.screenClear();
					view.title();
					loanSeq();
					break;
				case 6:
					view.screenClear();
					view.title();
					repaySeq();
					break;
				case 7:
					view.screenClear();
					view.title();
					deleteBank();
				case 8:
					view.screenClear();
					view.title();
					timeLeapSeq();
				}
			} catch (Exception e) {
				System.out.println("숫자만 입력하세요.");
				bankingSeq();
			}
		}
	}

	public void depositSeq() {
		view.deposit();
		try {
			int money = sc.nextInt();
			clients.selectedClient.deposit(money);
			view.screenClear();
			view.title();
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
			view.screenClear();
			view.title();
			if(!clients.selectedClient.withdraw(money)){
				System.out.println("금액부족으로 출금에 실패했습니다");
			}
			view.currentBalance(clients.selectedClient.getBalance());
			try {
				System.out.println("확인 후 아무 키나 누르시오");
				System.in.read();
			} catch (IOException e) {}
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
		System.out.println("계좌번호 : " + clients.selectedClient.getAccountNumber());
		view.printStateList(clients.selectedClient.getStateList());
		try {
			System.out.println("확인 후 아무 키나 누르시오");
			System.in.read();
			view.screenClear();
			view.title();
		} catch (IOException e) {}
	}

	public void loanSeq() {
		view.loan();
		try {
			int money = sc.nextInt();
			clients.selectedClient.loan(money);
			view.screenClear();
			view.title();
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
			view.screenClear();
			view.title();
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
