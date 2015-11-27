package mainsequence;

import view.View;

import client.Client;
import client.Clients;

public class MainSequence {
	View view = new View();
	int select;

	int selectedClient;
	Clients clients = Clients.getInstance();

	public void firstSequence() {
		while (true) {
			view.Title();
			view.Start_Page();

			switch (select) {
			case 1:
				view.User_Create();
				break;
			case 2:
				view.User_Delete(clients);
				break;
			case 3:
				clientSelectSequance();
			}
		}
	}

	public void clientSelectSequance() {
		int select = view.User_Select();
		if (select == 0) // 0은 뒤로가기
			firstSequence();
		else {
			clients.selectClient(select);
			bankSelectSeq();
		}
	}

	public void bankSelectSeq() {
		int select = view.Bank_Select();
		if(select == 0)
			clientSelectSequance();
		else if(select == 1){
			clients.selectBank("KB");
			
		}
		else if(select == 2){
			clients.selectBank("NH");

		}
		else if(select == 3){
			clients.selectBank("woori");
		}
		
		if(clients.haveAccount())
			bankingSeq();
		else
			accountErrorSeq();
	}
	
	public void accountErrorSeq(){
		int select = view.noAccountError();
		if(select == 0)
			bankSelectSeq();
		else if(select == 1){
			view.Acount_Create(clients.selectedClient.getName());
		}
	}
	
	public void bankingSeq(){
		while(true){
			int select = view.Banking();
			switch(select){
			case 0:
				firstSequence();
				break;
			case 1:
				view.Deposit();
				break;
			case 2:
				view.Withdraw();
				break;
			case 3:
				view.transfer();
				break;
			case 4:
				view.state_List();
				break;
			case 5:
				view.loan();
				break;
			case 6:
				view.repay();
				break;
			case 7:
				view.timeLeap();
		}
	}
		
		public void depositSeq(){
			
		}
		public void withdrawSeq(){
			
		}
		public void transfer(){
			
		}
		public void printSeq(){
			
		}
		public void loanSeq(){
			
		}
		public void repaySeq(){
			
		}
		public void timeLeapSeq(){
			
		}
		
}
