package src.Model;

 class PecaVazia extends Peca{

     PecaVazia() {
        
        super(Cor.Null);
        
    }
        
    @Override
     boolean isNotPecaVazia() { return false; }
    
}
