package mainsequence;
import view.View;
import client.Client;

// Bridge Pattern
// �߻�class View ����class Client
// view class��
public class MainSequence {
	View view;
	Client client;
	
	
	public static void main(String args[]){
		MainSequence mains;
		mains.view.show();
		//input = inputstream.nextint();
	}
}
