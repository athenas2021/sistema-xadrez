package principal;

import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class ProgramaPrincipal {
	
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		while(true) {
			UI.imprimeTabuleiro(partidaXadrez.getPecas());
			System.out.println();
			System.out.print("Origem: ");
			PosicaoXadrez origem = UI.leiaPosicaoXadrez(sc);
			
			System.out.println();
			System.out.print("Destino: ");
			PosicaoXadrez destino = UI.leiaPosicaoXadrez(sc);
			
			PecaXadrez pecaCapturada = partidaXadrez.moverPecaXadrez(origem, destino);
		}
		
	} 
}
