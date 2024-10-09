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
    
    private JLabel labelNumero;
    private JLabel labelNumeroPedras;
    private JLabel labelNumeroLaranjas;
    private JLabel labelNumeroAbacates;
    private JLabel labelNumeroOuroNoChao;
    private JLabel labelNumeroOuro;
    
    private int valor = 3; // Variável que será incrementada ou decrementada
    private int qtdPedras = 0;
    private int qtdFrutasOuroNoChao = 0;
    private int qtdFrutasOuro = 0;
    private int qtdLaranjas = 0;
    private int qtdAbacates = 0;
  
    private int verificador = qtdPedras + qtdLaranjas;
    
    
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
        	if(valor == 3) {
        		JOptionPane.showMessageDialog(null, "Você atingiu o valor mínimo permitido!", "Aviso", JOptionPane.WARNING_MESSAGE);
        	}else {
        		valor--;
                atualizarNumero(labelNumero, valor);
        	}
        });

        // COMPONENTENTES PARA A QUANTIDADE DE PEDRAS
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

        int[] qtdPedrasAtual = {qtdPedras};
        btnIncrementarPedras.addActionListener(e -> {
        	alterarValor(true, labelNumeroPedras, qtdPedrasAtual);
        	qtdPedras = qtdPedrasAtual[0];
            
        });

        btnDecrementarPedras.addActionListener(e -> {
        	alterarValor(false, labelNumeroPedras, qtdPedrasAtual);
        	qtdPedras = qtdPedrasAtual[0];
        });

        // COMPONENTES PARA A QUANTIDADE DE LARANJAS
        JLabel lblQuantidadeDeLaranjas = new JLabel("Quantidade de Laranjas:");
        lblQuantidadeDeLaranjas.setBounds(17, 87, 205, 15);
        add(lblQuantidadeDeLaranjas);

        labelNumeroLaranjas = new JLabel(String.valueOf(qtdLaranjas));
        labelNumeroLaranjas.setBounds(250, 87, 50, 25);
        labelNumeroLaranjas.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelNumeroLaranjas);

        JButton btnIncrementarLaranjas = criarBotao("+", 305, 87);
        JButton btnDecrementarLaranjas = criarBotao("-", 229, 87);
        add(btnIncrementarLaranjas);
        add(btnDecrementarLaranjas);

        int[] qtdLaranjasAtual = {qtdPedras};
        btnIncrementarLaranjas.addActionListener(e -> {
        	alterarValor(true, labelNumeroLaranjas, qtdLaranjasAtual);
        	qtdLaranjas = qtdLaranjasAtual[0];
        });

        btnDecrementarLaranjas.addActionListener(e -> {
        	alterarValor(false, labelNumeroLaranjas, qtdLaranjasAtual);
        	qtdLaranjas = qtdLaranjasAtual[0];
        });
        
        // COMPONENTES PARA A QUANTIDADE DE ABACATE
        JLabel lblQuantidadeDeAbacates = new JLabel("Quantidade de Abacates:");
        lblQuantidadeDeAbacates.setBounds(17, 114, 240, 15);
        add(lblQuantidadeDeAbacates);
        
        labelNumeroAbacates = new JLabel(String.valueOf(qtdLaranjas));
        labelNumeroAbacates.setBounds(250, 114, 50, 25);
        labelNumeroAbacates.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelNumeroAbacates);
        
        JButton btnIncrementarAbacates = criarBotao("+", 305, 114);
        JButton btnDecrementarAbacates = criarBotao("-", 229, 114);
        add(btnIncrementarAbacates);
        add(btnDecrementarAbacates);
        
        int[] qtdAbacatesAtual = {qtdAbacates};
        btnIncrementarAbacates.addActionListener(e -> {
        	alterarValor(true, labelNumeroAbacates, qtdAbacatesAtual);
        	qtdAbacates = qtdAbacatesAtual[0];
        });

        btnDecrementarAbacates.addActionListener(e -> {
        	alterarValor(false, labelNumeroAbacates, qtdAbacatesAtual);
        	qtdAbacates = qtdAbacatesAtual[0];
        });

        // Botão "Iniciar Jogo"
        iniciarButton = new JButton("Iniciar Jogo");
        iniciarButton.setBounds(341, 431, 150, 40);
        add(iniciarButton);

        iniciarButton.addActionListener(e -> iniciarJogo());
    }
    
    private void alterarValor(boolean incrementar, JLabel label, int[] valorAtual) {
        if (incrementar) {
            valorAtual[0]++; // Incrementa o valor
        } else {
            if (valorAtual[0] > 0) { // Certifica-se de que não se torne negativo
                valorAtual[0]--;
            } else {
                JOptionPane.showMessageDialog(null, "Você atingiu o valor mínimo permitido!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
        atualizarNumero(label, valorAtual[0]); // Atualiza o JLabel com o novo valor
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

    // Método que será chamado ao iniciar o jogo
    private void iniciarJogo() {
        String tamanhoFloresta = labelNumero.getText();
        String qtdPedrasText = labelNumeroPedras.getText();
        String qtdLaranjasText = labelNumeroLaranjas.getText();
        String qtdAbacatesText = labelNumeroAbacates.getText();

        try {
            int n = Integer.parseInt(tamanhoFloresta);
            int quantidadePedras = Integer.parseInt(qtdPedrasText);
            int quantidadeLaranjas = Integer.parseInt(qtdLaranjasText);
            int quantidadeAbacates = Integer.parseInt(qtdAbacatesText);

            if (n <= 2) {
                throw new NumberFormatException("Dimensão deve ser maior que 2 e quantidades não podem ser negativas.");
            }

            // Salvar as configurações e iniciar o jogo
            salvarConfiguracoes(tamanhoFloresta, qtdPedrasText, qtdLaranjasText, qtdAbacatesText);

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
    private void salvarConfiguracoes(String tamanho, String pedras, String laranjas, String abacate) {
        try (FileWriter writer = new FileWriter("src/Arquivo/configuracaoJogo.txt")) {
        	writer.write("dimensão: " + tamanho + "\n");
            writer.write("pedras: " + pedras + "\n");
            writer.write("maracuja: " + laranjas + " " + laranjas + "\n");
            writer.write("laranja: " + laranjas +  " " + laranjas + "\n");
            writer.write("abacate: " + abacate +  " " + abacate + "\n");
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
