package src.Model;

abstract class Peca {

    protected enum Cor {
        BRANCO(), PRETO(), Null();
    }

    private Cor cor;
    private int moveCount = 0;

    Peca(Cor cor) {
        this.cor = cor;
    }

    int getMoveCount() {
        return moveCount;
    }

    void moveCount() {
        moveCount++;
    }
    
    int getValor() {
        return 0;
    }

    Cor getCor() {
        return cor;
    }

    ResultadoMovimento movimentoComVerificacaoDeXeque(Peca[][] board, int linha, int coluna, int linhaSelecionada, int colunaSelecionada) {
        if(movimento(board, linha, coluna, linhaSelecionada, colunaSelecionada).getValor() > 0){
            Peca pecaDestino = Model.movimentoPreVerificacaoDeXeque(linha, coluna, linhaSelecionada, colunaSelecionada);
            if (Model.reiEstaEmXeque(linha,coluna)) {
                Model.desfazMovimento(linha, coluna, linhaSelecionada, colunaSelecionada, pecaDestino);
                return ResultadoMovimento.MovimentoInvalido;
            } else {
                Model.desfazMovimento(linha, coluna, linhaSelecionada, colunaSelecionada, pecaDestino);
                return movimento(board, linha, coluna, linhaSelecionada, colunaSelecionada);
            }
        }

        else return ResultadoMovimento.MovimentoInvalido;
    }

    ResultadoMovimento movimento(Peca[][] board, int linha, int coluna, int linhaSelecionada, int colunaSelecionada) {
        return ResultadoMovimento.MovimentoInvalido;
    }

    protected int auxLinha(int linha, int linhaSelecionada) {
        if (linha > linhaSelecionada)
            return -1;
        else if (linha < linhaSelecionada)
            return 1;
        return 0;
    }

    protected int auxColuna(int coluna, int colunaSelecionada) {
        if (coluna > colunaSelecionada)
            return -1;
        else if (coluna < colunaSelecionada)
            return 1;
        return 0;
    }

    boolean isRei() {
        return false;
    }

    boolean isRainha() {
        return false;
    }

    boolean isTorre() {
        return false;
    }

    boolean isBispo() {
        return false;
    }

    boolean isCavalo() {
        return false;
    }

    boolean isPeao() {
        return false;
    }

    boolean isNotPecaVazia() {
        return true;
    }

    boolean isBranco() {
        return this.cor == Cor.BRANCO;
    }

    boolean isPreto() {
        return this.cor == Cor.PRETO;
    }

}
