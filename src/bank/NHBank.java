package bank;

public class NHBank extends Bank{
	
	private static NHBank bank;
	final private static String INIT = "3"; // initial account number
	private static double interest = 0.12;
	
	private NHBank(){
		super(INIT, interest);
	}
	
	public static Bank getinstance() {
		if (bank == null)
			bank = new NHBank();
		return bank;
	}
}
