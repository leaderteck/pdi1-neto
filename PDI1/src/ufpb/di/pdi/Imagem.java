package ufpb.di.pdi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Representação de uma imagem
 * @since 23 de abril de 2010
 * @author Juracy Neto - juracylucena[at]gmail.com
 * @author Erison Moura - erisonmoura[at]gmail.com
 */
public class Imagem extends JPanel {

    private File arquivo;
    private BufferedImage imagem;
    private boolean RGB;

    public Imagem(File arquivo, BufferedImage imagem) {
        this.arquivo = arquivo;
        this.imagem = imagem;
        this.RGB = true;
    }

    public Imagem(String caminhoArquivo) {
        try {
            arquivo = new File(caminhoArquivo);
            imagem = ImageIO.read(arquivo);
            this.RGB = true;
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

    public boolean isRGB() {
        return RGB;
    }

    public void setRGB(boolean ehRGB) {
        this.RGB = ehRGB;
    }

}
