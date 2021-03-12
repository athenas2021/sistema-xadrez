package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez{

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override 
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0,0);
		
		//NOROESTE - NW
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() - 1, p.getColuna() - 1);
		}
		if(getTabuleiro().posicaoExiste(p) && aquiUmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//NORDESTE - NE
		p.setValues(posicao.getLinha() - 1 , posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() - 1, p.getColuna() + 1);
		}
		if(getTabuleiro().posicaoExiste(p) && aquiUmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//SUDESTE - SE
		p.setValues(posicao.getLinha() + 1 , posicao.getColuna()+ 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() + 1, p.getColuna() + 1);
		}
		if(getTabuleiro().posicaoExiste(p) && aquiUmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//SUDOESTE - SW
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() + 1, p.getColuna() - 1);
		}
		if(getTabuleiro().posicaoExiste(p) && aquiUmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
			
		return mat;
	
	}	
}
