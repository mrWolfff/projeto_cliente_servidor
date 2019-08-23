package trabalho_01_SD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ThreadWork extends Thread implements Runnable {

	private Socket cliente;
	private String mensagem = "";
	private Map<String, Integer> listaMapa;

	public ThreadWork(Socket cliente, Map<String, Integer> listaMapa) {
		this.cliente = cliente;
		this.listaMapa = listaMapa;
	}

	@Override
	public void run() {
		try {
			

			System.out.println("Cliente conectado! IP - " + cliente.getInetAddress().getHostAddress());
			
			PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);
			BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			String linha = entrada.readLine();
			System.out.println(linha.toUpperCase());
			
			if(listaMapa.containsKey(linha.toUpperCase())) {
				saida.println("LUCAS" + ";" + listaMapa.get(linha.toUpperCase()));
			}else {
				saida.println("Não existe seu nome na lista ;-; ");
			}
			
			

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			try {
				cliente.close();
			} catch (IOException e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}

	}

}
