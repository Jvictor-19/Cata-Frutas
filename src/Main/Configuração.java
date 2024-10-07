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
    private JTextField laranjas; // Corrigido para referência a laranjas

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
        
        JLabel lblQuantidadeDeLaranjas = new JLabel("Quantidade de Laranjas:");
        lblQuantidadeDeLaranjas.setBounds(17, 87, 170, 15);
        laranjas = new JTextField(); // Adicionada corretamente
        laranjas.setBounds(250, 87, 100, 15);

        iniciarButton = new JButton("Iniciar Jogo");
        iniciarButton.setBounds(341, 431, 150, 40); 

        // Adiciona os componentes ao painel
        add(tamanhoLabel);
        add(tamanhoCampo);
        add(lblQuantidadeDePedras);
        add(pedras);
        add(lblQuantidadeDeLaranjas);
        add(laranjas);
        add(iniciarButton);
        
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
            TelaJogo gamePainel = new TelaJogo(n, quantidadePedras, quantidadeLaranjas); // Passa as dimensões e quantidades
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
            writer.write("Tamanho: " + tamanho + "\n");
            writer.write("Quantidade de Pedras: " + pedras + "\n");
            writer.write("Quantidade de Laranjas: " + laranjas + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
