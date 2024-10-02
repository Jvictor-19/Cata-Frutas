package Button;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PixelButton extends JButton {

    public PixelButton(String imagePath, int width, int height) {
        // Configurar o botão com imagem
        super(new ImageIcon(imagePath));
        setSize(width, height); // Define o tamanho com base na imagem
        setBorderPainted(false); // Remove borda
        setContentAreaFilled(false); // Remove fundo
        setFocusPainted(false); // Remove foco visual
        
        // Tentar carregar a fonte pixelizada
        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("Button/FontePixel.ttf")).deriveFont(18f);
            setFont(pixelFont); // Definir a fonte no botão
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            setFont(new Font("Arial", Font.PLAIN, 18)); // Fallback para uma fonte padrão
        }
    }
}
