package principal;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import tabuleiro.TabuleiroException;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class ProgramaPrincipal {
	
	public static void main(String[] args) {		
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		List<PecaXadrez> capturada = new ArrayList<>();
		
		while(!partidaXadrez.getChequeMate()) {
			try {										
				UI.ClearScreen();
				UI.imprimePartida(partidaXadrez, capturada);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.leiaPosicaoXadrez(sc);
				
				boolean[][] possiveisMovimentos = partidaXadrez.possiveisMovimentos(origem);
				UI.ClearScreen();
				UI.imprimeTabuleiro(partidaXadrez.getPecas(),possiveisMovimentos);
				
				
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.leiaPosicaoXadrez(sc);
				
				PecaXadrez pecaCapturada = partidaXadrez.moverPecaXadrez(origem, destino);
			
				if(pecaCapturada != null) {
					capturada.add(pecaCapturada);
				}
				
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
		UI.ClearScreen();
		UI.imprimePartida(partidaXadrez, capturada);
	} 
}
