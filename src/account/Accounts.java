package account;

import java.util.HashMap;

public final class Accounts {

	private static final Accounts accounts = new Accounts();
	private HashMap<String, Account> accountList = new HashMap<String, Account>();

	private Accounts() {
	}

	public static Accounts getInstance() {
		return accounts;
	}

	public HashMap<String, Account> getAccountList() {
		return accountList;
	}
}
