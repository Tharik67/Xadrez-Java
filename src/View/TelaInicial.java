package src.View;

import javax.swing.*;
import java.awt.*;

class TelaInicial extends JFrame {
 

    TelaInicial(String s) {
        super(s);
        PainelTelaInicial painelTelaInicial = View.getPainelTelaInicial();
        painelTelaInicial.setBackground(Color.WHITE);
        getContentPane().add(painelTelaInicial);
        setSize(490, 540);
    }
}