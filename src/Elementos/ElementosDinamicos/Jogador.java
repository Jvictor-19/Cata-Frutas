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
    
   

    private int quantFrutasOuro;
    private String nome;

    public Jogador(int x, int y, String caminhoImagem) {
    	this.quantFrutasOuro = 0;
        this.x = x;
        this.y = y;
        this.mochila = new ArrayList<>();
        URL imagemURL = getClass().getResource(caminhoImagem);
        if (imagemURL != null) {
            this.imagem = new ImageIcon(imagemURL);
        } else {
            System.err.println("Imagem não encontrada: " + caminhoImagem);
            this.imagem = imagemPadrao; // Define imagem padrão caso a imagem não seja encontrada
        }
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
        boolean comeuCoco = false; // Variável para verificar se comeu um coco

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

        // Caso o jogador não esteja em uma posição com fruta
        if (!frutaComida) {
            JOptionPane.showMessageDialog(null,
                    "Não há frutas no local para comer!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
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
        this.forca *= 2; // Dobra a força do jogador
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

