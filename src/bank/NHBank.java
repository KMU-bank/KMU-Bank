package bank;

public class NHBank extends Bank{
	
	final private static String INIT = "3"; // initial account number
	private static double positiveInterest = 0.12;
	private static double negativeInterest = 0.32;
	
	public NHBank(){
		super(INIT, positiveInterest, negativeInterest);
	}
}
