package Botões;

import javax.swing.JButton;
import javax.swing.JLabel;

import Main.SorteioDados;

import java.awt.event.ActionListener;

public class BotaoSortear {

    private JButton botao; // O botão "Sortear"

    public BotaoSortear(JLabel labelDado1, JLabel labelDado2) {
        this.botao = new JButton("Sortear");
        configurarAcao(labelDado1, labelDado2);
    }

    private void configurarAcao(JLabel labelDado1, JLabel labelDado2) {
        ActionListener actionListener = e -> {
            int[] resultados = SorteioDados.sortearDados(); // Chama o método da classe Sorteio
            labelDado1.setText("Dado 1: " + resultados[0]); // Atualiza o label com o resultado do dado 1
            labelDado2.setText("Dado 2: " + resultados[1]); // Atualiza o label com o resultado do dado 2
        };
        this.botao.addActionListener(actionListener); // Adiciona a ação ao botão
    }

    public JButton getBotao() {
        return botao; // Método para obter o botão
    }
}
