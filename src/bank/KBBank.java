/*********************************************************
 * BanKMU - KBBank class
 * Description: Bank 클래스를 상속받은 은행 중 하나
 **********************************************************/
package bank;

public class KBBank extends Bank {

	private static final String INIT = "5"; // initial account number - 은행 고유 번호
	private static final double POSITIVEINTEREST = 0.21;		// 이자율
	private static final double NEGATIVEINTEREST = 0.41;		// 대출 이자율

	public KBBank() {
		super(INIT, POSITIVEINTEREST, NEGATIVEINTEREST);
	}
}
