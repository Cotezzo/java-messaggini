package serializableTestClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
	public static void main(String[] args) throws IOException {
		String address = "localhost"; 	//indirizzo del server
		int port=12345;					//porta del server
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("Tentativo connessione a WhatsApp1/2 [%s:%s].", address, port);
		Socket socket = new Socket(address, port);
		System.out.printf("\nConnessione tra CLIENT [%s:%s] e SERVER [%s:%s].", socket.getLocalAddress().getHostAddress(), socket.getLocalPort(), socket.getInetAddress().getHostAddress(), socket.getPort());
		PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);							//Stream in uscita verso server
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));	//Stream in entrata da server
		
		boolean play=true;
		String msgDaInviare;
		new ThreadClientReceiver(inFromServer).start();
		while(play) {
			msgDaInviare = scanner.nextLine();		//Invio il messaggio al server
			outToServer.println(msgDaInviare);
		}
		
		socket.close();
		scanner.close();
		System.out.println("Connessione terminata. ");

	}
}