package mainsequence;

import client.Clients;
import view.View;

public class MainSequence {
	View view = new View();
	int selectedClient;
	Clients clients = Clients.getInstance();

	public void firstSequence() {
		while (true) {
			view.Title();
			int select = view.Start_Page();

			switch (select) {
			case 1:
				clients.createClient(view.User_Create());
				break;
			case 2:
				clients.deleteClient(view.User_Delete(clients.clientsList));
				break;
			case 3:
				clientSelectSequance();
			}
		}
	}

	public void clientSelectSequance() {
		int select = view.User_Select(clients.clientsList);
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
		else
			clients.selectBank(select);
		
		if(clients.haveAccount())
			bankingSeq();
		else
			accountErrorSeq();
	}
	
	public void accountErrorSeq(){
		String select = view.no_Account();
		if(select == "n")
			bankSelectSeq();
		else if(select == "y"){
			view.Acount_Create(clients.selectedClient.getName());
			bankingSeq();
			
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
			clients.selectedClient.deposit(view.Deposit(clients.selectedClient.getBalance()));
		}
		public void withdrawSeq(){
			clients.selectedClient.withdraw(view.Withdraw(clients.selectedClient.getBalance()));
		}
		public void transfer(){
			Object[] accountNumberNMoney = view.Transfer();
			clients.selectedClient.transfer((int)accountNumberNMoney[1], (String)accountNumberNMoney[0]);
		}
		public void printStateSeq(){
			view.State_List(clients.selectedClient.getStateList());
		}
		public void loanSeq(){
			clients.selectedClient.loan(view.Loan(clients.selectedClient.getDebt()));
		}
		public void repaySeq(){
			clients.selectedClient.repay(view.Repay(clients.selectedClient.getDebt()));
		}
		public void timeLeapSeq(){
			view.Time_Leap();
			clients.selectedClient.timeLeap();
		}
		
}
