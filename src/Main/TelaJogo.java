package Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import Frutas.Laranja;
import Elementos.ElementosEstáticos.Pedra;

public class TelaJogo extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;

    // Tamanho original dos tiles
    final int tamanhoTileOriginal = 22;
    final int escalaMin = 2;
    final int escalaMax = 5;
    int tamanhoTile;
    int maxColunasTela;
    int maxLinhasTela;

    // Elementos do jogo
    private ImageIcon imagemGrama;
    private Laranja laranja;
    private ArrayList<Pedra> pedras;
    private ArrayList<Laranja> laranjasNoChao;
    private int quantidadePedras;
    private int quantidadeLaranjasNoChao;

    Thread threadJogo;

    public TelaJogo(int n, int quantidadePedras, int quantidadeLaranjasNoChao) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int larguraDisponivel = (int) screenSize.getWidth();
        int alturaDisponivel = (int) screenSize.getHeight();

        ajustarTamanhoTile(n, larguraDisponivel, alturaDisponivel);

        this.maxColunasTela = n;
        this.maxLinhasTela = n;
        this.quantidadePedras = quantidadePedras;
        this.quantidadeLaranjasNoChao = quantidadeLaranjasNoChao;

        this.pedras = new ArrayList<>();
        this.laranjasNoChao = new ArrayList<>();

        this.setPreferredSize(new Dimension(tamanhoTile * maxColunasTela, tamanhoTile * maxLinhasTela));
        this.setBackground(Color.lightGray);
        this.setDoubleBuffered(true);

        imagemGrama = new ImageIcon("src/imagens/grama.png");

        Random random = new Random();
        int x = random.nextInt(maxColunasTela);
        int y = random.nextInt(maxLinhasTela);
        laranja = new Laranja(x, y);

        gerarPedras();
        gerarLaranjasNoChao();

        iniciarThreadJogo();
    }

    private void ajustarTamanhoTile(int n, int larguraDisponivel, int alturaDisponivel) {
        int tamanhoTileHorizontal = (larguraDisponivel - 50) / n;
        int tamanhoTileVertical = (alturaDisponivel - 150) / n;
        tamanhoTile = Math.min(tamanhoTileHorizontal, tamanhoTileVertical);
        tamanhoTile = Math.max(tamanhoTileOriginal * escalaMin, Math.min(tamanhoTile, tamanhoTileOriginal * escalaMax));
    }

    private void gerarPedras() {
        Random random = new Random();
        int contagem = 0;

        while (contagem < quantidadePedras) {
            int x = random.nextInt(maxColunasTela);
            int y = random.nextInt(maxLinhasTela);

            if (!posicaoOcupada(x, y)) {
                pedras.add(new Pedra(x, y));
                contagem++;
            }
        }
    }

    private void gerarLaranjasNoChao() {
        Random random = new Random();
        int contagem = 0;

        while (contagem < quantidadeLaranjasNoChao) {
            int x = random.nextInt(maxColunasTela);
            int y = random.nextInt(maxLinhasTela);

            if (!posicaoOcupada(x, y)) {
                laranjasNoChao.add(new Laranja(x, y));
                contagem++;
            }
        }
    }

    private boolean posicaoOcupada(int x, int y) {
        for (Pedra pedra : pedras) {
            if (pedra.getX() == x && pedra.getY() == y) {
                return true;
            }
        }

        for (Laranja laranjaChao : laranjasNoChao) {
            if (laranjaChao.getX() == x && laranjaChao.getY() == y) {
                return true;
            }
        }

        return laranja.getX() == x && laranja.getY() == y;
    }

    public void iniciarThreadJogo() {
        threadJogo = new Thread(this);
        threadJogo.start();
    }

    @Override
    public void run() {
        while (threadJogo != null) {
            atualizarJogo();
            repaint();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void atualizarJogo() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int linha = 0; linha < maxLinhasTela; linha++) {
            for (int coluna = 0; coluna < maxColunasTela; coluna++) {
                g.drawImage(imagemGrama.getImage(), coluna * tamanhoTile, linha * tamanhoTile, tamanhoTile, tamanhoTile, null);
            }
        }

        for (Pedra pedra : pedras) {
            pedra.desenhar(g, tamanhoTile);
        }

        for (Laranja laranjaChao : laranjasNoChao) {
            laranjaChao.desenhar(g, tamanhoTile);
        }

        laranja.desenhar(g, tamanhoTile);
    }
}
