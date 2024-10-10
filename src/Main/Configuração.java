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

public class Configuração extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton iniciarButton;
    private JButton visualizarButton;
    private JButton salvarButton;

    private JLabel labelNumero;
    private JLabel labelNumeroPedras;
    private JLabel labelNumeroMaracujasTotal; // Adicionado para maracujás
    private JLabel labelNumeroMaracujas;
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
    private int qtdMaracujasTotal = 1; // Inicialização da quantidade de maracujás
    private int qtdMaracujas = 0;
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
    private int capMochila = 0;
    int[] qtdMaracujasTotalAtual = {qtdMaracujasTotal};
    int[] qtdMaracujasAtual = {qtdMaracujas};
    
    private int verificador = qtdPedras + qtdMaracujas + qtdLaranjas + qtdLaranjeiras + 
            qtdAbacates + qtdAbacateiros + qtdCoco + qtdCoqueiros + qtdAcerola + 
            qtdAceroleiras + qtdAmoras + qtdAmoreiras + qtdGoiaba + qtdGoiabeira;


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
      

        int[] qtdPedrasAtual = {qtdPedras};
        labelNumeroPedras = new JLabel();
        criarComponentesQuantidade("Quantidade de pedras:", qtdPedrasAtual, labelNumeroPedras, 22, 60, 305, 60, 229, 60);
            
        //int[] qtdMaracujasTotalAtual = {qtdMaracujasTotal};
        labelNumeroMaracujasTotal = new JLabel();
        criarComponentesQuantidade("Quantidade Total de maracujas:", qtdMaracujasTotalAtual, labelNumeroMaracujasTotal, 22, 87, 305, 87, 229, 87);
        
        //int[] qtdMaracujasAtual = {qtdMaracujas};
        labelNumeroMaracujas = new JLabel();
        criarComponentesQuantidade("Quantidade de maracujas:", qtdMaracujasAtual, labelNumeroMaracujas, 370, 87, 655, 87, 579, 87);
        
        int[] qtdLaranjeirasAtual = {qtdLaranjeiras};
        labelNumeroLaranjeiras = new JLabel();
        criarComponentesQuantidade("Quantidade de Laranjeiras:", qtdLaranjeirasAtual, labelNumeroLaranjeiras, 22, 114, 305, 114, 229, 114);
        
        int[] qtdLaranjasAtual = {qtdLaranjas};
        labelNumeroLaranjas = new JLabel();
        criarComponentesQuantidade("Quantidade de Laranjas:", qtdLaranjasAtual, labelNumeroLaranjas, 370, 114, 655, 114, 579, 114);

        int[] qtdAbacateirosAtual = {qtdAbacateiros};
        labelNumeroAbacateiros = new JLabel();
        criarComponentesQuantidade("Quantidade de Abacateiros:", qtdAbacateirosAtual, labelNumeroAbacateiros, 22, 141, 305, 141, 229, 141);

        int[] qtdAbacatesAtual = {qtdAbacates};
        labelNumeroAbacates = new JLabel();
        criarComponentesQuantidade("Quantidade de Abacates:", qtdAbacatesAtual, labelNumeroAbacates, 370, 141, 655, 141, 579, 141);

        int[] qtdCoqueirosAtual = {qtdCoqueiros};
        labelNumeroCoqueiros = new JLabel();
        criarComponentesQuantidade("Quantidade de Coqueiros:", qtdCoqueirosAtual, labelNumeroCoqueiros, 22, 168, 305, 168, 229, 168);
        
        int[] qtdCocoAtual = {qtdCoco};
        labelNumeroCocos = new JLabel();
        criarComponentesQuantidade("Quantidade de Cocos:", qtdCocoAtual, labelNumeroCocos, 370, 168, 655, 168, 579, 168);
        
        int[] qtdAceroleirasAtual = {qtdAceroleiras};
        labelNumeroAceroleiras = new JLabel();
        criarComponentesQuantidade("Quantidade de Aceroleiras:", qtdAceroleirasAtual, labelNumeroAceroleiras, 22, 195, 305, 195, 229, 195);
        
        int[] qtdAcerolaAtual = {qtdAcerola};
        labelNumeroAcerola = new JLabel();
        criarComponentesQuantidade("Quantidade de Acerolas:", qtdAcerolaAtual, labelNumeroAcerola, 370, 195, 655, 195, 579, 195);
        
        int[] qtdAmoreirasAtual = {qtdAmoreiras};
        labelNumeroAmoreiras = new JLabel();
        criarComponentesQuantidade("Quantidade de Amoreiras:", qtdAmoreirasAtual, labelNumeroAmoreiras, 22, 222, 305, 222, 229, 222);

        int[] qtdAmorasAtual = {qtdAmoras};
        labelNumeroAmoras = new JLabel();
        criarComponentesQuantidade("Quantidade de Amoras:", qtdAmorasAtual, labelNumeroAmoras, 370, 222, 655, 222, 579, 222);
        
        int[] qtdGoiabeirasAtual = {qtdGoiabeira};
        labelNumeroGoiabeira = new JLabel();
        criarComponentesQuantidade("Quantidade de Goiabeiras:", qtdGoiabeirasAtual, labelNumeroGoiabeira, 22, 249, 305, 249, 229, 249);

        int[] qtdGoiabasAtual = {qtdGoiaba};
        labelNumeroGoiaba = new JLabel();
        criarComponentesQuantidade("Quantidade de Goiabas:", qtdGoiabasAtual, labelNumeroGoiaba, 370, 249, 655, 249, 579, 249);
        
        int[] qtdBichadasAtual = {porBichadas};
        labelNumeroBichadas= new JLabel();
        criarComponentesQuantidade("Porcentagem de Bichadas:", qtdBichadasAtual, labelNumeroBichadas, 22, 276, 305, 276, 229, 276);

        int[] qtdMochilaAtual = {capMochila};
        labelNumeroMochila = new JLabel();
        criarComponentesQuantidade("Capacidade da Mochila:", qtdMochilaAtual, labelNumeroMochila, 22, 303, 305, 303, 229, 303);

        // Botão "Iniciar Jogo"
        iniciarButton = new JButton("Iniciar Jogo");
        iniciarButton.setBounds(341, 431, 150, 40);
        add(iniciarButton);

        iniciarButton.addActionListener(e -> iniciarJogo());
        
        visualizarButton = new JButton("Ver terreno");
        visualizarButton.setBounds(480, 431, 180, 40);
        add(visualizarButton);
        
        //visualizarButton.addActionListener(e -> visualizarTerreno());
              
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
    private void salvarConfiguracoes(String tamanho, String pedras,String maracujas, String laranjas, String laranjeiras, String abacate,String abacateiros,
    		String coqueiros, String cocos, String aceroleiras, String acerola, String amoreiras, String amora, String goiabeira, String goiaba, 
    		String bichadas, String mochila, String maracujasTotal) {
        try (FileWriter writer = new FileWriter("src/Arquivo/configuracaoJogo.txt")) {
        	writer.write("dimensão: " + tamanho + "\n");
            writer.write("pedras: " + pedras + "\n");
            writer.write("maracuja: " + maracujasTotal + " " + maracujas+ "\n");
            writer.write("laranja: " + laranjeiras +  " " + laranjas + "\n");
            writer.write("abacate: " + abacateiros +  " " + abacate + "\n");
            writer.write("coco: " + coqueiros +  " " + cocos + "\n");
            writer.write("acerola: " + aceroleiras + " " + acerola + "\n");
            writer.write("amora: " + amoreiras + " " + amora + "\n");
            writer.write("goiaba: " + goiabeira + " " + goiaba + "\n");
            writer.write("bichadas: " + bichadas + "\n");
            writer.write("mochila: " + mochila + "\n");
        } catch (IOException e) {
            e.printStackTrace();
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
