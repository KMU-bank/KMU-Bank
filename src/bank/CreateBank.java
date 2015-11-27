package bank;

public class CreateBank {
	public Bank createBank(String BankName){
		if(BankName == "woori"){
			return new WooriBank();
		}else if(BankName == "NH"){
			return new NHBank();
		}else
			return new KBBank();
	}
} // woori, NH, KB