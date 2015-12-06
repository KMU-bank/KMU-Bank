package bank;

public class KBBank extends Bank {

	private static final String INIT = "5"; // initial account number
	private static final double POSITIVEINTEREST = 0.21;
	private static final double NEGATIVEINTEREST = 0.41;

	public KBBank() {
		super(INIT, POSITIVEINTEREST, NEGATIVEINTEREST);
	}
}
