package Elementos.ElementosDinamicos;

import java.awt.Graphics;
import javax.swing.ImageIcon;

import Frutas.Frutas;

import java.net.URL;
import java.util.ArrayList;
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

    // Adiciona método para definir a imagem padrão
    public void setImagemPadrao(String caminhoImagemPadrao) {
        URL imagemPadraoURL = getClass().getResource(caminhoImagemPadrao);
        if (imagemPadraoURL != null) {
            this.imagemPadrao = new ImageIcon(imagemPadraoURL);
        } else {
            System.err.println("Imagem padrão não encontrada: " + caminhoImagemPadrao);
        }
    }
    
}

