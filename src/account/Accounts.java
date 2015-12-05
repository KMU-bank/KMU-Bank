package account;

import java.util.HashMap;

public class Accounts {

	private static Accounts accounts;
	private HashMap<String, Account> accountList = new HashMap<String, Account>();

	public static Accounts getInstance() {
		if (accounts == null)
			accounts = new Accounts();
		return accounts;
	}
	
	public HashMap<String, Account> getAccountList(){
		return accountList;
	}
}
