package mainsequence;
import view.View;
import java.io.InputStream;

import client.Client;

// Bridge Pattern
// �߻�class View ����class Client
// view class��
public class MainSequence {
	View view = new View();
	Client client;
	InputStream in = new InputStream(new );
	
	void sequence(){
		view.mainview(); // ù ȭ��
		int input = in.nextInt();
		if(in.nextInt() == 1){
			view.clientView();
		}
	}
	
	public static void main(String args[]){
		MainSequence main = new MainSequence();
		while(true){
			main.sequence();
		}
		//input = inputstream.nextint();
	}
}
