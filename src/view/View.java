package view;

import java.util.HashMap;
import java.util.LinkedList;

import client.Client;

public class View {

	public void title(){
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
	
	public void initialPage(){
		System.out.println(" 1. 사용자 생성");
		System.out.println(" 2. 사용자 삭제");
		System.out.println(" 3. 사용자 선택");
		System.out.print("Select Number : ");
	}
	
	public void printUserList(HashMap<Integer, Client> clients){
		for(int i=1; i<100; i++)
			if(clients.get(i) != null)
				System.out.println(i + ". " + clients.get(i).getName());
	}
		
	public void selectUser(HashMap<Integer, Client> clients){
		System.out.println("사용자 리스트");
		printUserList(clients);
		System.out.print("사용자 선택 : ");
	}
	
	public void createUser(){
		System.out.println("사용자 이름을 입력해주세요.");
		System.out.print(" 사용자 이름 : ");
	}
	
//------------------------------------------------------------------------------ 사용자 선택 화면 이후

	public void deleteUser(HashMap<Integer, Client> clients){		
		System.out.println("사용자 리스트");
		printUserList(clients);
		System.out.print(" 삭제할 사용자 번호: ");
	}
	
	public void selectBank(){
		System.out.println("은행을 선택해주세요");
		System.out.println("1. KB");
		System.out.println("2. NH");
		System.out.println("3. Woori");
		System.out.print(" Select Number : ");
	}
	
	public void banking(){
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
	
//------------------------------------------------------------------------------ 메뉴 함수
	public void currentBalance(int balance){
		System.out.println(" 현재 잔고 : " + balance);
	}
	
	public void currentDebt(int debt){
		System.out.println("------------------------------");
		System.out.println("현재 대출 금액 : " + (debt));
	}
	
	public void deposit(){
		System.out.print(" 입금할 금액 : ");
	}
	
	public void withdraw(){
		System.out.print(" 출금할 금액 : ");
	}
	

	public void transferAccountNumber(){
		System.out.print("계좌번호를 입력해 주세요.\n 계좌번호 : ");
	}
	
	public void transferMoney(){
		System.out.print("보내실 금액을 입력해주세요.\n 금액 : ");
	}
	
	public void printStateList(LinkedList<String> stateList){
		System.out.println("거래 내역을 출력합니다.");
		for(int i =0; i<stateList.size(); i++){
			System.out.println(stateList.get(i));
		}
	}
	
	public void loan(){
		System.out.println("대출하실 금액을 입력해 주시기 바랍니다.");
		System.out.print(" 대출 금액 : ");
	}
	
	public void repay(){
		System.out.println("상환하실 금액을 입력해 주시기 바랍니다.");
		System.out.print(" 상환 금액 : ");
	}
	
	public void timeLeap(){
		System.out.println("	1년 후로 이동합니다. (푸슝---~=★!)");
	}
	
//------------------------------------------------------------------------------ 계정 관련 함수
	
	public void noAccount(){
		System.out.print("	계좌가 존재하지 않습니다.\n 계좌를 생성하시겠습니까? ( y / n ) : ");
	}
	
	public void createAccount(){
		System.out.println("계좌가 생성되었습니다.");
	}
	
	public void deleteAccount(int bal){
		System.out.println("계좌를 삭제합니다.");
		System.out.println("계좌 내의 잔금 " + bal + "원이 자동으로 출금되었습니다.");
	}
}
