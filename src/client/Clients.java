package client;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import bank.*;

public class Clients {
	private static Clients clients;
	public Client selectedClient;
	BufferedReader inputStream;
	PrintWriter outputStream;
	
	public HashMap<Integer, Client> clientsList;
	
	public Clients(){
		try {
			inputStream = new BufferedReader(new FileReader("client.txt"));
			outputStream = new PrintWriter(new FileOutputStream("client.txt"));
			int data = 0;
			 while((data = inputStream.read()) != -1){
	        	  outputStream.write(data);
	          }
		} catch (Exception e) {
			System.out.println("exception occured");
			e.printStackTrace();
		}
	}
	
	
	public static Clients getInstance(){
		if(clients == null)
			clients = new Clients();
		return clients;
	}
	public void renewFile(){
		// 현재 맵에 있는 정보로 파일에 덮어씌움
	}
	public void createClient(String name){ // name, key
		for(int i=0; i<100; i++){
			if(!clientsList.containsKey(i)){
				clientsList.put(i, new Client(name));
				break;
			}
		}
		// 파일 입출력
		// 일단 새로운 client를 받아 맵에 넣어준다.
		// 그후 renew해 준다. client는  0~10까지 들어간다.
		renewFile();
		
	}
	public void deleteClient(int key){
		clientsList.remove(key);
		renewFile();
	}
	/*public 파일에서 client정보를 맵에 받아오는 함수(){
		// 파일 입출력
	}*/
	public void selectClient(int clientKey){
		selectedClient = clientsList.get(clientKey);
	} // client 선택
	
	public void selectBank(int whickBank){
		if(whickBank == 1){
			selectedClient.selectBank(new KBBank());
		}else if(whickBank == 2){
			selectedClient.selectBank(new NHBank());
		}else
			selectedClient.selectBank(new WooriBank());
	}
	
	public boolean haveAccount(){
		if(selectedClient.getAccountNumber() == "0"){
			return false;
		}else
			return true;
	}
}
