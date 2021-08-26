package src.Model;

 class Cavalo extends Peca{

     Cavalo( Cor color) {
        super(color);
    }
 
    @Override
     boolean isCavalo() {
        return true;
    }

    @Override
     ResultadoMovimento movimento(Peca[][] board, int linha, int coluna, int linhaSelecionada, int colunaSelecionada) {
        if (
            (Math.abs(linha - linhaSelecionada) == 2 && Math.abs(coluna - colunaSelecionada) == 1) // dois vertical um horizontal
        || (Math.abs(linha - linhaSelecionada) == 1 && Math.abs(coluna - colunaSelecionada) == 2) // dois horizontal um vertical
            ) {
                return ResultadoMovimento.MovimentoValido;
            }
            return ResultadoMovimento.MovimentoInvalido;
    }
}
