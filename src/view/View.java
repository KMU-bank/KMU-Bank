package view;

import java.util.HashMap;
import java.util.Scanner;

import client.Client;

public class View {

	public void Title(){
		System.out.println("");
		
		//---------------타티틀 화면----------------------
		
		System.out.println("**************************************************************************");
		System.out.println("*                                                                        *");
		System.out.println("*	■■■■■■                 ■       ■■ ■           ■ ■         ■      *");
		System.out.println("*	■     ■                ■     ■■   ■■         ■■ ■         ■      *");
		System.out.println("*	■     ■                ■   ■■     ■ ■       ■ ■ ■         ■      *");
		System.out.println("*	■■■■■■   ■■■■   ■■   ■ ■ ■■       ■  ■     ■  ■ ■         ■      *");
		System.out.println("*	■     ■ ■    ■  ■ ■  ■ ■■  ■■     ■   ■   ■   ■ ■         ■      *");
		System.out.println("*	■     ■ ■    ■  ■  ■ ■ ■     ■■   ■    ■ ■    ■  ■■     ■■       *");
		System.out.println("*	■■■■■■   ■■■■ ■ ■   ■■ ■       ■■ ■     ■     ■    ■■■■■         *");
		System.out.println("*                                                                        *");
		System.out.println("**************************************************************************");
	}
	
	public void Start_Page(){
		//--------------------시작화면---------------------
		
		System.out.println("	1. 사용자 선택");
		System.out.println("	2. 사용자 생성");
		System.out.println("	3. 사용자 삭제");
		
		select_Option();
	}
	
	
	public void User_Select(){
		//-------------사용자 선택 화면-------------------
		
		System.out.println("	1. 계좌 생성");
		System.out.println("	2. 계좌 삭제");
		System.out.println("	-----------");
		System.out.println("	3. 입금");
		System.out.println("	4. 출금");
		System.out.println("	5. 이체");		
		System.out.println("	. 조회");
		System.out.println("	-----------");
		System.out.println("	. 대출");
		System.out.println("	. 대출 상환");
		System.out.println("	--------------");
		System.out.println("");
		System.out.println("	1년 후...");
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
	
	public void Acount_Creat(){
		//----------------------계좌을 생성합니다.------------------
		System.out.println("	이름을 입력해 주세요.");
		System.out.println("	이름 : ");

		System.out.println("	생성된 계좌는 (랜덤) 입니다.");
	}
	
	public void Acount_Delete(){
		//--------------------계좌을 삭제합니다--------------------
		System.out.println("	계좌번호를 입력해 주세요.");
		System.out.println("	계좌번호 : ");
	}
	
	public void Deposit(){
		//------------------계좌에 돈을 입금합니다---------------------
		System.out.println("");
	}
	
	static Scanner sc = new Scanner(System.in);
	static public int select_Option(){
		System.out.print("Select Number : ");
		return sc.nextInt();
	}
	
	public void write_name(){
		System.out.print("	이름 : ");
		sc.next();
	}
}
