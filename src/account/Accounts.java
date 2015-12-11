/*********************************************************
 * BanKMU - Accounts class
 * Description: Account class를 해쉬맵에 담아 관리하는 클래스다.
 * 코드상 관리하는 맵은 단 하나지만 은행마다 key값의 첫자리수가 각기 다르게 정해져 있으므로
 * 오류가 나지는 않는다.
 **********************************************************/
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
