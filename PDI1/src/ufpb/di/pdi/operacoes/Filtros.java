package ufpb.di.pdi.operacoes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import ufpb.di.pdi.Imagem;

/**
 * Operações de Filtagem em uma imagem RGB
 * @since 23 de abril de 2010
 * @author Juracy Neto - juracylucena[at]gmail.com
 * @author Erison Moura - erisonmoura[at]gmail.com
 */
public class Filtros {

    public static Imagem media(Imagem imagemOriginal, File novoArquivo, int vizinhanca) {
        if (imagemOriginal.isRGB() == false) {
            imagemOriginal = Conversoes.YIQParaRGB(imagemOriginal, novoArquivo);
        }
        BufferedImage imagem = imagemOriginal.getImagem();
        Integer altura = imagem.getHeight();
        Integer largura = imagem.getWidth();
        int[][][] matrizImagem = new int[altura][largura][3];
        for (int j = 0; j < altura; j++) {
            for (int i = 0; i < largura; i++) {
                int rgb = imagem.getRGB(i, j);
                matrizImagem[j][i][0] = (rgb >> 16) & 255;
                matrizImagem[j][i][1] = (rgb >> 8) & 255;
                matrizImagem[j][i][2] = (rgb) & 255;
            }
        }
        imagem = mediaVizinhanca(vizinhanca, imagemOriginal.getImagem(), matrizImagem);

        Imagem resultado = new Imagem(novoArquivo, imagem);
        resultado.setRGB(true);
        return resultado;
    }

    public static Imagem mediana(Imagem imagemOriginal, File novoArquivo, int vizinhanca) {
        if (imagemOriginal.isRGB() == false) {
            imagemOriginal = Conversoes.YIQParaRGB(imagemOriginal, novoArquivo);
        }
        BufferedImage imagem = imagemOriginal.getImagem();
        Integer altura = imagem.getHeight();
        Integer largura = imagem.getWidth();
        int[][][] matrizImagem = new int[altura][largura][3];
        for (int j = 0; j < altura; j++) {
            for (int i = 0; i < largura; i++) {
                int rgb = imagem.getRGB(i, j);
                matrizImagem[j][i][0] = (rgb >> 16) & 255;
                matrizImagem[j][i][1] = (rgb >> 8) & 255;
                matrizImagem[j][i][2] = (rgb) & 255;
            }
        }
        imagem = medianaVizinhanca(vizinhanca, imagemOriginal.getImagem(), matrizImagem);

        Imagem resultado = new Imagem(novoArquivo, imagem);
        resultado.setRGB(true);
        return resultado;
    }

    public static BufferedImage mediaVizinhanca(int vizinhanca, BufferedImage imagemOriginal, int[][][] matrizImagem) {
        Integer altura = imagemOriginal.getHeight();
        Integer largura = imagemOriginal.getWidth();
        int[][][] matrizNovaImagem = new int[altura][largura][3];
        int somaR = 0;
        int somaG = 0;
        int somaB = 0;
        int contador = 0;


        int limiteLateral = vizinhanca / 2;
        int limSuplinha, limInflinha, limSupColuna, limInfColuna;
        for (int j = 0; j < altura; j++) {
            for (int i = 0; i < largura; i++) {
                limInflinha = j - limiteLateral;
                limSuplinha = j + limiteLateral;
                limInfColuna = i - limiteLateral;
                limSupColuna = i + limiteLateral;
                for (; limInflinha <= limSuplinha; limInflinha++, limInfColuna = i - limiteLateral) {
                    for (; limInfColuna <= limSupColuna; limInfColuna++) {
                        if ((limInflinha >= 0) && (limInfColuna >= 0) && (limInflinha < j) && (limInfColuna < i)) {
                            somaR += matrizImagem[limInflinha][limInfColuna][0];
                            somaG += matrizImagem[limInflinha][limInfColuna][1];
                            somaB += matrizImagem[limInflinha][limInfColuna][2];
                            contador++;
                        }

                    }
                }
                if (somaR != 0) {
                    somaR = (int) Math.round(somaR / contador);
                }
                if (somaG != 0) {
                    somaG = (int) Math.round(somaG / contador);
                }
                if (somaB != 0) {
                    somaB = (int) Math.round(somaB / contador);
                }
                matrizNovaImagem[j][i][0] = somaR;
                matrizNovaImagem[j][i][1] = somaG;
                matrizNovaImagem[j][i][2] = somaB;

                somaR = somaG = somaB = contador = 0;
            }
        }
        BufferedImage novaImagem = new BufferedImage(largura, altura, 1);
        for (int j = 0; j < altura; j++) {
            for (int i = 0; i < largura; i++) {
                int r = ((matrizNovaImagem[j][i][0])) & 255;
                int g = ((matrizNovaImagem[j][i][1])) & 255;
                int b = (matrizNovaImagem[j][i][2]) & 255;
                int rgb = (r << 16) | (g << 8) | (b);
                novaImagem.setRGB(i, j, rgb);
            }
        }

        return novaImagem;
    }

