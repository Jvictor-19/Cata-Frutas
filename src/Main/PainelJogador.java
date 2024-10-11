package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class PainelJogador extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel jogador1Label;
    private JLabel jogador2Label;
    private JButton botaoSortearJogador1;
    private JButton botaoSortearJogador2;

    public PainelJogador() {
        setLayout(new GridLayout(4, 1)); // Exibir as informações e botões em quatro linhas
        setPreferredSize(new Dimension(200, 600)); // Definir o tamanho fixo do painel

        // Informações do jogador 1
        jogador1Label = new JLabel("Jogador 1: 0 passos");
        jogador1Label.setHorizontalAlignment(SwingConstants.CENTER);
        add(jogador1Label);

        // Botão para sortear passos do jogador 1 com ícone de dado
        botaoSortearJogador1 = new JButton();
        botaoSortearJogador1.setVerticalAlignment(SwingConstants.BOTTOM);
        botaoSortearJogador1.setIcon(redimensionarImagem("src/imagens/dado.jpeg", 50, 50));
        botaoSortearJogador1.setPreferredSize(new Dimension(10, 50)); // Define o tamanho do botão
        botaoSortearJogador1.addActionListener(e -> sortearPassosJogador1());
        add(botaoSortearJogador1);

        // Informações do jogador 2
        jogador2Label = new JLabel("Jogador 2: 0 passos");
        jogador2Label.setHorizontalAlignment(SwingConstants.CENTER);
        add(jogador2Label);

        // Botão para sortear passos do jogador 2 com ícone de dado
        botaoSortearJogador2 = new JButton();
        botaoSortearJogador2.setIcon(redimensionarImagem("src/imagens/dado.jpeg", 50, 50));
        botaoSortearJogador2.setPreferredSize(new Dimension(100, 50)); // Define o tamanho do botão
        botaoSortearJogador2.addActionListener(e -> sortearPassosJogador2());
        add(botaoSortearJogador2);

        // Definir uma cor de fundo para diferenciar o painel
        setBackground(Color.LIGHT_GRAY);
    }

    // Método para redimensionar a imagem do dado
    private ImageIcon redimensionarImagem(String caminhoImagem, int largura, int altura) {
        try {
            BufferedImage imagemOriginal = ImageIO.read(new File(caminhoImagem));
            Image imagemRedimensionada = imagemOriginal.getScaledInstance(largura*4, altura*4, Image.SCALE_SMOOTH);
            return new ImageIcon(imagemRedimensionada);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para sortear passos para o jogador 1
    private void sortearPassosJogador1() {
        int passos = new Random().nextInt(6) + 1; // Número aleatório entre 1 e 6
        jogador1Label.setText("Jogador 1: " + passos + " passos");
    }

    // Método para sortear passos para o jogador 2
    private void sortearPassosJogador2() {
        int passos = new Random().nextInt(6) + 1; // Número aleatório entre 1 e 6
        jogador2Label.setText("Jogador 2: " + passos + " passos");
    }

    // Métodos para atualizar as informações dos jogadores
    public void atualizarInformacoesJogador1(String info) {
        jogador1Label.setText(info);
    }

    public void atualizarInformacoesJogador2(String info) {
        jogador2Label.setText(info);
    }
}
