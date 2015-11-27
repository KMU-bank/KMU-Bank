package view;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import client.Client;

public class View {

	public void Title(){
		System.out.println("");
		
		//---------------타티틀 화면----------------------
		
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
	
	public int Start_Page(){
		//--------------------시작화면---------------------
		
		System.out.println("	1. 사용자 선택");
		System.out.println("	2. 사용자 생성");
		System.out.println("	3. 사용자 삭제");
		
		return select_Option();
	}
	
	
	public int User_Select(HashMap<Integer, Client> clients){
		//-------------사용자 선택 화면-------------------
		
		System.out.println("	사용자 리스트");
		User_List(clients);
		System.out.print("	사용자 선택 : ");
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
		//--------------------------은행 업무 메뉴---------------
		System.out.println("------------------------");
		System.out.println("	1. 입금");
		System.out.println("	2. 출금");
		System.out.println("	3. 이체");		
		System.out.println("	4. 조회");
		System.out.println("------------------------");
		System.out.println("	. 대출");
		System.out.println("	. 대출 상환");
		System.out.println("------------------------");
		System.out.println("	2. 계좌 삭제");
		System.out.println("");
		System.out.println("	1년 후...");
		
		return select_Option();
	}
	
	public void User_Create(){
		//---------------------사용자 생성 화면-------------------
		write_name();	
	}
	
	public void User_Delete(HashMap<Integer, Client> clients){
		//-----------------------사용자 삭제 화면------------------
		
		System.out.println("	사용자 리스트");
		User_List(clients);
		System.out.print("	삭제할 사용자 : ");
		select_Option();
	}
	
	public void User_List(HashMap<Integer, Client> clients){
		//-----------------------사용자 리스트 출력------------------
		for(int i=0; i<clients.size(); i++)
			System.out.println(i + ". " + clients.get(i).getName());
	}
	
	public void Acount_Create(String s){
		//----------------------계좌을 생성합니다.-----------------
		System.out.println("	생성된 계좌는 " + s + " 입니다.");
	}
	
	public void Acount_Delete(int dep){
		//--------------------계좌을 삭제합니다--------------------
		System.out.println("	계좌를 삭제합니다.");
		System.out.println("	계좌 내의 잔금 " + dep + "원이 자동으로 출금되었습니다.");
	}
	
	public void Deposit(){
		//------------------계좌에 돈을 입금합니다---------------------
		System.out.print("	입금할 금액 : ");
		write_money();
	}
	
	public void Withdraw(){
		System.out.print("	출금할 금액 : ");
		write_money();
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
	
	public void Loan(){
		
	}
	
	public void Repay(){
		
	}
	
	static Scanner sc = new Scanner(System.in);
	static public int select_Option(){
		System.out.print("Select Number : ");
		return sc.nextInt();
	}
	
	public void write_acount(){
		System.out.print("	계좌번호 : ");
		sc.next();
	}
	
	public void write_name(){
		System.out.print("	이름 : ");
		sc.next();
	}
	
	public void write_money(){
		sc.nextInt();
	}
}
