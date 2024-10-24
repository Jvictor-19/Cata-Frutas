package Main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A classe {@code Configuração} representa um painel de configuração 
 * para um jogo, permitindo ao jogador ajustar a dimensão da floresta 
 * e a quantidade de diferentes tipos de plantas e frutas. 
 * Os dados de configuração podem ser salvos em um arquivo de texto.
 * 
 * Esta classe estende {@code JPanel} e contém componentes gráficos 
 * como botões e rótulos para interação do usuário.
 * 
 * @version 1.0
 */

public class Configuração extends JPanel {

    private static final long serialVersionUID = 1L;
    /** Botão para iniciar o jogo. */
    private JButton iniciarButton;

    /** Botão para visualizar as configurações. */
    private JButton visualizarButton;

    /** Botão para salvar as configurações. */
    private JButton salvarButton;

    /** Rótulo para exibir o número atual da dimensão da floresta. */
    private JLabel labelNumero;

    /** Rótulo para exibir a quantidade de pedras. */
    private JLabel labelNumeroPedras;

    /** Rótulo para exibir a quantidade total de maracujás. */
    private JLabel labelNumeroMaracujasTotal;

    /** Rótulo para exibir a quantidade de maracujás. */
    private JLabel labelNumeroMaracujas;

    /** Rótulo para exibir a quantidade de laranjas. */
    private JLabel labelNumeroLaranjas;

    /** Rótulo para exibir a quantidade de laranjeiras. */
    private JLabel labelNumeroLaranjeiras;

    /** Rótulo para exibir a quantidade de abacates. */
    private JLabel labelNumeroAbacates;

    /** Rótulo para exibir a quantidade de abacateiros. */
    private JLabel labelNumeroAbacateiros;

    /** Rótulo para exibir a quantidade de cocos. */
    private JLabel labelNumeroCocos;

    /** Rótulo para exibir a quantidade de coqueiros. */
    private JLabel labelNumeroCoqueiros;

    /** Rótulo para exibir a quantidade de acerolas. */
    private JLabel labelNumeroAcerola;

    /** Rótulo para exibir a quantidade de aceroleiras. */
    private JLabel labelNumeroAceroleiras;

    /** Rótulo para exibir a quantidade de amoras. */
    private JLabel labelNumeroAmoras;

    /** Rótulo para exibir a quantidade de amoreiras. */
    private JLabel labelNumeroAmoreiras;

    /** Rótulo para exibir a quantidade de goiabas. */
    private JLabel labelNumeroGoiaba;

    /** Rótulo para exibir a quantidade de goiabeiras. */
    private JLabel labelNumeroGoiabeira;

    /** Rótulo para exibir a porcentagem de bichadas. */
    private JLabel labelNumeroBichadas;

    /** Rótulo para exibir a capacidade da mochila. */
    private JLabel labelNumeroMochila;

    /** Variável que será incrementada ou decrementada para a dimensão da floresta. */
    private int valor = 3;

    /** Quantidade de pedras no jogo. */
    private int qtdPedras = 0;

    /** Quantidade total de maracujás. */
    private int qtdMaracujasTotal = 1;

    /** Quantidade de maracujás. */
    private int qtdMaracujas = 0;

    /** Quantidade de laranjas. */
    private int qtdLaranjas = 0;

    /** Quantidade de laranjeiras. */
    private int qtdLaranjeiras = 0;

    /** Quantidade de abacates. */
    private int qtdAbacates = 0;

    /** Quantidade de abacateiros. */
    private int qtdAbacateiros = 0;

    /** Quantidade de cocos. */
    private int qtdCoco = 0;

    /** Quantidade de coqueiros. */
    private int qtdCoqueiros = 0;

    /** Quantidade de acerolas. */
    private int qtdAcerola = 0;

    /** Quantidade de aceroleiras. */
    private int qtdAceroleiras = 0;

