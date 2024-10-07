package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Configuração extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField tamanhoCampo;
    private JButton iniciarButton;
    private JTextField pedras;
    private JTextField maracujas;
    private JTextField laranjeira;
    private JTextField maracujaNoChao;
    private JTextField laranjaNoChao;
    private JTextField Coqueiro;
    private JTextField cocoNoChao;
    private JTextField abacateiro;
    private JTextField abacateNoChao;
    private JTextField aceroleira;
    private JTextField acerolaNoChao;
    private JTextField amoreira;
    private JTextField amoraNoChao;
    private JTextField gaiabeira;
    private JTextField goiabaNoChao;
    private JTextField porcentagemBichadas;
    private JTextField capacidadeDaMochila;

    public Configuração() {
        setLayout(null); 
        setSize(800, 600);

        // Criação dos componentes
        JLabel tamanhoLabel = new JLabel("Dimensão da floresta (n x n):");
        tamanhoLabel.setBounds(17, 29, 232, 30); 
        tamanhoCampo = new JTextField();
        tamanhoCampo.setBounds(250, 32, 100, 25); 

        JLabel lblQuantidadeDePedras = new JLabel("Quantidade de Pedras:");
        lblQuantidadeDePedras.setBounds(17, 60, 170, 15);
        pedras = new JTextField();
        pedras.setBounds(250, 60, 100, 15);
        
        iniciarButton = new JButton("Iniciar Jogo");
        iniciarButton.setBounds(341, 431, 150, 40); 

        // Adiciona os componentes ao painel
        add(tamanhoLabel);
        add(tamanhoCampo);
        add(lblQuantidadeDePedras);
        add(pedras);
        add(iniciarButton);
        
        JLabel lblQuantidadeDeMaracujas = new JLabel("Quantidade de maracujujás:");
        lblQuantidadeDeMaracujas.setBounds(17, 87, 204, 15);
        add(lblQuantidadeDeMaracujas);
        
        maracujas = new JTextField();
        maracujas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        maracujas.setBounds(250, 87, 93, 15);
        add(maracujas);
        
        maracujaNoChao = new JTextField();
        maracujaNoChao.setBounds(376, 85, 93, 15);
        add(maracujaNoChao);
        
        JLabel lblQuantidadeDeLaranjas = new JLabel("Quantidade de laranjas");
        lblQuantidadeDeLaranjas.setBounds(17, 114, 204, 15);
        add(lblQuantidadeDeLaranjas);
        
        laranjeira = new JTextField();
        laranjeira.setBounds(250, 112, 93, 15);
        add(laranjeira);
        
        laranjaNoChao = new JTextField();
        laranjaNoChao.setBounds(376, 112, 93, 15);
        add(laranjaNoChao);
        
        JLabel lblQuantidadeDeAbacate = new JLabel("Quantidade de abacate");
        lblQuantidadeDeAbacate.setBounds(17, 145, 204, 15);
        add(lblQuantidadeDeAbacate);
        
        JLabel lblElementosEstticos = new JLabel("Elementos Estáticos");
        lblElementosEstticos.setBounds(108, 12, 150, 15);
        add(lblElementosEstticos);
        
        JLabel lblQuantidadeDeCoco = new JLabel("Quantidade de coco");
        lblQuantidadeDeCoco.setBounds(17, 172, 204, 15);
        add(lblQuantidadeDeCoco);
        
        Coqueiro = new JTextField();
        Coqueiro.setBounds(250, 172, 93, 15);
        add(Coqueiro);
        
        cocoNoChao = new JTextField();
        cocoNoChao.setBounds(376, 172, 93, 15);
        add(cocoNoChao);
        
        abacateiro = new JTextField();
        abacateiro.setBounds(250, 143, 93, 15);
        add(abacateiro);
        
        abacateNoChao = new JTextField();
        abacateNoChao.setBounds(376, 143, 93, 15);
        add(abacateNoChao);
        
        JLabel lblQuantidadeAcerola = new JLabel("Quantidade de acerola");
        lblQuantidadeAcerola.setBounds(17, 200, 204, 15);
        add(lblQuantidadeAcerola);
        
        aceroleira = new JTextField();
        aceroleira.setBounds(250, 198, 93, 15);
        add(aceroleira);
        
        acerolaNoChao = new JTextField();
        acerolaNoChao.setBounds(376, 199, 93, 15);
        add(acerolaNoChao);
        
        JLabel lblQuantidadeDeAmora = new JLabel("Quantidade de amora");
        lblQuantidadeDeAmora.setBounds(17, 233, 204, 15);
        add(lblQuantidadeDeAmora);
        
        amoreira = new JTextField();
        amoreira.setBounds(250, 231, 93, 15);
        add(amoreira);
        
        amoraNoChao = new JTextField();
        amoraNoChao.setBounds(376, 231, 93, 15);
        add(amoraNoChao);
        
        JLabel lblQuantidadeDeGoiaba = new JLabel("Quantidade de goiaba");
        lblQuantidadeDeGoiaba.setBounds(17, 260, 204, 15);
        add(lblQuantidadeDeGoiaba);
        
        gaiabeira = new JTextField();
        gaiabeira.setBounds(250, 258, 93, 15);
        add(gaiabeira);
        
        goiabaNoChao = new JTextField();
        goiabaNoChao.setBounds(376, 260, 93, 15);
        add(goiabaNoChao);
        
        JLabel lblBichadas = new JLabel("Bichadas");
        lblBichadas.setBounds(17, 303, 204, 15);
        add(lblBichadas);
        
        porcentagemBichadas = new JTextField();
        porcentagemBichadas.setBounds(250, 301, 93, 15);
        add(porcentagemBichadas);
        
        JLabel lblMochila = new JLabel("Mochila");
        lblMochila.setBounds(17, 355, 204, 15);
        add(lblMochila);
        
        capacidadeDaMochila = new JTextField();
        capacidadeDaMochila.setBounds(250, 353, 93, 15);
        add(capacidadeDaMochila);
        
        // Adiciona o ouvinte de ação ao botão
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo();
            }
        });
    }

    private void iniciarJogo() {
        String tamanhoFloresta = tamanhoCampo.getText();
        
        try {
            // Converte o tamanho da floresta e quantidade de pedras para int
            int n = Integer.parseInt(tamanhoFloresta);
            int qtdPedras = Integer.parseInt(pedras.getText());
            int qtdMaracujas = Integer.parseInt(maracujas.getText());
            int qtdMaracujaNoChao = Integer.parseInt(maracujaNoChao.getText());
            int qtdLaranjeira = Integer.parseInt(laranjeira.getText());
            int qtdLaranjaNoChao = Integer.parseInt(laranjaNoChao.getText());
            int qtdAbacateiro = Integer.parseInt(abacateiro.getText());
            int qtdAbacateNoChao = Integer.parseInt(abacateNoChao.getText());
            int qtdCoqueiro = Integer.parseInt(Coqueiro.getText());
            int qtdCocoNoChao = Integer.parseInt(cocoNoChao.getText());
            int qtdAceroleira = Integer.parseInt(aceroleira.getText());
            int qtdAcerolaNoChao = Integer.parseInt(acerolaNoChao.getText());
            int qtdAmoreira = Integer.parseInt(amoreira.getText());
            int qtdAmoraNoChao = Integer.parseInt(amoraNoChao.getText());
            int qtdGaiabeira = Integer.parseInt(gaiabeira.getText());
            int qtdGoiabaNoChao = Integer.parseInt(goiabaNoChao.getText());
            int porcentagem = Integer.parseInt(porcentagemBichadas.getText());
            int capacidadeMochila = Integer.parseInt(capacidadeDaMochila.getText());

            // Valida se os campos foram preenchidos corretamente
            if (n <= 5 || qtdPedras < 0) {
                throw new NumberFormatException("Dimensão deve ser maior que 5 e pedras não pode ser negativa.");
            }

            // Salvar as configurações em um arquivo de texto
            salvarConfiguracoes(n, qtdPedras, qtdMaracujas, qtdMaracujaNoChao, 
                qtdLaranjeira, qtdLaranjaNoChao, qtdAbacateiro, qtdAbacateNoChao,
                qtdCoqueiro, qtdCocoNoChao, qtdAceroleira, qtdAcerolaNoChao, 
                qtdAmoreira, qtdAmoraNoChao, qtdGaiabeira, qtdGoiabaNoChao,
                porcentagem, capacidadeMochila);

            // Criar uma nova janela do jogo
            JFrame gameWindow = new JFrame("Cata Frutas");
            gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameWindow.setResizable(false);
            TelaJogo gamePainel = new TelaJogo(n); // Passa a dimensão da floresta
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

    private void salvarConfiguracoes(int tamanho, int pedras, int maracujas, int maracujaNoChao, 
                                      int laranjeira, int laranjaNoChao, int abacateiro, int abacateNoChao,
                                      int coqueiro, int cocoNoChao, int aceroleira, int acerolaNoChao, 
                                      int amoreira, int amoraNoChao, int gaiabeira, int goiabaNoChao,
                                      int porcentagem, int capacidadeMochila) {
        try (FileWriter writer = new FileWriter("src/Arquivo/configuracaoJogo.txt")) {
            writer.write("dimensao: " + tamanho + "\n");
            writer.write("pedras: " + pedras + "\n");
            writer.write("maracujas: " + maracujas + " " + maracujaNoChao + "\n");
            writer.write("laranja: " + laranjeira + " " + laranjaNoChao + "\n");
            writer.write("abacate: " + abacateiro + " " + abacateNoChao + "\n");
            writer.write("coco: " + coqueiro + " " + cocoNoChao + "\n");
            writer.write("acerola: " + aceroleira + " " + acerolaNoChao + "\n");
            writer.write("amora: " + amoreira + " " + amoraNoChao + "\n");
            writer.write("goiaba: " + gaiabeira + " " + goiabaNoChao + "\n");
            writer.write("bichadas: " + porcentagem + "\n");
            writer.write("mochila: " + capacidadeMochila + "\n");
            
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
}
//teste