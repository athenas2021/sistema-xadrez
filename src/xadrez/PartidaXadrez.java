package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Torre;

public class PartidaXadrez {	
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		configInicial();
	}
	
	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas();i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PecaXadrez) tabuleiro.peca(i,j);
			}
		}
		return matriz;		
	}
	
	private void posicionarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.posicionarPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
	}
	 
	private void configInicial() {
		posicionarNovaPeca('b', 6, new Torre(tabuleiro, Cor.BRANCO));
	}
}
