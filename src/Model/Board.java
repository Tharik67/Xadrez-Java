package src.Model;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import src.Model.Peca.Cor;
import src.Util.Observable;


class Board extends Observable {

    private Peca board[][];

    public Board() {
        board = new Peca[8][8];
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                if (linha == 0) {
                    if (coluna == 0 || coluna == 7)
                        board[linha][coluna] = new Torre(Cor.BRANCO);

                    else if (coluna == 1 || coluna == 6)
                        board[linha][coluna] = new Cavalo(Cor.BRANCO);

                    else if (coluna == 2 || coluna == 5)
                        board[linha][coluna] = new Bispo(Cor.BRANCO);

                    else if (coluna == 3)
                        board[linha][coluna] = new Rainha(Cor.BRANCO);

                    else if (coluna == 4)
                        board[linha][coluna] = new Rei(Cor.BRANCO);
                }

                else if (linha == 1)
                    board[linha][coluna] = new Peao(Cor.BRANCO);

                else if (linha == 6)
                    board[linha][coluna] = new Peao(Cor.PRETO);

                else if (linha == 7) {
                    if (coluna == 0 || coluna == 7)
                        board[linha][coluna] = new Torre(Cor.PRETO);

                    else if (coluna == 1 || coluna == 6)
                        board[linha][coluna] =new Cavalo(Cor.PRETO);

                    else if (coluna == 2 || coluna == 5)
                        board[linha][coluna] = new Bispo(Cor.PRETO);

                    else if (coluna == 3)
                        board[linha][coluna] = new Rainha(Cor.PRETO);

                    else if (coluna == 4)
                        board[linha][coluna] = new Rei(Cor.PRETO);
                }

                else {
                    board[linha][coluna] = new PecaVazia();
                }
                
            }
        }
    }

    public Peca[][] getBoard() {

        return board;
    }
    
    public void saveBin(FileOutputStream save) throws IOException {
		for(int linha = 0; linha < 8; linha++) {
			for(int coluna = 0; coluna < 8; coluna++) {
				Peca peca = board[linha][coluna];
				save.write(1);
				save.write(linha);
				save.write(coluna);
			
				if(peca.isNotPecaVazia()) {
				
					save.write(peca.isBranco()? 1 : 2);
					
					if(peca.isPeao())
						save.write(1);
					else if(peca.isCavalo())
						save.write(2);
					else if(peca.isTorre())
						save.write(3);
					else if(peca.isBispo())
						save.write(4);
					else if(peca.isRainha())
						save.write(5);
					else if(peca.isRei())
						save.write(6);
					else
						throw new IOException("Erro no board");
				}
				else {
					save.write(0);
				}
			}
		}
		save.write(-1);
		
	}

	public void openBin(FileInputStream arq) throws IOException {
		board = new Peca[8][8];
		
		while((arq.read()) != 255){
			int linha = arq.read();
			int coluna = arq.read();
			int aux = arq.read(); 
			if (aux == 0)
				board[linha][coluna] = new PecaVazia();
			else {
				int peca = arq.read();
				
				Peca.Cor cor = (aux == 1) ? Cor.BRANCO : Cor.PRETO;
				
				if(peca == 1)
					board[linha][coluna] = new Peao(cor);
				else if(peca == 2)
					board[linha][coluna] = new Cavalo(cor);
				else if(peca == 3)
					board[linha][coluna] = new Torre(cor);
				else if(peca == 4)
					board[linha][coluna] = new Bispo(cor);
				else if(peca == 5)
					board[linha][coluna] = new Rainha(cor);
				else if(peca == 6)
					board[linha][coluna] = new Rei(cor);
				else
					throw new IOException("File is corrupted");		
			}
		}
	}

}
