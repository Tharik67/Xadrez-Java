package src.Model;

class Torre extends Peca {

    Torre(Peca.Cor cor) {
        super(cor);
    }

    @Override
    boolean isTorre() {
        return true;
    }

    @Override
    ResultadoMovimento movimento(Peca[][] board, int linha, int coluna, int linhaSelecionada, int colunaSelecionada) {
        int l = this.auxLinha(linha, linhaSelecionada);
        int c = super.auxColuna(coluna, colunaSelecionada);
        int auxl = linha + l;
        int auxc = coluna + c;

        if (linha != linhaSelecionada && coluna != colunaSelecionada)
            return ResultadoMovimento.MovimentoInvalido;

        // condincinal movimento horizontal
        while (auxl != linhaSelecionada || auxc != colunaSelecionada) {

            if (board[auxl][auxc].isNotPecaVazia()) {
                return ResultadoMovimento.MovimentoInvalido;
            }
            if (auxl != linhaSelecionada)
                auxl += l;
            if (auxc != colunaSelecionada)
                auxc += c;
        }

        return ResultadoMovimento.MovimentoValido;

    }

}
