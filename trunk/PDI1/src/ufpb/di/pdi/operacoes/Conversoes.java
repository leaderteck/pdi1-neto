package ufpb.di.pdi.operacoes;

import java.awt.image.BufferedImage;
import java.io.File;
import ufpb.di.pdi.Imagem;

/**
 * Operações de Conversão em uma imagem RGB
 * @since 23 de abril de 2010
 * @author Juracy Neto - juracylucena[at]gmail.com
 */
public class Conversoes {

    public static Imagem bandaR(Imagem imagemOriginal, File novoArquivo) {
        BufferedImage imagem = imagemOriginal.getImagem();
        Integer altura = imagem.getHeight();
        Integer largura = imagem.getWidth();
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                int rgb = imagem.getRGB(j, i);
                int r = (rgb >> 16) & 255;
                rgb = (r << 16) | 0 | 0;
                imagem.setRGB(j, i, rgb);
            }
        }
        return new Imagem(novoArquivo, imagem);
    }

    public static Imagem bandaG(Imagem imagemOriginal, File novoArquivo) {
        BufferedImage imagem = imagemOriginal.getImagem();
        Integer altura = imagem.getHeight();
        Integer largura = imagem.getWidth();
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                int rgb = imagem.getRGB(j, i);
                int g = (rgb >> 8) & 255;
                rgb = 0 | (g << 8) | 0;
                imagem.setRGB(j, i, rgb);
            }
        }
        return new Imagem(novoArquivo, imagem);
    }

    public static Imagem bandaB(Imagem imagemOriginal, File novoArquivo) {
        BufferedImage imagem = imagemOriginal.getImagem();
        Integer altura = imagem.getHeight();
        Integer largura = imagem.getWidth();
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                int rgb = imagem.getRGB(j, i);
                int b = (rgb) & 255;
                rgb = 0 | 0 | (b);
                imagem.setRGB(j, i, rgb);
            }
        }
        return new Imagem(novoArquivo, imagem);
    }

    public static Imagem negativo(Imagem imagemOriginal, File novoArquivo) {
        BufferedImage imagem = imagemOriginal.getImagem();
        Integer altura = imagem.getHeight();
        Integer largura = imagem.getWidth();
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                int rgb = imagem.getRGB(j, i);
                int r = (rgb >> 16) & 255;
                int g = (rgb >> 8) & 255;
                int b = (rgb) & 255;
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;
                rgb = (r << 16) | (g << 8) | (b);
                imagem.setRGB(j, i, rgb);
            }
        }
        return new Imagem(novoArquivo, imagem);
    }

    public static Imagem RGBParaYIQ(Imagem imagemOriginal, File novoArquivo) {
        BufferedImage imagem = imagemOriginal.getImagem();
        Integer altura = imagem.getHeight();
        Integer largura = imagem.getWidth();
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                int rgb = imagem.getRGB(j, i);
                int r = (rgb >> 16) & 255;
                int g = (rgb >> 8) & 255;
                int b = (rgb) & 255;
                Double y = ((0.299 * r) + (0.587 * g) + (0.114 * b));
                Double u = ((-0.14713 * r) + (-0.28886 * g) + (0.436 * b));
                Double v = ((0.615 * r) + (-0.51498 * g) + (-0.10001 * b));
                int yuv = (r >> 16) | (g << 8) | b;
                imagem.setRGB(j, i, yuv);
            }
        }
        Imagem resultado = new Imagem(novoArquivo, imagem);
        resultado.setRGB(false);
        return resultado;
    }

    public static Imagem YIQParaRGB(Imagem imagemOriginal, File novoArquivo) {
        BufferedImage imagem = imagemOriginal.getImagem();
        Integer altura = imagem.getHeight();
        Integer largura = imagem.getWidth();
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
            }
        }
        Imagem resultado = new Imagem(novoArquivo, imagem);
        resultado.setRGB(true);
        return resultado;
    }
}
