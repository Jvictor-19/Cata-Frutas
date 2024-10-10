package Main;

import javax.swing.*;
import Fonte.FontePixel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import musica.Music; // Importando a classe Music

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/**
 * Classe que representa a tela inicial do jogo "Cata Frutas".
 * A tela inicial permite ao usuário escolher entre importar um arquivo para iniciar o jogo
 * ou configurar manualmente as definições do jogo.
 */
public class TelaInicial extends JFrame {

    private boolean soundOn = true;
    private Music music; // Instância da classe Music

    /**
     * Construtor que inicializa a tela inicial do jogo.
     * Define os botões de controle e o layout da janela.
     */
    public TelaInicial() {
        // Configurações da janela
        setTitle("Home Screen");
        setSize(1472, 832);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Instanciar a classe Music
        music = new Music();
        tocarMusica("musica/homescreen.wav"); // Tocar música de fundo

        // Configurar o fundo da tela
        JLabel background = new JLabel(new ImageIcon("src/imagens/backgraund1.jpg"));
        background.setBounds(0, 0, 1472, 832);
        add(background);

        // Botão de som no canto superior esquerdo
        JLabel soundLabel = new JLabel();
        ImageIcon soundOnIcon = new ImageIcon("src/imagens/alto.png");
        ImageIcon soundOffIcon = new ImageIcon("src/imagens/mudo.png");

        int iconWidth = 50; 
        int iconHeight = 50; 
        soundLabel.setIcon(new ImageIcon(soundOnIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH)));
        soundLabel.setBounds(10, 10, iconWidth, iconHeight);
        background.add(soundLabel);

        soundLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                soundOn = !soundOn;
                soundLabel.setIcon(new ImageIcon((soundOn ? soundOnIcon : soundOffIcon).getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH)));

        JButton soundButton = new JButton("Som: Ligado");
        soundButton.setBounds(10, 10, 120, 30);
        soundButton.addActionListener(e -> {
            soundOn = !soundOn;
            if (soundOn) {
                tocarMusica("musica/homescreen.wav");
                soundButton.setText("Som: Ligado");
            } else {
                pararMusica();
                soundButton.setText("Som: Desligado");

            }
        });

        // Botão Jogar
        JButton playButton = new JButton("Jogar");
        playButton.setFont(FontePixel.carregarFontePixel(10));
        playButton.setBounds(680, 400, 120, 50); 
        background.add(playButton);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame gameFrame = new JFrame("Game");

        playButton.setBounds(340, 400, 120, 50);

        playButton.addActionListener(e -> {
            // Opções a serem apresentadas ao usuário
            String[] options = {"Importar Arquivo", "Definir Configurações"};

            // Exibe o JOptionPane com as opções
            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma opção:",
                    "Configurações do Jogo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            // Verifica a escolha do usuário
            if (escolha == 0) {
                // Se o usuário escolher "Importar Arquivo"
                JFileChooser fileChooser = new JFileChooser();
                int retorno = fileChooser.showOpenDialog(null);
                if (retorno == JFileChooser.APPROVE_OPTION) {
                    File arquivo = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Arquivo importado: " + arquivo.getName());

                    // Iniciar o jogo com base no arquivo importado
                    try {
                        JFrame gameWindow = new JFrame("Cata Frutas");
                        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        gameWindow.setResizable(false);

                        // Passa o caminho do arquivo selecionado para a TelaJogo
                        TelaJogo gamePainel = new TelaJogo(arquivo.getAbsolutePath());
                        gameWindow.getContentPane().add(gamePainel);
                        gameWindow.pack();
                        gameWindow.setLocationRelativeTo(null);
                        gameWindow.setVisible(true);

                        // Fechar a tela inicial
                        setVisible(false);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum arquivo foi selecionado.");
                }
            } else if (escolha == 1) {
                // Se o usuário escolher "Definir Configurações"
                setVisible(false); // Esconde a tela inicial
                JFrame gameFrame = new JFrame("Configuração do Jogo");
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setSize(800, 600);
                gameFrame.setLocationRelativeTo(null);
                gameFrame.add(new Configuração()); 
                gameFrame.setVisible(true);
            }
        });

/*<<<<<<< irandi_branch
        // Botão Importar Arquivo
        JButton importButton = new JButton("Importar");
        importButton.setFont(FontePixel.carregarFontePixel(10));
        importButton.setBounds(680, 470, 120, 50); 
        background.add(importButton);

        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pathToGameFolder = System.getProperty("user.dir") + "/src/cata frutas/arquivo";
                File gameFolder = new File(pathToGameFolder);
                JFileChooser fileChooser = new JFileChooser(gameFolder);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Arquivo selecionado: " + selectedFile.getAbsolutePath());
                    try {
                        JFrame gameWindow = new JFrame("Cata Frutas");
                        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        gameWindow.setResizable(false);
                        TelaJogo gamePainel = new TelaJogo(selectedFile.getAbsolutePath());
                        gameWindow.getContentPane().add(gamePainel);
                        gameWindow.pack();
                        gameWindow.setLocationRelativeTo(null);
                        gameWindow.setVisible(true);

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
=======*/
        background.add(playButton);

        // Botão Créditos
        JButton creditsButton = new JButton("Créditos");
        creditsButton.setFont(FontePixel.carregarFontePixel(10));
        creditsButton.setBounds(680, 550, 120, 50); 
        background.add(creditsButton);

     // Carrega a imagem original de créditos
        ImageIcon originalIcon = new ImageIcon("src/imagens/FundoCreditos.png");

        // Redimensiona a imagem para o tamanho desejado (por exemplo, 1600x900)
        Image scaledImage = originalIcon.getImage().getScaledInstance(900, 900, Image.SCALE_SMOOTH);

        // Cria um novo ImageIcon com a imagem redimensionada
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // JLabel para exibir a imagem de créditos com a nova imagem redimensionada
        JLabel creditsImageLabel = new JLabel(scaledIcon);
        creditsImageLabel.setBounds(0, 0, 1500, 900); // Ajuste o tamanho do JLabel para corresponder à nova imagem
        creditsImageLabel.setVisible(false); // Inicialmente invisível
        add(creditsImageLabel);

        // Ação do botão Créditos
        creditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                background.setVisible(false); // Esconde o fundo principal
                creditsImageLabel.setVisible(true); // Mostra a imagem de créditos
            }
        });

        
        // Botão "X" para fechar os créditos
        JButton closeCreditsButton = new JButton("X");
        closeCreditsButton.setFont(FontePixel.carregarFontePixel(15));
        closeCreditsButton.setBounds(1400, 20, 50, 50);
        closeCreditsButton.setFocusable(false);
        closeCreditsButton.setVisible(false); // Inicialmente invisível
        closeCreditsButton.setBackground(Color.BLACK); // Cor de fundo (ex: vermelho)
        closeCreditsButton.setForeground(Color.RED); // Cor do texto (ex: branco)
        closeCreditsButton.setBorderPainted(false); // Remover a borda pintada
        closeCreditsButton.setFocusPainted(false); // Remover a borda de foco ao clicar
        add(closeCreditsButton);
        
        closeCreditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creditsImageLabel.setVisible(false); // Esconde a imagem de créditos
                background.setVisible(true); // Mostra o fundo principal
                closeCreditsButton.setVisible(false);
            }
        });

        creditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                background.setVisible(false); // Esconde o fundo principal
                creditsImageLabel.setVisible(true); // Mostra a imagem de créditos
                closeCreditsButton.setVisible(true); // Mostra o botão "X"
            }
        });
    }

/*<<<<<<< irandi_branch
 
    public static void main(String[] args) {
        // Executa a GUI em um thread separado para evitar problemas de thread
        SwingUtilities.invokeLater(() -> {
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.setVisible(true);
        });
=======*/
    /**
     * Método para tocar música de fundo usando a classe Music.
     * @param arquivo Caminho para o arquivo de som
     */
    private void tocarMusica(String arquivo) {
        try {
            music.play(arquivo);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para parar a música.
     */
    private void pararMusica() {
        music.stop();
    }
}
