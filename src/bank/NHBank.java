package bank;

public class NHBank extends Bank {

	private static final String INIT = "3"; // initial account number
	private static final double POSITIVEINTEREST = 0.12;
	private static final double NEGATIVEINTEREST = 0.32;

	public NHBank() {
		super(INIT, POSITIVEINTEREST, NEGATIVEINTEREST);
	}
}
