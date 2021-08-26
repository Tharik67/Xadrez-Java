package src.Model;

import src.View.View;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import src.Util.Observer;
import src.Controller.Controller;

public class Model{
    private static int contaJogadas = 0;
    private static boolean selecionaPeca = true;
    private static int linhaSelecionada;
    private static int colunaSelecionada;
    private static Board classBoard;

    public static void setBoard(){
        classBoard = new Board();
    }

    public static void addObserverToObservableBoard(Observer o){
        classBoard.addObserver(o);
    }

    public static boolean isBranco(int linha, int coluna) {
        return classBoard.getBoard()[linha][coluna].isBranco();
    }

    public static boolean isNotPecaVazia(int linha, int coluna) {
        return classBoard.getBoard()[linha][coluna].isNotPecaVazia();
    }

    public static boolean isPeao(int linha, int coluna) {
        return classBoard.getBoard()[linha][coluna].isPeao();
    }

    public static boolean isBispo(int linha, int coluna) {
        return classBoard.getBoard()[linha][coluna].isBispo();

    }

    public static boolean isCavalo(int linha, int coluna) {
        return classBoard.getBoard()[linha][coluna].isCavalo();

    }

    public static boolean isTorre(int linha, int coluna) {
        return classBoard.getBoard()[linha][coluna].isTorre();

    }

    public static boolean isRainha(int linha, int coluna) {
        return classBoard.getBoard()[linha][coluna].isRainha();

    }

    public static boolean isRei(int linha, int coluna) {
        return classBoard.getBoard()[linha][coluna].isRei();

    }

    public static boolean processaClique(int linha, int coluna) {
        if (selecionaPeca) {
            if (selecionaOrigemPeca(linha, coluna)) {
                selecionaPeca = false;
                linhaSelecionada = linha;
                colunaSelecionada = coluna;
                View.setHighlightPecaSelecionada(linha, coluna);
                highlightDestinosPossiveis();
                classBoard.notificarObservers();
                return true;
            }
            return false;

        }

        else {
            switch ((selecionaPosicaoDestino(linha, coluna))) {
                case MovimentoValido:
                    View.setCorQuadradoDefault(linhaSelecionada, colunaSelecionada);
                    desfazHighlightDestinosPossiveis();
                    movimentoPosVerificacaoDeXeque(linha, coluna);
                    classBoard.notificarObservers();
                    contaJogadas++;
                    selecionaPeca = true;
                    verificaXequeCongelamentoMateDoInimigo();
                    return true;

                case PecaSelecionada:
                    View.setCorQuadradoDefault(linhaSelecionada, colunaSelecionada);
                    desfazHighlightDestinosPossiveis();
                    classBoard.notificarObservers();
                    selecionaPeca = true;
                    return false;

                case PecaAmiga:
                    View.setHighlightPecaSelecionada(linha, coluna);
                    View.setCorQuadradoDefault(linhaSelecionada, colunaSelecionada);
                    desfazHighlightDestinosPossiveis();
                    linhaSelecionada = linha;
                    colunaSelecionada = coluna;
                    highlightDestinosPossiveis();
                    classBoard.notificarObservers();
                    return false;

                case RoqueD:
                    contaJogadas++;
                    selecionaPeca = true;
                    View.setCorQuadradoDefault(linhaSelecionada, colunaSelecionada);
                    desfazHighlightDestinosPossiveis();
                    movimentoPosVerificacaoDeXeque(linha, 6);
                    linhaSelecionada = linha;
                    colunaSelecionada = coluna;
                    movimentoPosVerificacaoDeXeque(linha, 5);
                    verificaXequeCongelamentoMateDoInimigo();
                    classBoard.notificarObservers();
                    return true;

                case RoqueE:

                    contaJogadas++;
                    selecionaPeca = true;
                    View.setCorQuadradoDefault(linhaSelecionada, colunaSelecionada);
                    desfazHighlightDestinosPossiveis();
                    classBoard.notificarObservers();
                    movimentoPosVerificacaoDeXeque(linha, 2);
                    linhaSelecionada = linha;
                    colunaSelecionada = coluna;
                    movimentoPosVerificacaoDeXeque(linha, 3);
                    verificaXequeCongelamentoMateDoInimigo();
                    return true;

                default:
                    break;
            }
            selecionaPeca = true;
            View.setCorQuadradoDefault(linhaSelecionada, colunaSelecionada);
            desfazHighlightDestinosPossiveis();
            classBoard.notificarObservers();
            return false;
        }
    }

