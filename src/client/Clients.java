package client;

import java.util.HashMap;

public class Clients {

	private Clients clients;
	
	public HashMap<Integer, Client> clientsList;
	
	public Clients getInstance(){
		if(clients == null)
			clients = new Clients();
		return clients;
	}
	
}
