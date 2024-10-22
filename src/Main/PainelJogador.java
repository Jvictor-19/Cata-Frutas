package Main;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * A classe PainelJogador representa um painel que exibe informações sobre dois jogadores
 * e um botão para realizar um sorteio entre eles.
 * 
 * Este painel é um componente gráfico que utiliza um layout de grade para organizar
 * os elementos na tela, incluindo labels para os jogadores e um botão para sortear
 * um dos jogadores aleatoriamente.
 */
public class PainelJogador extends JPanel {

    private JLabel jogador1Label;
    private JLabel jogador2Label;
    private JButton botaoSortear;
    private Random random;

    /**
     * Construtor da classe PainelJogador.
     * 
     * Este construtor inicializa o layout do painel, configura o tamanho e a cor de fundo,
     * e cria os componentes que serão exibidos: dois labels para mostrar os passos dos
     * jogadores e um botão para realizar o sorteio.
     */
    public PainelJogador() {
        this.setLayout(new GridLayout(1, 3, 10, 10)); // Três colunas para os dois jogadores e o botão "Sortear"
        this.setPreferredSize(new Dimension(400, 80)); // Tamanho do painel
        this.setBackground(Color.LIGHT_GRAY);

        random = new Random();

        // Informações do Jogador 1
        jogador1Label = new JLabel("Jogador 1: 0 passos");
        this.add(jogador1Label);

        // Informações do Jogador 2
        jogador2Label = new JLabel("Jogador 2: 0 passos");
        this.add(jogador2Label);

        // Botão "Sortear"
        botaoSortear = new JButton("Sortear");
        botaoSortear.addActionListener(e -> realizarSorteio());
        this.add(botaoSortear);
    }

    /**
     * Atualiza a quantidade de passos do Jogador 1.
     * 
     * @param passos o número de passos a ser atualizado no label do Jogador 1.
     */
    public void atualizarPassosJogador1(int passos) {
        jogador1Label.setText("Jogador 1: " + passos + " passos");
    }

    /**
     * Atualiza a quantidade de passos do Jogador 2.
     * 
     * @param passos o número de passos a ser atualizado no label do Jogador 2.
     */
    public void atualizarPassosJogador2(int passos) {
        jogador2Label.setText("Jogador 2: " + passos + " passos");
    }

    /**
     * Realiza o sorteio entre os jogadores.
     * 
     * Este método sorteia aleatoriamente um número entre 1 e 2, e exibe uma mensagem
     * informando qual jogador foi sorteado.
     */
    private void realizarSorteio() {
        int sorteado = random.nextInt(2) + 1; // Sorteia um número entre 1 e 2
        JOptionPane.showMessageDialog(null, "Jogador " + sorteado + " foi sorteado!");
    }
}
