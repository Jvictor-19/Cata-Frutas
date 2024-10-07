package Main;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // Cria a janela principal
        JFrame janela = new JFrame("Cata-Frutas");

        // Painel de jogo (Matriz 10x10, com 5 pedras e 3 laranjas)
        TelaJogo telaJogo = new TelaJogo(10, 5, 3);

        // Painel de botões (parte inferior)
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());
        painelBotoes.setPreferredSize(new Dimension(800, 100)); // Altura do painel de botões

        // Botões de exemplo
        JButton botaoPausa = new JButton("Pausar");
        JButton botaoReiniciar = new JButton("Reiniciar");

        // Adiciona os botões ao painel
        painelBotoes.add(botaoPausa);
        painelBotoes.add(botaoReiniciar);

        // Configura o layout da janela principal
        janela.setLayout(new BorderLayout());

        // Adiciona o painel de jogo ao centro da janela
        janela.add(telaJogo, BorderLayout.CENTER);

        // Adiciona o painel de botões na parte inferior da janela
        janela.add(painelBotoes, BorderLayout.SOUTH);

        // Configurações finais da janela
        janela.pack(); // Ajusta o tamanho da janela para comportar os componentes
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true); // Exibe a janela
    }
}
