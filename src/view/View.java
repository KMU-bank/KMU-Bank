/*********************************************************
 * BanKMU - View class
 * Description: MainSequence에 의해 콘솔에 띄워지는 텍스트를 출력하는 함수.
 **********************************************************/
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

	// 간판 로고 출력 [ BanKMU ]
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
		System.out.println("                     - Tip : 모든 선택, 입력 화면에서 0을 입력하면 이전 단계로 넘어갑니다.");
	}

	public static final void title() {
		OriginalTitle();
	}

	// 사용자 선택 후 은행 선택 화면에서 현재 사용자의 이름과 자산(현금)을 출력해줍니다.
	public static final void title(Client selectedClient) {
		OriginalTitle();
		System.out
				.println("현재 선택된 사용자 : " + selectedClient.getName() + " 현재 선택된 사용자의 자산 : " + selectedClient.getAsset());
		System.out.println("---------------------------------------------------------------------------");
	}

	// 은행 선택 후 은행업무 화면에서의 현재 사용자의 자금 현황을 출력합니다.
	public static final void title(Client selectedClient, Bank selectedBank) {
		String accountNumber = selectedClient.getAccountNumber(selectedBank);
		OriginalTitle();
		System.out.print("현재 선택된 사용자 : " + selectedClient.getName() + "		현재 선택된 사용자의 자산 : " + selectedClient.getAsset()
				+ "\n현재 선택된 계좌의 계좌번호 : " + accountNumber + "	현재 선택된 은행계좌의 잔고 : "
				+ selectedBank.getBalance(accountNumber));
		if (selectedBank.getDebt(accountNumber) != 0)
			System.out.print("	현재 선택된 은행계좌의 대출금 : " + selectedBank.getDebt(accountNumber));
		System.out.println("\n---------------------------------------------------------------------------");
	}

	// ------------------------------------------------------------------------------
	// 시작 화면

	// 프로그램 시작, 첫 화면입니다.
	public static final void initialPage() {
		System.out.println(" 1. 사용자 생성");
		System.out.println(" 2. 사용자 삭제");
		System.out.println(" 3. 사용자 선택");
		System.out.print("Select Number : ");
	}

	// 사용자리스트를 출력합니다. 사용자 삭제, 사용자 선택에 이용됩니다.
	public static final void printUserList(HashMap<Integer, Client> clients) {
		for (int i = 1; i < 100; i++)
			if (clients.get(i) != null)
				System.out.println(i + ". " + clients.get(i).getName());
	}

	// 사용자 선택 화면입니다. 사용자 리스트와 선택 콘솔에서 번호로 사용자를 선택합니다.
	public static final void selectUser(HashMap<Integer, Client> clients) {
		System.out.println("사용자 리스트");
		printUserList(clients);
		System.out.print("사용자 선택 : ");
	}

	// 사용자 생성 화면입니다. 사용자 이름을 입력하면 계정생성과 동시에 기본 자금 3,000,000원 이 생성됩니다.
	public static final void createUser() {
		System.out.println("사용자 이름을 입력해주세요.");
		System.out.print(" 사용자 이름 : ");
	}

	// ------------------------------------------------------------------------------
	// 사용자 선택 화면 이후

	// 사용자 삭제 화면입니다. 사용자 리스트를 출력하고 삭제할 사용자의 번호를 입력하면 해당사용자의 모든 정보가 삭제됩니다.
	public static final void deleteUser(HashMap<Integer, Client> clients) {
		System.out.println("사용자 리스트");
		printUserList(clients);
		System.out.print(" 삭제할 사용자 번호: ");
	}

	// 은행 선택 화면입니다. 사용자는 총 3개의 은행중에 사용할 은행을 자유로이 선택해 사용 할 수 있습니다.
	public static final void selectBank() {
		System.out.println("은행을 선택해주세요");
		System.out.println("1. KB Bank");
		System.out.println("2. NH Bank");
		System.out.println("3. Woori Bank");
		System.out.print(" Select Number : ");
	}

	// 은행 업무 화면입니다. 이 화면에서 사용자는 본격적인 은행 업무를 볼 수 있습니다.
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
	
	// 첫번 째 대분류 메뉴인 입금, 출금, 이체, 조회 업무에서 업무를 마치고 난 다음의 잔고를 출력합니다.
	public static final void currentBalance(int balance) {
		System.out.println(" 현재 잔고 : " + balance);
	}

	// 두번 째 대분류 메뉴인 대출, 대출상환 업무에서 남아있는 대출금의 잔고를 출력합니다.
	public static final void currentDebt(int debt) {
		System.out.println("현재 대출 금액 : " + (debt));
		System.out.println("------------------------------");
	}

	// 입금 메뉴에서 입금할 금액을 입력해줍니다.
	public static final void deposit() {
		System.out.print(" 입금할 금액 : ");
	}

	// 출금 메뉴에서 출금할 금액을 입력해줍니다.
	public static final void withdraw() {
		System.out.print(" 출금할 금액 : ");
	}

	// 계좌이체 화면에서 입금대상 계좌번호를 입력합니다. 입금할 대상의 계좌번호를 모를시 -1을 입력하면 모든 사용자의 모든 은행 계좌를 보여줍니다.
	public static final void transferAccountNumber() {
		System.out.println("계좌번호를 입력해 주세요.");
		System.out.println("계좌번호를 모르시면 -1을 입력해 주세요.");
		System.out.print("계좌번호 : ");
	}

	// 계좌이체 화면에서 -1을 입력할시 나오는 사용자-계좌 리스트를 출력하고 계좌번호 입력 화면입니다.
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

	// 계좌번호를 입련한 후, 송금할 금액을 입력해줍니다. 
	public static final void transferMoney() {
		System.out.print("보내실 금액을 입력해주세요.\n 금액 : ");
	}

	// 조회 화면입니다. 지금까지 거래한 모든 거래 내역을 출력합니다.
	public static final void printStateList(LinkedList<String> stateList) {
		System.out.println("거래 내역을 출력합니다.");
		for (int i = 0; i < stateList.size(); i++) {
			System.out.println(stateList.get(i));
		}
	}

	// 대출 화면입니다. 대출할 금액을 입력합니다.
	public static final void loan() {
		System.out.println("대출하실 금액을 입력해 주시기 바랍니다.");
		System.out.print(" 대출 금액 : ");
	}

	// 대출 상환 화면입니다. 상환할 금액을 입력합니다.
	public static final void repay() {
		System.out.println("상환하실 금액을 입력해 주시기 바랍니다.");
		System.out.print(" 상환 금액 : ");
	}
	
	// 가지고 있는 현금과 계좌내의 금액중에 상환의 대상이 될 방법을 선택합니다.
	public static final void selectAssetOrAccount(){
		System.out.println("피상환 개체를 선택해 주시기 바랍니다.");
		System.out.println("1. My Asset");
		System.out.println("2. My Account");
		System.out.print(" Select Number : ");
	}

	// 1년 후로 이동합니다. (푸슝---~=★!)
	public static final void timeLeap() {
		System.out.println("	1년 후로 이동합니다. (푸슝---~=★!)");
	}

	// ------------------------------------------------------------------------------
	// 계정 관련 함수

	// 계정 생성 후, 아직 해당 은행 계좌가 존재하지 않는다면 은행업무를 볼 수 없기에 계좌 생성 화면을 거칩니다. y/n 로 생성할지 말지를 선택 할 수 있습니다.
	public static final void noAccount() {
		System.out.print("	계좌가 존재하지 않습니다.\n 계좌를 생성하시겠습니까? ( y / n ) : ");
	}

	// 계좌 생성여부에서 y를 선택하면 난수를 거쳐 일정한 형식에 의해 계좌가 생성됩니다.
	public static final void createAccount(String accountNumber) {
		System.out.println("계좌가 생성되었습니다.");
		System.out.println("계좌번호는 : " + accountNumber + "입니다.");
		System.out.println("확인 후 엔터를 누르세요.");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 계좌 삭제 화면입니다. 잔금 혹은 미상환 대출금이 있을지 자동으로 계산되어 현금에 적용됩니다.
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

	// 입금, 출금, 등 자산의 변화가 있는 작업을 수행 한 후, 변경사항을 보여주기 전에 넘어가게 하지 않기 위해 잠시 대기할 수 있는 화면입니다.
	public static final void pressEnter() {
		System.out.print("계속하려면 엔터를 누르세요.");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 숫자가 입력돼야 할 입력칸에 문자열이 입력돼었을 시 출력되는 경고 메시지 입니다.
	public static final void intError() {
		System.out.println("숫자만 입력하세요.");
		pressEnter();
	}

	// 현금 -> 계좌로 입금시 현금 이상의 금액이 입력되면 출력되는 경고 메시지 입니다.
	public static final void notEnoughAssetError() {
		System.out.println("소지금이 부족합니다.");
	}

	// 계좌에서 출금이 이뤄지는 작업수행중에 출금액이 계좌의 잔액보다 클시 출력되는 경고 메시지 입니다.
	public static final void notEnoughBalanceError() {
		System.out.println("계좌에 잔액이 부족합니다.");
	}
	
	// 문자열입력이 필요한 부분에 숫자를 입력하거나, 숫자입력이 필요한 부분에 문자열을 입력하는 등 기타 의도하지 않은 입력으로인한 접근시 출력되는 경고 메시지 입니다.
	public static final void unvalidInput(){
		System.out.println("잘못된 입력입니다.");
	}
}
