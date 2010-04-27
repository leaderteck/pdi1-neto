/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ufpb.di.pdi;

import java.io.File;
import ufpb.di.pdi.operacoes.Brilho;
import ufpb.di.pdi.operacoes.Conversoes;
import ufpb.di.pdi.operacoes.Filtros;

public class Main {

    public static void main(String[] args) {
        Imagem img = new Imagem("/home/neto/entrada.jpg");
        File arquivo = new File("/home/neto/saida.jpg");
        Imagem saida = Filtros.media(img, arquivo, 10);
        //Brilho.brilhoAditivo(img, arquivo, 50);
       // Imagem saida = Conversoes.negativo(img, arquivo);
        saida.salvarImagem(arquivo);
    }

}
