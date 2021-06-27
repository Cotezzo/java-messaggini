package serializableTestServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadServer extends Thread{
	int outToClientIndex;
	int ThreadID;
	String nomeClient;
	Socket socket;
	BufferedReader inFromClient;	
	ArrayList<PrintWriter> outToClient; 			//Gestita da main per inoltrare a tutti
	//ArrayList<ThreadServer> serverThread;

	public ThreadServer(int ThreadID, Socket socket, ArrayList<PrintWriter> outToClient) {//, ArrayList<ThreadServer> serverThread){
		this.ThreadID = ThreadID;
		this.outToClient = outToClient;
		this.socket = socket;
		//this.serverThread = serverThread;
	}
	
	@Override
	public void run(){
		try {
			String msgDaInoltrare, msgArrivato;
			synchronized(outToClient){
				outToClient.add(new PrintWriter(socket.getOutputStream(), true));
				outToClientIndex = outToClient.size() - 1;
			}
			inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));	//Stream in entrata da client
			System.out.printf("\nConnessione con [%s:%s] avvenuta.", socket.getInetAddress().getHostAddress(), socket.getPort());
			
			boolean play=true;
			
			outToClient.get(outToClientIndex).println("Inserisci il tuo nome: ");
			nomeClient = inFromClient.readLine();
			outToClient.get(outToClientIndex).println("Benvenuto in Whatsapp1/2, " + nomeClient + "! ");
			while(play){
				msgArrivato = inFromClient.readLine();
				//comando chiusura
				msgDaInoltrare = nomeClient + ": " + msgArrivato;
				System.out.printf("\nMessaggio da inoltrare: %s", msgDaInoltrare);
				for (int i=0; i<outToClient.size(); i++){							//Inoltro a tutti gli outToClient dell'ArrayList 
					if(i!=outToClientIndex)
						outToClient.get(i).println(msgDaInoltrare);
				}
			}
			socket.close();
			//serverThread.remove(ThreadID);
		}catch(Exception e){}
	}
}
