package Main;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TelaVisualizacao extends JFrame {
    private int[][] terreno; // Matriz para representar o terreno
    private int dimensao; // Dimensão do terreno
    private int qtdPedras;
    private int qtdMaracujas, qtdLaranjas, qtdAbacates, qtdCoco, qtdAcerola, qtdAmora, qtdGoiaba, qtdBichadas;
    private int qtdMochila;

    public TelaVisualizacao(int dimensao, int qtdPedras, int qtdMaracujas, int qtdLaranjas, int qtdAbacates,
                            int qtdCoco, int qtdAcerola, int qtdAmora, int qtdGoiaba, int qtdBichadas, 
                            int qtdMochila) {
        this.dimensao = dimensao;
        this.qtdPedras = qtdPedras;
        this.qtdMaracujas = qtdMaracujas;
        this.qtdLaranjas = qtdLaranjas;
        this.qtdAbacates = qtdAbacates;
        this.qtdCoco = qtdCoco;
        this.qtdAcerola = qtdAcerola;
        this.qtdAmora = qtdAmora;
        this.qtdGoiaba = qtdGoiaba;
        this.qtdBichadas = qtdBichadas;
        this.qtdMochila = qtdMochila;
        this.terreno = new int[dimensao][dimensao];

        setTitle("Visualização do Terreno");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}
