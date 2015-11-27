package bank;

public class NHBank extends Bank{
	
	private static NHBank bank;
	final private static String INIT = "3"; // initial account number
	private static double positiveInterest = 0.12;
	private static double negativeInterest = 0.32;
	
	private NHBank(){
		super(INIT, positiveInterest, negativeInterest);
	}
	
	public static Bank getinstance() {
		if (bank == null)
			bank = new NHBank();
		return bank;
	}
}
