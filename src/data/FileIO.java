package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import account.Account;
import client.Client;

public class FileIO {
	
	public static void backUpAccountsOnFile(HashMap<String, Account> accountList) {
		try {
			FileOutputStream fos = new FileOutputStream("Accounts.db");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			for (String key : accountList.keySet())
				oos.writeObject(accountList.get(key));
		} catch (Exception e) {}
	}

	public static void restoreAccountsFromFile(HashMap<String, Account> accountList) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Accounts.db"));

			Account account;
			do {
				account = (Account) ois.readObject();
				if (account != null)
					accountList.put(account.getAccountNumber(), account);

			} while (account != null);
		} catch (Exception e) {}
	}
	
	public static void backUpClientsOnFile(HashMap<Integer, Client> clientList){
		try {
			FileOutputStream fos = new FileOutputStream("Clients.db");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			for (int key : clientList.keySet())
				oos.writeObject(clientList.get(key));
		} catch (Exception e) {}
	}
	
	public static void restoreClientsFromFile(HashMap<Integer, Client> clientList) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Clients.db"));

			int i = 1;
			Client client;
			do {
				client = (Client) ois.readObject();
				if (client != null)
					clientList.put(i++, client);

			} while (client != null);
		} catch (Exception e) {}
	}
}
