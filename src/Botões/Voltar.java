package Botões;

import javax.swing.*;
import java.awt.*;
import Fonte.FontePixel;

public class Voltar extends JButton {

    public Voltar(JPanel backGround, JLabel creditsImageLabel) {
        super("Voltar"); // Define o texto do botão como "Voltar"
        setFont(FontePixel.carregarFontePixel(10)); // Define a fonte
        setBounds(1400, 20, 100, 50); // Define as dimensões do botão
        setFocusable(false);
        setBorderPainted(false); // Remove a borda pintada
        setFocusPainted(false); // Remove a borda de foco ao clicar

        // Adiciona o ActionListener para a ação de voltar
        addActionListener(e -> {
            creditsImageLabel.setVisible(false); // Esconde a imagem de créditos
            backGround.setVisible(true); // Mostra o fundo principal
            setVisible(true); // Esconde o botão "Voltar"
        });
    }

    public Voltar(JLabel backGround, JLabel creditsImageLabel) {
        //TODO Auto-generated constructor stub
    }

    // Você pode adicionar mais configurações ou comportamentos personalizados aqui, se necessário.
}
