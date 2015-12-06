package view;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import bank.Bank;
import bank.KBBank;
import bank.NHBank;
import bank.WooriBank;
import client.Client;
import client.Clients;

public class View {

	public static final void OriginalTitle() {
		System.out.println("***************************************************************************");
		System.out.println("*                                                                         *");
		System.out.println("*       ■■■■■■                 ■       ■■ ■           ■ ■         ■       *");
		System.out.println("*       ■     ■                ■     ■■   ■■         ■■ ■         ■       *");
		System.out.println("*       ■     ■                ■   ■■     ■ ■       ■ ■ ■         ■       *");
		System.out.println("*       ■■■■■■   ■■■■   ■■   ■ ■ ■■       ■  ■     ■  ■ ■         ■       *");
		System.out.println("*       ■     ■ ■    ■  ■ ■  ■ ■■  ■■     ■   ■   ■   ■ ■         ■       *");
		System.out.println("*       ■     ■ ■    ■  ■  ■ ■ ■     ■■   ■    ■ ■    ■  ■■     ■■        *");
		System.out.println("*       ■■■■■■   ■■■■ ■ ■   ■■ ■       ■■ ■     ■     ■    ■■■■■          *");
		System.out.println("*                                                                         *");
		System.out.println("***************************************************************************");
	}

	public static final void title() {
		OriginalTitle();
	}

	public static final void title(Client selectedClient) {
		OriginalTitle();
		System.out
				.println("현재 선택된 사용자 : " + selectedClient.getName() + " 현재 선택된 사용자의 자산 : " + selectedClient.getAsset());
		System.out.println("---------------------------------------------------------------------------");
	}

	public static final void title(Client selectedClient, Bank selectedBank) {
		String accountNumber = selectedClient.getAccountNumber(selectedBank);
		OriginalTitle();
		System.out.print("현재 선택된 사용자 : " + selectedClient.getName() + " 현재 선택된 사용자의 자산 : " + selectedClient.getAsset()
				+ " 현재 선택된 계좌의 계좌번호 : " + accountNumber + "\n현재 선택된 은행계좌의 잔고 : "
				+ selectedBank.getBalance(accountNumber));
		if (selectedBank.getDebt(accountNumber) != 0)
			System.out.print(" 현재 선택된 은행계좌의 대출금 : " + selectedBank.getDebt(accountNumber));
		System.out.println("\n---------------------------------------------------------------------------");
	}

	// ------------------------------------------------------------------------------
	// 시작 화면

	public static final void initialPage() {
		System.out.println(" 1. 사용자 생성");
		System.out.println(" 2. 사용자 삭제");
		System.out.println(" 3. 사용자 선택");
		System.out.print("Select Number : ");
	}

	public static final void printUserList(HashMap<Integer, Client> clients) {
		for (int i = 1; i < 100; i++)
			if (clients.get(i) != null)
				System.out.println(i + ". " + clients.get(i).getName());
	}

	public static final void selectUser(HashMap<Integer, Client> clients) {
		System.out.println("사용자 리스트");
		printUserList(clients);
		System.out.print("사용자 선택 : ");
	}

	public static final void createUser() {
		System.out.println("사용자 이름을 입력해주세요.");
		System.out.print(" 사용자 이름 : ");
	}

	// ------------------------------------------------------------------------------
	// 사용자 선택 화면 이후

	public static final void deleteUser(HashMap<Integer, Client> clients) {
		System.out.println("사용자 리스트");
		printUserList(clients);
		System.out.print(" 삭제할 사용자 번호: ");
	}

	public static final void selectBank() {
		System.out.println("은행을 선택해주세요");
		System.out.println("1. KB");
		System.out.println("2. NH");
		System.out.println("3. Woori");
		System.out.print(" Select Number : ");
	}

