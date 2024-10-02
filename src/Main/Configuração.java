package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Configuração extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField tamanhoCampo;
    private JButton iniciarButton;

    public Configuração() {
        // Configurações do painel
        setLayout(null);

        // Criação dos componentes
        JLabel tamanhoLabel = new JLabel("Digite a dimensão da floresta (n x n):");
        tamanhoLabel.setBounds(17, 29, 232, 30);

        tamanhoCampo = new JTextField();
        tamanhoCampo.setBounds(267, 30, 100, 30);

        iniciarButton = new JButton("Iniciar Jogo");
        iniciarButton.setBounds(120, 271, 150, 40);

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
    }

    private void iniciarJogo() {
        String input = tamanhoCampo.getText();
        int n;

        try {
            n = Integer.parseInt(input);
            if (n <= 5) {
                throw new NumberFormatException();
            }
            // Cria uma nova janela para o jogo
            JFrame gameWindow = new JFrame("Cata Frutas");
            gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameWindow.setResizable(false);

            TelaJogo gamePainel = new TelaJogo(n);
            gameWindow.getContentPane().add(gamePainel);
            gameWindow.pack();
            gameWindow.setLocationRelativeTo(null);
            gameWindow.setVisible(true);

            // Fecha a tela de configuração se não for mais necessária
            SwingUtilities.getWindowAncestor(this).dispose(); 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
