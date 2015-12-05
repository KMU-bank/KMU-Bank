package bank;

public class WooriBank extends Bank {

	final private static String INIT = "9"; // initial account number
	private static double positiveInterest = 0.18;
	private static double negativeInterest = 0.38;

	public WooriBank() {
		super(INIT, positiveInterest, negativeInterest);
	}
}
