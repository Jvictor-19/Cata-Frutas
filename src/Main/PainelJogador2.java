package Main;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PainelJogador2 extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel label;
    private JButton botaoSortear;

    public PainelJogador2() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 600)); // Tamanho do painel de status do jogador 2

        label = new JLabel("Jogador 2: 0 passos");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        botaoSortear = new JButton("Sortear Passos");
        botaoSortear.addActionListener(e -> sortearPassos());
        add(botaoSortear, BorderLayout.SOUTH);

        setBackground(Color.LIGHT_GRAY); // Cor de fundo
    }

    private void sortearPassos() {
        int passos = new Random().nextInt(6) + 1; // Número aleatório entre 1 e 6
        label.setText("Jogador 2: " + passos + " passos");
    }
}
