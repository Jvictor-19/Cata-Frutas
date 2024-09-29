package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePainel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
    
    final int originalTilesize = 16;
    final int scale = 3;
    
    final int tilesize = originalTilesize * scale;
    final int maxScreenCol;
    final int maxScreenRow;
    final int screenWidth;
    final int screenHeight;

    Thread gameThread;

    public GamePainel(int n) {
        // Define a dimensão da floresta como n x n
        maxScreenCol = n;
        maxScreenRow = n;
        screenWidth = tilesize * maxScreenCol;
        screenHeight = tilesize * maxScreenRow;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.lightGray);
        this.setDoubleBuffered(true);
        
        // Iniciar o thread do jogo
        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Lógica para desenhar os elementos do jogo
        // Exemplo: desenhar a floresta com tiles, personagens, etc.
    }
}
