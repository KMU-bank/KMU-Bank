package client;

import java.util.HashMap;
import bank.CreateBank;

public class Clients {
	private static Clients clients;
	public Client selectedClient;
	CreateBank createBank = new CreateBank();
	
	public HashMap<Integer, Client> clientsList;
	
	public Clients getInstance(){
		if(clients == null)
			clients = new Clients();
		return clients;
	}
	
	public void sort(){}
	public void createClient(){
		
	}
	public void deleteClient(){
		
	}
	public void selectClient(){
		
	}
	public 파일에서 client정보를 맵에 받아오는 함수(){
		// 파일 입출력
	}
	public void selectClient(int clientKey){
		selectedClient = clientsList.get(clientKey);
	} // client 선택
	
	public void selectBank(String bank){
		selectedClient.selectBank(createBank.createBank(bank));
	}
	
	public boolean haveAccount(){
		if(selectedClient.getAccountNumber() == "0"){
			return false;
		}else
			return true;
	}
}
