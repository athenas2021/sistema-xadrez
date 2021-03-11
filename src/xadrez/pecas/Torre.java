package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez{

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override 
	public String toString() {
		return "T";
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0,0);
		
		//acima da pe�a
		p.setValues(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if(getTabuleiro().posicaoExiste(p) && aquiUmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//esquerda da pe�a
		p.setValues(posicao.getLinha() , posicao.getColuna()- 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if(getTabuleiro().posicaoExiste(p) && aquiUmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//direita da pe�a
		p.setValues(posicao.getLinha() , posicao.getColuna()+ 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if(getTabuleiro().posicaoExiste(p) && aquiUmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		//abaixo da pe�a
		p.setValues(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().aquiUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if(getTabuleiro().posicaoExiste(p) && aquiUmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
			
		return mat;
	
	}	
}
