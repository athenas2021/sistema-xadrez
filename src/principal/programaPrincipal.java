package principal;

import java.util.InputMismatchException;
import java.util.Scanner;

import tabuleiro.TabuleiroException;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class ProgramaPrincipal {
	
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		while(true) {
			try {	
									
				UI.ClearScreen();
				UI.imprimeTabuleiro(partidaXadrez.getPecas());
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.leiaPosicaoXadrez(sc);
				
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.leiaPosicaoXadrez(sc);
				
				PecaXadrez pecaCapturada = partidaXadrez.moverPecaXadrez(origem, destino);
			
			}
			catch(TabuleiroException e) {
				 System.out.println(e.getMessage());
				 sc.nextLine();
			}
			catch(InputMismatchException e) {
				 System.out.println(e.getMessage());
				 sc.nextLine();
			}
		}
		
	} 
}
