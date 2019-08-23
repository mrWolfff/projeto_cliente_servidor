package trabalho_01_SD;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Cliente {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket cliente = new Socket();
		ArrayList<String> listaServers = new ArrayList<String>();
		Map<String, String> listaMap = new HashMap<String, String>();
		String ipServers = "";
		for (int i = 104; i < 119; i++) {
			ipServers = "192.168.1." + i;

			if (InetAddress.getByName(ipServers).isReachable(100)) {
				System.out.println("IP " + ipServers + " OK");
				listaServers.add(ipServers);
			} else {
				System.out.println("IP " + ipServers + " Não deu");
			}
		}
		System.out.println("");
		System.out.println("Os Server conectados são: " + listaServers);
		System.out.println("");
		String array[] = new String[2];
		for (int i = 0; i < listaServers.size(); i++) {
			try {
				cliente = new Socket((String) listaServers.get(i), 6666);
				PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);
				BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				saida.println("lucas");
				String linha = entrada.readLine();
				System.out.println("IP - " + listaServers.get(i) + " - " + linha);
				String frase = linha;
				array = frase.split(";");
				
				listaMap.put(array[0].toUpperCase(), array[1].toUpperCase());
				cliente.close();
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
			
			
		}
		System.out.println("");
		System.out.println("Mapa dos nomes e codigo dos servidores:"+ listaMap);

	}
}