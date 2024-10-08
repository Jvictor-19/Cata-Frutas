package Main;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import Frutas.Laranja;
import Elementos.ElementosEstáticos.Pedra;

public class TelaJogo extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;

    // Tamanho original dos tiles
    final int tamanhoTileOriginal = 22;
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

    private boolean jogoPausado = false;  // Controle de pausa
    private Thread threadJogo;

    // Limite máximo para as dimensões da matriz
    private static final int LIMITE_MATRIZ = 30;

    // Adicionar os layouts
    public TelaJogo(String configFilePath) {
        // Lê os parâmetros do arquivo de configuração
        lerConfiguracao(configFilePath);

        this.pedras = new ArrayList<>();
        this.laranjasNoChao = new ArrayList<>();

        // Configurar o painel principal
        this.setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ajustarTamanhoTile(screenSize.width, screenSize.height);

        this.setPreferredSize(new Dimension(tamanhoTile * maxColunasTela, tamanhoTile * maxLinhasTela + 35)); // Aumentar altura para os botões
        this.setBackground(Color.lightGray);
        this.setDoubleBuffered(true);

        imagemGrama = new ImageIcon("src/imagens/grama.png");

        Random random = new Random();
        int x = random.nextInt(maxColunasTela);
        int y = random.nextInt(maxLinhasTela);
        laranja = new Laranja(x, y);

        gerarPedras();
        gerarLaranjasNoChao();

        // Adicionar painel com botões "Pausar" e "Sair"
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());

        JButton botaoPausar = new JButton("Pausar");
        botaoPausar.addActionListener(e -> {
            if (jogoPausado) {
                botaoPausar.setText("Pausar");
                iniciarThreadJogo();  // Retoma o jogo
            } else {
                botaoPausar.setText("Continuar");
                pararJogo();  // Pausa o jogo
            }
            jogoPausado = !jogoPausado;
        });

        // Modificação para exibir confirmação antes de sair
        JButton botaoSair = new JButton("Sair");
        botaoSair.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(null, "Você deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                System.exit(0);  // Sai do jogo se o usuário confirmar
            }
        });

        painelBotoes.add(botaoPausar);
        painelBotoes.add(botaoSair);

        // Adicionar painel de botões ao painel principal
        this.add(painelBotoes, BorderLayout.SOUTH);

        iniciarThreadJogo();
    }

    private void lerConfiguracao(String configFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(configFilePath))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Tamanho:")) {
                    maxColunasTela = maxLinhasTela = Integer.parseInt(linha.split(":")[1].trim());
                } else if (linha.startsWith("Quantidade de Pedras:")) {
                    quantidadePedras = Integer.parseInt(linha.split(":")[1].trim());
                } else if (linha.startsWith("Quantidade de Laranjas:")) {
                    quantidadeLaranjasNoChao = Integer.parseInt(linha.split(":")[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Você pode definir valores padrão aqui em caso de falha ao ler o arquivo
            maxColunasTela = maxLinhasTela = 10; // Valor padrão
            quantidadePedras = 5; // Valor padrão
            quantidadeLaranjasNoChao = 5; // Valor padrão
        }
    }

    private void ajustarTamanhoTile(int larguraDisponivel, int alturaDisponivel) {
        // Calcular o tamanho do tile para que a matriz se ajuste à tela
        int alturaAreaJogo = alturaDisponivel - 100; // Deixar espaço para os botões
        int larguraAreaJogo = larguraDisponivel;

        // Calcular o tamanho do tile
        int tamanhoTileHorizontal = larguraAreaJogo / maxColunasTela;
        int tamanhoTileVertical = alturaAreaJogo / maxLinhasTela;

        // Escolher o menor tamanho para garantir que a matriz fique dentro da tela
        tamanhoTile = Math.min(tamanhoTileHorizontal, tamanhoTileVertical);
        tamanhoTile = Math.max(tamanhoTileOriginal, tamanhoTile); // Remover limite inferior para o tamanho do tile

        // Verificar se as dimensões da matriz se encaixam na tela
        if (tamanhoTile * maxColunasTela > larguraDisponivel) {
            maxColunasTela = larguraDisponivel / tamanhoTile; // Ajustar colunas
        }
        if (tamanhoTile * maxLinhasTela > alturaAreaJogo) {
            maxLinhasTela = alturaAreaJogo / tamanhoTile; // Ajustar linhas
        }
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
        if (!jogoPausado) {
            threadJogo = new Thread(this);
            threadJogo.start();
        }
    }

    public void pararJogo() {
        jogoPausado = true;
        threadJogo = null;  // Pausa o jogo
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
        // Lógica de atualização do jogo aqui
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
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Jogo de Laranjas");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setContentPane(new TelaJogo("config.txt")); // Passando o caminho do arquivo de configuração
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }*/
}
