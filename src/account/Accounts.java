package account;

import java.util.HashMap;

public class Accounts {

	private static Accounts accounts;
	public HashMap<String, Account> accountList = new HashMap<String, Account>();

	public static Accounts getInstance() {
		if (accounts == null)
			accounts = new Accounts();
		return accounts;
	}
}
