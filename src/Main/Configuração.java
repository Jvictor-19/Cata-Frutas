package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Configuração extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField tamanhoCampo;
    private JButton iniciarButton;
    private JTextField pedras;

    public Configuração() {
        setLayout(null); 
        setSize(800, 600);

        // Criação dos componentes
        JLabel tamanhoLabel = new JLabel("Dimensão da floresta (n x n):");
        tamanhoLabel.setBounds(17, 29, 232, 30); 
        tamanhoCampo = new JTextField();
        tamanhoCampo.setBounds(250, 30, 100, 30); 

        iniciarButton = new JButton("Iniciar Jogo");
        iniciarButton.setBounds(120, 271, 150, 40); 

        JLabel lblQuantidadeDePedras = new JLabel("Quantidade de Pedras:");
        lblQuantidadeDePedras.setBounds(17, 60, 170, 15);
        pedras = new JTextField();
        pedras.setBounds(250, 60, 100, 30);
        
        // Adiciona os componentes ao painel
        add(tamanhoLabel);
        add(tamanhoCampo);
        add(lblQuantidadeDePedras);
        add(pedras);
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

        try {
            // Valida se os campos foram preenchidos corretamente
            int n = Integer.parseInt(tamanhoFloresta);
            int pedras = Integer.parseInt(qtdPedras);

            if (n <= 5 || pedras < 0) {
                throw new NumberFormatException();
            }

            // Salvar as configurações em um arquivo de texto
            salvarConfiguracoes(tamanhoFloresta, qtdPedras);

            // Criar uma nova janela do jogo
            JFrame gameWindow = new JFrame();
            gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameWindow.setResizable(false);
            gameWindow.setTitle("Cata Frutas");

            TelaJogo gamePainel = new TelaJogo(n); // Passa a dimensão da floresta
            gameWindow.getContentPane().add(gamePainel);
            gameWindow.pack();
            gameWindow.setLocationRelativeTo(null);
            gameWindow.setVisible(true);

            // Fechar a tela de configuração
            SwingUtilities.getWindowAncestor(this).dispose(); 
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salvarConfiguracoes(String tamanho, String pedras) {
        try (FileWriter writer = new FileWriter("config.txt")) {
            writer.write("Dimensão: " + tamanho + "\n");
            writer.write("Pedras: " + pedras + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
