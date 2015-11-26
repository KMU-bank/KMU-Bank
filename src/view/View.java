package view;

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
	}
	
	public void User_Select(){
		//-------------사용자 선택 화면-------------------
		
		System.out.println("	1. 계정 생성");
		System.out.println("	2. 계정 삭제");
		System.out.println("	-----------");
		System.out.println("	3. 입금");
		System.out.println("	4. 출금");
		System.out.println("	5. 초회");
		System.out.println("	-----------");
		System.out.println("	6. 대출");
		System.out.println("	7. 대출 상환");
		System.out.println("	--------------");
		System.out.println("");
		System.out.println("	시간이동");
	}
	
	public void User_Create(){
		//---------------------사용자 생성 화면-------------------

		System.out.println("	은행 : ");
		System.out.println("	이름 : ");
		System.out.println("");
		System.out.println("	생성계좌 : ");
	}
	
	public void User_Delete(){
		//-----------------------사용자 삭제 화면------------------
		
		System.out.println("	");
		System.out.println("	");
		System.out.println("	");
		System.out.println("	");
		System.out.println("");
	}
}