    /** Quantidade de amoras. */
    private int qtdAmoras = 0;

    /** Quantidade de amoreiras. */
    private int qtdAmoreiras = 0;

    /** Quantidade de goiabas. */
    private int qtdGoiaba = 0;

    /** Quantidade de goiabeiras. */
    private int qtdGoiabeira = 0;

    /** Porcentagem de bichadas. */
    private int porBichadas = 0;

    /** Capacidade da mochila. */
    private int capMochila = 0;
    
    /** Atualiza as quantidades atuais de maracujás, pedras, laranjas, etc. */
    int[] qtdMaracujasTotalAtual = {qtdMaracujasTotal};
    int[] qtdMaracujasAtual = {qtdMaracujas};
    int[] qtdPedrasAtual = {qtdPedras};
    int[] qtdLaranjeirasAtual = {qtdLaranjeiras};
    int[] qtdLaranjasAtual = {qtdLaranjas};
    int[] qtdAbacateirosAtual = {qtdAbacateiros};
    int[] qtdAbacatesAtual = {qtdAbacates};
    int[] qtdCoqueirosAtual = {qtdCoqueiros};
    int[] qtdCocoAtual = {qtdCoco};
    int[] qtdAceroleirasAtual = {qtdAceroleiras};
    int[] qtdAcerolaAtual = {qtdAcerola};
    int[] qtdAmoreirasAtual = {qtdAmoreiras};
    int[] qtdAmorasAtual = {qtdAmoras};
    int[] qtdGoiabeirasAtual = {qtdGoiabeira};
    int[] qtdGoiabasAtual = {qtdGoiaba};
    int[] qtdBichadasAtual = {porBichadas};
    int[] qtdMochilaAtual = {capMochila};
    
    private int verificador = qtdPedras + qtdMaracujas + qtdLaranjas + qtdLaranjeiras + 
            qtdAbacates + qtdAbacateiros + qtdCoco + qtdCoqueiros + qtdAcerola + 
            qtdAceroleiras + qtdAmoras + qtdAmoreiras + qtdGoiaba + qtdGoiabeira;


    /** 
     * Construtor da classe {@code Configuração}. 
     * Inicializa o painel, configura a interface gráfica e 
     * adiciona os componentes.
     */
    public Configuração() {
        setLayout(null);
        setPreferredSize(new Dimension(800, 600));
        //setBackground(Color.decode("#e08475"));

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
        	int limite = (valor-1)*(valor-1);
            if (valor == 3 || verificador >= limite) {
                JOptionPane.showMessageDialog(null, "Você atingiu o valor mínimo permitido!", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                valor--;
                atualizarNumero(labelNumero, valor);
            }
        });
      

        
        labelNumeroPedras = new JLabel();
        criarComponentesQuantidade("Quantidade de pedras:", qtdPedrasAtual, labelNumeroPedras, 22, 60, 305, 60, 229, 60);
            
        //int[] qtdMaracujasTotalAtual = {qtdMaracujasTotal};
        labelNumeroMaracujasTotal = new JLabel();
        criarComponentesQuantidade("Quantidade Total de maracujas:", qtdMaracujasTotalAtual, labelNumeroMaracujasTotal, 22, 87, 305, 87, 229, 87);
        
        //int[] qtdMaracujasAtual = {qtdMaracujas};
        labelNumeroMaracujas = new JLabel();
        criarComponentesQuantidade("Quantidade de maracujas:", qtdMaracujasAtual, labelNumeroMaracujas, 370, 87, 655, 87, 579, 87);
        
        //int[] qtdLaranjeirasAtual = {qtdLaranjeiras};
        labelNumeroLaranjeiras = new JLabel();
        criarComponentesQuantidade("Quantidade de Laranjeiras:", qtdLaranjeirasAtual, labelNumeroLaranjeiras, 22, 114, 305, 114, 229, 114);
        
