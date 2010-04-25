package ufpb.di.pdi;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * @author neto
 */
public class Imagem {

    File arquivo;
    BufferedImage imagem;

    public Imagem(File arquivo, BufferedImage imagem) {
        this.arquivo = arquivo;
        this.imagem = imagem;
    }

    public Imagem(String caminhoArquivo) {
        
    }

    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    public BufferedImage getImagem() {
        return imagem;
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }

}
