package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import Fonte.FontePixel;

public class TelaInicial extends JFrame {

    private boolean soundOn = true;

    public TelaInicial() {
        // Configurações da janela
        setTitle("Home Screen");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Configurar o fundo da tela
        ImageIcon backgroundImage = new ImageIcon("src/imagens/floresta.jpg"); // caminho da imagem de fundo
        Image scaledImage = backgroundImage.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(scaledImage));
        background.setBounds(0, 0, 800, 600);
        add(background);

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
        playButton.setFont(FontePixel.carregarFontePixel(10));
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
    /*   JButton creditsButton = new JButton("Créditos");
        creditsButton.setFont(FontePixel.carregarFontePixel(10));
        creditsButton.setBounds(340, 500, 120, 50);
        background.add(creditsButton);

        // Tornar visível
      setVisible(true);  */
    }

    public static void main(String[] args) {
        new TelaInicial();
    }
}
