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
    public TelaJogo(int n, int quantidadePedras, int quantidadeLaranjasNoChao) {
        // Verificar se n ultrapassa o limite e exibir mensagem
        if (n > LIMITE_MATRIZ) {
            JOptionPane.showMessageDialog(null, "Para melhor experiência, delimitamos a dimensão do jogo a uma matriz 30x30.", "Limite de Dimensão", JOptionPane.INFORMATION_MESSAGE);
            n = LIMITE_MATRIZ;  // Ajustar n para o limite
        }

        this.maxColunasTela = n;
        this.maxLinhasTela = n;
        this.quantidadePedras = quantidadePedras;
        this.quantidadeLaranjasNoChao = quantidadeLaranjasNoChao;

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

    private void ajustarTamanhoTile(int larguraDisponivel, int alturaDisponivel) {
        // Calcular o tamanho do tile para que a matriz se ajuste à tela
        int alturaAreaJogo = alturaDisponivel - 100; // Deixar espaço para os botões
        int larguraAreaJogo = larguraDisponivel;

        // Calcular o tamanho do tile
        int tamanhoTileHorizontal = larguraAreaJogo / maxColunasTela;
        int tamanhoTileVertical = alturaAreaJogo / maxLinhasTela;

<<<<<<< HEAD
    // Caminho para a imagem do tile
private ImageIcon tileImage;

// Construtor que recebe a dimensão da floresta
public TelaJogo(int n) {
    this.maxScreenCol = n;
    this.maxScreenRow = n;
    this.screenWidth = tilesize * maxScreenCol;
    this.screenHeight = tilesize * maxScreenRow;

    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.lightGray); 
    this.setDoubleBuffered(true); 

    // Carrega a imagem do tile
    tileImage = new ImageIcon("src/imagens/grama.png"); // Atualize com o caminho da sua imagem

    // Tenta carregar as configurações de arquivo
    carregarConfiguracoes();

    startGameThread();
}

private void carregarConfiguracoes() {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/Arquivo/configuracaoJogo.txt"))) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            System.out.println(linha); // Aqui você pode adicionar lógica para configurar o terreno com base no arquivo
=======
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
>>>>>>> 70b15392eb9338da3d89f9a1252ed0aead85665e
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

<<<<<<< HEAD
public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start(); 
}

@Override
public void run() {
    while (gameThread != null) {
        updateGame();
        repaint(); 
        try {
            Thread.sleep(1000 / 60); 
        } catch (InterruptedException e) {
            e.printStackTrace();
=======
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
>>>>>>> 70b15392eb9338da3d89f9a1252ed0aead85665e
        }
    }
}

<<<<<<< HEAD
// Método responsável por atualizar o estado do jogo
private void updateGame() {
    // Lógica para atualizar o estado do jogo
}

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Lógica para desenhar o terreno, as pedras, etc.
    for (int linha = 0; linha < maxScreenRow; linha++) {
        for (int coluna = 0; coluna < maxScreenCol; coluna++) {
            // Desenha a imagem do tile
                g.drawImage(tileImage.getImage(), coluna * tilesize, linha * tilesize, tilesize, tilesize, null);
=======
    private void atualizarJogo() {
        // Lógica de atualização do jogo aqui
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int linha = 0; linha < maxLinhasTela; linha++) {
            for (int coluna = 0; coluna < maxColunasTela; coluna++) {
                g.drawImage(imagemGrama.getImage(), coluna * tamanhoTile, linha * tamanhoTile, tamanhoTile, tamanhoTile, null);
>>>>>>> 70b15392eb9338da3d89f9a1252ed0aead85665e
            }
        }

        for (Pedra pedra : pedras) {
            pedra.desenhar(g, tamanhoTile);
        }

        for (Laranja laranjaChao : laranjasNoChao) {
            laranjaChao.desenhar(g, tamanhoTile);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cata-Frutas");
            TelaJogo telaJogo = new TelaJogo(10, 5, 5); // Exemplo de inicialização
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(telaJogo);
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null); // Centraliza a janela na tela
        });
    }
}
