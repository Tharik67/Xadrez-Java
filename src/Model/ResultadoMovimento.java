package src.Model;

enum ResultadoMovimento {
    MovimentoInvalido(-2), PecaAmiga(-1), PecaSelecionada(0), MovimentoValido(1), RoqueE(3), RoqueD(2);

    private int valorPeca;

    ResultadoMovimento(int valor) {
        valorPeca = valor;
    }

    int getValor() {
        return valorPeca;
    }
}