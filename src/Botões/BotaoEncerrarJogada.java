package Botões;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;

public class BotaoEncerrarJogada {

    private JButton botao; // O botão "Encerrar Jogada"
    private boolean jogadaEncerrada;
    private boolean dadosSorteados = false;
    private boolean JogadaSorteada;

    public BotaoEncerrarJogada(boolean JogadaSorteada) {
        this.botao = new JButton("Encerrar Jogada");
        this.jogadaEncerrada = false;
        this.JogadaSorteada = JogadaSorteada;
        configurarAcao();
    }

    private void configurarAcao() {
        ActionListener actionListener = e -> {
            // Exibe uma caixa de diálogo de confirmação
        	if (!JogadaSorteada) { // Verifica se os dados foram sorteados
                // Exibe um aviso para informar que o jogador deve sortear os dados primeiro
                JOptionPane.showMessageDialog(null, 
                        "Você deve sortear os dados antes de encerrar a jogada!", 
                        "Aviso", 
                        JOptionPane.WARNING_MESSAGE);

        	}else if (podeEncerrarJogada()) { // Verifica se as condições para encerrar estão atendidas
            	JOptionPane.showConfirmDialog(null, 
                        "Deseja realmente encerrar a jogada?", 
                        "Confirmação de Encerramento", 
                        JOptionPane.YES_NO_OPTION);
                jogadaEncerrada = true; // Marca que a jogada foi encerrada
                
            } else {
            	JOptionPane.showMessageDialog(null, 
                        "A jogada já foi encerrada!", 
                        "Aviso", 
                        JOptionPane.WARNING_MESSAGE);
            }
        };
        this.botao.addActionListener(actionListener); // Adiciona a ação ao botão
    }
    public void setDadosSorteados(boolean sorteados) {
        this.dadosSorteados = sorteados; // Atualiza o estado de sorteio dos dados
    }
    
 // Método que define as condições para poder encerrar a jogada
    private boolean podeEncerrarJogada() {
        // Exemplo de condição: A jogada só pode ser encerrada se ela ainda não foi encerrada
        // Você pode adicionar mais condições aqui se necessário
        return !jogadaEncerrada;
    }
    
    public boolean isJogadaEncerrada() {
        return jogadaEncerrada; // Método para verificar se a jogada foi encerrada
    }

    // Método para resetar o estado da jogada (se necessário)
    public void resetarJogada() {
        jogadaEncerrada = false;
    }

    public JButton getBotao() {
        return botao; // Método para obter o botão
    }
}
