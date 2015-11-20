package mainsequence;
import view.View;
import client.Client;

// Bridge Pattern
// 추상class View 구현class Client
// view class는
public class MainSequence {
	View view;
	Client client;
	
	
	public static void main(String args[]){
		MainSequence mains;
		mains.view.show();
		//input = inputstream.nextint();
	}
}
