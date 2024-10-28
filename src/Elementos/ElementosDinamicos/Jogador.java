package Elementos.ElementosDinamicos;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Frutas.Abacate;
import Frutas.Coco;
import Frutas.Frutas;
import Frutas.Laranja;
import Frutas.Maracuja;


import Frutas.Frutas;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A classe Jogador representa o jogador em um jogo, com uma posição (x, y) e uma imagem associada.
 * O jogador é desenhado em uma célula do tabuleiro e seu tamanho é ajustado para ocupar 80% da célula.
 */
public class Jogador {
    private int x;
    private int y;
    private List<Frutas> mochila; 
    private ImageIcon imagem;
    private ImageIcon imagemPadrao; // Adiciona uma imagem padrão
  
    // Atributos adicionais para efeitos de frutas
    private int pontosMovimento = 1;      // Efeito do coco (agilidade)
    private int forca = 1;                // Efeito do abacate (força)
    private boolean antidotoAtivo = false; // Efeito da laranja (antídoto)
    private int pontosVitoria = 0;         // Pontuação do jogador
    private boolean comeuCoco; // Indica se o jogador comeu um coco
    private boolean comeuAbacate;
    private int multiplicadorDeForca;

    private int quantFrutasOuro;
    private String nome;
	private int quantidadeLaranjas;
	private boolean movimentoBloqueado;
    private int bloqueadoPorRodadas;
	private int rodadasBloqueio;

    public Jogador(int x, int y, String caminhoImagem) {
    	this.quantFrutasOuro = 0;
        this.x = x;
        this.y = y;
        this.mochila = new ArrayList<>();
        this.multiplicadorDeForca = 1; // Começa com força normal
        this.movimentoBloqueado = false;
        URL imagemURL = getClass().getResource(caminhoImagem);
        if (imagemURL != null) {
            this.imagem = new ImageIcon(imagemURL);
        } else {
            System.err.println("Imagem não encontrada: " + caminhoImagem);
            this.imagem = imagemPadrao; // Define imagem padrão caso a imagem não seja encontrada
        }
    }
    
    public int getQuantidadeFrutasMochila() {
        return mochila.size();
    }
    
    
    public int getForca() {
        return getQuantidadeFrutasMochila() * multiplicadorDeForca;
    }
    
    public void setRodadasBloqueio(int rodadas) {
        this.bloqueadoPorRodadas = rodadas;
        this.movimentoBloqueado = rodadas > 0;
    }
    public int getRodadasBloqueio() {
        return rodadasBloqueio; // Retorna o número de rodadas bloqueadas
    }

    public boolean getMovimentoBloqueado() {
        return movimentoBloqueado;
    }

    public void decrementarBloqueio() {
        if (bloqueadoPorRodadas > 0) {
            bloqueadoPorRodadas--;
            if (bloqueadoPorRodadas == 0) {
                movimentoBloqueado = false;
            }
        }
    }

    // Método para consumir uma laranja
    private void consumirLaranja() {
        for (Frutas fruta : mochila) {
            if (fruta instanceof Laranja) {
                mochila.remove(fruta);
                break;
            }
        }
    }

 

    // Método lidarComBichada
    public void lidarComBichada() {
        int quantidadeLaranjas = contarLaranjas();

        if (quantidadeLaranjas > 0) {
            int opcao = JOptionPane.showOptionDialog(null,
                    "Você tem " + quantidadeLaranjas + " laranja(s) na mochila. Deseja consumir uma para anular o efeito da fruta bichada?",
                    "Fruta Bichada",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Sim", "Não"},
                    "Sim");

            if (opcao == JOptionPane.YES_OPTION) {
                consumirLaranja();
                JOptionPane.showMessageDialog(null,
                        "Você consumiu uma laranja e anulou o efeito da fruta bichada!",
                        "Antídoto Consumido",
                        JOptionPane.INFORMATION_MESSAGE);
                this.setMovimentoBloqueado(false); // Remove o bloqueio
            } else {
                JOptionPane.showMessageDialog(null,
                        "Você decidiu não consumir a laranja. Seu movimento está bloqueado nas próximas duas rodadas.",
                        "Efeito da Fruta Bichada",
                        JOptionPane.WARNING_MESSAGE);
                this.setMovimentoBloqueado(true); // Bloqueia o movimento por uma rodadas
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Você não tem laranjas na mochila. Seu movimento está bloqueado por uma rodada.",
                    "Efeito da Fruta Bichada",
                    JOptionPane.WARNING_MESSAGE);
            this.setMovimentoBloqueado(true); // Bloqueia o movimento por uma rodadas
        }
    }

    
  
 // Método que retorna a quantidade de laranjas na mochila
    public int contarLaranjas() {
        int contagem = 0; // Inicializa a contagem de laranjas
        for (Frutas item : mochila) {
            if (item.getNome().equalsIgnoreCase("Laranja")) { // Verifica se o item é uma laranja
                contagem += 1; // Soma a quantidade de laranjas
            }
        }
        return contagem; // Retorna a contagem total de laranjas
    }

    public void setMovimentoBloqueado (boolean estado) {
    	this.movimentoBloqueado = estado;
    }
    
   

