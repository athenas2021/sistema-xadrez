package principal;

import xadrez.PartidaXadrez;

public class ProgramaPrincipal {
	
	public static void main(String[] args) {
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		UI.imprimeTabuleiro(partidaXadrez.getPecas());
		
	} 
}
