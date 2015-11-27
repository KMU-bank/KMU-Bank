package bank;

public class WooriBank extends Bank{

	private static WooriBank bank;
	final private static String INIT = "9"; // initial account number
	private static double positiveInterest = 0.18;
	private static double negativeInterest = 0.38;
	
	private WooriBank(){
		super(INIT, positiveInterest, negativeInterest);
	}
	
	public static Bank getinstance() {
		if (bank == null)
			bank = new WooriBank();
		return bank;
	}
}
