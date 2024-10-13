package Main;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PainelJogador extends JPanel {

    private JLabel jogador1Label;
    private JLabel jogador2Label;
    private JButton botaoSortear;
    private Random random;

    public PainelJogador() {
        this.setLayout(new GridLayout(1, 3, 10, 10)); // Três colunas para os dois jogadores e o botão "Sortear"
        this.setPreferredSize(new Dimension(400, 80)); // Tamanho do painel
        this.setBackground(Color.LIGHT_GRAY);

        random = new Random();

        // Informações do Jogador 1
        jogador1Label = new JLabel("Jogador 1: 0 passos");
        this.add(jogador1Label);

        // Informações do Jogador 2
        jogador2Label = new JLabel("Jogador 2: 0 passos");
        this.add(jogador2Label);

        // Botão "Sortear"
        botaoSortear = new JButton("Sortear");
        botaoSortear.addActionListener(e -> realizarSorteio());
        this.add(botaoSortear);
    }

    // Método para atualizar os passos do Jogador 1
    public void atualizarPassosJogador1(int passos) {
        jogador1Label.setText("Jogador 1: " + passos + " passos");
    }

    // Método para atualizar os passos do Jogador 2
    public void atualizarPassosJogador2(int passos) {
        jogador2Label.setText("Jogador 2: " + passos + " passos");
    }

    // Método para realizar o sorteio
    private void realizarSorteio() {
        int sorteado = random.nextInt(2) + 1; // Sorteia um número entre 1 e 2
        JOptionPane.showMessageDialog(null, "Jogador " + sorteado + " foi sorteado!");
    }
}