    private static boolean selecionaOrigemPeca(int linha, int coluna) {
        if (contaJogadas % 2 == 0) { // vez do Branco

            if (classBoard.getBoard()[linha][coluna].isBranco()) {
                linhaSelecionada = linha;
                colunaSelecionada = coluna;
                return true;
            } else
                return false;

        }

        else { // vez do Preto

            if (classBoard.getBoard()[linha][coluna].isPreto()) {
                linhaSelecionada = linha;
                colunaSelecionada = coluna;
                return true;
            } else
                return false;

        }

    }

    private static void highlightDestinosPossiveis() {
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {

                if (selecionaPosicaoDestino(linha, coluna).getValor() > 0) {
                    View.setHighlightDestino(linha, coluna);
                }

            }
        }
    }

    private static void desfazHighlightDestinosPossiveis() {
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {

                if (selecionaPosicaoDestino(linha, coluna).getValor() > 0) {
                    View.setCorQuadradoDefault(linha, coluna);
                }
            }
        }
    }

    static void promocaoDePeao() {
        Controller.mostraMenuPopupPromocaoPeao();
    }

    private static ResultadoMovimento selecionaPosicaoDestino(int linha, int coluna) {

        Peca PecaSelecionada = classBoard.getBoard()[linhaSelecionada][colunaSelecionada];

        Peca PosicaoDestino = classBoard.getBoard()[linha][coluna];

        if (linha == linhaSelecionada && coluna == colunaSelecionada) {
            return ResultadoMovimento.PecaSelecionada;
        }

        if (PosicaoDestino.isNotPecaVazia() && PecaSelecionada.getCor() == PosicaoDestino.getCor()
                && !(PecaSelecionada.isRei() && PosicaoDestino.isTorre() && Math.abs(linha - linhaSelecionada) < 2
                        && PecaSelecionada.getMoveCount() == 0 && PosicaoDestino.getMoveCount() == 0)) {
            return ResultadoMovimento.PecaAmiga;
        }

        return PecaSelecionada.movimentoComVerificacaoDeXeque(classBoard.getBoard(), linha, coluna, linhaSelecionada,
                colunaSelecionada);
    }

    public static void promovePeaoParaCavalo() {

        Peca pecaSelecionada = classBoard.getBoard()[linhaSelecionada][colunaSelecionada];

        if (pecaSelecionada.isBranco())
            classBoard.getBoard()[linhaSelecionada][colunaSelecionada] = new Cavalo(Peca.Cor.BRANCO);

        else
            classBoard.getBoard()[linhaSelecionada][colunaSelecionada] = new Cavalo(Peca.Cor.PRETO);

        classBoard.notificarObservers();

    }

    public static void promovePeaoParaTorre() {

        Peca pecaSelecionada = classBoard.getBoard()[linhaSelecionada][colunaSelecionada];

        if (pecaSelecionada.isBranco())
            classBoard.getBoard()[linhaSelecionada][colunaSelecionada] = new Torre(Peca.Cor.BRANCO);

        else
            classBoard.getBoard()[linhaSelecionada][colunaSelecionada] = new Torre(Peca.Cor.PRETO);

        classBoard.getBoard()[linhaSelecionada][colunaSelecionada].moveCount();

        classBoard.notificarObservers();

    }

    public static void promovePeaoParaBispo() {

        Peca pecaSelecionada = classBoard.getBoard()[linhaSelecionada][colunaSelecionada];

        if (pecaSelecionada.isBranco())
            classBoard.getBoard()[linhaSelecionada][colunaSelecionada] = new Bispo(Peca.Cor.BRANCO);

        else
            classBoard.getBoard()[linhaSelecionada][colunaSelecionada] = new Bispo(Peca.Cor.PRETO);

        classBoard.notificarObservers();
    }

    public static void promovePeaoParaRainha() {

        Peca pecaSelecionada = classBoard.getBoard()[linhaSelecionada][colunaSelecionada];

        if (pecaSelecionada.isBranco())
            classBoard.getBoard()[linhaSelecionada][colunaSelecionada] = new Rainha(Peca.Cor.BRANCO);

        else
            classBoard.getBoard()[linhaSelecionada][colunaSelecionada] = new Rainha(Peca.Cor.PRETO);

        classBoard.notificarObservers();

    }

    private static void movimentoPosVerificacaoDeXeque(int linha, int coluna) {

        Peca pecaSelecionada = classBoard.getBoard()[linhaSelecionada][colunaSelecionada];

        pecaSelecionada.moveCount();

        if (pecaSelecionada.isPeao()
                && (pecaSelecionada.isBranco() && linha == 7 || pecaSelecionada.isPreto() && linha == 0)) {
            Controller.mostraMenuPopupPromocaoPeao();
        }

        classBoard.getBoard()[linha][coluna] = pecaSelecionada;
        classBoard.getBoard()[linhaSelecionada][colunaSelecionada] = new PecaVazia();
        linhaSelecionada = linha;
        colunaSelecionada = coluna;
    }

    static Peca movimentoPreVerificacaoDeXeque(int linha, int coluna, int linhaSelecionada,
            int colunaSelecionada) {

        Peca pecaSelecionada = classBoard.getBoard()[linhaSelecionada][colunaSelecionada];
        Peca pecaDestino = classBoard.getBoard()[linha][coluna];

        classBoard.getBoard()[linha][coluna] = pecaSelecionada;
        classBoard.getBoard()[linhaSelecionada][colunaSelecionada] = new PecaVazia();

        return pecaDestino;
    }

    static void desfazMovimento(int linha, int coluna, int linhaSelecionada, int colunaSelecionada,
            Peca pecaDestino) {

        Peca pecaSelecionada = classBoard.getBoard()[linha][coluna];

        classBoard.getBoard()[linhaSelecionada][colunaSelecionada] = pecaSelecionada;
        classBoard.getBoard()[linha][coluna] = pecaDestino;
    }

    static boolean reiEstaEmXeque(int linhaPecaAmigaDoRei, int colunaPecaAmigaDoRei) {

        Peca peca, pecaAmigaDoRei;
        int linhaRei, colunaRei = 0;
        boolean encontrouRei = false;

        pecaAmigaDoRei = classBoard.getBoard()[linhaPecaAmigaDoRei][colunaPecaAmigaDoRei];

        if (pecaAmigaDoRei.isRei()) {
            linhaRei = linhaPecaAmigaDoRei;
            colunaRei = colunaPecaAmigaDoRei;
        }

        else {
            for (linhaRei = 0; linhaRei < 8; linhaRei++) {
                for (colunaRei = 0; colunaRei < 8; colunaRei++) {

                    peca = classBoard.getBoard()[linhaRei][colunaRei];

                    if (peca.isRei() && peca.getCor() == pecaAmigaDoRei.getCor()) {
                        encontrouRei = true;
                        break;
                    }
                }

                if (encontrouRei)
                    break;
            }
        }

        for (int linhaPeca = 0; linhaPeca < 8; linhaPeca++) {
            for (int colunaPeca = 0; colunaPeca < 8; colunaPeca++) {

                peca = classBoard.getBoard()[linhaPeca][colunaPeca];

                if (peca.isNotPecaVazia() && peca.getCor() != pecaAmigaDoRei.getCor()) {
                    if (peca.movimento(classBoard.getBoard(), linhaRei, colunaRei, linhaPeca,
                            colunaPeca) == ResultadoMovimento.MovimentoValido) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static ResultadoMovimento verificaMovimentacaoParaCongelamento(int linha, int coluna,
            int linhaPecaInimiga, int colunaPecaInimiga) {

        Peca PecaSelecionada = classBoard.getBoard()[linhaPecaInimiga][colunaPecaInimiga];

        Peca PosicaoDestino = classBoard.getBoard()[linha][coluna];

        if (linha == linhaPecaInimiga && coluna == colunaPecaInimiga) {
            return ResultadoMovimento.PecaSelecionada;
        }

        if (PosicaoDestino.isNotPecaVazia() && PecaSelecionada.getCor() == PosicaoDestino.getCor()
                && !(PecaSelecionada.isRei() && PosicaoDestino.isTorre() && PecaSelecionada.getMoveCount() == 0
                        && PosicaoDestino.getMoveCount() == 0)) {
            return ResultadoMovimento.PecaAmiga;
        }

        return PecaSelecionada.movimentoComVerificacaoDeXeque(classBoard.getBoard(), linha, coluna, linhaPecaInimiga,
                colunaPecaInimiga);
    }

    static void verificaXequeCongelamentoMateDoInimigo() {
        boolean inimigoCongelado, verificouXequeDoInimigo, inimigoEstaEmXeque;
        Peca peca, pecaAmiga;

        inimigoCongelado = true;
        verificouXequeDoInimigo = false;
        inimigoEstaEmXeque = false;

        pecaAmiga = classBoard.getBoard()[linhaSelecionada][colunaSelecionada];

        for (int linhaPecaInimiga = 0; linhaPecaInimiga < 8; linhaPecaInimiga++) {
            for (int colunaPecaInimiga = 0; colunaPecaInimiga < 8; colunaPecaInimiga++) {

                peca = classBoard.getBoard()[linhaPecaInimiga][colunaPecaInimiga];

                if (peca.isNotPecaVazia() && peca.getCor() != pecaAmiga.getCor()) {

                    if (peca.isRei()) {
                        inimigoEstaEmXeque = reiEstaEmXeque(linhaPecaInimiga, colunaPecaInimiga);
                        verificouXequeDoInimigo = true;
                    }

                    if (inimigoCongelado)
                        for (int linhaDestino = 0; linhaDestino < 8; linhaDestino++) {
                            for (int colunaDestino = 0; colunaDestino < 8; colunaDestino++) {

                                if (verificaMovimentacaoParaCongelamento(linhaDestino, colunaDestino,
                                        linhaPecaInimiga, colunaPecaInimiga).getValor() > 0) {

                                    inimigoCongelado = false;
                                    break;

                                }
                            }

                            if (!inimigoCongelado)
                                break;
                        }

                    if (verificouXequeDoInimigo && !inimigoCongelado)
                        break;
                }

                if (verificouXequeDoInimigo && !inimigoCongelado)
                    break;
            }
        }

        if (!inimigoCongelado && inimigoEstaEmXeque)
            Controller.mostraMensagemXeque();

        else if (inimigoCongelado && inimigoEstaEmXeque)
            Controller.mostraMensagemXequeMate();

        else if (inimigoCongelado && !inimigoEstaEmXeque)
            Controller.mostraMensagemEmpate();

    }

    public static void saveBin(FileOutputStream out) throws IOException {
        out.write(contaJogadas % 2);
        classBoard.saveBin(out);
    }

    public static void openBin(FileInputStream arq) throws IOException {
        contaJogadas = arq.read();
        classBoard.openBin(arq);
    }
}
