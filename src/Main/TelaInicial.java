package Main;

import javax.swing.*;
import Fonte.FontePixel;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.Window;

/**
 * Classe que representa a tela inicial do jogo "Cata Frutas".
 * A tela inicial permite ao usuário escolher entre importar um arquivo para iniciar o jogo
 * ou configurar manualmente as definições do jogo.
 */
public class TelaInicial extends JFrame {

    private boolean soundOn = true;

    /**
     * Construtor que inicializa a tela inicial do jogo.
     * Define os botões de controle e o layout da janela.
     */
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
        soundButton.addActionListener(e -> {
            soundOn = !soundOn;
            soundButton.setText("Som: " + (soundOn ? "Ligado" : "Desligado"));
        });
        background.add(soundButton);

        // Botão Jogar
        JButton playButton = new JButton("Jogar");
        playButton.setFont(FontePixel.carregarFontePixel(10));
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
                gameFrame.add(new Configuração()); // Adiciona o painel de configuração
                gameFrame.setVisible(true);
            }
        });

        background.add(playButton);

        // Botão Créditos
        JButton creditsButton = new JButton("Créditos");
        creditsButton.setFont(FontePixel.carregarFontePixel(10));
        creditsButton.setBounds(340, 500, 120, 50);
        background.add(creditsButton);

        // Tornar visível
        setVisible(true);
    }
}
