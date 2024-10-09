package Main;

import javax.swing.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Frutas.Frutas;
import Frutas.Abacate;
import Frutas.GerenciadorDeFrutas;
import Frutas.Laranja;
import Frutas.Maracuja;
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
    private ArrayList<Pedra> pedras;
    private int quantidadePedras;

    private Laranja laranja;
    private ArrayList<Laranja> laranjasNoChao;
    private int quantidadeLaranjasNoChao;

    private Abacate abacate;
    private ArrayList<Abacate> abacatesNoChao;
    private int quantidadeAbacatesNoChao;

    private Maracuja maracuja;
    private ArrayList<Maracuja> maracujasNoChao;
    private int quantidadeMaracujaNoChao;

    private int quantidadeBichadas;
    private int tamanhoMochila;

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
        this.abacatesNoChao = new ArrayList<>();
        this.maracujasNoChao = new ArrayList<>(); // Inicializa a lista de maracujás

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
        gerarAbacatesNoChao();
        gerarMaracujaNoChao(); // Chame o método para gerar maracujás

        // Adicionar painel com botões "Pausar" e "Sair"
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());

        // Modificação para exibir confirmação antes de sair
        JButton botaoSair = new JButton("Sair");
        botaoSair.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(null, "Você deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                System.exit(0);  // Sai do jogo se o usuário confirmar
            }
        });

        
        painelBotoes.add(botaoSair);

        // Adicionar painel de botões ao painel principal
        this.add(painelBotoes, BorderLayout.SOUTH);

        iniciarThreadJogo();
    }

    // Variáveis para armazenar a quantidade de árvores de cada fruta
    private int quantidadeArvoresMaracuja;
    private int quantidadeArvoresLaranja;
    private int quantidadeArvoresAbacate;
    private int quantidadeArvoresCoco;
    private int quantidadeArvoresAcerola;
    private int quantidadeArvoresAmora;
    private int quantidadeArvoresGoiaba;

    // Variáveis para armazenar a quantidade de frutas no chão, se necessário
    private int frutasNoChaoMaracuja;
    private int frutasNoChaoAbacate;
    private int frutasNoChaoCoco;
    private int frutasNoChaoAcerola;
    private int frutasNoChaoAmora;
    private int frutasNoChaoGoiaba;

    private void lerConfiguracao(String configFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(configFilePath))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(":");
                String chave = partes[0].trim();
                String valor = partes[1].trim();

                switch (chave) {
                    case "dimensão":
                        maxColunasTela = maxLinhasTela = Integer.parseInt(valor);
                        break;
                    case "pedras":
                        quantidadePedras = Integer.parseInt(valor);
                        break;
                    case "maracuja":
                        String[] valoresMaracuja = valor.split(" ");
                        quantidadeArvoresMaracuja = Integer.parseInt(valoresMaracuja[0].trim());
                        frutasNoChaoMaracuja = Integer.parseInt(valoresMaracuja[1].trim());
                        break;
                    case "laranja":
                        String[] valoresLaranja = valor.split(" ");
                        quantidadeArvoresLaranja = Integer.parseInt(valoresLaranja[0].trim());
                        quantidadeLaranjasNoChao = Integer.parseInt(valoresLaranja[1].trim());
                        break;
                    case "abacate":
                        String[] valoresAbacate = valor.split(" ");
                        quantidadeArvoresAbacate = Integer.parseInt(valoresAbacate[0].trim());
                        quantidadeAbacatesNoChao = Integer.parseInt(valoresAbacate[1].trim());
                        break;
                    case "coco":
                        String[] valoresCoco = valor.split(" ");
                        quantidadeArvoresCoco = Integer.parseInt(valoresCoco[0].trim());
                        frutasNoChaoCoco = Integer.parseInt(valoresCoco[1].trim());
                        break;
                    case "acerola":
                        String[] valoresAcerola = valor.split(" ");
                        quantidadeArvoresAcerola = Integer.parseInt(valoresAcerola[0].trim());
                        frutasNoChaoAcerola = Integer.parseInt(valoresAcerola[1].trim());
                        break;
                    case "amora":
                        String[] valoresAmora = valor.split(" ");
                        quantidadeArvoresAmora = Integer.parseInt(valoresAmora[0].trim());
                        frutasNoChaoAmora = Integer.parseInt(valoresAmora[1].trim());
                        break;
                    case "goiaba":
                        String[] valoresGoiaba = valor.split(" ");
                        quantidadeArvoresGoiaba = Integer.parseInt(valoresGoiaba[0].trim());
                        frutasNoChaoGoiaba = Integer.parseInt(valoresGoiaba[1].trim());
                        break;
                    case "bichadas":
                        quantidadeBichadas = Integer.parseInt(valor);
                        break;
                    case "mochila":
                        tamanhoMochila = Integer.parseInt(valor);
                        break;
                    default:
                        // Se houver outras chaves desconhecidas, pode-se ignorar ou tratar de outro modo
                        System.out.println("Parâmetro desconhecido: " + chave);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Valores padrão em caso de erro na leitura
            maxColunasTela = maxLinhasTela = 10; // Valor padrão
            quantidadePedras = 5; // Valor padrão
            quantidadeArvoresLaranja = 2; // Valor padrão
            quantidadeBichadas = 0; // Valor padrão
            tamanhoMochila = 5; // Valor padrão
        }
    }

    private void ajustarTamanhoTile(int larguraDisponivel, int alturaDisponivel) {
        // Calcular o tamanho do tile para que a matriz se ajuste à tela
        int alturaAreaJogo = alturaDisponivel - 100; // Deixar espaço para os botões
        int larguraAreaJogo = larguraDisponivel;

        // Calcular o tamanho do tile
        int tamanhoTileHorizontal = larguraAreaJogo / maxColunasTela;
        int tamanhoTileVertical = alturaAreaJogo / maxLinhasTela;

        // Escolher o menor tamanho para garantir que a matriz caiba na tela
        tamanhoTile = Math.min(tamanhoTileHorizontal, tamanhoTileVertical);
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

    private void gerarAbacatesNoChao() {
        Random random = new Random();
        int contagem = 0;

        while (contagem < quantidadeAbacatesNoChao) {
            int x = random.nextInt(maxColunasTela);
            int y = random.nextInt(maxLinhasTela);

            if (!posicaoOcupada(x, y)) {
                abacatesNoChao.add(new Abacate(x, y));
                contagem++;
            }
        }
    }

    private void gerarMaracujaNoChao() {
        Random random = new Random();
        int contagem = 0;

        while (contagem < frutasNoChaoMaracuja) {
            int x = random.nextInt(maxColunasTela);
            int y = random.nextInt(maxLinhasTela);

            if (!posicaoOcupada(x, y)) {
                maracujasNoChao.add(new Maracuja(x, y));
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

        for (Abacate abacateChao : abacatesNoChao) {
            if (abacateChao.getX() == x && abacateChao.getY() == y) {
                return true;
            }
        }

        for (Maracuja maracujaChao : maracujasNoChao) {
            if (maracujaChao.getX() == x && maracujaChao.getY() == y) {
                return true;
            }
        }

        return false; // A posição está livre
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
        
        for (Abacate abacateChao : abacatesNoChao) {
            abacateChao.desenhar(g, tamanhoTile); // Desenhe o abacate
        }
        
        for (Maracuja maracujaChao : maracujasNoChao) {
        	maracujaChao.desenhar(g, tamanhoTile);
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
