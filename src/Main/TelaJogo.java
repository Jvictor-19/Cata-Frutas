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
    private int quantidadeJogadores; 
    
    private Image imagemJogador1; // Imagem do primeiro jogador
    private Image imagemJogador2; // Imagem do segundo jogador
    
    private int quantidadeBichadas;
    private int tamanhoMochila;

    private boolean jogoPausado = false;  // Controle de pausa
    private Thread threadJogo;
   
    
    // Adicionar os layouts
    public TelaJogo(String configFilePath) {
        // Lê os parâmetros do arquivo de configuração
        lerConfiguracao(configFilePath);

        this.pedras = new ArrayList<>();
        this.laranjasNoChao = new ArrayList<>();
        this.abacatesNoChao = new ArrayList<>();
        this.maracujasNoChao = new ArrayList<>(); // Inicializa a lista de maracujás
        this.laranjeiraNoChao = new ArrayList<>();
        this.abacateiroNoChao = new ArrayList<>();
        this.cocoNoChao = new ArrayList<>();
        this.coqueiroNoChao = new ArrayList<>();
        this.acerolaNoChao = new ArrayList <>();
        this.aceroleiraNoChao = new ArrayList <>();
        this.amoraNoChao = new ArrayList <>();
        this.amoreiraNoChao = new ArrayList <>();
        this.goiabaNoChao = new ArrayList <>();
        this.goiabeiraNoChao = new ArrayList <>();
        this.jogadoresNoChao = new ArrayList<>();
        this.quantidadeJogadores = 2;
        
        imagemJogador1 = Toolkit.getDefaultToolkit().getImage("Cata-Frutas/src/imagens/jogador1.png");
        imagemJogador2 = Toolkit.getDefaultToolkit().getImage("Cata-Frutas/src/imagens/jogador2.png");


        // Configurar o painel principal
        this.setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ajustarTamanhoTile(screenSize.width, screenSize.height);

        this.setPreferredSize(new Dimension(tamanhoTile * maxColunasTela, tamanhoTile * maxLinhasTela + 35)); // Aumentar altura para os botões
        this.setBackground(Color.lightGray);
        this.setDoubleBuffered(true);

        imagemGrama = new ImageIcon("src/imagens/grama.png");


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
    private void gerarLaranjeiraNoChao() {
        Random random = new Random();
        int contagem = 0;

        while (contagem < quantidadeLaranjeirasNoChao) {
            int x = random.nextInt(maxColunasTela);
            int y = random.nextInt(maxLinhasTela);

            if (!posicaoOcupada(x, y)) {
                laranjeiraNoChao.add(new Laranjeira(x, y));
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
        private void gerarAbacateiroNoChao() {
            Random random = new Random();
            int contagem = 0;

            while (contagem < quantidadeAbacateiroNoChao) {
                int x = random.nextInt(maxColunasTela);
                int y = random.nextInt(maxLinhasTela);

                if (!posicaoOcupada(x, y)) {
                    abacateiroNoChao.add(new Abacateiro(x, y));
                    contagem++;
                }
            }
        }
    private void gerarMaracujaNoChao() {
        Random random = new Random();
        int contagem = 0;

        while (contagem < quantidadeMaracujaNoChao) {
            int x = random.nextInt(maxColunasTela);
            int y = random.nextInt(maxLinhasTela);

            if (!posicaoOcupada(x, y)) {
                maracujasNoChao.add(new Maracuja(x, y));
                contagem++;
            }
        }
    }
    private void gerarCoqueiroNoChao() {
        Random random = new Random();
        int contagem = 0;

        while (contagem < quantidadeCoqueiroNoChao) {
            int x = random.nextInt(maxColunasTela);
            int y = random.nextInt(maxLinhasTela);

            if (!posicaoOcupada(x, y)) {
                coqueiroNoChao.add(new Coqueiro(x, y));
                contagem++;
            }
        }
    }
    private void gerarCocoNoChao() {
        Random random = new Random();
        int contagem = 0;

        while (contagem < quantidadeCocoNoChao) {
            int x = random.nextInt(maxColunasTela);
            int y = random.nextInt(maxLinhasTela);

            if (!posicaoOcupada(x, y)) {
                cocoNoChao.add(new Coco(x, y));
                contagem++;
            }
        }
    }
    private void gerarAcerolaNoChao() {
        Random random = new Random();
        int contagem = 0;

        while (contagem < quantidadeAcerolaNoChao) {
            int x = random.nextInt(maxColunasTela);
            int y = random.nextInt(maxLinhasTela);

            if (!posicaoOcupada(x, y)) {
                acerolaNoChao.add(new Acerola(x, y));
                contagem++;
            }
        }
    }
    
    private void gerarAceroleiraNoChao() {
        Random random = new Random();
        int contagem = 0;

        while (contagem < quantidadeAceroleiraNoChao) {
            int x = random.nextInt(maxColunasTela);
            int y = random.nextInt(maxLinhasTela);

            if (!posicaoOcupada(x, y)) {
                aceroleiraNoChao.add(new Aceroleira(x, y));
                contagem++;
            }
        }
    }
    
    private void gerarAmoraNoChao() {
        Random random = new Random();
        int contagem = 0;

        while (contagem < quantidadeAmoraNoChao) {
            int x = random.nextInt(maxColunasTela);
            int y = random.nextInt(maxLinhasTela);

            if (!posicaoOcupada(x, y)) {
                amoraNoChao.add(new Amora(x, y));
                contagem++;
            }
        }
    }
    
    private void gerarAmoreiraNoChao() {
        Random random = new Random();
        int contagem = 0;

        while (contagem < quantidadeAmoreiraNoChao) {
            int x = random.nextInt(maxColunasTela);
            int y = random.nextInt(maxLinhasTela);

            if (!posicaoOcupada(x, y)) {
                amoreiraNoChao.add(new Amoreiro(x, y));
                contagem++;
            }
        }
    }
    private void gerarGoiabeiraNoChao() {
        Random random = new Random();
        int contagem = 0;

        while (contagem < quantidadeGoiabeiraNoChao) {
            int x = random.nextInt(maxColunasTela);
            int y = random.nextInt(maxLinhasTela);

            if (!posicaoOcupada(x, y)) {
                goiabeiraNoChao.add(new Goiabeira(x, y));
                contagem++;
            }
        }
    }
    private void gerarGoiabaNoChao() {
        Random random = new Random();
        int contagem = 0;

        while (contagem < quantidadeGoiabaNoChao) {
            int x = random.nextInt(maxColunasTela);
            int y = random.nextInt(maxLinhasTela);

            if (!posicaoOcupada(x, y)) {
                goiabaNoChao.add(new Goiaba(x, y));
                contagem++;
            }
        }
    }
    
    private void gerarJogadoresNoChao() {
        Random random = new Random();
        int contagem = 0;
        int ver = 0;

        // Caminhos das imagens para os dois jogadores
        String caminhoImagemJogador1 = "src/imagens/Joagdor1.png"; // Imagem do jogador 1
        String caminhoImagemJogador2 = "src/imagens/Jogador2.png"; // Imagem do jogador 2

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
