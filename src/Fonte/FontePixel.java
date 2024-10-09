package Fonte;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontePixel {
    public static Font carregarFontePixel(int tamanho) {
        Font fontePixel = null;
        try {
            // Carregar a fonte pixelizada
            fontePixel = Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonte/FontePixel.ttf"));
            // Ajustar o tamanho da fonte
            fontePixel = fontePixel.deriveFont(Font.PLAIN, tamanho);
            // Registrar a fonte no sistema
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontePixel);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return fontePixel;
    }
}