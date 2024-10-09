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
        
<<<<<<< Updated upstream
=======
        labelNumeroPedras = new JLabel(String.valueOf(qtdPedras));
        labelNumeroPedras.setBounds(250, 60, 50, 25);
        labelNumeroPedras.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelNumeroPedras);

        /*pedras = new JTextField(String.valueOf(qtdPedras));
        pedras.setBounds(250, 60, 50, 25);
        pedras.setEditable(false); // Torna o campo de texto não editável*/
        
        //add(pedras);

        JButton btnIncrementarPedras = criarBotao("+", 305, 60);
        JButton btnDecrementarPedras = criarBotao("-", 229, 60);
        add(btnIncrementarPedras);
        add(btnDecrementarPedras);

        btnIncrementarPedras.addActionListener(e -> {
            if (qtdPedras >= (valor*valor)-2) {
                // Exibe um aviso ao usuário
                JOptionPane.showMessageDialog(null, "Você atingiu o valor máximo permitido!", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                qtdPedras++; // Incrementa a quantidade de pedras se o valor não foi atingido
            }
            atualizarNumero(labelNumeroPedras, qtdPedras); // Atualiza o número de pedras exibido
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
>>>>>>> Stashed changes
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
        String qtdPedras = pedras.getText();
        String qtdMaracujas = maracujas.getText();
        String qtdMaracujaNoChao = maracujaNoChao.getText();
        String qtdLaranjeira = laranjeira.getText();
        String qtdLaranjaNoChao = laranjaNoChao.getText();
        String qtdAbacateiro = abacateiro.getText();
        String qtdAbacateNoChao = abacateNoChao.getText();
        String qtdCoqueiro = Coqueiro.getText();
        String qtdCocoNoChao = cocoNoChao.getText();
        String qtdAceroleira = aceroleira.getText();
        String qtdAcerolaNoChao = acerolaNoChao.getText();
        String qtdAmoreira = amoreira.getText();
        String qtdAmoraNoChao = amoraNoChao.getText();
        String qtdGaiabeira = gaiabeira.getText();
        String qtdGoiabaNoChao = goiabaNoChao.getText();
        String porcentagem = porcentagemBichadas.getText();
        String capacidadeMochila = capacidadeDaMochila.getText();

        try {
            // Valida se os campos foram preenchidos corretamente
            int n = Integer.parseInt(tamanhoFloresta);
            int pedras = Integer.parseInt(qtdPedras);

            if (n <= 5 || pedras < 0) {
                throw new NumberFormatException("Dimensão deve ser maior que 5 e pedras não pode ser negativa.");
            }

            // Salvar as configurações em um arquivo de texto
            salvarConfiguracoes(tamanhoFloresta, qtdPedras, qtdMaracujas, qtdMaracujaNoChao, 
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

    private void salvarConfiguracoes(String tamanho, String pedras, String maracujas, String maracujaNoChao, 
                                      String laranjeira, String laranjaNoChao, String abacateiro, String abacateNoChao,
                                      String coqueiro, String cocoNoChao, String aceroleira, String acerolaNoChao, 
                                      String amoreira, String amoraNoChao, String gaiabeira, String goiabaNoChao,
                                      String porcentagem, String capacidadeMochila) {
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