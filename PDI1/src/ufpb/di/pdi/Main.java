/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ufpb.di.pdi;

import java.io.File;
import ufpb.di.pdi.operacoes.Brilho;
import ufpb.di.pdi.operacoes.Conversoes;

public class Main {

    public static void main(String[] args) {
        Imagem img = new Imagem("/home/neto/entrada.jpg");
        File arquivo = new File("/home/neto/saida.jpg");
        Imagem saida = Brilho.brilhoMultiplicativo(img, arquivo, new Double(2));
        //Brilho.brilhoAditivo(img, arquivo, 50);
        saida.salvarImagem(arquivo);
    }

}
