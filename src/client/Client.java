package client;

import bank.Bank;

public class Client {
	int asset; // ���࿡ �Ա����� ���� ������
	String name;
	Bank bank;
	String accountNumber;
	
	public String GetAccountNumber(){ // ���¹�ȣ ��ȯ
		return accountNumber;
	}
	public void Client(String Name, int money){ // ������
		asset = money;
		name = Name;
	}
	public void selectBank(Bank banks){ // ���� ����
		bank = banks;
	}
	public String getName(){
		return name;
	}
	public int getAsset(){
		return asset;
	}
	public void deposit(int money){ // ����
		if(money < asset){
			System.out.println("�ݾ��� �����մϴ�.");
			return;
		}
		asset -= money;
		bank.deposit(accountNumber, money);
	}
	public void withdraw(int money){
		if(bank.withdraw(accountNumber, money)){
			asset += money;
		} // ��ݿ� ������������ �����ڻ��� ��ȭ��
	}
	public void transfer(int money){ // �ڱ� ���¿��� �۱�
		
	}
	public void transfer_without_bankbook(int money, String transferNum){ // ������۱�
		
	}
	public void closeAccount(){
		asset += bank.closeAccount(accountNumber); // ���¸� ����ϸ� 
	}
	public void openAccount(){
		bank.openAccount(accountNumber, name);
	}
}
