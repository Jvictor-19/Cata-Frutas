package Main;

import javax.swing.JFrame;

public class Inicio extends JFrame {
    
    public Inicio() {
        // Configurações da janela
    	setLayout(null);
        setTitle("Minha Janela");
        setSize(400, 300);  // Largura e altura da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Fecha o programa quando a janela é fechada
        setLocationRelativeTo(null);  // Centraliza a janela na tela
    }

}
