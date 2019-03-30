package analisadorlexico;


public class CustomException extends Exception {
    
    int linha;
    int coluna;
    
    public CustomException(int linha, int coluna){
        this.linha=linha;
        this.coluna=coluna;
    }
    
    public String toString(){
        return "Caracter nao reconhecido na linha " + linha + ", coluna " + coluna + ".";
    }
}