    public List<Frutas> removerFrutas(int quantidade) {
        List<Frutas> frutasRemovidas = new ArrayList<>();
        Iterator<Frutas> iterador = mochila.iterator();
        
        while (iterador.hasNext() && frutasRemovidas.size() < quantidade) {
            frutasRemovidas.add(iterador.next());
            iterador.remove(); // Remove da mochila
        }
        return frutasRemovidas;
    }

    
 // Método setter para o nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método getter para o nome
    public String getNome() {
        return nome;
    }

    public boolean venceu(int quantidadeMaracujasTotal) {
        return quantFrutasOuro > (quantidadeMaracujasTotal / 2);
    }

    
 // Método para coletar um maracujá
    public void coletarMaracuja() {
    	quantFrutasOuro++;
    }
    
    // Getter para a quantidade de maracujás que o jogador coletou
    public int getMaracujasColetados() {
        return quantFrutasOuro;
    }

    public void adicionarNaMochila(Frutas fruta) {
        mochila.add(fruta); // Adiciona fruta na mochila
    }
    
    public List<Frutas> getMochila() {
        return mochila; // Retorna a mochila
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Método para mover o jogador
    public void mover(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
        // Aqui você pode adicionar lógica para restringir a movimentação, se necessário
    }

    public void desenhar(Graphics g, int tamanhoTile) {
        if (imagem != null) {
            int tamanhoJogador = (int) (tamanhoTile * 0.8);
            int offset = (tamanhoTile - tamanhoJogador) / 2;
            g.drawImage(imagem.getImage(), x * tamanhoTile + offset, y * tamanhoTile + offset, tamanhoJogador, tamanhoJogador, null);
        }
    }

    public boolean comerFruta(Jogador jogador, List<List<? extends Frutas>> frutasNoChao,int passos) {
        boolean frutaComida = false;
        comeuCoco = false; // Variável para verificar se comeu um coco
        comeuAbacate = false;
        // Itera sobre cada lista de frutas no chão
        for (List<? extends Frutas> listaFrutas : frutasNoChao) {
            Iterator<? extends Frutas> iterator = listaFrutas.iterator();

            while (iterator.hasNext()) {
                Frutas fruta = iterator.next();

                // Verifica se a posição do jogador é igual à da fruta no terreno
                if (jogador.getX() == fruta.getX() && jogador.getY() == fruta.getY()) {
                    // Remove a fruta do terreno
                    iterator.remove();
                    frutaComida = true;

                    // Exibe uma mensagem informando que a fruta foi consumida
                    JOptionPane.showMessageDialog(null,
                            "Você comeu uma " + fruta.getNome() + "!",
                            "Comendo Fruta",
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    // Verifica o tipo da fruta comida e aplica o efeito correspondente
                    if (fruta instanceof Coco) {
                        jogador.aplicarEfeitoCoco(passos); // Chama o método que dobra os passos
                        comeuCoco = true; // Marca que comeu um coco
                    } else if (fruta instanceof Abacate) {
                        jogador.dobrarForca(); // Aplica o efeito do abacate
                    } else if (fruta instanceof Laranja) {
                        jogador.ativarAntidoto(); // Aplica o efeito da laranja
                    } else if (fruta instanceof Maracuja) {
                        jogador.adicionarPontoVitoria(); // Aplica o efeito do maracujá
                    }
                    break; // Sai do loop após comer a fruta
                }
            }
            if (frutaComida) break; // Sai do loop principal se a fruta foi comida
        }

        // Retorna se a fruta foi comida e se foi um coco
        return frutaComida; // Retorna se a fruta foi comida
    }


    // Método adicional para saber se comeu coco
    public boolean comeuCoco() {
        return comeuCoco; // Método que deve ser chamado após comer a fruta para verificar se comeu um coco
    }

    public void aplicarEfeitoCoco(int numeroAtualPassos) {
        this.pontosMovimento = 2 * numeroAtualPassos; // Dobra os pontos de movimento
        JOptionPane.showMessageDialog(null,
                "Você agora tem o dobro de pontos de movimento!",
                "Efeito do Coco",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void dobrarForca() {
        multiplicadorDeForca *= 2;
        JOptionPane.showMessageDialog(null,
                "Sua força foi dobrada!",
                "Efeito do Abacate",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void ativarAntidoto() {
        this.antidotoAtivo = true; // Ativa o antídoto
        JOptionPane.showMessageDialog(null,
                "Antídoto ativado!",
                "Efeito da Laranja",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void adicionarPontoVitoria() {
        this.pontosVitoria += 1; // Adiciona um ponto de vitória
        JOptionPane.showMessageDialog(null,
                "Você ganhou um ponto de vitória!",
                "Efeito do Maracujá",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Adiciona método para definir a imagem padrão
    public void setImagemPadrao(String caminhoImagemPadrao) {
        URL imagemPadraoURL = getClass().getResource(caminhoImagemPadrao);
        if (imagemPadraoURL != null) {
            this.imagemPadrao = new ImageIcon(imagemPadraoURL);
        } else {
            System.err.println("Imagem padrão não encontrada: " + caminhoImagemPadrao);
        }
    }


	public int getPontosMovimento() {
		// TODO Auto-generated method stub
		return pontosMovimento;
	}
}
	

