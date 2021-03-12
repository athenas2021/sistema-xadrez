package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import tabuleiro.TabuleiroException;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {	
	
	private Tabuleiro tabuleiro;	
	private int turno;
	private Cor jogadorAtual;
	private boolean cheque;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		configInicial();
	}
	
	public int getTurno() {
		return turno;
	} 
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
	public boolean getCheque() {
		return cheque;
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
	
	public boolean[][] possiveisMovimentos(PosicaoXadrez posicaoOrigem){
		Posicao posicao = posicaoOrigem.paraPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).possiveisMovimentos();
		
	}
	
	public PecaXadrez moverPecaXadrez(PosicaoXadrez	posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.paraPosicao();
		Posicao destino = posicaoDestino.paraPosicao();		
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = realizarMovimento(origem,destino);	
		
		if (testarCheque(jogadorAtual)) {
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new XadrezException("Voce nao pode se colocar em cheque");
		}
		
		cheque = (testarCheque(oponente(jogadorAtual))) ? true : false;		
		
		proximoTurno();
		return (PecaXadrez)pecaCapturada;
	}
	
	private Peca realizarMovimento(Posicao origem, Posicao alvo) {
		Peca p = tabuleiro.removePeca(origem);
		Peca pecaCapturada = tabuleiro.removePeca(alvo);
		tabuleiro.posicionarPeca(p, alvo);
		
		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);			
		}
		return (PecaXadrez)pecaCapturada;
	}
	
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		Peca p = tabuleiro.removePeca(destino);
		tabuleiro.posicionarPeca(p, origem);
		
		if (pecaCapturada != null) {
			tabuleiro.posicionarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.aquiUmaPeca(posicao)) {
			throw new TabuleiroException("Nao ha peca na local indicado");
		}
		if (jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezException("A peça escolhida nao é sua");
		}
		if(!tabuleiro.peca(posicao).aquiMovimentoPossivel()) {
			throw new XadrezException("Nao existe movimentos possiveis para peça escolhida");	 
		}
	}
	
	private void validarPosicaoDestino(Posicao origem,Posicao destino) {
		if(!tabuleiro.peca(origem).possivelMovimento(destino)		) {
			throw new XadrezException("A peça de origem nao pode se mover para o destino selecionado");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private PecaXadrez rei(Cor cor) {
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor()==cor).collect(Collectors.toList());
		for (Peca p : lista) {
			if(p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Nao ha rei da cor: " + cor +" no tabuleiro");
	}
	
	private boolean testarCheque(Cor cor) {
		Posicao posicaoRei = rei(cor).getPosicaoXadrez().paraPosicao();
		List<Peca> pecasDoOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor()==oponente(cor)).collect(Collectors.toList());
		for (Peca p : pecasDoOponente) {
			boolean[][] mat = p.possiveisMovimentos();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
		
	}
	
	private void posicionarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.posicionarPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
		pecasNoTabuleiro.add(peca);
	}
	 
	private void configInicial() {

		posicionarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

		posicionarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}
}
