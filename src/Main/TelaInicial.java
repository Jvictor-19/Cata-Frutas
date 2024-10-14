package Main;

import javax.swing.*;
import Fonte.FontePixel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import musica.Music; // Importando a classe Music
//import Botões.Voltar;

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
    private JLabel creditsImageLabel; // Declare a JLabel para a imagem de créditos
    private JPanel backGround; // Declare a JPanel para o fundo
    private ImageIcon backgroundIcon;
    private JLabel background;
    
    private JFrame parentFrame;
    
    private int largura = getContentPane().getWidth();
    private int eixoX = (largura)/2;

    
    /**
     * Construtor que inicializa a tela inicial do jogo.
     * Define os botões de controle e o layout da janela.
     */
    public TelaInicial() {
        // Configurações da janela
        setTitle("Cata Frutas");
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
        //background.add(soundLabel);

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
        playButton.setBounds(610, 400, 120, 50);
        background.add(playButton);

     // Variáveis para os nomes dos jogadores
        //String nomeJogador1 = "";
        //String nomeJogador2 = "";
        
        playButton.addActionListener(e -> {
        	 while (true) {
                 // Opções a serem apresentadas ao usuário
                 String[] options = {"Importar Arquivo", "Definir Configurações"};

                 // Campos de texto para os jogadores
                 JTextField jogador1Field = new JTextField(10);
                 JTextField jogador2Field = new JTextField(10);

                 // Painel para os campos de texto dos jogadores
                 JPanel panel = new JPanel();
                 panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                 panel.add(new JLabel("Nome do Jogador 1:"));
                 panel.add(jogador1Field);
                 panel.add(new JLabel("Nome do Jogador 2:"));
                 panel.add(jogador2Field);

                 // Exibe o JOptionPane com as opções e campos de texto
                 int escolha = JOptionPane.showOptionDialog(
                     null,
                     panel,
                     "Configurações do Jogo",
                     JOptionPane.DEFAULT_OPTION,
                     JOptionPane.INFORMATION_MESSAGE,
                     null,
                     options,
                     options[0]
                 );

                 // Verifica se o usuário fechou a janela ou pressionou Cancelar
                 if (escolha == JOptionPane.CLOSED_OPTION) {
                 	break;
                 }

                 // Verifica se os campos de nome dos jogadores estão preenchidos
                 String nomeJogador1 = jogador1Field.getText().trim();
                 String nomeJogador2 = jogador2Field.getText().trim();

                 if (nomeJogador1.isEmpty() || nomeJogador2.isEmpty()) {            	
                     JOptionPane.showMessageDialog(null, "Por favor, insira os nomes dos dois jogadores antes de continuar.");
                 } else {
                     // Lógica para escolher entre "Importar Arquivo" ou "Definir Configurações"
                     if (escolha == 0) {
                         importarArquivo();
                     } else if (escolha == 1) {
                         // Aqui você pode abrir a tela de configuração
                         JFrame configFrame = new JFrame("Configuração do Jogo");
                         configFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Usar DISPOSE_ON_CLOSE
                         configFrame.setSize(800, 600);
                         configFrame.setLocationRelativeTo(null);
                         configFrame.add(new Configuração());
                         configFrame.setVisible(true);
                         setVisible(false);
                         pararMusica();
        
                     }
                     break; // Sai do loop após processar os nomes com sucesso
                 }
             }
        });

        // Botão Créditos
        JButton creditsButton = new JButton("Créditos");
        creditsButton.setFont(FontePixel.carregarFontePixel(10));
        creditsButton.setBounds(610, 480, 120, 50); 
        
        //background.add(creditsButton);

        // Carrega a imagem original de créditos
        //ImageIcon originalIcon = new ImageIcon(getClass().getResource("/imagens/FundoCreditos.png"));
        backgroundIcon = new ImageIcon(getClass().getResource("/imagens/backgraund1.jpg"));
        background.setBounds(0, 0, 1472, 832);
        add(background); // Adiciona o fundo primeiro

        // Adiciona outros componentes depois
        
        background.add(playButton);
        background.add(creditsButton);
        background.add(soundLabel);
        //add(creditsButton);
        

        // Redimensiona a imagem para o tamanho desejado (por exemplo, 1600x900)
        Image scaledImage = backgroundIcon.getImage().getScaledInstance(900, 900, Image.SCALE_SMOOTH);

        // Cria um novo ImageIcon com a imagem redimensionada
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // JLabel para exibir a imagem de créditos com a nova imagem redimensionada
        JLabel creditsImageLabel = new JLabel(scaledIcon);
        creditsImageLabel.setBounds(0, 0, 1500, 900); // Ajuste o tamanho do JLabel para corresponder à nova imagem
        creditsImageLabel.setVisible(true); // Inicialmente invisível
        add(creditsImageLabel);
        

         // Instancia o botão "Voltar"
         /*Voltar voltarButton = new Voltar(background, creditsImageLabel);
         voltarButton.setVisible(false); // Inicialmente invisível
         add(voltarButton);*/

        creditsButton.addActionListener(e -> {
        background.setVisible(false); // Esconde o fundo principal
        creditsImageLabel.setVisible(true); // Mostra a imagem de créditos
        //voltarButton.setVisible(true); 
        });
    }
    
    
    private void importarArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        int retorno = fileChooser.showOpenDialog(null);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "Arquivo importado: " + arquivo.getName());

            // Iniciar o jogo com base no arquivo importado
            try {
            	
                JFrame gameWindow = new JFrame("Cata Frutas");
                gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Apenas fechar a janela
                gameWindow.setResizable(false);

                // Passa o caminho do arquivo selecionado para a TelaJogo
                TelaJogo gamePainel = new TelaJogo(arquivo.getAbsolutePath());
                gameWindow.getContentPane().add(gamePainel);
                gameWindow.pack();
                gameWindow.setLocationRelativeTo(null);
                gameWindow.setVisible(true);
                
                setVisible(false);
                pararMusica();

                // Fechar a janela atual de configuração
                //parentFrame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum arquivo foi selecionado.");
        }
    }
    
    private void ajustarTamanhoFundo() {
        // Obter o tamanho atual da janela
        largura = getContentPane().getWidth();
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

    /*public static void main(String[] args) {
        // Executa a GUI em um thread separado para evitar problemas de thread
        SwingUtilities.invokeLater(() -> {
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.setVisible(true);
        });
    }*/
}
