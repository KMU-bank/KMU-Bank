package bank;

public class KBBank extends Bank {

	private static KBBank bank;
	final private static String INIT = "5"; // initial account number
	private static double positiveInterest = 0.21;
	private static double negativeInterest = 0.41;
	
	private KBBank(){
		super(INIT, positiveInterest, negativeInterest);
	}

	public static Bank getinstance() {
		if (bank == null)
			bank = new KBBank();
		return bank;
	}

}
