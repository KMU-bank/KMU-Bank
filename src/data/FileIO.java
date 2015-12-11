/*********************************************************
 * BanKMU - FileIO class
 * Description: 파일 입출력이 정의되어 있는 클래스
 **********************************************************/
package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import account.Account;
import client.Client;

public class FileIO {
	
	@SuppressWarnings("resource")
	public static void backUpAccountsOnFile(HashMap<String, Account> accountList) {
		try {
			final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Accounts.db"));

			for (String key : accountList.keySet())
				oos.writeObject(accountList.get(key));
		} catch (Exception e) {}
	}

	@SuppressWarnings("resource")
	public static void restoreAccountsFromFile(HashMap<String, Account> accountList) {
		try {
			final ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Accounts.db"));

			Account account;
			do {
				account = (Account) ois.readObject();
				if (account != null)
					accountList.put(account.getAccountNumber(), account);

			} while (account != null);
		} catch (Exception e) {}
	}
	
	@SuppressWarnings("resource")
	public static void backUpClientsOnFile(HashMap<Integer, Client> clientList){
		try {
			final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Clients.db"));

			for (int key : clientList.keySet())
				oos.writeObject(clientList.get(key));
		} catch (Exception e) {}
	}
	
	@SuppressWarnings("resource")
	public static void restoreClientsFromFile(HashMap<Integer, Client> clientList) {
		try {
			final ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Clients.db"));

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
