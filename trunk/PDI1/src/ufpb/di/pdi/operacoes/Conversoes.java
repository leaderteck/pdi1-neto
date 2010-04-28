package ufpb.di.pdi.operacoes;

import java.awt.image.BufferedImage;
import java.io.File;
import ufpb.di.pdi.Imagem;

/**
 * Operações de Conversão em uma imagem RGB
 * @since 23 de abril de 2010
 * @author Juracy Neto - juracylucena[at]gmail.com
 * @author Erison Moura - erisonmoura[at]gmail.com
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

    public static Imagem merge(Imagem imagem1, Imagem imagem2, File novoArquivo) {
        BufferedImage imagemA = imagem1.getImagem();
        BufferedImage imagemB = imagem2.getImagem();
        BufferedImage imagemFinal = imagem1.getImagem();
        Integer altura = imagemA.getHeight();
        Integer largura = imagemA.getWidth();
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                int rgbA = imagemA.getRGB(j, i);
                int rA = (rgbA >> 16) & 255;
                int gA = (rgbA >> 8) & 255;
                int bA = (rgbA) & 255;
                int rgbB = imagemB.getRGB(j, i);
                int rB = (rgbB >> 16) & 255;
                int gB = (rgbB >> 8) & 255;
                int bB = (rgbB) & 255;
                int r = (rA + rB) / 2;
                int g = (gA + gB) / 2;
                int b = (bA + bB) / 2;
                int rgb = (r << 16) | (g << 8) | (b);
                imagemFinal.setRGB(j, i, rgb);
            }
        }
        return new Imagem(novoArquivo, imagemFinal);
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
