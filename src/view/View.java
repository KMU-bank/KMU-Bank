package view;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import client.Client;

public class View {
	static Scanner sc = new Scanner(System.in);
	

	public void Title(){
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
	
//------------------------------------------------------------------------------ 시작 화면
	
	public void Start_Page(){
		System.out.println("	1. 사용자 생성");
		System.out.println("	2. 사용자 삭제");
		System.out.println("	3. 사용자 선택");
		System.out.print("Select Number : ");
	}
	
	public void User_List(HashMap<Integer, Client> clients){
		for(int i=1; i<100; i++)
			if(clients.get(i) != null)
				System.out.println(i + ". " + clients.get(i).getName());
	}
		
	public void User_Select(HashMap<Integer, Client> clients){
		System.out.println("	사용자 리스트");
		User_List(clients);
		System.out.println("0. 뒤로가기");
		System.out.print("	사용자 선택 : ");
	}
	
	public void User_Create(){
		System.out.println("	사용자 이름을 입력해주세요.");
		System.out.print("	사용자 이름 : ");
	}
	
//------------------------------------------------------------------------------ 사용자 선택 화면 이후

	public void User_Delete(HashMap<Integer, Client> clients){		
		System.out.println("	사용자 리스트");
		User_List(clients);
		System.out.print("	삭제할 사용자 : ");
		System.out.print("Select Number : ");
	}
	
	public void Bank_Select(){
		System.out.println("	은행을 선택해주세요");
		System.out.println("	1. KB");
		System.out.println("	2. NH");
		System.out.println("	3. SH");
		System.out.print("Select Number : ");
	}
	
	public void Banking(){
		System.out.println("	1. 입금");
		System.out.println("	2. 출금");
		System.out.println("	3. 이체");		
		System.out.println("	4. 조회");
		System.out.println("------------------------");
		System.out.println("	5. 대출");
		System.out.println("	6. 대출 상환");
		System.out.println("------------------------");
		System.out.println("	7. 계좌 삭제");
		System.out.println("------------------------");
		System.out.println("	8. 1년 후...");
		System.out.print("Select Number : ");
	}
	
//------------------------------------------------------------------------------ 메뉴 함수
	public void currentBalance(int balance){
		System.out.println("	현재 잔고 : " + balance);
	}
	
	public void currentDebt(int debt){
		System.out.println("------------------------------");
		System.out.println("	현재 대출 금액 : " + (debt));
	}
	
	public void Deposit(){
		System.out.print("	입금할 금액 : ");
	}
	
	
	public void Withdraw(){
		System.out.print("	출금할 금액 : ");
	}
	
	public Object[] Transfer(){
		Object[] AccountNumberNMoney = new Object[2];
		System.out.print("	계좌번호를 입력해 주세요.\n 계좌번호 : ");
		AccountNumberNMoney[0] = sc.next();
		System.out.println("	보내실 금액을 입력해주세요.");
		AccountNumberNMoney[1] = sc.nextInt();
		return AccountNumberNMoney;
	}
	
	public void State_List(LinkedList<String> stateList){
		System.out.println("	거래 내역을 출력합니다.");
		for(int i =0; i<stateList.size(); i++){
			System.out.println(stateList.get(i));
		}
	}
	
	public void Loan(){
		System.out.println("	대출하실 금액을 입력해 주시기 바랍니다.");
		System.out.print("	대출 금액 : ");
	}
	
	public void Repay(){
		System.out.println("	상환하실 금액을 입력해 주시기 바랍니다.");
		System.out.print("	상환 금액 : ");
	}
	
	public void Time_Leap(){
		System.out.println("	1년 후로 이동합니다. (푸슝---~=★!)");
	}
	
//------------------------------------------------------------------------------ 계정 관련 함수
	
	public void no_Account(){
		System.out.print("	계좌가 존재하지 않습니다. 계좌를 생성하시겠습니까? ( y / n ) : ");
	}
	
	public void Acount_Create(){
		System.out.println("	계좌가 생성되었습니다.");
	}
	
	public void Acount_Delete(int bal){
		System.out.println("	계좌를 삭제합니다.");
		System.out.println("	계좌 내의 잔금 " + bal + "원이 자동으로 출금되었습니다.");
	}
	

}
