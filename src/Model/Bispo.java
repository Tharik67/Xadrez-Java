package src.Model;

 class Bispo extends Peca{

     Bispo(Cor color) {
        super(color);
    }

    @Override
     boolean isBispo() {
        return true;
    }
    
    @Override
     ResultadoMovimento movimento(Peca[][] board, int linha, int coluna, int linhaSelecionada, int colunaSelecionada) {
        int l = this.auxLinha(linha, linhaSelecionada);
        int c = super.auxColuna(coluna, colunaSelecionada);
        int auxl = linha + l;
        int auxc = coluna + c;

        if (Math.abs(linha - linhaSelecionada) != Math.abs(coluna - colunaSelecionada))
                    return ResultadoMovimento.MovimentoInvalido;

                while (auxl != linhaSelecionada && auxc != colunaSelecionada) {

                    if (board[auxl][auxc].isNotPecaVazia())
                        return ResultadoMovimento.MovimentoInvalido;
                    auxl += l;
                    auxc += c;

                }

                return ResultadoMovimento.MovimentoValido;
    }
}
