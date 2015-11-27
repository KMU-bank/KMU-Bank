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
	
	public int Start_Page(){		
		System.out.println("	1. 사용자 선택");
		System.out.println("	2. 사용자 생성");
		System.out.println("	3. 사용자 삭제");
		
		return select_Option();
	}
		
	public int User_Select(HashMap<Integer, Client> clients){		
		System.out.println("	사용자 리스트");
		User_List(clients);
		System.out.print("	사용자 선택 : ");
		return select_Option();
	}
	
	public String User_Create(){
		System.out.println("	사용자 이름을 입력해주세요.");
		System.out.print("	사용자 이름 : ");
		return write_name();
	}
	
//------------------------------------------------------------------------------ 사용자 선택 화면 이후

	public int User_Delete(HashMap<Integer, Client> clients){		
		System.out.println("	사용자 리스트");
		User_List(clients);
		System.out.print("	삭제할 사용자 : ");
		return select_Option();
	}
	
	public int Bank_Select(){
		System.out.println("	은행을 선택해주세요");
		System.out.println("	1. KB");
		System.out.println("	2. NH");
		System.out.println("	3. SH");
		
		return select_Option();
	}
	
	public int Banking(){
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
		
		return select_Option();
	}
	
//------------------------------------------------------------------------------ 메뉴 함수
	
	public int Deposit(int bal){
		System.out.print("	입금할 금액 : ");
		int money = write_money();
		System.out.println("------------------------------");
		System.out.println("	현재 잔고 : " + (money + bal));
		return money;
	}
	
	public int Withdraw(int bal){
		System.out.print("	출금할 금액 : ");
		int money = write_money();
		System.out.println("------------------------------");
		System.out.println("	현재 잔고 : " + (money + bal));
		return money;
	}
	
	public void Transfer(){
		System.out.println("	계좌번호를 입력해 주세요.");
		write_acount();
		System.out.println("	보내실 금액을 입력해주세요.");
		write_money();
	}
	
	public void State_List(LinkedList<String> stateList){
		System.out.println("	거래 내역을 출력합니다.");
		for(int i =0; i<stateList.size(); i++){
			System.out.println(stateList.get(i));
		}
	}
	
	public int Loan(int debt){
		System.out.println("	대출하실 금액을 입력해 주시기 바랍니다.");
		System.out.print("	대출 금액 : ");
		int money = write_money();
		System.out.println("------------------------------");
		System.out.println("	현재 대출 금액 : " + (money + debt));
		return money;
	}
	
	public int Repay(int debt){
		System.out.println("	상환하실 금액을 입력해 주시기 바랍니다.");
		System.out.print("	상환 금액 : ");
		int money = write_money();
		System.out.println("------------------------------");
		System.out.println("	현제 대출 금액 : " + (debt-money));
		return money;
	}
	
	public void Time_Leap(){
		System.out.println("	1년 후로 이동합니다. (푸슝---~=★!)");
	}
	
//------------------------------------------------------------------------------ 계정 관련 함수
	
	public void no_Account(){
		System.out.println("	계좌가 존재하지 않습니다. 계좌를 생성하시겠습니까? ( y / n )");
	}
	
	public void Acount_Create(String s){
		System.out.println("	생성된 계좌는 " + s + " 입니다.");
	}
	
	public void Acount_Delete(int bal){
		System.out.println("	계좌를 삭제합니다.");
		System.out.println("	계좌 내의 잔금 " + bal + "원이 자동으로 출금되었습니다.");
	}
	
//------------------------------------------------------------------------------ 기타 기능 함수
	
	public void write_acount(){
		System.out.print("	계좌번호 : ");
		sc.next();
	}
	
	public String write_name(){
		System.out.print("	이름 : ");
		return sc.next();
	}
	
	public int write_money(){
		return sc.nextInt();
	}
	
	public void User_List(HashMap<Integer, Client> clients){
		for(int i=0; i<clients.size(); i++)
			System.out.println(i + ". " + clients.get(i).getName());
	}
	
	static public int select_Option(){
		System.out.print("Select Number : ");
		return sc.nextInt();
	}
}
