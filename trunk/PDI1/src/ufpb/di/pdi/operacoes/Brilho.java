package ufpb.di.pdi.operacoes;

import java.awt.image.BufferedImage;
import java.io.File;
import ufpb.di.pdi.Imagem;

/**
 * Operações de Brilho aditivo e multiplicativo em uma imagem RGB
 * @since 23 de abril de 2010
 * @author Juracy Neto  - juracylucena[at]gmail.com
 * @author Erison Moura - erisonmoura[at]gmail.com
 */
public class Brilho {

    public static Imagem brilhoAditivo(Imagem imagemOriginal, File novoArquivo, int constante) {
        if (imagemOriginal.isRGB() == false) {
            imagemOriginal = Conversoes.YIQParaRGB(imagemOriginal, novoArquivo);
        }
        BufferedImage imagem = imagemOriginal.getImagem();
        Integer altura = imagem.getHeight();
        Integer largura = imagem.getWidth();
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                int rgb = imagem.getRGB(j, i);
                int r = (rgb >> 16) & 255;
                int g = (rgb >> 8) & 255;
                int b = (rgb) & 255;
                r = r + constante;
                g = g + constante;
                b = b + constante;
                if (r > 255) {
                    r = 255;
                }
                if(r < 0) {
                    r = 0;
                }
                if (g > 255) {
                    g = 255;
                }
                if(g < 0) {
                    g = 0;
                }
                if (b > 255) {
                    b = 255;
                }
                if(r < 0) {
                    b = 0;
                }
                rgb = (r << 16) | (g << 8) | (b);
                imagem.setRGB(j, i, rgb);
            }
        }
        Imagem resultado = new Imagem(novoArquivo, imagem);
        resultado.setRGB(true);
        return resultado;
    }

    public static Imagem brilhoMultiplicativo(Imagem imagemOriginal, File novoArquivo, Double constante) {
        if (imagemOriginal.isRGB() == false) {
            imagemOriginal = Conversoes.YIQParaRGB(imagemOriginal, novoArquivo);
        }
        BufferedImage imagem = imagemOriginal.getImagem();
        Integer altura = imagem.getHeight();
        Integer largura = imagem.getWidth();
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                int rgb = imagem.getRGB(j, i);
                int r = (rgb >> 16) & 255;
                int g = (rgb >> 8) & 255;
                int b = (rgb) & 255;
                r = (int) (r * constante);
                g = (int) (g * constante);
                b = (int) (b * constante);
                if (r > 255) {
                    r = 255;
                }
                if(r < 0) {
                    r = 0;
                }
                if (g > 255) {
                    g = 255;
                }
                if(g < 0) {
                    g = 0;
                }
                if (b > 255) {
                    b = 255;
                }
                if(r < 0) {
                    b = 0;
                }
                rgb = (r << 16) | (g << 8) | (b);
                imagem.setRGB(j, i, rgb);
            }
        }
        Imagem resultado = new Imagem(novoArquivo, imagem);
        resultado.setRGB(true);
        return resultado;
    }
}
