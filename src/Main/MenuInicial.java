package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInicial extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField tamanhoCampo;
    private JButton iniciarButton;
    private JTextField pedras;
    private JLabel lblMaracujjs;
    private JLabel lblLaranja;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblAcerola;
    private JLabel lblAmora;
    private JLabel lblGoiaba;
    private JLabel lblBichadas;
    private JLabel lblMochila;

    public MenuInicial() {
        // Configurações do painel
        setLayout(null); // Desativar o layout padrão

        // Criação dos componentes
        JLabel tamanhoLabel = new JLabel("Dimensão da floresta (n x n):");
        tamanhoLabel.setBounds(17, 29, 232, 30); // Define a posição e o tamanho do rótulo

        tamanhoCampo = new JTextField();
        tamanhoCampo.setBounds(267, 30, 100, 30); // Define a posição e o tamanho do campo de texto

        iniciarButton = new JButton("Iniciar Jogo");
        iniciarButton.setBounds(120, 271, 150, 40); // Define a posição e o tamanho do botão

        // Adiciona os componentes ao painel
        add(tamanhoLabel);
        add(tamanhoCampo);
        add(iniciarButton);

        // Adiciona o ouvinte de ação ao botão
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo();
            }
        });

        // Define o tamanho do painel
        setPreferredSize(new Dimension(467, 488)); 
        
        pedras = new JTextField();
        pedras.setBounds(253, 72, 114, 19);
        add(pedras);
        pedras.setColumns(10);
        
        JLabel lblQuantidadeDePedras = new JLabel("Pedras");
        lblQuantidadeDePedras.setBounds(17, 63, 170, 15);
        add(lblQuantidadeDePedras);
        
        lblMaracujjs = new JLabel("Maracujás");
        lblMaracujjs.setBounds(17, 87, 170, 15);
        add(lblMaracujjs);
        
        lblLaranja = new JLabel("Laranja");
        lblLaranja.setBounds(17, 106, 70, 15);
        add(lblLaranja);
        
        lblNewLabel = new JLabel("Abacate");
        lblNewLabel.setBounds(17, 133, 70, 15);
        add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel("Coco");
        lblNewLabel_1.setBounds(17, 160, 70, 15);
        add(lblNewLabel_1);
        
        lblAcerola = new JLabel("Acerola");
        lblAcerola.setBounds(12, 175, 70, 15);
        add(lblAcerola);
        
        lblAmora = new JLabel("Amora");
        lblAmora.setBounds(12, 187, 70, 15);
        add(lblAmora);
        
        lblGoiaba = new JLabel("Goiaba");
        lblGoiaba.setBounds(12, 218, 70, 15);
        add(lblGoiaba);
        
        lblBichadas = new JLabel("Bichadas");
        lblBichadas.setBounds(127, 191, 70, 15);
        add(lblBichadas);
        
        lblMochila = new JLabel("Mochila");
        lblMochila.setBounds(127, 218, 70, 15);
        add(lblMochila);
    }

    private void iniciarJogo() {
        // Pega o valor da dimensão do campo de texto
        String input = tamanhoCampo.getText();
        int n;

        try {
            n = Integer.parseInt(input);
            if (n <= 5) {
                throw new NumberFormatException();
            }
            // Cria uma nova janela para o jogo
            JFrame gameWindow = new JFrame();
            gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameWindow.setResizable(false);
            gameWindow.setTitle("Cata Frutas");

            GamePainel gamePainel = new GamePainel(n);
            gameWindow.getContentPane().add(gamePainel);
            gameWindow.pack();
            gameWindow.setLocationRelativeTo(null);
            gameWindow.setVisible(true);

            // Opcional: Fecha o menu inicial se não for mais necessário
            SwingUtilities.getWindowAncestor(this).dispose(); 
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
