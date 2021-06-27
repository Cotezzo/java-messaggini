package serializableTestServer;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.Scanner;
import java.util.ArrayList;

public class MainServer{
	static final int PORT = 12345;	
	public static void main(String[] args) throws IOException {
		boolean play = true;
		int ThreadID;
		ServerSocket serverSocket = new ServerSocket(PORT);
		ArrayList<ThreadServer> serverThread = new ArrayList<ThreadServer>(); 	//Un Thread per ogni client connesso
		ArrayList<PrintWriter> outToClient = new ArrayList<PrintWriter>();		//Uno stream per ogni client, fatto array per poterlo passare ai thread e inoltrare a tutti da lì
		while(play){															//Riceve client, poi torna in attesa
			System.out.printf("WhatsApp1/2 in attesa sulla porta %s.", PORT);
			ThreadID = serverThread.size();
			serverThread.add(new ThreadServer(ThreadID, serverSocket.accept(), outToClient));
			serverThread.get(ThreadID).start();
		}
		serverSocket.close();
	}//fine main
}