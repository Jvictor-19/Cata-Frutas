package Button;

import javax.swing.*;

public class PixelButton extends JButton {

    public PixelButton(String imagePath, int width, int height) {
        // Configurar o botão com imagem
        super(new ImageIcon(imagePath));
        setSize(width, height); // Define o tamanho com base na imagem
        setBorderPainted(false); // Remove borda
        setContentAreaFilled(false); // Remove fundo
        setFocusPainted(false); // Remove foco visual
    }
}