        //int[] qtdLaranjasAtual = {qtdLaranjas};
        labelNumeroLaranjas = new JLabel();
        criarComponentesQuantidade("Quantidade de Laranjas:", qtdLaranjasAtual, labelNumeroLaranjas, 370, 114, 655, 114, 579, 114);

        //int[] qtdAbacateirosAtual = {qtdAbacateiros};
        labelNumeroAbacateiros = new JLabel();
        criarComponentesQuantidade("Quantidade de Abacateiros:", qtdAbacateirosAtual, labelNumeroAbacateiros, 22, 141, 305, 141, 229, 141);

        //int[] qtdAbacatesAtual = {qtdAbacates};
        labelNumeroAbacates = new JLabel();
        criarComponentesQuantidade("Quantidade de Abacates:", qtdAbacatesAtual, labelNumeroAbacates, 370, 141, 655, 141, 579, 141);

        //int[] qtdCoqueirosAtual = {qtdCoqueiros};
        labelNumeroCoqueiros = new JLabel();
        criarComponentesQuantidade("Quantidade de Coqueiros:", qtdCoqueirosAtual, labelNumeroCoqueiros, 22, 168, 305, 168, 229, 168);
        
        //int[] qtdCocoAtual = {qtdCoco};
        labelNumeroCocos = new JLabel();
        criarComponentesQuantidade("Quantidade de Cocos:", qtdCocoAtual, labelNumeroCocos, 370, 168, 655, 168, 579, 168);
        
        //int[] qtdAceroleirasAtual = {qtdAceroleiras};
        labelNumeroAceroleiras = new JLabel();
        criarComponentesQuantidade("Quantidade de Aceroleiras:", qtdAceroleirasAtual, labelNumeroAceroleiras, 22, 195, 305, 195, 229, 195);
        
        //int[] qtdAcerolaAtual = {qtdAcerola};
        labelNumeroAcerola = new JLabel();
        criarComponentesQuantidade("Quantidade de Acerolas:", qtdAcerolaAtual, labelNumeroAcerola, 370, 195, 655, 195, 579, 195);
        
        //int[] qtdAmoreirasAtual = {qtdAmoreiras};
        labelNumeroAmoreiras = new JLabel();
        criarComponentesQuantidade("Quantidade de Amoreiras:", qtdAmoreirasAtual, labelNumeroAmoreiras, 22, 222, 305, 222, 229, 222);

        //int[] qtdAmorasAtual = {qtdAmoras};
        labelNumeroAmoras = new JLabel();
        criarComponentesQuantidade("Quantidade de Amoras:", qtdAmorasAtual, labelNumeroAmoras, 370, 222, 655, 222, 579, 222);
        
        //int[] qtdGoiabeirasAtual = {qtdGoiabeira};
        labelNumeroGoiabeira = new JLabel();
        criarComponentesQuantidade("Quantidade de Goiabeiras:", qtdGoiabeirasAtual, labelNumeroGoiabeira, 22, 249, 305, 249, 229, 249);

        //int[] qtdGoiabasAtual = {qtdGoiaba};
        labelNumeroGoiaba = new JLabel();
        criarComponentesQuantidade("Quantidade de Goiabas:", qtdGoiabasAtual, labelNumeroGoiaba, 370, 249, 655, 249, 579, 249);
        
        //int[] qtdBichadasAtual = {porBichadas};
        labelNumeroBichadas= new JLabel();
        criarComponentesQuantidade("Porcentagem de Bichadas:", qtdBichadasAtual, labelNumeroBichadas, 22, 276, 305, 276, 229, 276);

        //int[] qtdMochilaAtual = {capMochila};
        labelNumeroMochila = new JLabel();
        criarComponentesQuantidade("Capacidade da Mochila:", qtdMochilaAtual, labelNumeroMochila, 22, 303, 305, 303, 229, 303);

