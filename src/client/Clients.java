/*********************************************************
 * BanKMU - Clients class
 * Description: Client class를 해쉬맵에 담아 관리하는 클래스다.
 * Client를 생성/수정/삭제할 수 있다.
 **********************************************************/
package client;

import java.util.HashMap;
import bank.*;

public final class Clients {
	private static final Clients clients = new Clients();
	private HashMap<Integer, Client> clientList = new HashMap<Integer, Client>();

	private Clients() {
	}

	public static Clients getInstance() {
		return clients;
	}

	public HashMap<Integer, Client> getClientList() {
		return clientList;
	}

	public Client getClient(int key) {
		return clientList.get(key);
	}

	public void createClient(String name) { // name, key
		if (name.equals("0"))
			return;
		for (int i = 1; i < Integer.MAX_VALUE; i++)
			if (!clientList.containsKey(i)) {
				clientList.put(i, new Client(name));
				return;
			}
	}

	public void deleteClient(int key) {
		if (clientList.containsKey(key))
			clientList.remove(key);
	}

	public boolean haveAccount(Bank bank, Client client) {
		if (client.getAccountNumber(bank).equals("0"))
			return false;
		return true;
	}
}
