package mainsequence;

import java.util.Scanner;

import client.Clients;
import view.View;

public class MainSequence {
	View view = new View();
	Clients clients = Clients.getInstance();
	static Scanner sc = new Scanner(System.in);

	public void firstSequence() {
		while (true) {
			view.Title();
			view.Start_Page();
			int select = select_Option();
			

			switch (select) {
			case 1:
				view.User_Create();
				clients.createClient(inputString());
				break;
			case 2:
				view.User_Delete(clients.clientsList);
				clients.deleteClient(select_Option());
				break;
			case 3:
				clientSelectSequance();
			}
		}
	}

	public void clientSelectSequance() {
		view.User_Select(clients.clientsList);
		int select = select_Option();
		if (select == 0) // 0은 뒤로가기
			firstSequence();
		else {
			clients.selectClient(select);
			bankSelectSeq();
		}
	}

	public void bankSelectSeq() {
		view.Bank_Select();
		int select = select_Option();
		
		if(select == 0)
			clientSelectSequance();
		else
			clients.selectBank(select);
		
		if(clients.haveAccount())
			bankingSeq();
		else
			accountErrorSeq();
		
	}
	
	public void accountErrorSeq(){
		view.no_Account();
		String select = inputString();
		if(select.equals("n"))
			bankSelectSeq();
		else if(select.equals("y")){
			view.Acount_Create();
			clients.selectedClient.openAccount();
			bankingSeq();
		}
	}
	
	public void bankingSeq(){
		while(true){
			view.Banking();
			int select = select_Option();
			switch(select){
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
				transfer();
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
				timeLeapSeq();
			}
		}
	}
		
		public void depositSeq(){
			view.Deposit();
			int money = inputInt();
			clients.selectedClient.deposit(money);
			view.currentBalance(clients.selectedClient.getBalance());
		}
		public void withdrawSeq(){
			view.Withdraw();
			int money = inputInt();
			clients.selectedClient.withdraw(money);
			view.currentBalance(clients.selectedClient.getBalance());
		}
		public void transfer(){
			Object[] accountNumberNMoney = view.Transfer();
			clients.selectedClient.transfer((int)accountNumberNMoney[1], (String)accountNumberNMoney[0]);
		}
		public void printStateSeq(){
			view.State_List(clients.selectedClient.getStateList());
		}
		public void loanSeq(){
			view.Loan();
			int money = inputInt();
			clients.selectedClient.loan(money);
			view.currentDebt(clients.selectedClient.getDebt());
		}
		public void repaySeq(){
			view.Repay();
			int money = inputInt();
			clients.selectedClient.repay(money);
			view.currentDebt(clients.selectedClient.getDebt());
		}
		public void timeLeapSeq(){
			view.Time_Leap();
			clients.selectedClient.timeLeap();
		}
		
//------------------------------------------------------------------------------ 기타 기능 함수
		
		public int inputInt(){
			return sc.nextInt();
		}
		
		public String inputString(){
			return sc.next();
		}
		
		public int select_Option(){
			return sc.nextInt();
		}
		
		public void clearScreen(){
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}
}
