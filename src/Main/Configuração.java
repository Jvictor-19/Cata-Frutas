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
    private int valor = 0; // Variável que será incrementada ou decrementada

    public Configuração() {
    	setLayout(null);
        setPreferredSize(new Dimension(800, 600));

        // Criação dos componentes
        JLabel tamanhoLabel = new JLabel("Dimensão da floresta (n x n):");
        tamanhoLabel.setBounds(17, 29, 232, 30);

        // Label para exibir o número atual
        labelNumero = new JLabel(String.valueOf(valor));
        labelNumero.setBounds(267, 34, 20, 20);
        labelNumero.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelNumero);
        
     // Botão de incremento
        JButton btnIncrementar = criarBotao("+", 305, 34);
        add(btnIncrementar);

        // Botão de decremento
        JButton btnDecrementar = criarBotao("-", 229, 34);
        add(btnDecrementar);

        // Botão de incremento
        /*JButton btnIncrementar = new JButton("+");
        btnIncrementar.setBounds(305, 34, 20, 20);
        btnIncrementar.setMargin(new Insets(0, 0, 0, 0));
        btnIncrementar.setBackground(Color.orange);
        btnIncrementar.setOpaque(true);       // Necessário para alguns Look and Feel
        btnIncrementar.setBorderPainted(false); // Remove a borda padrão
        btnIncrementar.setBorderPainted(false);

        add(btnIncrementar);

        // Botão de decremento
        JButton btnDecrementar = new JButton("-");
        btnDecrementar.setBounds(229, 34, 20, 20);
        btnDecrementar.setMargin(new Insets(0, 0, 0, 0));
        btnDecrementar.setBackground(Color.orange);
        add(btnDecrementar);*/

        // Adiciona o ouvinte de ação ao botão de incremento
        btnIncrementar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                valor++;
                atualizarNumero();
            }
        });

        // Adiciona o ouvinte de ação ao botão de decremento
        btnDecrementar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valor--;
                atualizarNumero();
            }
        });

        JLabel lblQuantidadeDePedras = new JLabel("Quantidade de Pedras:");
        lblQuantidadeDePedras.setBounds(17, 60, 170, 15);
        pedras = new JTextField();
        pedras.setBounds(250, 60, 100, 25);  // Aumentei o tamanho para que o campo seja visível

        JLabel lblQuantidadeDeLaranjas = new JLabel("Quantidade de Laranjas:");
        lblQuantidadeDeLaranjas.setBounds(17, 87, 170, 15);
        laranjas = new JTextField();
        laranjas.setBounds(250, 87, 100, 25);  // Aumentei o tamanho para que o campo seja visível

        iniciarButton = new JButton("Iniciar Jogo");
        iniciarButton.setBounds(341, 431, 150, 40);

        // Adiciona o ouvinte de ação ao botão "Iniciar Jogo"
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo();
            }
        });

        // Adiciona os componentes ao painel
        add(tamanhoLabel);
        add(lblQuantidadeDePedras);
        add(pedras);
        add(lblQuantidadeDeLaranjas);
        add(laranjas);
        add(iniciarButton);
    }
    
    private JButton criarBotao(String texto, int x, int y) {
        JButton botao = new JButton(texto);
        botao.setBounds(x, y, 20, 20);
        botao.setMargin(new Insets(0, 0, 0, 0));
        botao.setBackground(Color.orange);
        botao.setOpaque(true);
        botao.setBorderPainted(false);

        // Adiciona um MouseListener para manter a cor do botão ao ser clicado
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                botao.setBackground(Color.ORANGE.darker()); // Cor ao pressionar
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                botao.setBackground(Color.ORANGE); // Retorna à cor original ao soltar
            }
        });

        return botao;
    }


    // Método para atualizar o número exibido no JLabel
    private void atualizarNumero() {
        labelNumero.setText(String.valueOf(valor));
    }

    private void iniciarJogo() {
        String tamanhoFloresta = labelNumero.getText();
        String qtdPedras = pedras.getText();
        String qtdLaranjas = laranjas.getText();

        try {
            // Valida se os campos foram preenchidos corretamente
            int n = Integer.parseInt(tamanhoFloresta);
            int quantidadePedras = Integer.parseInt(qtdPedras);
            int quantidadeLaranjas = Integer.parseInt(qtdLaranjas);

            if (n <= 5 || quantidadePedras < 0 || quantidadeLaranjas < 0) {
                throw new NumberFormatException("Dimensão deve ser maior que 5 e quantidades não podem ser negativas.");
            }

            // Salvar as configurações em um arquivo de texto
            salvarConfiguracoes(tamanhoFloresta, qtdPedras, qtdLaranjas);

            // Criar uma nova janela do jogo
            JFrame gameWindow = new JFrame("Cata Frutas");
            gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameWindow.setResizable(false);
            TelaJogo gamePainel = new TelaJogo("src/Arquivo/configuracaoJogo.txt"); // Passa as dimensões e quantidades
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