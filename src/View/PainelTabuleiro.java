package src.View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import src.Util.Observer;

public class PainelTabuleiro extends JPanel implements Observer {

    private static quadrado matrizQuadrados[][];

    private Map<String, Image> imagens;

    private class quadrado {
        Rectangle2D retangulo;
        Color cor;
    }

    quadrado[][] getMatrizQuadrados() {
        return matrizQuadrados;
    }

    PainelTabuleiro() {

        double leftX = 0.0;
        double topY = 420.0;
        double larg = 60.0;
        double alt = 60.0;

        imagens = new HashMap<String, Image>();

        matrizQuadrados = new quadrado[8][8];

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                matrizQuadrados[linha][coluna] = new quadrado();
                matrizQuadrados[linha][coluna].retangulo = new Rectangle2D.Double(leftX + 60 * coluna,
                        topY - 60 * linha, larg, alt);
                if ((coluna + linha) % 2 == 0)
                    matrizQuadrados[linha][coluna].cor = Color.GRAY;
                else
                    matrizQuadrados[linha][coluna].cor = Color.WHITE;

            }
        }
    }

    public void notificar() {
        repaint();
    }

    Color getCorQuadrado(int linha, int coluna) {
        return matrizQuadrados[linha][coluna].cor;
    }

    void setCorQuadrado(int linha, int coluna, Color cor) {
        matrizQuadrados[linha][coluna].cor = cor;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Desenha retângulos

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                g2d.setPaint(matrizQuadrados[linha][coluna].cor);
                g2d.fill(matrizQuadrados[linha][coluna].retangulo);
            }
        }

        int dim_largura = 0;
        int dim_altura = 420;
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                if (View.isNotPecaVazia(linha, coluna)) {
                    desenhaPeca(g, linha, coluna, dim_largura, dim_altura);
                }
                dim_largura += 60;
            }

            dim_altura -= 60;
            dim_largura = 0;

        }

    }

    private void desenhaPeca(Graphics g, int linha, int coluna, int x, int y) {
        try {
            String source = "imagens_pecas/";
            if (View.isPeao(linha, coluna))
                source += "peao";
            else if (View.isBispo(linha, coluna))
                source += "bispo";
            else if (View.isCavalo(linha, coluna))
                source += "cavalo";
            else if (View.isTorre(linha, coluna))
                source += "torre";
            else if (View.isRainha(linha, coluna))
                source += "rainha";
            else if (View.isRei(linha, coluna))
                source += "rei";

            if (View.isBranco(linha, coluna))
                source += "branco.png";
            else
                source += "preto.png";

            Image image = imagens.get(source);
            if (image == null) {
                image = ImageIO.read(new File(source));
                imagens.put(source, image);
            }
            g.drawImage(image, x, y, null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}