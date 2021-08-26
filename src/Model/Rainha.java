package src.Model;

 class Rainha extends Peca{

     Rainha(Cor color) {
        super(color);
    }
    
    @Override
     boolean isRainha() {
        return true;
    }

    @Override
     ResultadoMovimento movimento(Peca[][] board, int linha, int coluna, int linhaSelecionada, int colunaSelecionada) {
        int l = this.auxLinha(linha, linhaSelecionada);
        int c = super.auxColuna(coluna, colunaSelecionada);
        int auxl = linha + l;
        int auxc = coluna + c;

        if (Math.abs(linha - linhaSelecionada) == Math.abs(coluna - colunaSelecionada)) { // movimento diagonal

            // condincinal movimento diagonal
            while (auxl != linhaSelecionada && auxc != colunaSelecionada) {
                if (board[auxl][auxc].isNotPecaVazia())
                    return ResultadoMovimento.MovimentoInvalido;
                auxl += l;
                auxc += c;

            }

            return ResultadoMovimento.MovimentoValido;
        } else if (linha == linhaSelecionada || coluna == colunaSelecionada) { 
            // movimento horizontal e vertical

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

        return ResultadoMovimento.MovimentoInvalido;
    }
    
}
