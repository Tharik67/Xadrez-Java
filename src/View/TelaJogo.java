package src.View;

import javax.swing.*;
import java.awt.*;

class TelaJogo extends JFrame {

    TelaJogo(String s) {
        super(s);
        this.setJMenuBar(MenuSuperior.getMenuSuperior());
        PainelTabuleiro painelTabuleiro = View.getPainelTabuleiro();
        painelTabuleiro.setBackground(Color.BLACK);
        getContentPane().add(painelTabuleiro);
        setSize(490, 540);
    }
}