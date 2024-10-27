package Botões;

import javax.swing.JButton;
import javax.swing.JLabel;

import Main.SorteioDados;

import java.awt.event.ActionListener;

public class BotaoSortear {

    private JButton botao; // O botão "Sortear"
    

    // Construtor agora recebe apenas um JLabel para exibir a soma dos passos
    public BotaoSortear(JLabel labelPassos) {
        this.botao = new JButton("Sortear");
        configurarAcao(labelPassos); // Configura a ação do botão
    }

    // Configura a ação do botão
    private void configurarAcao(JLabel labelPassos) {
        ActionListener actionListener = e -> {
            int[] resultados = SorteioDados.sortearDados(); // Chama o método para sortear os dados
            int somaPassos = resultados[0] + resultados[1]; // Calcula a soma dos dois dados
            labelPassos.setText("Passos: " + somaPassos); // Atualiza o label com a soma dos passos
        };
        this.botao.addActionListener(actionListener); // Adiciona a ação ao botão
    }

    // Método para obter o botão
    public JButton getBotao() {
        return botao;
    }
}
