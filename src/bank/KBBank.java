/*********************************************************
 * BanKMU - KBBank class
 * Description: Bank 클래스를 상속받은 은행 중 하나
 **********************************************************/
package bank;

public class KBBank extends Bank {

	private static final String INIT = "5"; // initial account number
	private static final double POSITIVEINTEREST = 0.21;
	private static final double NEGATIVEINTEREST = 0.41;

	public KBBank() {
		super(INIT, POSITIVEINTEREST, NEGATIVEINTEREST);
	}
}
