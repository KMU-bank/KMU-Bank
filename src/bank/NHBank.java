/*********************************************************
 * BanKMU - NHBank class
 * Description: Bank 클래스를 상속받은 은행 중 하나
 **********************************************************/
package bank;

public class NHBank extends Bank {

	private static final String INIT = "3"; // initial account number - 은행 고유 번호
	private static final double POSITIVEINTEREST = 0.12;		// 이자율
	private static final double NEGATIVEINTEREST = 0.32;		// 대출 이자율

	public NHBank() {
		super(INIT, POSITIVEINTEREST, NEGATIVEINTEREST);
	}
}
