package Elementos.ElementosDinamicos;

import Frutas.Frutas;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe Jogador representa o jogador em um jogo, com uma posição (x, y) e uma imagem associada.
 * O jogador é desenhado em uma célula do tabuleiro e seu tamanho é ajustado para ocupar 80% da célula.
 */
public class Jogador {
    private int x;
    private int y;
    private ImageIcon imagem;
    private ImageIcon imagemPadrao; // Imagem padrão
    private List<Frutas> mochila;   // Lista de frutas coletadas (mochila)
    private int capacidadeMochila;  // Capacidade máxima da mochila

    public Jogador(int x, int y, String caminhoImagem, int capacidadeMochila) {
        this.x = x;
        this.y = y;
        this.mochila = new ArrayList<>(); // Inicializa a mochila como uma lista vazia
        this.capacidadeMochila = capacidadeMochila; // Define a capacidade máxima
        carregarImagem(caminhoImagem); // Chama o método para carregar a imagem
    }

    // Método para carregar a imagem do jogador
    private void carregarImagem(String caminhoImagem) {
        URL imagemURL = getClass().getResource(caminhoImagem);
        if (imagemURL != null) {
            this.imagem = new ImageIcon(imagemURL);
        } else {
            System.err.println("Imagem não encontrada: " + caminhoImagem);
            this.imagem = imagemPadrao; // Define imagem padrão caso a imagem não seja encontrada
        }
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
    }

    public void desenhar(Graphics g, int tamanhoTile) {
        if (imagem != null) {
            int tamanhoJogador = (int) (tamanhoTile * 0.8);
            int offset = (tamanhoTile - tamanhoJogador) / 2;
            g.drawImage(imagem.getImage(), x * tamanhoTile + offset, y * tamanhoTile + offset, tamanhoJogador, tamanhoJogador, null);
        }
    }

    // Método para definir a imagem padrão
    public void setImagemPadrao(String caminhoImagemPadrao) {
        URL imagemPadraoURL = getClass().getResource(caminhoImagemPadrao);
        if (imagemPadraoURL != null) {
            this.imagemPadrao = new ImageIcon(imagemPadraoURL);
        } else {
            System.err.println("Imagem padrão não encontrada: " + caminhoImagemPadrao);
        }
    }

 // Método para coletar uma fruta
    public void coletarFruta(Frutas fruta, Graphics g, int tamanhoTile) {
        if (mochila.size() < capacidadeMochila) { // Verifica se há espaço na mochila
            if (!fruta.isColetada()) { // Verifica se a fruta ainda não foi coletada
                fruta.coletar(); // Marca a fruta como coletada e desenha a imagem de fundo
                mochila.add(fruta); // Adiciona a fruta à mochila
                System.out.println("Fruta coletada e adicionada à mochila!");
            } else {
                System.out.println("Esta fruta já foi coletada.");
            }
        } else {
            System.out.println("Mochila cheia! Não é possível coletar mais frutas.");
        }
    }

    
    // Método para adicionar uma fruta à lista de frutas coletadas
    public void adicionarFruta(Frutas fruta) {
        mochila.add(fruta);
    }

    // Método para visualizar o conteúdo da mochila
    public List<Frutas> getMochila() {
        return mochila;
    }
    
    // Método para obter a capacidade máxima da mochila
    public int getCapacidadeMochila() {
        return capacidadeMochila;
    }
}
