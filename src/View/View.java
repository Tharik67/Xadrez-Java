package src.View;

import javax.swing.*;
import java.awt.*;

import src.Model.Model;

public class View{
    
    private static TelaJogo telaJogo;
    private static TelaInicial telaInicial;

    private static PainelTabuleiro painelTabuleiro;
    private static PainelTelaInicial painelTelaInicial;

    private static JButton botaoComecaNovoJogo;
    private static JButton botaoContinuaJogoSalvo;

    public static void paint(){
        painelTabuleiro.repaint();
    }

    public static JButton getBotaoComecaNovoJogo(){
        return botaoComecaNovoJogo;
    }

    public static JButton getBotaoContinuaJogoSalvo(){
        return botaoContinuaJogoSalvo;
    }

    public static void criaTelaJogo(){
        telaJogo = new TelaJogo("Chess");
        telaJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centralizaFrame(telaJogo);
        telaJogo.setVisible(false);
    }

    public static void iniciaVisualJogoNovo(){
        telaInicial.setVisible(false);
        telaJogo.setVisible(true);
    }

    public static void criaTelaInicial(){
        telaInicial = new TelaInicial("Chess");
        telaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centralizaFrame(telaInicial);
        telaInicial.setVisible(true);
    }

    private static void centralizaFrame(JFrame tela) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - tela.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - tela.getHeight()) / 2);
        tela.setLocation(x, y);
    }

    private static void criaPainelTabuleiro(){
        painelTabuleiro = new PainelTabuleiro();
    }

    private static void criaPainelTelaInicial(){
        painelTelaInicial = new PainelTelaInicial();
        botaoComecaNovoJogo = new JButton("Iniciar novo jogo"); 
        botaoContinuaJogoSalvo = new JButton("Continuar jogo salvo");
        painelTelaInicial.add(botaoComecaNovoJogo);
        painelTelaInicial.add(botaoContinuaJogoSalvo);
    }

    public static PainelTabuleiro getPainelTabuleiro() {
        if (painelTabuleiro == null)
            criaPainelTabuleiro();
        return painelTabuleiro;
    }

    public static PainelTelaInicial getPainelTelaInicial() {
        if (painelTelaInicial== null)
            criaPainelTelaInicial();
        return painelTelaInicial;
    }

    public static Frame getTelaJogo() {
        return telaJogo;
    }

    public static Frame getTelaInicial() {
        return telaJogo;
    }

    public static Color getCorQuadradoTabuleiro(int linha, int coluna) {
        return painelTabuleiro.getCorQuadrado(linha,coluna);
    }

    public static JMenuItem getMenuItemOpen(){
        return MenuSuperior.getMenuItemOpen();
    }

    public static JMenuItem getMenuItemSave(){
        return MenuSuperior.getMenuItemSave();
    }

    static boolean isBranco(int linha, int coluna){
        return Model.isBranco(linha,coluna);
    }

    static boolean isNotPecaVazia(int linha, int coluna){
        return Model.isNotPecaVazia(linha,coluna);
    }

    static boolean isPeao(int linha, int coluna){
        return Model.isPeao(linha,coluna);
    }

    static boolean isBispo(int linha, int coluna){
        return Model.isBispo(linha,coluna);

    }

    static boolean isCavalo(int linha, int coluna){
        return Model.isCavalo(linha,coluna);

    }

    static boolean isTorre(int linha, int coluna){
        return Model.isTorre(linha,coluna);

    }
    
    static boolean isRainha(int linha, int coluna){
        return Model.isRainha(linha,coluna);

    }

    static boolean isRei(int linha, int coluna){
        return Model.isRei(linha,coluna);

    }

    public static void setCorQuadradoDefault(int linha, int coluna){
        if ((linha + coluna) % 2 == 0)
            painelTabuleiro.setCorQuadrado(linha, coluna, Color.GRAY);
        else
            painelTabuleiro.setCorQuadrado(linha, coluna, Color.WHITE);
    }

    public static void setHighlightPecaSelecionada(int linha, int coluna){
            painelTabuleiro.setCorQuadrado(linha, coluna, Color.CYAN);
    }

    public static void setHighlightDestino(int linha, int coluna){
        painelTabuleiro.setCorQuadrado(linha, coluna, Color.GREEN);
    }
}
