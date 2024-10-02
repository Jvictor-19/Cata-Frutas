package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
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

    // Caminho para a imagem do tile
    private ImageIcon tileImage;

    // Construtor que recebe a dimensão da floresta
    public TelaJogo(int n) {
        this.maxScreenCol = n;
        this.maxScreenRow = n;
        this.screenWidth = tilesize * maxScreenCol;
        this.screenHeight = tilesize * maxScreenRow;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.lightGray); 
        this.setDoubleBuffered(true); 

        // Carrega a imagem do tile
        tileImage = new ImageIcon("src/imagens/grama.png"); // Atualize com o caminho da sua imagem

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
                // Desenha a imagem do tile
                g.drawImage(tileImage.getImage(), coluna * tilesize, linha * tilesize, tilesize, tilesize, null);
            }
        }
    }
}
