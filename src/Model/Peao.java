package src.Model;

 class Peao extends Peca {

    Peao(Cor color) {
        super(color);
    }

    @Override
    boolean isPeao() {
        return true;
    }

    @Override
    ResultadoMovimento movimento(Peca[][] board, int linha, int coluna, int linhaSelecionada,
            int colunaSelecionada) {
        Peca PosicaoDestino = board[linha][coluna];
        if (this.isBranco()) {

            // movimentacao
            if (!PosicaoDestino.isNotPecaVazia()) {

                if (linha <= linhaSelecionada)
                    return ResultadoMovimento.MovimentoInvalido;

                if (linhaSelecionada == 1 && (linha - linhaSelecionada) <= 2 && (coluna == colunaSelecionada)) {
                    // caso do peao andar duas casas no comeco
                    Peca PecaMeio = board[linhaSelecionada + 1][coluna];
                    if (PecaMeio.isNotPecaVazia())
                        return ResultadoMovimento.MovimentoInvalido;

                    return ResultadoMovimento.MovimentoValido;

                } else if ((linha - linhaSelecionada == 1) && (coluna == colunaSelecionada)) {

                    return ResultadoMovimento.MovimentoValido;
                }
            }
            // ataque
            else {
                if ((linha - linhaSelecionada) == 1 && Math.abs(coluna - colunaSelecionada) == 1) {

                    return ResultadoMovimento.MovimentoValido;
                }
            }

            return ResultadoMovimento.MovimentoInvalido;
        } else if (this.isPreto()) {

            // movimentacao
            if (!PosicaoDestino.isNotPecaVazia()) {

                if (linha >= linhaSelecionada) {
                    return ResultadoMovimento.MovimentoInvalido;
                } else if (linhaSelecionada == 6 && (linhaSelecionada - linha) <= 2 && (coluna == colunaSelecionada)) {
                    // caso do peao andar duas casas no comeco
                    Peca PecaMeio = board[linhaSelecionada - 1][coluna];
                    if (PecaMeio.isNotPecaVazia())
                        return ResultadoMovimento.MovimentoInvalido;

                    return ResultadoMovimento.MovimentoValido;
                } else if ((linha - linhaSelecionada == -1) && (coluna == colunaSelecionada)) {

                    return ResultadoMovimento.MovimentoValido;
                }
            }
            // ataque
            else {
                if ((linha - linhaSelecionada) == -1 && Math.abs(coluna - colunaSelecionada) == 1) {

                    return ResultadoMovimento.MovimentoValido;
                }
            }

        }
        return ResultadoMovimento.MovimentoInvalido;
    }
}
