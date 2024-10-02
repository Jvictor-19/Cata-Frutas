package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class TelaJogo extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;

    final int tamanhoTileOriginal = 16; // Tamanho original do tile
    final int escala = 3; // Fator de escala para o tamanho do tile
    final int tamanhoTile = tamanhoTileOriginal * escala; // Tamanho do tile escalado
    final int maxColunasTela; // Colunas máximas na tela
    final int maxLinhasTela; // Linhas máximas na tela
    final int larguraTela; // Largura da tela
    final int alturaTela; // Altura da tela

    Thread threadJogo; // Thread do jogo

    // Construtor que recebe a dimensão da floresta
    public TelaJogo(int n) {
        maxColunasTela = n;
        maxLinhasTela = n;
        larguraTela = tamanhoTile * maxColunasTela;
        alturaTela = tamanhoTile * maxLinhasTela;

        this.setPreferredSize(new Dimension(larguraTela, alturaTela));
        this.setBackground(Color.lightGray);
        this.setDoubleBuffered(true);

        iniciarThreadJogo(); // Inicia a thread do jogo
    }

    // Inicia a thread do jogo
    public void iniciarThreadJogo() {
        threadJogo = new Thread(this);
        threadJogo.start(); // Inicia a thread do jogo
    }

    @Override
    public void run() {
        while (threadJogo != null) {
            atualizarJogo();
            repaint(); // Chama o método paintComponent() para desenhar o painel
            try {
                Thread.sleep(1000 / 60); // Tenta rodar a 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Método responsável por atualizar o estado do jogo
    private void atualizarJogo() {
        // Lógica para atualizar o estado do jogo
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha a matriz de imagens (tiles)
        for (int linha = 0; linha < maxLinhasTela; linha++) {
            for (int coluna = 0; coluna < maxColunasTela; coluna++) {
                // Desenha um quadrado colorido ou uma letra
                g.setColor(Color.RED); // Mude a cor se desejar
                g.fillRect(coluna * tamanhoTile, linha * tamanhoTile, tamanhoTile, tamanhoTile);
                
                // Para desenhar letras
                g.setColor(Color.BLACK);
                g.drawString("S", coluna * tamanhoTile + 5, linha * tamanhoTile + 15); // Ajuste a posição da letra
            }
        }
    }
}
