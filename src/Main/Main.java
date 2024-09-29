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
		
		//Título da janela como "Cata Frutas"
		window.setTitle("Cata Frutas");
		
		// -----------
		//Cria uma instância de 'GamePainel', que é onde o jogo será desenhado
		//GamePainel gamePainel = new GamePainel(5);
		// Exibe o menu inicial para configurar o jogo
        MenuInicial menuInicial = new MenuInicial();
        window.add(menuInicial);
		
		// Adiciona o 'GamePainel' à janela para que seja exibido
		//window.add(gamePainel);
		
		// Ajusta o tamanho da janela de acordo com o tamanho preferido do 'GamePainel'
		window.pack();
		
		window.setLocationRelativeTo(null); //Centralizar a janela na tela
		window.setVisible(true); //Tornar a janela visível para o usuário
		
		//gamePainel.startGameThread();
	}

}
