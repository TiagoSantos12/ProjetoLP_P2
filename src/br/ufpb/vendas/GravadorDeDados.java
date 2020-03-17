package br.ufpb.vendas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GravadorDeDados {
	
	public static void main(String [] args) {
		
		List<String> lista = new ArrayList<String>();
		GravadorDeDados gravador = new GravadorDeDados();
		try {
			lista = gravador.recuperaTextoEmArquivo("teste.txt");
			System.out.println("Texto recuperado com sucesso:");
			for (String t: lista) {
				System.out.println(t);
			}
		} catch (IOException e) {
			System.err.println("Não foi possível recuperar texto do arquivo.");
		}
		
		Scanner leitor = new Scanner(System.in);
		System.out.println("Quantas linhas a adicionar no arquivo?");
		int quantLinhas = Integer.parseInt(leitor.nextLine());
		for (int k=0; k< quantLinhas; k++) {
			System.out.println("Digite a linha "+(k+1));
			lista.add(leitor.nextLine());
		}
		
		try {
			gravador.gravaTextoEmArquivo(lista, "teste.txt");
		} catch(IOException e) {
			e.printStackTrace();
		}
		leitor.close();
		
	}
	
	public List<String> recuperaTextoEmArquivo(String nomeArquivo) 
			throws IOException {
		List<String> linhasLidas = new ArrayList<String>();
		BufferedReader leitor = null;
		try {
			leitor = new BufferedReader(new FileReader(nomeArquivo));
			String linha = null;
			do {
				linha = leitor.readLine();
				if (linha!=null) {
					linhasLidas.add(linha);
				}
			} while(linha!=null);
			
		} finally {
			if (leitor != null) {
				leitor.close();
			}
		}
		return linhasLidas;
	}
	
	
	public void gravaTextoEmArquivo(List<String> texto, String nomeArquivo) 
			throws IOException {
		
		BufferedWriter escritor = null;
		try {
			escritor = new BufferedWriter(new FileWriter(nomeArquivo));
			for (String s: texto) {
				escritor.write(s+"\n");
			}
		} finally {
			if (escritor != null) {
				escritor.close();
			}
		}
	}

}