    public static BufferedImage medianaVizinhanca(int vizinhanca, BufferedImage imagemOriginal, int[][][] matrizImagem) {
        Integer altura = imagemOriginal.getHeight();
        Integer largura = imagemOriginal.getWidth();
        int[][][] matrizNovaImagem = new int[altura][largura][3];
        int[] somaR = new int[altura * largura];
        int[] somaG = new int[altura * largura];
        int[] somaB = new int[altura * largura];
        int contador = 0;
        if (vizinhanca % 2 != 0) {
            vizinhanca--;
        }
        int pivo = (altura * largura) / 2;
        int limiteLateral = vizinhanca / 2;
        int limSuplinha, limInflinha, limSupColuna, limInfColuna;
        for (int j = 0; j < altura; j++) {
            for (int i = 0; i < largura; i++) {
                limInflinha = j - limiteLateral;
                limSuplinha = j + limiteLateral;
                limInfColuna = i - limiteLateral;
                limSupColuna = i + limiteLateral;
                for (; limInflinha <= limSuplinha; limInflinha++, limInfColuna = i - limiteLateral) {
                    for (; limInfColuna <= limSupColuna; limInfColuna++) {
                        if ((limInflinha >= 0) && (limInfColuna >= 0) && (limInflinha < j) && (limInfColuna < i)) {
                            somaR[contador] = matrizImagem[limInflinha][limInfColuna][0];
                            somaG[contador] = matrizImagem[limInflinha][limInfColuna][1];
                            somaB[contador] = matrizImagem[limInflinha][limInfColuna][2];
                            contador++;
                        }

                    }
                }
                Arrays.sort(somaR);
                Arrays.sort(somaG);
                Arrays.sort(somaB);

                int medianaR = (somaR[pivo] + somaR[pivo - 1]) / 2;
                int medianaG = (somaG[pivo] + somaG[pivo - 1]) / 2;
                int medianaB = (somaB[pivo] + somaB[pivo - 1]) / 2;

                matrizNovaImagem[j][i][0] = medianaR;
                matrizNovaImagem[j][i][1] = medianaG;
                matrizNovaImagem[j][i][2] = medianaB;

                contador = 0;
            }
        }
        BufferedImage novaImagem = new BufferedImage(largura, altura, 1);
        for (int j = 0; j < altura; j++) {
            for (int i = 0; i < largura; i++) {
                int r = ((matrizNovaImagem[j][i][0])) & 255;
                int g = ((matrizNovaImagem[j][i][1])) & 255;
                int b = (matrizNovaImagem[j][i][2]) & 255;
                int rgb = (r << 16) | (g << 8) | (b);
                novaImagem.setRGB(i, j, rgb);
            }
        }

        return novaImagem;
    }
}
