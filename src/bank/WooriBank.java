package bank;

public class WooriBank extends Bank{

	private static WooriBank bank;
	final private static String INIT = "9"; // initial account number
	private static double interest = 0.18;
	
	private WooriBank(){
		super(INIT, interest);
	}
	
	public static Bank getinstance() {
		if (bank == null)
			bank = new WooriBank();
		return bank;
	}

}
