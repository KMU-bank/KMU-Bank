package client;

import java.util.HashMap;
import bank.*;

public class Clients {
	private static Clients clients;
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
	
	public boolean haveAccount(Bank bank, Client client){
		if(client.getAccountNumber(bank) == "0")
			return false;
		return true;
	}
}