        salvarButton = new JButton("Salvar terreno");
        salvarButton.setBounds(159, 431, 150, 40);
        add(salvarButton);
        
        salvarButton.addActionListener(e -> {
            // Solicita ao jogador o nome do arquivo
            String nomeArquivo = JOptionPane.showInputDialog(null, "Digite o nome do arquivo para salvar o jogo:", "Salvar Jogo", JOptionPane.QUESTION_MESSAGE);

            // Verifica se o nome do arquivo não está vazio ou nulo
            if (nomeArquivo != null && !nomeArquivo.trim().isEmpty()) {
                // Adiciona a extensão ".txt" ao nome do arquivo, se necessário
                if (!nomeArquivo.endsWith(".txt")) {
                    nomeArquivo += ".txt";
                }

                // Tenta salvar o arquivo com os dados do jogo
                try (FileWriter writer = new FileWriter(nomeArquivo)) {
                    writer.write("dimensão: " + valor + "\n");
                    writer.write("pedras: " + qtdPedrasAtual[0] + "\n");
                    writer.write("maracuja: " + qtdMaracujasTotalAtual[0] + " " + qtdMaracujasAtual[0] + "\n");
                    writer.write("laranja: " + qtdLaranjeirasAtual[0] + " " + qtdLaranjasAtual[0] + "\n");
                    writer.write("abacate: " + qtdAbacateirosAtual[0] + " " + qtdAbacatesAtual[0] + "\n");
                    writer.write("coco: " + qtdCoqueirosAtual[0] + " " + qtdCocoAtual[0] + "\n");
                    writer.write("acerola: " + qtdAceroleirasAtual[0] + " " + qtdAcerolaAtual[0] + "\n");
                    writer.write("amora: " + qtdAmoreirasAtual[0] + " " + qtdAmorasAtual[0] + "\n");
                    writer.write("goiaba: " + qtdGoiabeirasAtual[0] + " " + qtdGoiabasAtual[0] + "\n");
                    writer.write("bichadas: " + qtdBichadasAtual[0] + "\n");
                    writer.write("mochila: " + qtdMochilaAtual[0] + "\n");

                    // Exibe uma mensagem informando que o arquivo foi salvo com sucesso
                    JOptionPane.showMessageDialog(null, "Jogo salvo com sucesso no arquivo: " + nomeArquivo);
                } catch (IOException ex) {
                    // Caso ocorra um erro ao salvar o arquivo
                    JOptionPane.showMessageDialog(null, "Erro ao salvar o jogo: " + ex.getMessage());
                }
            } else {
                // Caso o nome do arquivo seja inválido ou o usuário tenha cancelado
                JOptionPane.showMessageDialog(null, "O nome do arquivo não pode ser vazio!");
            }
        });


        
        // Botão "Iniciar Jogo"
        iniciarButton = new JButton("Iniciar Jogo");
        iniciarButton.setBounds(325, 431, 150, 40);
        add(iniciarButton);

        iniciarButton.addActionListener(e -> iniciarJogo());
        
        visualizarButton = new JButton("Ver terreno");
        visualizarButton.setBounds(491, 431, 150, 40);
        add(visualizarButton);
        
