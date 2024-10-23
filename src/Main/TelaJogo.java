package Main;

import javax.swing.*;

import Botões.BotaoSair;
import Botões.BotaoSortear;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Main.SorteioDados;
import Frutas.Frutas;
import Frutas.Abacate;
import Frutas.Acerola;
import Frutas.Amora;
import Frutas.Coco;
import Frutas.GerenciadorDeFrutas;
import Frutas.Goiaba;
import Frutas.Laranja;
import Frutas.Maracuja;
import Elementos.ElementosDinamicos.Jogador;
import Elementos.ElementosEstáticos.Abacateiro;
import Elementos.ElementosEstáticos.Aceroleira;
import Elementos.ElementosEstáticos.Amoreiro;
import Elementos.ElementosEstáticos.Coqueiro;
import Elementos.ElementosEstáticos.Goiabeira;
import Elementos.ElementosEstáticos.Laranjeira;
import Elementos.ElementosEstáticos.Pedra;

/**
 * Classe que representa a tela do jogo, responsável pela lógica de jogo e
 * interface gráfica.
 */
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

   
    private ArrayList<Laranja> laranjasNoChao;
    private int quantidadeLaranjasNoChao;
  
    private ArrayList<Laranjeira> laranjeiraNoChao;
    private int quantidadeLaranjeirasNoChao;

    private ArrayList<Abacate> abacatesNoChao;
    private int quantidadeAbacatesNoChao;

    private ArrayList<Abacateiro> abacateiroNoChao;
    private int quantidadeAbacateiroNoChao;
    
    private ArrayList<Maracuja> maracujasNoChao;
    private int quantidadeMaracujaNoChao;
    private int quantidadeMaracujasTotal;

    private ArrayList<Coco> cocoNoChao;
    private int quantidadeCocoNoChao;

    private ArrayList<Coqueiro> coqueiroNoChao;
    private int quantidadeCoqueiroNoChao;

    private ArrayList<Aceroleira> aceroleiraNoChao;
    private int quantidadeAceroleiraNoChao;

    private ArrayList<Acerola> acerolaNoChao;
    private int quantidadeAcerolaNoChao;
    
    private ArrayList<Amora> amoraNoChao;
    private int quantidadeAmoraNoChao;

    private ArrayList<Amoreiro> amoreiraNoChao;
    private int quantidadeAmoreiraNoChao;

    private ArrayList<Goiaba> goiabaNoChao;
    private int quantidadeGoiabaNoChao;

    private ArrayList<Goiabeira> goiabeiraNoChao;
    private int quantidadeGoiabeiraNoChao;
    
    private ArrayList<Jogador> jogadoresNoChao;
    
    private int quantidadeBichadas;
    private int tamanhoMochila;

    private boolean jogoPausado = false;  // Controle de pausa
    private Thread threadJogo;
    
    private JLabel labelDado1;
    private JLabel labelDado2;
    private JButton botaoSortear;
   
    /**
     * Construtor da classe TelaJogo.
     * 
     * @param configFilePath O caminho do arquivo de configuração a ser lido para
     *                       inicializar a tela do jogo.
     */	
    // Adicionar os layouts
    public TelaJogo(String configFilePath) {
        // Lê os parâmetros do arquivo de configuração
        lerConfiguracao(configFilePath);

        // Inicializa as listas
        this.pedras = new ArrayList<>();
        this.laranjasNoChao = new ArrayList<>();
        this.abacatesNoChao = new ArrayList<>();
        this.maracujasNoChao = new ArrayList<>();
        this.laranjeiraNoChao = new ArrayList<>();
        this.abacateiroNoChao = new ArrayList<>();
        this.cocoNoChao = new ArrayList<>();
        this.coqueiroNoChao = new ArrayList<>();
        this.acerolaNoChao = new ArrayList<>();
        this.aceroleiraNoChao = new ArrayList<>();
        this.amoraNoChao = new ArrayList<>();
        this.amoreiraNoChao = new ArrayList<>();
        this.goiabaNoChao = new ArrayList<>();
        this.goiabeiraNoChao = new ArrayList<>();
        this.jogadoresNoChao = new ArrayList<>();
        
     // Adicionar KeyListener
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                for (Jogador jogador : jogadoresNoChao) {
                    switch (keyCode) {
                        case KeyEvent.VK_UP: // Cima
                            jogador.mover(0, -1); // Mover para cima
                            break;
                        case KeyEvent.VK_DOWN: // Baixo
                            jogador.mover(0, 1); // Mover para baixo
                            break;
                        case KeyEvent.VK_LEFT: // Esquerda
                            jogador.mover(-1, 0); // Mover para esquerda
                            break;
                        case KeyEvent.VK_RIGHT: // Direita
                            jogador.mover(1, 0); // Mover para direita
                            break;
                    }
                }
                repaint(); // Redesenhar a tela após a movimentação
            }
        });
        this.setFocusable(true);


        // Configurar o painel principal
        this.setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ajustarTamanhoTile(screenSize.width, screenSize.height);

        // Configurar o tamanho da tela e fundo
        this.setPreferredSize(new Dimension(tamanhoTile * maxColunasTela, tamanhoTile * maxLinhasTela + 40)); // Aumentar altura para os botões
        this.setBackground(Color.lightGray);
        this.setDoubleBuffered(true);

        imagemGrama = new ImageIcon(getClass().getResource("/imagens/grama.png"));

        // Gerar objetos no chão
        gerarPedras();
        gerarLaranjasNoChao();
        gerarAbacatesNoChao();
        gerarMaracujaNoChao(); 
        gerarLaranjeiraNoChao();
        gerarAbacateiroNoChao();
        gerarCocoNoChao();
        gerarCoqueiroNoChao();
        gerarAcerolaNoChao();
        gerarAceroleiraNoChao();
        gerarAmoraNoChao();
        gerarAmoreiraNoChao();
        gerarGoiabaNoChao();
        gerarGoiabeiraNoChao();
        gerarJogadoresNoChao();

        // Painel inferior contendo botões e informações dos jogadores
        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new BorderLayout());
        painelInferior.setPreferredSize(new Dimension(400, 60)); // Define a altura do painel inferior
        painelInferior.setBackground(Color.LIGHT_GRAY);

        // Painel para os botões "Sair" e "Pausar"
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Cria uma instância do botão "Sair"
        BotaoSair botaoSair = new BotaoSair();
        // Adiciona o botão "Sair" ao painel de botões
        painelBotoes.add(botaoSair.getBotao());

        // Painel para as informações dos jogadores
        JPanel painelJogadores = new JPanel();
        painelJogadores.setLayout(new GridLayout(1, 3, 10, 10)); // Layout com três colunas para exibir dois jogadores e o botão "Sortear"

        // Informações do Jogador 1
        JLabel jogador1Label = new JLabel("Jogador 1: 0 passos");
        painelJogadores.add(jogador1Label);

        // Informações do Jogador 2
        JLabel jogador2Label = new JLabel("Jogador 2: 0 passos");
        painelJogadores.add(jogador2Label);

        // Adicionar o botão "Sortear"
     // Criar os componentes
        labelDado1 = new JLabel("Dado 1: ?");
        labelDado2 = new JLabel("Dado 2: ?");

        // Estilizando os textos
        labelDado1.setFont(new Font("Serif", Font.BOLD, 14));
        labelDado2.setFont(new Font("Serif", Font.BOLD, 14));

     // Supondo que você tenha duas labels: labelDado1 e labelDado2
        BotaoSortear botaoSortear = new BotaoSortear(labelDado1, labelDado2);
        // Adiciona o botão "Sortear" ao painel
        painelBotoes.add(botaoSortear.getBotao());

        // Adicionar componentes ao painel
        painelJogadores.add(labelDado1);
        painelJogadores.add(labelDado2);
        //painelJogadores.add(botaoSortear);

        // Adicionar os painéis ao painel principal
        painelInferior.add(painelBotoes, BorderLayout.WEST); // Botões à esquerda
        painelInferior.add(painelJogadores, BorderLayout.CENTER); // Informações dos jogadores no centro

        // Adicionar o painel inferior diretamente ao JFrame
        this.add(painelInferior, BorderLayout.SOUTH);
    }


    /**
     * Lê a configuração do jogo a partir de um arquivo.
     *
     * @param configFilePath O caminho para o arquivo de configuração.
     */
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
                        quantidadeMaracujasTotal = Integer.parseInt(valoresMaracuja[0].trim());
                        quantidadeMaracujaNoChao = Integer.parseInt(valoresMaracuja[1].trim());
                        break;
                    case "laranja":
                        String[] valoresLaranja = valor.split(" ");
                        quantidadeLaranjeirasNoChao= Integer.parseInt(valoresLaranja[0].trim());
                        quantidadeLaranjasNoChao = Integer.parseInt(valoresLaranja[1].trim());
                        break;
                    case "abacate":
                        String[] valoresAbacate = valor.split(" ");
                        quantidadeAbacateiroNoChao = Integer.parseInt(valoresAbacate[0].trim());
                        quantidadeAbacatesNoChao = Integer.parseInt(valoresAbacate[1].trim());
                        break;
                    case "coco":
                        String[] valoresCoco = valor.split(" ");
                        quantidadeCoqueiroNoChao = Integer.parseInt(valoresCoco[0].trim());
                        quantidadeCocoNoChao = Integer.parseInt(valoresCoco[1].trim());
                        break;
                    case "acerola":
                        String[] valoresAcerola = valor.split(" ");
                        quantidadeAceroleiraNoChao= Integer.parseInt(valoresAcerola[0].trim());
                        quantidadeAcerolaNoChao = Integer.parseInt(valoresAcerola[1].trim());
                        break;
                    case "amora":
                        String[] valoresAmora = valor.split(" ");
                        quantidadeAmoreiraNoChao = Integer.parseInt(valoresAmora[0].trim());
                        quantidadeAmoraNoChao= Integer.parseInt(valoresAmora[1].trim());
                        break;
                    case "goiaba":
                        String[] valoresGoiaba = valor.split(" ");
                        quantidadeGoiabeiraNoChao = Integer.parseInt(valoresGoiaba[0].trim());
                        quantidadeGoiabaNoChao = Integer.parseInt(valoresGoiaba[1].trim());
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
            quantidadeLaranjeirasNoChao = 2; // Valor padrão
            quantidadeBichadas = 0; // Valor padrão
            tamanhoMochila = 5; // Valor padrão
        }
    }
    
    /**
     * Ajusta o tamanho dos tiles para que a matriz se ajuste à tela.
     *
     * @param larguraDisponivel A largura disponível da tela.
     * @param alturaDisponivel  A altura disponível da tela.
     */
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
    
    /**
     * Método genérico para gerar objetos em posições aleatórias na matriz.
     * 
     * @param lista a lista de objetos (por exemplo, pedras, laranjas, etc.)
     * @param quantidade a quantidade de objetos a serem gerados
     * @param tipoObjeto a classe do objeto a ser gerado
     */
    private <T> void gerarObjetosAleatorios(List<T> lista, int quantidade, Class<T> tipoObjeto) {
        Random random = new Random();
        int contagem = 0;

        while (contagem < quantidade) {
            int x = random.nextInt(maxColunasTela);
            int y = random.nextInt(maxLinhasTela);

            if (!posicaoOcupada(x, y)) {
                try {
                    // Usa reflexão para criar uma nova instância do objeto
                    T objeto = tipoObjeto.getDeclaredConstructor(int.class, int.class).newInstance(x, y);
                    lista.add(objeto);
                    contagem++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Gera pedras em posições aleatórias na matriz.
     */
    private void gerarPedras() {
        gerarObjetosAleatorios(pedras, quantidadePedras, Pedra.class);
    }

    /**
     * Gera laranjas no chão em posições aleatórias na matriz.
     */
    private void gerarLaranjasNoChao() {
        gerarObjetosAleatorios(laranjasNoChao, quantidadeLaranjasNoChao, Laranja.class);
    }

    /**
     * Gera laranjeiras no chão em posições aleatórias na matriz.
     */
    private void gerarLaranjeiraNoChao() {
        gerarObjetosAleatorios(laranjeiraNoChao, quantidadeLaranjeirasNoChao, Laranjeira.class);
    }
    
    /**
     * Gera abacates no chão em posições aleatórias na matriz.
     */
    private void gerarAbacatesNoChao() {
        gerarObjetosAleatorios(abacatesNoChao, quantidadeAbacatesNoChao, Abacate.class);
    }
    
    /**
     * Gera abacateiros no chão em posições aleatórias na matriz.
     */
    private void gerarAbacateiroNoChao() {
        gerarObjetosAleatorios(abacateiroNoChao, quantidadeAbacateiroNoChao, Abacateiro.class);
    }
    
    /**
     * Gera maracujás no chão em posições aleatórias na matriz.
     */
    private void gerarMaracujaNoChao() {
        gerarObjetosAleatorios(maracujasNoChao, quantidadeMaracujaNoChao, Maracuja.class);
    }
    
    /**
     * Gera coqueiros no chão em posições aleatórias na matriz.
     */
    private void gerarCoqueiroNoChao() {
        gerarObjetosAleatorios(coqueiroNoChao, quantidadeCoqueiroNoChao, Coqueiro.class);
    }
    
    /**
     * Gera cocos no chão em posições aleatórias na matriz.
     */
    private void gerarCocoNoChao() {
        gerarObjetosAleatorios(cocoNoChao, quantidadeCocoNoChao, Coco.class);
    }
    
    /**
     * Gera acerolas no chão em posições aleatórias na matriz.
     */
    private void gerarAcerolaNoChao() {
        gerarObjetosAleatorios(acerolaNoChao, quantidadeAcerolaNoChao, Acerola.class);
    }
    
    /**
     * Gera aceroleiras no chão em posições aleatórias na matriz.
     */
    private void gerarAceroleiraNoChao() {
        gerarObjetosAleatorios(aceroleiraNoChao, quantidadeAceroleiraNoChao, Aceroleira.class);
    }
    
    /**
     * Gera amoras no chão em posições aleatórias na matriz.
     */
    private void gerarAmoraNoChao() {
        gerarObjetosAleatorios(amoraNoChao, quantidadeAmoraNoChao, Amora.class);
    }
    
    /**
     * Gera amoreiras no chão em posições aleatórias na matriz.
     */
    private void gerarAmoreiraNoChao() {
        gerarObjetosAleatorios(amoreiraNoChao, quantidadeAmoreiraNoChao, Amoreiro.class);
    }
    
    /**
     * Gera goiabeiras no chão em posições aleatórias na matriz.
     */
    private void gerarGoiabeiraNoChao() {
        gerarObjetosAleatorios(goiabeiraNoChao, quantidadeGoiabeiraNoChao, Goiabeira.class);
    }
    
    /**
     * Gera goiabas no chão em posições aleatórias na matriz.
     */
    private void gerarGoiabaNoChao() {
        gerarObjetosAleatorios(goiabaNoChao, quantidadeGoiabaNoChao, Goiaba.class);
    }
    
    /**
     * Gera os jogadores no chão em posições aleatórias na matriz.
     */
    private void gerarJogadoresNoChao() {
        Random random = new Random();
        int contagem = 0;
        int ver = 0;

        // Caminhos das imagens para os dois jogadores
        String caminhoImagemJogador1 = "/imagens/Joagdor1.png"; // Imagem do jogador 1
        String caminhoImagemJogador2 = "/imagens/Jogador2.png"; // Imagem do jogador 2

        while (ver < 2) {
            int x = random.nextInt(maxColunasTela);
            int y = random.nextInt(maxLinhasTela);

            if (!posicaoOcupada(x, y)) {
                // Define qual imagem usar com base na contagem
                String caminhoImagem = (ver == 0) ? caminhoImagemJogador1 : caminhoImagemJogador2;
                
                // Adiciona o jogador na lista com a imagem apropriada
                jogadoresNoChao.add(new Jogador(x, y, caminhoImagem)); 
                ver++;
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
       for (Laranjeira laranjeiraChao : laranjeiraNoChao) {
            if (laranjeiraChao.getX() == x && laranjeiraChao.getY() == y) {
                return true;
                }
        }
       for (Abacateiro abacateiroChao : abacateiroNoChao) {
           if (abacateiroChao.getX() == x && abacateiroChao.getY() == y) {
               return true;
               }
       }
       for (Coco cocoChao : cocoNoChao) {
           if (cocoChao.getX() == x && cocoChao.getY() == y) {
               return true;
               }
       }
       for (Coqueiro coqueiroChao : coqueiroNoChao) {
           if (coqueiroChao.getX() == x && coqueiroChao.getY() == y) {
               return true;
               }
           
       }
       
       for (Acerola acerolaChao : acerolaNoChao) {
           if (acerolaChao.getX() == x && acerolaChao.getY() == y) {
               return true;
               }
       }
       for (Aceroleira aceroleiraChao : aceroleiraNoChao) {
           if (aceroleiraChao.getX() == x && aceroleiraChao.getY() == y) {
               return true;
               }
           }
       for (Amora amoraChao : amoraNoChao) {
           if (amoraChao.getX() == x && amoraChao.getY() == y) {
               return true;
               }
           }
       for (Amoreiro amoreiraChao : amoreiraNoChao) {
           if (amoreiraChao.getX() == x && amoreiraChao.getY() == y) {
               return true;
               }
           }
       for (Goiaba goiabaChao : goiabaNoChao) {
           if (goiabaChao.getX() == x && goiabaChao.getY() == y) {
               return true;
               }
           }
       for (Goiabeira goiabeiraChao : goiabeiraNoChao) {
           if (goiabeiraChao.getX() == x && goiabeiraChao.getY() == y) {
               return true;
               }
           }
       for (Jogador jogador : jogadoresNoChao) {
           if (jogador.getX() == x && jogador.getY() == y) {
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
            laranjaChao.desenhar(g, tamanhoTile); // Chame com o novo tamanho
        }
        for (Laranjeira laranjeiraChao : laranjeiraNoChao) {
            laranjeiraChao.desenhar(g,tamanhoTile ); // Chame com o novo tamanho
        }
        for (Abacate abacateChao : abacatesNoChao) {
            abacateChao.desenhar(g, tamanhoTile ); // Chame com o novo tamanho
        }
        for (Abacateiro abacateiroChao : abacateiroNoChao) {
            abacateiroChao.desenhar(g, tamanhoTile ); // Chame com o novo tamanho
        }
        for (Maracuja maracujaChao : maracujasNoChao) {
            maracujaChao.desenhar(g,tamanhoTile); // Chame com o novo tamanho
        }
        for (Coco cocoChao : cocoNoChao) {
            cocoChao.desenhar(g,tamanhoTile); // Chame com o novo tamanho
        }
        for (Coqueiro coqueiroChao : coqueiroNoChao) {
            coqueiroChao.desenhar(g,tamanhoTile); // Chame com o novo tamanho
        }
        for (Acerola acerolaChao : acerolaNoChao) {
           acerolaChao.desenhar(g,tamanhoTile); // Chame com o novo tamanho
        }
        for (Aceroleira aceroleiraChao : aceroleiraNoChao) {
            aceroleiraChao.desenhar(g,tamanhoTile); // Chame com o novo tamanho
         }
        for (Amora amoraChao : amoraNoChao) {
            amoraChao.desenhar(g,tamanhoTile); // Chame com o novo tamanho
         }
         for (Amoreiro amoreiraraChao : amoreiraNoChao) {
        	 amoreiraraChao.desenhar(g,tamanhoTile); // Chame com o novo tamanho
          }
        for (Goiabeira goiabeiraChao : goiabeiraNoChao) {
            goiabeiraChao.desenhar(g,tamanhoTile); // Chame com o novo tamanho
         }
        for (Goiaba goiabaChao : goiabaNoChao) {
            goiabaChao.desenhar(g,tamanhoTile); // Chame com o novo tamanho
         }
        for (Jogador jogador : jogadoresNoChao) {
            jogador.desenhar(g, tamanhoTile); // Chama o método de desenhar do jogador
        }
        

    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            JFrame frame = new JFrame("Jogo de Laranjas");
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setResizable(false);
	            frame.setContentPane(new TelaJogo("src/Arquivo/configuracaoJogo.txt")); // Passando o caminho do arquivo de configuração
	            frame.pack();
	            frame.setLocationRelativeTo(null);
	            frame.setVisible(true);
	        });
	    }
}
