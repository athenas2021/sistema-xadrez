package principal;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class ProgramaPrincipal {
	
	public static void main(String[] args) {
		
		Posicao pos = new Posicao(3, 5);
		Tabuleiro tabuleiro = new Tabuleiro(8,8);
		System.out.println(pos);
	}
}
