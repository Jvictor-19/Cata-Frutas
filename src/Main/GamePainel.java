package Main;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GamePainel extends JPanel implements Runnable{
	
	// Tamanho original de cada tile (um bloco do jogo) em pixels
	// Tile representa uma unidade de superfície na tela do jogo
	final int originalTilesize = 16;
	final int scale = 3; //Fator de escala
	
	// Tamanho dos tiles após a aplicação da escala
    final int tilesize = originalTilesize * scale;
    // Número máximo de colunas na tela
    final int maxScreenCol = 10;
    // Número máximo de linhas na tela
    final int maxScreenRow = 10;
    // Largura total da tela do jogo (em pixels)
    final int screenWidth = tilesize * maxScreenCol;
    // Altura total da tela do jogo (em pixels)
    final int screenHeight = tilesize * maxScreenRow;
    
    // Thread que controlará o loop do jogo
	Thread gameThread;
	
	// Construtor da classe
    public GamePainel() {
        
        // Define o tamanho preferido do painel do jogo (largura e altura)
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        // Define o fundo do painel como preto
        this.setBackground(Color.black);
        // Ativa o "double buffering" para melhorar o desempenho gráfico
        this.setDoubleBuffered(true);
    }
    
    // Método para iniciar a thread do jogo
    public void startGameThread() {
        // Cria uma nova thread e passa o GamePainel como o alvo a ser executado (run())
        gameThread = new Thread(this);
        // Inicia a thread, o que fará o método run() ser executado
        gameThread.start();
    }

    // Método run() que será executado quando a thread do jogo começar
    @Override
    public void run() {
        // O loop principal do jogo será implementado aqui para atualizar o estado e desenhar na tela
    }
}
