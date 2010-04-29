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
        Imagem img = new Imagem("/home/neto/merge1.jpg");
        Imagem img2 = new Imagem("/home/neto/merge2.jpg");
        File arquivo = new File("/home/neto/saida.jpg");        
        Imagem saida = Conversoes.merge(img, img2, arquivo);
        //Imagem saida2 = Conversoes.YIQParaRGB(saida, arquivo);
        saida.salvarImagem(arquivo);
    }

}