        visualizarButton.addActionListener(e -> {
        	iniciarJogo();
            // Exemplo de valores a serem passados; substitua pelas suas variáveis reais
            /*int dimensao = valor; // Use o valor que você armazenou anteriormente
            int qtdPedras = qtdPedrasAtual[0]; // Quantidade de pedras
            int qtdMaracujas = qtdMaracujasAtual[0]; // Quantidade de maracujas
            int qtdLaranjas = qtdLaranjasAtual[0]; // Quantidade de laranjas
            int qtdAbacates = qtdAbacatesAtual[0]; // Quantidade de abacates
            int qtdCoco = qtdCocoAtual[0]; // Quantidade de cocos
            int qtdAcerolas = qtdAcerolaAtual[0]; // Quantidade de acerolas
            int qtdAmoras = qtdAmorasAtual[0]; // Quantidade de amoras
            int qtdGoiabas = qtdGoiabasAtual[0]; // Quantidade de goiabas
            int qtdBichadas = qtdBichadasAtual[0]; // Quantidade de bichadas
            int qtdMochila = qtdMochilaAtual[0]; // Quantidade de mochilas

            // Instancia e torna a tela visível
            TelaVisualizacao tela = new TelaVisualizacao(dimensao, qtdPedras, qtdMaracujas, qtdLaranjas, 
                                                            qtdAbacates, qtdCoco, qtdAcerolas, 
                                                            qtdAmoras, qtdGoiabas, qtdBichadas, 
                                                            qtdMochila);
            tela.setVisible(true); // Exibe a tela de visualização do terreno*/
        });


        
        //visualizarButton.addActionListener(e -> visualizarTerreno());
        
              
    }
    
    /**
     * Cria componentes para exibir a quantidade de itens, 
     * incluindo rótulo e botões para incrementar e decrementar.
     * 
     * @param texto o texto do rótulo
     * @param qtdAtual o array que contém a quantidade atual
     * @param label o rótulo que exibirá a quantidade
     * @param xLabel a posição x do rótulo
     * @param yLabel a posição y do rótulo
     * @param xIncrementar a posição x do botão de incremento
     * @param yIncrementar a posição y do botão de incremento
     * @param xDecrementar a posição x do botão de decremento
     * @param yDecrementar a posição y do botão de decremento
     */
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
        // Atualiza o verificador com a soma atual de todas as variáveis
        
        if (incrementar) {
            // Verifica se o verificador é menor ou igual ao limite definido (valor - 2) para permitir o incremento
            if (verificador <= (valor*valor)-3 || label == labelNumeroMaracujasTotal) {
            	if(label == labelNumeroMaracujas && valorAtual[0]+1 > qtdMaracujasTotalAtual[0]) {
            		JOptionPane.showMessageDialog(null, "Você atingiu o valor máximo permitido!", "Aviso", JOptionPane.WARNING_MESSAGE);
            	}else {         		
            		if(label == labelNumeroMaracujasTotal) {
        				valorAtual[0]++;
        			}else {
        				valorAtual[0]++; // Decrementa o valor
	                    atualizarVerificador(1);
        			}         		           		
            	}              
            } else {
                JOptionPane.showMessageDialog(null, "Você atingiu o valor máximo permitido!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            // Permite decremento apenas se o valor atual for maior que 0
            if (valorAtual[0] > 0 || label == labelNumeroMaracujasTotal) {
            	if(label == labelNumeroMaracujasTotal && valorAtual[0] == 1) {
            		JOptionPane.showMessageDialog(null, "Você atingiu o valor mínimo permitido!", "Aviso", JOptionPane.WARNING_MESSAGE);
            	}else {
            		if(label == labelNumeroMaracujasTotal && valorAtual[0]-1 < qtdMaracujasAtual[0]){
            			JOptionPane.showMessageDialog(null, "Você atingiu o valor mínio permitido!", "Aviso", JOptionPane.WARNING_MESSAGE);
            		}else {
            			if(label == labelNumeroMaracujasTotal) {
            				valorAtual[0]--;
            			}else {
            				valorAtual[0]--; // Decrementa o valor
    	                    atualizarVerificadorDec(1);
            			}
	            		
            		}
            	}
                                
            } else {
                JOptionPane.showMessageDialog(null, "Você atingiu o valor mínimo permitido!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }

        
     // Atualiza o JLabel com o novo valor
        atualizarNumero(label, valorAtual[0]);
        
        
        //atualizarVerificador(valorAtual[0]);
    }
	
	    // Método para atualizar o verificador com a soma de todas as variáveis relevantes
	    private void atualizarVerificador(int valorr) {
	        verificador += valorr;
	    }
	    
	    private void atualizarVerificadorDec(int valorr) {
	        verificador -= valorr;
	    }
	    
	    /**
	     * Cria um botão com o texto e a posição especificados.
	     * 
	     * @param texto o texto do botão
	     * @param x a posição x do botão
	     * @param y a posição y do botão
	     * @return o botão criado
	     */
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
	    
	    /**
	     * Atualiza o rótulo com o novo valor fornecido.
	     * 
	     * @param label o rótulo a ser atualizado
	     * @param novoValor o novo valor a ser exibido
	     */	
	    // Atualiza o número exibido na interface
	    private void atualizarNumero(JLabel label, int novoValor) {
	        label.setText(String.valueOf(novoValor));
	    }
	
	    // Método que será chamado ao iniciar o jogo
	    private void iniciarJogo() {
	        String tamanhoFloresta = labelNumero.getText();
	        String qtdMaracujasTotalText = labelNumeroMaracujasTotal.getText();
	        String qtdMaracujasText = labelNumeroMaracujas.getText();
	        String qtdPedrasText = labelNumeroPedras.getText();
	        String qtdLaranjasText = labelNumeroLaranjas.getText();
	        String qtdAbacatesText = labelNumeroAbacates.getText();
	        String qtdLaranjeirasText = labelNumeroLaranjeiras.getText();
	        String qtdAbacateirosText = labelNumeroAbacateiros.getText();
	        String qtdCoqueirosText = labelNumeroCoqueiros.getText();
	        String qtdCocoText = labelNumeroCocos.getText();
	        String qtdAceroleirasText = labelNumeroAceroleiras.getText();
	        String qtdAcerolasText = labelNumeroAcerola.getText();
	        String qtdAmoreirasText = labelNumeroAmoreiras.getText();
	        String qtdAmorasText = labelNumeroAmoras.getText();
	        String qtdGoiabeirasText = labelNumeroGoiabeira.getText();
	        String qtdGoiabaText = labelNumeroGoiaba.getText();
	        String qtdBichadasText = labelNumeroBichadas.getText();
	        String qtdMochilaText = labelNumeroMochila.getText();
	        
	        try {
	            int n = Integer.parseInt(tamanhoFloresta);
	            int quantidadePedras = Integer.parseInt(qtdPedrasText);
	            int quantidadeLaranjas = Integer.parseInt(qtdLaranjasText);
	            int quantidadeLaranjeiras = Integer.parseInt(qtdLaranjeirasText);
	            int quantidadeAbacates = Integer.parseInt(qtdAbacatesText);
	            int quantidadeAbacateiros = Integer.parseInt(qtdAbacateirosText);
	            int quantidadeMaracujas = Integer.parseInt(qtdMaracujasText);
	            int quantidadeCoqueiro = Integer.parseInt(qtdCoqueirosText);
	            int quantidadeCocos = Integer.parseInt(qtdCocoText);
	            int quantidadeAceroleiras = Integer.parseInt(qtdAceroleirasText);
	            int quantidadeAcerolas = Integer.parseInt(qtdAcerolasText);
	            int quantidadeAmoreiras = Integer.parseInt(qtdAmoreirasText);
	            int quantidadeAmoras = Integer.parseInt(qtdAmorasText);
	            int quantidadeGoiabeiras = Integer.parseInt(qtdGoiabeirasText);
	            int quantidadeGoiabas = Integer.parseInt(qtdGoiabaText);
	            int quantidadeBichadas = Integer.parseInt(qtdBichadasText);
	            int quantidadeMochila = Integer.parseInt(qtdMochilaText);
	
	
	            // Salvar as configurações e iniciar o jogo
	            salvarConfiguracoes(tamanhoFloresta, qtdPedrasText, qtdMaracujasText, qtdLaranjasText, qtdLaranjeirasText, qtdAbacatesText,qtdAbacateirosText,
	            		qtdCoqueirosText, qtdCocoText, qtdAceroleirasText, qtdAcerolasText, qtdAmoreirasText, qtdAmorasText, 
	            		qtdGoiabeirasText, qtdGoiabaText, qtdBichadasText, qtdMochilaText, qtdMaracujasTotalText);
	
	            // Criar a tela do jogo
	            JFrame gameWindow = new JFrame("Cata Frutas");
	            gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            gameWindow.setResizable(false);
	         // Obtém o diretório do usuário
	            String userDir = System.getProperty("user.home");
	            // Define o caminho completo para o arquivo de configuração
	            String caminhoConfiguracao = userDir + File.separator + "configuracaoJogo.txt";
	
	            // Passa o caminho para o construtor TelaJogo
	            TelaJogo gamePainel = new TelaJogo(caminhoConfiguracao);
	
	            //TelaJogo gamePainel = new TelaJogo("src/Arquivo/configuracaoJogo.txt");
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
	    private void salvarConfiguracoes(String tamanho, String pedras, String maracujas, String laranjas, String laranjeiras, String abacate, String abacateiros,
	            String coqueiros, String cocos, String aceroleiras, String acerola, String amoreiras, String amora, String goiabeira, String goiaba,
	            String bichadas, String mochila, String maracujasTotal) {
	        // Obtém o diretório do usuário
	        String userDir = System.getProperty("user.home");
	        // Define o caminho para o arquivo de configuração no diretório do usuário
	        File configFile = new File(userDir, "configuracaoJogo.txt");
	
	        try (FileWriter writer = new FileWriter(configFile)) {
	            writer.write("dimensão: " + tamanho + "\n");
	            writer.write("pedras: " + pedras + "\n");
	            writer.write("maracuja: " + maracujasTotal + " " + maracujas + "\n");
	            writer.write("laranja: " + laranjeiras + " " + laranjas + "\n");
	            writer.write("abacate: " + abacateiros + " " + abacate + "\n");
	            writer.write("coco: " + coqueiros + " " + cocos + "\n");
	            writer.write("acerola: " + aceroleiras + " " + acerola + "\n");
	            writer.write("amora: " + amoreiras + " " + amora + "\n");
	            writer.write("goiaba: " + goiabeira + " " + goiaba + "\n");
	            writer.write("bichadas: " + bichadas + "\n");
	            writer.write("mochila: " + mochila + "\n");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	  
		// Botão Importar Arquivo
		/*JButton importButton = new JButton("Importar Arquivo");
		importButton.setBounds(300, 460, 120, 50);
		importButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        int result = fileChooser.showOpenDialog(null);
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            JOptionPane.showMessageDialog(null, "Arquivo selecionado: " + selectedFile.getAbsolutePath());
		
		            // Tentativa de leitura do arquivo
		            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
		                String linha;
		                StringBuilder conteudo = new StringBuilder(); // Armazenar o conteúdo do arquivo
		                while ((linha = br.readLine()) != null) {
		                    conteudo.append(linha).append("\n");
		                }
		                
		                // Exibir o conteúdo do arquivo em um JOptionPane
		                JOptionPane.showMessageDialog(null, "Conteúdo do arquivo:\n" + conteudo.toString());
		
		            } catch (IOException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo.");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Nenhum arquivo foi selecionado.");
		        }
		    }
		});
		add(importButton);
		setVisible(true);*/
	
	
	    // Método principal para iniciar a interface
	    /*public static void main(String[] args) {
	        JFrame frame = new JFrame("Tela de Configuração");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setResizable(false);
	        Configuração telaInicial = new Configuração();
	        frame.getContentPane().add(telaInicial);
	        frame.pack(); // Ajusta o tamanho do JFrame com base nos componentes
	        frame.setLocationRelativeTo(null); // Centraliza na tela
	        frame.setVisible(true); // Torna a janela visível
	    }*/
	    
	}
