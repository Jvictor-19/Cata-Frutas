package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends JFrame {

    private static final long serialVersionUID = 1L; // Adicionando serialVersionUID
    private boolean soundOn = true;

    public TelaInicial() {
        // Configurações da janela
        setTitle("Home Screen");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Criar um painel para o fundo
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Desenhar a imagem de fundo
                ImageIcon img = new ImageIcon("src/imagens/backgraund.jpeg"); // Ajuste o caminho da imagem
                g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        background.setLayout(null); // Definindo layout nulo para adicionar os botões
        add(background, BorderLayout.CENTER);

        // Botão de som no canto superior esquerdo
        JButton soundButton = new JButton("Som: Ligado");
        soundButton.setBounds(10, 10, 120, 30);
        soundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundOn = !soundOn;
                soundButton.setText("Som: " + (soundOn ? "Ligado" : "Desligado"));
            }
        });
        background.add(soundButton);

        // Botão Jogar
        JButton playButton = new JButton("Jogar");
        playButton.setBounds(340, 400, 120, 50);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Esconde a tela inicial
                JFrame gameFrame = new JFrame("Game");
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setSize(800, 600);
                gameFrame.setLocationRelativeTo(null);
                gameFrame.add(new Configuração()); // Adiciona o painel de configuração
                gameFrame.setVisible(true);
            }
        });
        background.add(playButton);

        // Botão Créditos
        JButton creditsButton = new JButton("Créditos");
        creditsButton.setBounds(340, 460, 120, 50);
        background.add(creditsButton);

        // Tornar visível
        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaInicial();
    }
}
