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
    
    private ImageIcon backgroundIcon;
    private JLabel background;

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

     // Carregar a imagem de fundo
        backgroundIcon = new ImageIcon("src/imagens/backgraund1.jpg");
        background = new JLabel(backgroundIcon);
        background.setBounds(0, 0, 1472, 832);
        add(background);

        // Adicionar um listener para redimensionamento da janela
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                ajustarTamanhoFundo();
            }
        });
        
        

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

                if (soundOn) {
                    tocarMusica("musica/homescreen.wav");
                } else {
                    pararMusica();
                }
            }
        });

        //Botão Jogar
        JButton playButton = new JButton("Jogar");
        playButton.setFont(FontePixel.carregarFontePixel(10));
        playButton.setBounds(650, 400, 120, 50);
        background.add(playButton);

        playButton.addActionListener(e -> {
            // Cria um novo JFrame para as configurações do jogo
            JFrame gameFrame = new JFrame("Configuração do Jogo");
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.setSize(800, 600);
            gameFrame.setLocationRelativeTo(null);
            gameFrame.setVisible(false);
            
            // Cria uma nova instância da classe Personagens
            Personagens personagens = new Personagens(gameFrame);

            // Adiciona a instância da classe Personagens ao JFrame
            gameFrame.add(personagens);
            
            //setVisible(false);  // Esconde a tela inicial
            //gameFrame.setVisible(true);
        });

        // Botão Créditos
        JButton creditsButton = new JButton("Créditos");
        creditsButton.setFont(FontePixel.carregarFontePixel(10));
        creditsButton.setBounds(650, 550, 120, 50); 
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

        // Botão "X" para fechar os créditos
        JButton closeCreditsButton = new JButton("X");
        closeCreditsButton.setFont(FontePixel.carregarFontePixel(15));
        closeCreditsButton.setBounds(1400, 20, 50, 50);
        closeCreditsButton.setFocusable(false);
        closeCreditsButton.setVisible(false); // Inicialmente invisível
        closeCreditsButton.setBackground(Color.BLACK); // Cor de fundo
        closeCreditsButton.setForeground(Color.RED); // Cor do texto
        closeCreditsButton.setBorderPainted(false); // Remover a borda pintada
        closeCreditsButton.setFocusPainted(false); // Remover a borda de foco ao clicar
        add(closeCreditsButton);
        
        closeCreditsButton.addActionListener(e -> {
            creditsImageLabel.setVisible(false); // Esconde a imagem de créditos
            background.setVisible(true); // Mostra o fundo principal
            closeCreditsButton.setVisible(false);
        });

        creditsButton.addActionListener(e -> {
            background.setVisible(false); // Esconde o fundo principal
            creditsImageLabel.setVisible(true); // Mostra a imagem de créditos
            closeCreditsButton.setVisible(true); // Mostra o botão "X"
        });
    }
    
    private void ajustarTamanhoFundo() {
        // Obter o tamanho atual da janela
        int largura = getContentPane().getWidth();
        int altura = getContentPane().getHeight();

        // Redimensionar a imagem de fundo
        Image img = backgroundIcon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);

        // Atualizar o ícone da JLabel com a imagem redimensionada
        background.setIcon(new ImageIcon(img));
        background.setBounds(0, 0, largura, altura);
    }



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

    public static void main(String[] args) {
        // Executa a GUI em um thread separado para evitar problemas de thread
        SwingUtilities.invokeLater(() -> {
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.setVisible(true);
        });
    }
}
