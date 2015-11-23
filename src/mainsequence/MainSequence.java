package mainsequence;
import view.View;
import java.io.InputStream;

import client.Client;

// Bridge Pattern
// 추상class View 구현class Client
// view class는
public class MainSequence {
	View view = new View();
	Client client;
	InputStream in = new InputStream(new );
	
	void sequence(){
		view.mainview(); // 첫 화면
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
