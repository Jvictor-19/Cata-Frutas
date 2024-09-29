package Main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// Cria uma nova janela (JFrame) que será a interface principal do jogo
		JFrame window = new JFrame();
		
		// Define a ação de fechar o programa ao clicar no botão de fechar a janela
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Impede que a janela seja redimensionada pelo usuário
		window.setResizable(false);
		window.setTitle("Cata Frutas");
		
		GamePainel gamePainel = new GamePainel();
		window.add(gamePainel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

}
