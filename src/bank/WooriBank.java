package bank;

public class WooriBank extends Bank {

	private static final String INIT = "9"; // initial account number
	private static final double POSITIVEINTEREST = 0.18;
	private static final double NEGATIVEINTEREST = 0.38;

	public WooriBank() {
		super(INIT, POSITIVEINTEREST, NEGATIVEINTEREST);
	}
}
