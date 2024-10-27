package Main;

import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A classe Personagens é um painel que permite a entrada dos nomes de dois jogadores
 * e fornece opções para importar um arquivo ou definir configurações do jogo.
 * 
 * Este painel é exibido em um JFrame e interage com o usuário para coletar
 * informações necessárias antes de iniciar o jogo.
 */
public class Personagens extends JPanel {
    // Referência ao JFrame que contém esta instância de Personagens
    private JFrame parentFrame;

    // Variáveis para os nomes dos jogadores
    String nomeJogador1 = "";
    String nomeJogador2 = "";

    /**
     * Construtor da classe Personagens.
     * 
     * Este construtor recebe um JFrame pai e configura o layout do painel.
     * 
     * @param frame o JFrame que contém este painel de personagens.
     */
    public Personagens(JFrame frame) {
        this.parentFrame = frame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        iniciarConfiguracoes();
    }

    /**
     * Inicia as configurações, permitindo que o usuário insira os nomes dos jogadores
     * e escolha entre importar um arquivo ou definir configurações.
     * 
     * Este método apresenta um diálogo até que os nomes dos jogadores sejam
     * inseridos corretamente.
     */
    private void iniciarConfiguracoes() {
        // Loop até que os nomes dos jogadores sejam inseridos
        while (true) {
            // Opções a serem apresentadas ao usuário
            String[] options = {"Importar Arquivo", "Definir Configurações"};

            // Campos de texto para os jogadores
            JTextField jogador1Field = new JTextField(10);
            JTextField jogador2Field = new JTextField(10);

            // Painel para os campos de texto dos jogadores
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Nome do Jogador 1:"));
            panel.add(jogador1Field);
            panel.add(new JLabel("Nome do Jogador 2:"));
            panel.add(jogador2Field);

            // Exibe o JOptionPane com as opções e campos de texto
            int escolha = JOptionPane.showOptionDialog(
                null,
                panel,
                "Configurações do Jogo",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
            );

            // Verifica se o usuário fechou a janela ou pressionou Cancelar
            if (escolha == JOptionPane.CLOSED_OPTION) {
            	break;
            }

            // Verifica se os campos de nome dos jogadores estão preenchidos
            nomeJogador1 = jogador1Field.getText().trim();
            nomeJogador2 = jogador2Field.getText().trim();

            if (nomeJogador1.isEmpty() || nomeJogador2.isEmpty()) {            	
                JOptionPane.showMessageDialog(null, "Por favor, insira os nomes dos dois jogadores antes de continuar.");
            } else {
                // Lógica para escolher entre "Importar Arquivo" ou "Definir Configurações"
                if (escolha == 0) {
                    importarArquivo();
                } else if (escolha == 1) {
                    // Aqui você pode abrir a tela de configuração
                    JFrame configFrame = new JFrame("Configuração do Jogo");
                    configFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Usar DISPOSE_ON_CLOSE
                    configFrame.setSize(800, 600);
                    configFrame.setLocationRelativeTo(null);
                    configFrame.add(new Configuração());
                    configFrame.setVisible(true);
                }
                break; // Sai do loop após processar os nomes com sucesso
            }
        }
    }

    /**
     * Permite ao usuário importar um arquivo.
     * 
     * Este método exibe um JFileChooser para que o usuário selecione um arquivo.
     * Se um arquivo for selecionado, inicia o jogo com base no arquivo importado.
     */
    private void importarArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        int retorno = fileChooser.showOpenDialog(null);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "Arquivo importado: " + arquivo.getName());

            // Iniciar o jogo com base no arquivo importado
            try {
                JFrame gameWindow = new JFrame("Cata Frutas");
                gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Apenas fechar a janela
                gameWindow.setResizable(false);

                // Passa o caminho do arquivo selecionado para a TelaJogo
                TelaJogo gamePainel = new TelaJogo(arquivo.getAbsolutePath());
                gameWindow.getContentPane().add(gamePainel);
                gameWindow.pack();
                gameWindow.setLocationRelativeTo(null);
                gameWindow.setVisible(true);

                // Fechar a janela atual de configuração
                parentFrame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum arquivo foi selecionado.");
        }
    }
}
