package Main;

import javax.swing.*;

import Fonte.FontePixel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Window;

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
        JLabel background = new JLabel(new ImageIcon("src.jpg"));
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

// Botão Importar Arquivo
JButton importButton = new JButton("Importar\nArquivo");
importButton.setFont(FontePixel.carregarFontePixel(10));
importButton.setBounds(360, 400, 120, 80);
importButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Caminho da pasta "cata frutas/arquivo" em relação ao diretório do projeto
        String pathToGameFolder = System.getProperty("user.dir") + "/scr/cata frutas/arquivo";
        File gameFolder = new File(pathToGameFolder);

        // Configura o JFileChooser para abrir na pasta especificada
        JFileChooser fileChooser = new JFileChooser(gameFolder);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "Arquivo selecionado: " + selectedFile.getAbsolutePath());

            // Criar a tela do jogo usando o arquivo selecionado
            try {
                JFrame gameWindow = new JFrame("Cata Frutas");
                gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameWindow.setResizable(false);

                // Passa o caminho do arquivo selecionado para a TelaJogo
                TelaJogo gamePainel = new TelaJogo(selectedFile.getAbsolutePath());
                gameWindow.getContentPane().add(gamePainel);
                gameWindow.pack();
                gameWindow.setLocationRelativeTo(null);
                gameWindow.setVisible(true);

                // Fechar a tela de configuração (usando o botão para obter a janela pai)
                Window parentWindow = SwingUtilities.getWindowAncestor(importButton);
                if (parentWindow != null) {
                    parentWindow.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Nenhum arquivo foi selecionado.");
        }
    }
});
background.add(importButton);


        // Botão Créditos
        JButton creditsButton = new JButton("Créditos");
        creditsButton.setFont(FontePixel.carregarFontePixel(10));
        creditsButton.setBounds(340, 500, 120, 50);
        background.add(creditsButton);

        // Tornar visível
        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaInicial();
    }
}