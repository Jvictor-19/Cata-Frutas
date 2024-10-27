package Botões;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;

public class BotaoSair {

    private JButton botao; // O botão "Sair"

    public BotaoSair() {
        this.botao = new JButton("Sair");
        configurarAcao();
    }

    private void configurarAcao() {
        ActionListener actionListener = e -> {
            int resposta = JOptionPane.showConfirmDialog(null, "Você deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                System.exit(0); // Sai do jogo se o usuário confirmar
            }
        };
        this.botao.addActionListener(actionListener); // Adiciona a ação ao botão
    }

    public JButton getBotao() {
        return botao; // Método para obter o botão
    }
}

