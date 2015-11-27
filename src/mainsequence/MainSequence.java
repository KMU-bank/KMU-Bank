package mainsequence;

import view.View;

import client.Client;

public class MainSequence {
	View view = new View();
	int select;
	
	Client selectedClient;
	Clients clients = Clients.getInstance();
	
	public void firstSequence(){
		while(true){
			view.Title();
			view.Start_Page();
			
			switch(select){
			case 1:
				view.User_Create();
				break;
			case 2:
				view.User_Delete(clients);
				break;
			case 3:
				selectedClient = view.User_Select();
			}
		}
	}
	
	public void bankselectSeq(boolean haveAccount){
		if(haveAccount)
			view.Banking();
		else{
			view.Account_Create();
			view.Bank_Seleect();
		}
	}
}
