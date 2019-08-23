package trabalho_01_SD;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Servidor extends Thread {
	public static void main(String[] args) {
		Map<String, Integer> listaMapa = new HashMap<String, Integer>();
		listaMapa.put("LUCAS", 2);
		listaMapa.put("EVELIN", 4);
		listaMapa.put("MATEUS", 8);
		listaMapa.put("RAFAEL", 16);
		listaMapa.put("JOHN", 32);
		listaMapa.put("SILVIO", 64);
		listaMapa.put("KARINE", 128);
		listaMapa.put("FERNANDO", 256);
		listaMapa.put("TIAGO", 512);
		listaMapa.put("THOMAS", 1024);
		listaMapa.put("GUSTAVO", 2048);
		listaMapa.put("GIOVANNI", 4098);
		listaMapa.put("LUCIANO", 8192);
		try {
			ServerSocket servidor = new ServerSocket(6666);
			System.out.println("PORTA 6666 ABERTA!");
			while (true) {
				Socket cliente = servidor.accept();
				Thread clienteThread = new ThreadWork(cliente, listaMapa);
				clienteThread.start();
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {

		}
	}
}