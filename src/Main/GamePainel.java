package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePainel extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;

    final int originalTilesize = 16; // Tamanho original do tile
    final int scale = 3; // Fator de escala para o tamanho do tile

    final int tilesize = originalTilesize * scale; // Tamanho do tile escalado
    final int maxScreenCol; // Colunas máximas na tela
    final int maxScreenRow; // Linhas máximas na tela
    final int screenWidth; // Largura da tela
    final int screenHeight; // Altura da tela

    Thread gameThread; // Thread do jogo

    public GamePainel(int n) {
        
        // Define a dimensão da floresta como n x n
        maxScreenCol = n;
        maxScreenRow = n;
        screenWidth = tilesize * maxScreenCol;
        screenHeight = tilesize * maxScreenRow;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.lightGray); // Cor de fundo do painel
        this.setDoubleBuffered(true); // Permite o uso de buffer duplo para melhor desempenho

        // Iniciar o thread do jogo
        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); // Inicia a thread do jogo
    }

    @Override
    public void run() {
        // O loop principal do jogo deve ser implementado aqui
        while (gameThread != null) {
            // Atualizar o estado do jogo e redesenhar a tela
            updateGame();
            repaint(); // Chama o método paintComponent() para desenhar o painel
            try {
                Thread.sleep(1000 / 60); // Tenta rodar a 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateGame() {
        // Lógica para atualizar o estado do jogo
        // Adicione a lógica de atualização aqui
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Lógica para desenhar os elementos do jogo
        // Exemplo: desenhar a floresta com tiles, personagens, etc.
    }
}
