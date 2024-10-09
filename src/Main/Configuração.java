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
    private JLabel labelNumeroMaracujas; // Adicionado para maracujás
    private JLabel labelNumeroLaranjas;
    private JLabel labelNumeroLaranjeiras;
    private JLabel labelNumeroAbacates;
    private JLabel labelNumeroAbacateiros;
    private JLabel labelNumeroCocos;
    private JLabel labelNumeroCoqueiros;
    private JLabel labelNumeroAcerola;
    private JLabel labelNumeroAceroleiras;
    private JLabel labelNumeroAmoras;
    private JLabel labelNumeroAmoreiras;
    private JLabel labelNumeroGoiaba;
    private JLabel labelNumeroGoiabeira;
    private JLabel labelNumeroBichadas;
    private JLabel labelNumeroMochila;
    
    private int valor = 3; // Variável que será incrementada ou decrementada
    private int qtdPedras = 0;
    private int qtdMaracujas = 0; // Inicialização da quantidade de maracujás
    private int qtdLaranjas = 0;
    private int qtdLaranjeiras = 0;
    private int qtdAbacates = 0;
    private int qtdAbacateiros = 0;
    private int qtdCoco = 0;
    private int qtdCoqueiros = 0;
    private int qtdAcerola =0;
    private int qtdAceroleiras = 0;
    private int qtdAmoras =0;
    private int qtdAmoreiras = 0;
    private int qtdGoiaba =0;
    private int qtdGoiabeira = 0;
    private int porBichadas =0;
    private int capMochila =0;

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
            if (valor == 3) {
                JOptionPane.showMessageDialog(null, "Você atingiu o valor mínimo permitido!", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                valor--;
                atualizarNumero(labelNumero, valor);
            }
        });

        // COMPONENTES PARA A QUANTIDADE DE PEDRAS
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

        // COMPONENTES PARA A QUANTIDADE DE MARACUJÁS 
        JLabel lblQuantidadeDeMaracujas = new JLabel("Quantidade de Maracujás:");
        lblQuantidadeDeMaracujas.setBounds(17, 87, 170, 15);
        add(lblQuantidadeDeMaracujas);

        labelNumeroMaracujas = new JLabel(String.valueOf(qtdMaracujas));
        labelNumeroMaracujas.setBounds(250, 87, 50, 25);
        labelNumeroMaracujas.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelNumeroMaracujas);

        JButton btnIncrementarMaracujas = criarBotao("+", 305, 87);
        JButton btnDecrementarMaracujas = criarBotao("-", 229, 87);
        add(btnIncrementarMaracujas);
        add(btnDecrementarMaracujas);

        int[] qtdMaracujasAtual = {qtdMaracujas};
        btnIncrementarMaracujas.addActionListener(e -> {
            alterarValor(true, labelNumeroMaracujas, qtdMaracujasAtual);
            qtdMaracujas = qtdMaracujasAtual[0];
        });

        btnDecrementarMaracujas.addActionListener(e -> {
            alterarValor(false, labelNumeroMaracujas, qtdMaracujasAtual);
            qtdMaracujas = qtdMaracujasAtual[0];
        });

        // COMPONENTES PARA A QUANTIDADE DE LARANJAS
        JLabel lblQuantidadeDeLaranjas = new JLabel("Quantidade de Laranjas:");
        lblQuantidadeDeLaranjas.setBounds(17, 114, 205, 15);
        add(lblQuantidadeDeLaranjas);

        labelNumeroLaranjas = new JLabel(String.valueOf(qtdLaranjas));
        labelNumeroLaranjas.setBounds(250, 114, 50, 25);
        labelNumeroLaranjas.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelNumeroLaranjas);

        JButton btnIncrementarLaranjas = criarBotao("+", 305, 114);
        JButton btnDecrementarLaranjas = criarBotao("-", 229, 114);
        add(btnIncrementarLaranjas);
        add(btnDecrementarLaranjas);

        int[] qtdLaranjasAtual = {qtdLaranjas};
        btnIncrementarLaranjas.addActionListener(e -> {
            alterarValor(true, labelNumeroLaranjas, qtdLaranjasAtual);
            qtdLaranjas = qtdLaranjasAtual[0];
        });

        btnDecrementarLaranjas.addActionListener(e -> {	
            alterarValor(false, labelNumeroLaranjas, qtdLaranjasAtual);
            qtdLaranjas = qtdLaranjasAtual[0];
        });
        
        // COMPONENTES PARA A QUANTIDADE DE LARANJEIRAS
        /*JLabel lblQuantidadeDeLaranjeiras = new JLabel("Quantidade de Laranjeiras:");
        lblQuantidadeDeLaranjeiras.setBounds(370, 114, 205, 15); // Ao lado da quantidade de laranjas
        add(lblQuantidadeDeLaranjeiras);

        labelNumeroLaranjeiras = new JLabel(String.valueOf(qtdLaranjeiras)); // Quantidade de laranjeiras
        labelNumeroLaranjeiras.setBounds(600, 114, 50, 25); // Ao lado da label de laranjeiras
        labelNumeroLaranjeiras.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelNumeroLaranjeiras);

        JButton btnIncrementarLaranjeiras = criarBotao("+", 655, 114); // Botão ao lado da quantidade de laranjeiras
        JButton btnDecrementarLaranjeiras = criarBotao("-", 579, 114);
        add(btnIncrementarLaranjeiras);
        add(btnDecrementarLaranjeiras);

        int[] qtdLaranjeirasAtual = {qtdLaranjeiras};
        btnIncrementarLaranjeiras.addActionListener(e -> {
            alterarValor(true, labelNumeroLaranjeiras, qtdLaranjeirasAtual);
            qtdLaranjeiras = qtdLaranjeirasAtual[0];
        });

        btnDecrementarLaranjeiras.addActionListener(e -> {
            alterarValor(false, labelNumeroLaranjeiras, qtdLaranjeirasAtual);
            qtdLaranjeiras = qtdLaranjeirasAtual[0];
        });

        // COMPONENTES PARA A QUANTIDADE DE ABACATE
        JLabel lblQuantidadeDeAbacates = new JLabel("Quantidade de Abacates:");
        lblQuantidadeDeAbacates.setBounds(17, 141, 240, 15);
        add(lblQuantidadeDeAbacates);

        labelNumeroAbacates = new JLabel(String.valueOf(qtdAbacates));
        labelNumeroAbacates.setBounds(250, 141, 50, 25);
        labelNumeroAbacates.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelNumeroAbacates);

        JButton btnIncrementarAbacates = criarBotao("+", 305, 141);
        JButton btnDecrementarAbacates = criarBotao("-", 229, 141);
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
        
      //COMPONENTES PARA A QUANTIDADE DE ABACATEIROS
        JLabel lblQuantidadeDeAbacateiros = new JLabel("Quantidade de Abacateiros:");
        lblQuantidadeDeAbacateiros.setBounds(370, 141, 240, 15); 
        add(lblQuantidadeDeAbacateiros);

        labelNumeroAbacateiros = new JLabel(String.valueOf(qtdAbacateiros)); // Quantidade de abacateiros
        labelNumeroAbacateiros.setBounds(600, 141, 50, 25); // Posição corrigida para estar ao lado da label de abacateiros
        labelNumeroAbacateiros.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelNumeroAbacateiros);

        JButton btnIncrementarAbacateiros = criarBotao("+", 655, 141); // Botão ao lado da quantidade de abacateiros
        JButton btnDecrementarAbacateiros = criarBotao("-", 579, 141);
        add(btnIncrementarAbacateiros);
        add(btnDecrementarAbacateiros);

        int[] qtdAbacateirosAtual = {qtdAbacateiros};
        btnIncrementarAbacateiros.addActionListener(e -> {
            alterarValor(true, labelNumeroAbacateiros, qtdAbacateirosAtual);
            qtdAbacateiros = qtdAbacateirosAtual[0];
        });

        btnDecrementarAbacateiros.addActionListener(e -> {
            alterarValor(false, labelNumeroAbacateiros, qtdAbacateirosAtual);
            qtdAbacateiros = qtdAbacateirosAtual[0];
        });
        
      //COMPONENTES PARA A QUANTIDADE DE COCOQUEIROS
        JLabel lblQuantidadeDeCoqueiros = new JLabel("Quantidade de Coqueiros:");
        lblQuantidadeDeCoqueiros.setBounds(17, 168, 232, 15); 
        add(lblQuantidadeDeCoqueiros);

        labelNumeroCoqueiros = new JLabel(String.valueOf(qtdCoqueiros)); // Quantidade de abacateiros
        labelNumeroCoqueiros.setBounds(250, 168, 50, 25); // Posição corrigida para estar ao lado da label de abacateiros
        labelNumeroCoqueiros.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelNumeroCoqueiros);

        JButton btnIncrementarCoqueiros = criarBotao("+", 305, 168); // Botão ao lado da quantidade de abacateiros
        JButton btnDecrementarCoqueiros = criarBotao("-", 229, 168);
        add(btnIncrementarCoqueiros);
        add(btnDecrementarCoqueiros);

        int[] qtdCoqueirosAtual = {qtdCoqueiros};
        btnIncrementarCoqueiros.addActionListener(e -> {
            alterarValor(true, labelNumeroCoqueiros, qtdCoqueirosAtual);
            qtdCoqueiros = qtdCoqueirosAtual[0];
        });

        btnDecrementarAbacateiros.addActionListener(e -> {
            alterarValor(false, labelNumeroAbacateiros, qtdAbacateirosAtual);
            qtdAbacateiros = qtdAbacateirosAtual[0];
        });
        
        
      //COMPONENTES PARA A QUANTIDADE DE COCO
        JLabel lblQuantidadeDeCoco = new JLabel("Quantidade de Cocos:");
        lblQuantidadeDeCoco.setBounds(370, 168, 240, 15); 
        add(lblQuantidadeDeCoco);

        labelNumeroCocos = new JLabel(String.valueOf(qtdCoco)); // Quantidade de abacateiros
        labelNumeroCocos.setBounds(600, 168, 50, 25); // Posição corrigida para estar ao lado da label de abacateiros
        labelNumeroCocos.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelNumeroCocos);

        JButton btnIncrementarCoco = criarBotao("+", 655, 168); // Botão ao lado da quantidade de abacateiros
        JButton btnDecrementarCoco = criarBotao("-", 579, 168);
        add(btnIncrementarCoco);
        add(btnDecrementarCoco);

        int[] qtdCocoAtual = {qtdCoco};
        btnIncrementarCoco.addActionListener(e -> {
            alterarValor(true, labelNumeroCocos, qtdCocoAtual);
            qtdCoco = qtdCocoAtual[0];
        });

        btnDecrementarCoco.addActionListener(e -> {
            alterarValor(false, labelNumeroCocos, qtdCocoAtual);
            qtdCoco = qtdCocoAtual[0];
        });*/
        int[] qtdLaranjeirasAtual = {qtdLaranjeiras};
        labelNumeroLaranjeiras = new JLabel();
        criarComponentesQuantidade("Quantidade de Laranjeiras:", qtdLaranjeirasAtual, labelNumeroLaranjeiras, 370, 114, 655, 114, 579, 114);
        
        int[] qtdAbacatesAtual = {qtdAbacates};
        labelNumeroAbacates = new JLabel();
        criarComponentesQuantidade("Quantidade de Abacates:", qtdAbacatesAtual, labelNumeroAbacates, 17, 141, 305, 141, 229, 141);

        int[] qtdAbacateirosAtual = {qtdAbacateiros};
        labelNumeroAbacateiros = new JLabel();
        criarComponentesQuantidade("Quantidade de Abacateiros:", qtdAbacateirosAtual, labelNumeroAbacateiros, 370, 141, 655, 141, 579, 141);

        
        int[] qtdCoqueirosAtual = {qtdCoqueiros};
        labelNumeroCoqueiros = new JLabel();
        criarComponentesQuantidade("Quantidade de Coqueiros:", qtdCoqueirosAtual, labelNumeroCoqueiros, 17, 168, 305, 168, 229, 168);
        
        int[] qtdCocoAtual = {qtdCoco};
        labelNumeroCocos = new JLabel();
        criarComponentesQuantidade("Quantidade de Cocos:", qtdCocoAtual, labelNumeroCocos, 370, 168, 655, 168, 579, 168);
        
        int[] qtdAceroleirasAtual = {qtdAceroleiras};
        labelNumeroAceroleiras = new JLabel();
        criarComponentesQuantidade("Quantidade de Aceroleiras:", qtdAceroleirasAtual, labelNumeroAceroleiras, 17, 195, 305, 195, 229, 195);
        
        int[] qtdAcerolaAtual = {qtdAcerola};
        labelNumeroAcerola = new JLabel();
        criarComponentesQuantidade("Quantidade de Acerolas:", qtdAcerolaAtual, labelNumeroAcerola, 370, 195, 655, 195, 579, 195);
        
        int[] qtdAmoreirasAtual = {qtdAmoreiras};
        labelNumeroAmoreiras = new JLabel();
        criarComponentesQuantidade("Quantidade de Amoreiras:", qtdAmoreirasAtual, labelNumeroAmoreiras, 17, 222, 305, 222, 229, 222);

        int[] qtdAmorasAtual = {qtdAmoras};
        labelNumeroAmoras = new JLabel();
        criarComponentesQuantidade("Quantidade de Amoras:", qtdAmorasAtual, labelNumeroAmoras, 370, 222, 655, 222, 579, 222);
        
        int[] qtdGoiabeirasAtual = {qtdGoiabeira};
        labelNumeroGoiabeira = new JLabel();
        criarComponentesQuantidade("Quantidade de Goiabeiras:", qtdGoiabeirasAtual, labelNumeroGoiabeira, 17, 249, 305, 249, 229, 249);

        int[] qtdGoiabasAtual = {qtdGoiaba};
        labelNumeroGoiaba = new JLabel();
        criarComponentesQuantidade("Quantidade de Goiabas:", qtdGoiabasAtual, labelNumeroGoiaba, 370, 249, 655, 249, 579, 249);
        
        int[] qtdBichadasAtual = {porBichadas};
        labelNumeroBichadas= new JLabel();
        criarComponentesQuantidade("Porcentagem de Bichadas:", qtdBichadasAtual, labelNumeroBichadas, 17, 276, 305, 276, 229, 276);

        int[] qtdMochilaAtual = {capMochila};
        labelNumeroMochila = new JLabel();
        criarComponentesQuantidade("Capacidade da Mochila:", qtdMochilaAtual, labelNumeroMochila, 17, 303, 305, 303, 229, 303);

        // Botão "Iniciar Jogo"
        iniciarButton = new JButton("Iniciar Jogo");
        iniciarButton.setBounds(341, 431, 150, 40);
        add(iniciarButton);

        iniciarButton.addActionListener(e -> iniciarJogo());
    }
    
    private void criarComponentesQuantidade(String labelTexto, int[] quantidadeAtual, JLabel labelQuantidade, int xLabel, int yLabel, int xBotaoMais, int yBotaoMais, int xBotaoMenos, int yBotaoMenos) {
        JLabel label = new JLabel(labelTexto);
        label.setBounds(xLabel, yLabel, 205, 15); // Configurações de posição e tamanho
        add(label);

        labelQuantidade.setText(String.valueOf(quantidadeAtual[0]));
        labelQuantidade.setBounds(xLabel + 230, yLabel, 50, 25);
        labelQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelQuantidade);

        JButton btnIncrementar = criarBotao("+", xBotaoMais, yBotaoMais);
        JButton btnDecrementar = criarBotao("-", xBotaoMenos, yBotaoMenos);
        add(btnIncrementar);
        add(btnDecrementar);

        btnIncrementar.addActionListener(e -> {
            alterarValor(true, labelQuantidade, quantidadeAtual);
        });

        btnDecrementar.addActionListener(e -> {
            alterarValor(false, labelQuantidade, quantidadeAtual);
        });
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
                botao.setBackground(Color.red);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                botao.setBackground(Color.orange);
            }
        });

        return botao;
    }

    // Atualiza o número exibido na interface
    private void atualizarNumero(JLabel label, int novoValor) {
        label.setText(String.valueOf(novoValor));
    }

    // Método que será chamado ao iniciar o jogo
    private void iniciarJogo() {
        String tamanhoFloresta = labelNumero.getText();
        String qtdMaracujasText = labelNumeroMaracujas.getText();
        String qtdPedrasText = labelNumeroPedras.getText();
        String qtdLaranjasText = labelNumeroLaranjas.getText();
        String qtdAbacatesText = labelNumeroAbacates.getText();
        String qtdLaranjeirasText = labelNumeroLaranjeiras.getText();
        String qtdAbacateirosText = labelNumeroAbacateiros.getText();

        try {
            int n = Integer.parseInt(tamanhoFloresta);
            int quantidadePedras = Integer.parseInt(qtdPedrasText);
            int quantidadeLaranjas = Integer.parseInt(qtdLaranjasText);
            int quantidadeLaranjeiras = Integer.parseInt(qtdLaranjeirasText);
            int quantidadeAbacates = Integer.parseInt(qtdAbacatesText);
            int quantidadeAbacateiros = Integer.parseInt(qtdAbacateirosText);
            int quantidadeMaracujas = Integer.parseInt(qtdMaracujasText);

            if (n <= 2) {
                throw new NumberFormatException("Dimensão deve ser maior que 2 e quantidades não podem ser negativas.");
            }

            // Salvar as configurações e iniciar o jogo
            salvarConfiguracoes(tamanhoFloresta, qtdPedrasText, qtdMaracujasText, qtdLaranjasText, qtdLaranjeirasText, qtdAbacatesText,qtdAbacateirosText);

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
    private void salvarConfiguracoes(String tamanho, String pedras,String maracujas, String laranjas, String laranjeiras, String abacate,String abacateiros) {
        try (FileWriter writer = new FileWriter("src/Arquivo/configuracaoJogo.txt")) {
        	writer.write("dimensão: " + tamanho + "\n");
            writer.write("pedras: " + pedras + "\n");
            writer.write("maracuja: " + maracujas + " " + maracujas+ "\n");
            writer.write("laranja: " + laranjeiras +  " " + laranjas + "\n");
            writer.write("abacate: " + abacateiros +  " " + abacate + "\n");
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
