package bank;

public class KBBank extends Bank {

	final private static String INIT = "5"; // initial account number
	private static double positiveInterest = 0.21;
	private static double negativeInterest = 0.41;

	public KBBank() {
		super(INIT, positiveInterest, negativeInterest);
	}
}
