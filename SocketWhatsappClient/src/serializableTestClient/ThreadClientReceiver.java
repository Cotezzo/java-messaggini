package serializableTestClient;

import java.io.BufferedReader;

public class ThreadClientReceiver extends Thread{
	BufferedReader inFromServer;
	public ThreadClientReceiver(BufferedReader inFromServer){
		this.inFromServer = inFromServer;
	}
	
	@Override
	public void run(){
		try {
			boolean play = true;
			String msgArrivato;
			while(play){
				msgArrivato = inFromServer.readLine();			//Leggo e stampo quello che manda il server
				System.out.println(msgArrivato);
			}
		}catch(Exception e){}
	}
}
