package src.Model;

class Rei extends Peca {

    Rei(Cor color) {
        super(color);
    }

    @Override
    boolean isRei() {
        return true;
    }

    @Override
    ResultadoMovimento movimento(Peca[][] board, int linha, int coluna, int linhaSelecionada, int colunaSelecionada) {
        int l = this.auxLinha(linha, linhaSelecionada);
        int c = super.auxColuna(coluna, colunaSelecionada);
        int auxl = linha + l;
        int auxc = coluna + c;
        Peca posicaoDestino = board[linha][coluna];
        if (Math.abs(linha - linhaSelecionada) <= 1 && Math.abs(coluna - colunaSelecionada) <= 1)
                return ResultadoMovimento.MovimentoValido;
        // Roque: movimento especial torre -> rei
        else if ( posicaoDestino.isTorre() && posicaoDestino.getCor() == this.getCor()
         && this.getMoveCount() == 0 && posicaoDestino.getMoveCount() == 0
         
                && !Model.reiEstaEmXeque(linhaSelecionada,colunaSelecionada)
                ) {
            while (auxc != colunaSelecionada) {
                if (board[auxl][auxc].isNotPecaVazia()) {
                    return ResultadoMovimento.MovimentoInvalido;
                }
                auxc += c;
            }
            if (coluna == 0) 
                return ResultadoMovimento.RoqueE;
            else 
                return ResultadoMovimento.RoqueD;
            
        }
        return ResultadoMovimento.MovimentoInvalido;
    }
}
