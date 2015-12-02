package client;

import java.util.HashMap;
import bank.*;

public class Clients {
	private static Clients clients;
	public Client selectedClient;
	public HashMap<Integer, Client> clientsList;
	
	private Clients(){
		clientsList = new HashMap<Integer, Client>();
	}
	
	public static Clients getInstance(){
		if(clients == null)
			clients = new Clients();
		return clients;
	}
	
	public void createClient(String name){ // name, key
		for(int i=1; i<100; i++)
			if(!clientsList.containsKey(i)){
				clientsList.put(i, new Client(name));
				return;
			}
	}
	
	public void deleteClient(int key){
		if(clientsList.containsKey(key))
			clientsList.remove(key);
	}
	
	public void selectClient(int clientKey){
		selectedClient = clientsList.get(clientKey);
	} // client 선택
	
	public void selectBank(int whichBank){
		if(whichBank == 1){
			selectedClient.selectBank(new KBBank());
		}else if(whichBank == 2){
			selectedClient.selectBank(new NHBank());
		}else
			selectedClient.selectBank(new WooriBank());
	}
	
	public boolean haveAccount(){
		if(selectedClient.getAccountNumber() == "0")
			return false;
		return true;
	}
}