	public static final void banking() {
		System.out.println("1. 입금");
		System.out.println("2. 출금");
		System.out.println("3. 이체");
		System.out.println("4. 조회(잔액, 대출금, 거래내역)");
		System.out.println("------------------------");
		System.out.println("5. 대출");
		System.out.println("6. 대출 상환");
		System.out.println("------------------------");
		System.out.println("7. 계좌 삭제");
		System.out.println("------------------------");
		System.out.println("8. 1년 후...");
		System.out.print(" Select Number : ");
	}

	// ------------------------------------------------------------------------------
	// 메뉴 함수
	public static final void currentBalance(int balance) {
		System.out.println(" 현재 잔고 : " + balance);
	}

	public static final void currentDebt(int debt) {
		System.out.println("현재 대출 금액 : " + (debt));
		System.out.println("------------------------------");
	}

	public static final void deposit() {
		System.out.print(" 입금할 금액 : ");
	}

	public static final void withdraw() {
		System.out.print(" 출금할 금액 : ");
	}

	public static final void transferAccountNumber() {
		System.out.println("계좌번호를 입력해 주세요.");
		System.out.println("계좌번호를 모르시면 -1을 입력해 주세요.");
		System.out.print("계좌번호 : ");
	}

	public static final void printAccountNumber() {
		for(int key: Clients.getInstance().getClientList().keySet()){
			Client client = Clients.getInstance().getClientList().get(key);
			
			System.out.println("------------------------------");
			System.out.println(client.getName() + "님의 계좌번호 : ");
			System.out.println("KB : " + client.getAccountNumber(new KBBank())
								+ " NH : " + client.getAccountNumber(new NHBank())
								+ " Woori : " + client.getAccountNumber(new WooriBank()));
		}
		System.out.println("------------------------------");
		System.out.print(" 계좌번호 : ");
	}

	public static final void transferMoney() {
		System.out.print("보내실 금액을 입력해주세요.\n 금액 : ");
	}

	public static final void printStateList(LinkedList<String> stateList) {
		System.out.println("거래 내역을 출력합니다.");
		for (int i = 0; i < stateList.size(); i++) {
			System.out.println(stateList.get(i));
		}
	}

	public static final void loan() {
		System.out.println("대출하실 금액을 입력해 주시기 바랍니다.");
		System.out.print(" 대출 금액 : ");
	}

	public static final void repay() {
		System.out.println("상환하실 금액을 입력해 주시기 바랍니다.");
		System.out.print(" 상환 금액 : ");
	}

	public static final void timeLeap() {
		System.out.println("	1년 후로 이동합니다. (푸슝---~=★!)");
	}

	// ------------------------------------------------------------------------------
	// 계정 관련 함수

	public static final void noAccount() {
		System.out.print("	계좌가 존재하지 않습니다.\n 계좌를 생성하시겠습니까? ( y / n ) : ");
	}

	public static final void createAccount(String accountNumber) {
		System.out.println("계좌가 생성되었습니다.");
		System.out.println("계좌번호는 : " + accountNumber + "입니다.");
		System.out.println("확인 후 아무키나 누르세요.");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final void deleteAccount(int bal) {
		System.out.println("계좌를 삭제합니다.");
		System.out.println("계좌 내의 잔금 " + bal + "원이 자동으로 출금되었습니다.");
	}

	// ------------------------------------------------------------------------------
	// 화면 밀어내기 ( cls )

	public static final void screenClear() {
		for (int i = 0; i < 80; i++)
			System.out.println("");
	}

	// ------------------------------------------------------------------------------
	// Error print functions...

	public static final void pressEnter() {
		System.out.print("계속하려면 엔터를 누르세요.");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final void intError() {
		System.out.println("숫자만 입력하세요.");
		pressEnter();
	}

	public static final void notEnoughAssetError() {
		System.out.println("소지금이 부족합니다.");
	}

	public static final void notEnoughBalanceError() {
		System.out.println("계좌에 잔액이 부족합니다.");
	}
}
