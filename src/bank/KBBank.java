package bank;

public class KBBank extends Bank {

	private static KBBank bank;
	final private static String INIT = "5"; // initial account number
	private static double interest = 0.21;
	
	private KBBank(){
		super(INIT, interest);
	}

	public static Bank getinstance() {
		if (bank == null)
			bank = new KBBank();
		return bank;
	}

}
