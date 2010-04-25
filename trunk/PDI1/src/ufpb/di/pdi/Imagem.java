package ufpb.di.pdi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

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
        try {
            arquivo = new File(caminhoArquivo);
            imagem = ImageIO.read(arquivo);
        } catch (IOException ex) {
            System.out.println("Erro na abertura do arquivo. Verifique a extensão. \n");
            Logger.getLogger(Imagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvarImagem(File arquivo) {
        try {
            ImageIO.write(getImagem(), "JPG", arquivo);
        } catch (IOException ex) {
            System.out.println("Não foi possível salvar o arquivo. \n");
            Logger.getLogger(Imagem.class.getName()).log(Level.SEVERE, null, ex);
        }
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
