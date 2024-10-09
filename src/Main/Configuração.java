package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;

public class Configuração extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton iniciarButton;
    private JTextField pedras;
    private JTextField laranjas;
    private JLabel labelNumero;
    private JLabel labelNumeroPedras;
    private JLabel labelNumeroLaranjas;
    private int valor = 3; // Variável que será incrementada ou decrementada
    private int qtdPedras = 0;
    private int qtdLaranjas = 0;
    
    public Configuração() {
    	setLayout(null);
        setPreferredSize(new Dimension(800, 600));

        // Criação dos componentes
        JLabel tamanhoLabel = new JLabel("Dimensão da floresta (n x n):");
        tamanhoLabel.setBounds(17, 29, 232, 30);
        add(tamanhoLabel);

        // Label para exibir o número atual
        labelNumero = new JLabel(String.valueOf(valor));
        labelNumero.setBounds(267, 34, 20, 20);
        labelNumero.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelNumero);

        // Botão de incremento para Dimensão
        JButton btnIncrementarDimensao = criarBotao("+", 305, 34);
        add(btnIncrementarDimensao);

        // Botão de decremento para Dimensão
        JButton btnDecrementarDimensao = criarBotao("-", 229, 34);
        add(btnDecrementarDimensao);

        // Ações para Dimensão
        btnIncrementarDimensao.addActionListener(e -> {
            valor++;
            atualizarNumero(labelNumero, valor);
        });

        btnDecrementarDimensao.addActionListener(e -> {
            valor--;
            atualizarNumero(labelNumero, valor);
        });

        // Componentes para "Quantidade de Pedras"
        JLabel lblQuantidadeDePedras = new JLabel("Quantidade de Pedras:");
        lblQuantidadeDePedras.setBounds(17, 60, 170, 15);
        add(lblQuantidadeDePedras);
        
        labelNumeroPedras = new JLabel(String.valueOf(qtdPedras));
        labelNumeroPedras.setBounds(250, 60, 50, 25);
        labelNumeroPedras.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelNumeroPedras);

        JButton btnIncrementarPedras = criarBotao("+", 305, 60);
        JButton btnDecrementarPedras = criarBotao("-", 229, 60);
        add(btnIncrementarPedras);
        add(btnDecrementarPedras);

        btnIncrementarPedras.addActionListener(e -> {
            qtdPedras++;
            atualizarNumero(labelNumeroPedras, qtdPedras);
        });

        btnDecrementarPedras.addActionListener(e -> {
            qtdPedras--;
            atualizarNumero(labelNumeroPedras, qtdPedras);
        });

        // Componentes para "Quantidade de Laranjas"
        JLabel lblQuantidadeDeLaranjas = new JLabel("Quantidade de Laranjas:");
        lblQuantidadeDeLaranjas.setBounds(17, 87, 170, 15);
        add(lblQuantidadeDeLaranjas);

        laranjas = new JTextField(String.valueOf(qtdLaranjas));
        laranjas.setBounds(250, 87, 50, 25);
        laranjas.setEditable(false); // Torna o campo de texto não editável
        add(laranjas);

        JButton btnIncrementarLaranjas = criarBotao("+", 305, 87);
        JButton btnDecrementarLaranjas = criarBotao("-", 229, 87);
        add(btnIncrementarLaranjas);
        add(btnDecrementarLaranjas);

        btnIncrementarLaranjas.addActionListener(e -> {
            qtdLaranjas++;
            atualizarNumero(laranjas, qtdLaranjas);
        });

        btnDecrementarLaranjas.addActionListener(e -> {
            qtdLaranjas--;
            atualizarNumero(laranjas, qtdLaranjas);
        });

        // Botão "Iniciar Jogo"
        iniciarButton = new JButton("Iniciar Jogo");
        iniciarButton.setBounds(341, 431, 150, 40);
        add(iniciarButton);

        iniciarButton.addActionListener(e -> iniciarJogo());
    }

    // Cria os botões de incremento e decremento com estilização
    private JButton criarBotao(String texto, int x, int y) {
        JButton botao = new JButton(texto);
        botao.setBounds(x, y, 20, 20);
        botao.setMargin(new Insets(0, 0, 0, 0));
        botao.setBackground(Color.orange);
        botao.setOpaque(true);
        botao.setBorderPainted(false);

        // Efeito ao clicar o botão
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                botao.setBackground(Color.ORANGE.darker());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                botao.setBackground(Color.ORANGE);
            }
        });

        return botao;
    }

    // Atualiza o valor numérico nos campos de exibição
    private void atualizarNumero(JLabel label, int valor) {
        label.setText(String.valueOf(valor));
    }

    private void atualizarNumero(JTextField textField, int valor) {
        textField.setText(String.valueOf(valor));
    }

    // Método que será chamado ao iniciar o jogo
    private void iniciarJogo() {
        String tamanhoFloresta = labelNumero.getText();
        String qtdPedrasText = labelNumeroPedras.getText();
        String qtdLaranjasText = laranjas.getText();

        try {
            int n = Integer.parseInt(tamanhoFloresta);
            int quantidadePedras = Integer.parseInt(qtdPedrasText);
            int quantidadeLaranjas = Integer.parseInt(qtdLaranjasText);

            if (n <= 2 || quantidadePedras < 0 || quantidadeLaranjas < 0) {
                throw new NumberFormatException("Dimensão deve ser maior que 2 e quantidades não podem ser negativas.");
            }

            // Salvar as configurações e iniciar o jogo
            salvarConfiguracoes(tamanhoFloresta, qtdPedrasText, qtdLaranjasText);

            // Criar a tela do jogo
            JFrame gameWindow = new JFrame("Cata Frutas");
            gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameWindow.setResizable(false);
            TelaJogo gamePainel = new TelaJogo("src/Arquivo/configuracaoJogo.txt");
            gameWindow.getContentPane().add(gamePainel);
            gameWindow.pack();
            gameWindow.setLocationRelativeTo(null);
            gameWindow.setVisible(true);

            // Fechar a tela de configuração
            SwingUtilities.getWindowAncestor(this).dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Salva as configurações em um arquivo de texto
    private void salvarConfiguracoes(String tamanho, String pedras, String laranjas) {
        try (FileWriter writer = new FileWriter("src/Arquivo/configuracaoJogo.txt")) {
        	writer.write("dimensão: " + tamanho + "\n");
            writer.write("pedras: " + pedras + "\n");
            writer.write("maracuja: " + laranjas + " " + laranjas + "\n");
            writer.write("laranja: " + laranjas +  " " + laranjas + "\n");
            writer.write("abacate: " + laranjas +  " " + laranjas + "\n");
            writer.write("coco: " + laranjas +  " " + laranjas + "\n");
            writer.write("acerola: " + laranjas + " " + laranjas + "\n");
            writer.write("amora: " + laranjas + " " + laranjas + "\n");
            writer.write("goiaba: " + laranjas + " " + laranjas + "\n");
            writer.write("bichadas: " + laranjas + "\n");
            writer.write("mochila: " + laranjas + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método principal para iniciar a interface
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tela de Configuração");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        Configuração telaInicial = new Configuração();
        frame.getContentPane().add(telaInicial);
        frame.pack(); // Ajusta o tamanho do JFrame com base nos componentes
        frame.setLocationRelativeTo(null); // Centraliza na tela
        frame.setVisible(true); // Torna a janela visível
    }

}
