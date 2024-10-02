package Main;
//Compppp
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TelaJogo extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;

    final int originalTilesize = 16; 
    final int scale = 3; 
    final int tilesize = originalTilesize * scale;
    
    int maxScreenCol;
    int maxScreenRow;
    int screenWidth;
    int screenHeight;

    Thread gameThread;

    // Construtor que recebe a dimensão da floresta
    public TelaJogo(int n) {
        this.maxScreenCol = n;
        this.maxScreenRow = n;
        this.screenWidth = tilesize * maxScreenCol;
        this.screenHeight = tilesize * maxScreenRow;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.lightGray); 
        this.setDoubleBuffered(true); 

        // Tenta carregar as configurações de arquivo
        carregarConfiguracoes();

        startGameThread();
    }

    private void carregarConfiguracoes() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Arquivo/configuracaoJogo.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha); // Aqui você pode adicionar lógica para configurar o terreno com base no arquivo
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); 
    }

    @Override
    public void run() {
        while (gameThread != null) {
            updateGame();
            repaint(); 
            try {
                Thread.sleep(1000 / 60); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Método responsável por atualizar o estado do jogo
    private void updateGame() {
        // Lógica para atualizar o estado do jogo
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Lógica para desenhar o terreno, as pedras, etc.
        for (int linha = 0; linha < maxScreenRow; linha++) {
            for (int coluna = 0; coluna < maxScreenCol; coluna++) {
                // Desenha um quadrado colorido ou uma letra
                g.setColor(Color.BLACK); // Mude a cor se desejar
                g.fillRect(coluna * tilesize, linha * tilesize, tilesize, tilesize);
                
                // Para desenhar letras
                g.setColor(Color.WHITE);
                g.drawString("S", coluna * tilesize + 5, linha * tilesize + 15); // Ajuste a posição da letra
            }
        }
    }
}
