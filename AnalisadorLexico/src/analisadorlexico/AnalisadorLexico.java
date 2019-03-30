package analisadorlexico;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.JOptionPane;


public class AnalisadorLexico {
    
    static boolean flag1=true;
    static boolean flag2=false;
    
    static void comparador(Character a, Character b) {
        
        if(Character.isLetter(a)){
            Character.toLowerCase(a);
        }
        if(Character.isLetter(b)){
            Character.toLowerCase(b);
        }
        
        if(a.equals(b)){
            flag2=true;
        }
    }
    
    static void verificarErro(int linha, int coluna) throws CustomException{
        
        if (!flag2){
            
            flag1 = false;
            throw new CustomException(linha, coluna);
        }
    }
    
    public static void main(String[] args) {
        String line;
        Set<Character>alfabeto=new LinkedHashSet<>();
        
        try{
            FileReader programaInicial = new FileReader("programaInicial.txt");
            BufferedReader a = new BufferedReader(programaInicial);
            while((line=a.readLine())!=null){
                line.chars().forEachOrdered(i->alfabeto.add((char)i));
            }
            for(char c:alfabeto){
                System.out.println(c);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        
        String nomePrograma = JOptionPane.showInputDialog("Por favor, digite o nome do programa a ser executado (com extensão): ");
        
        try{
            FileReader programaTeste = new FileReader(nomePrograma);
            BufferedReader a = new BufferedReader(programaTeste);
            
            for(int i=0;(line=a.readLine())!=null;i++){
                for(int j=0; j<line.length();j++){
                    
                    for(char c:alfabeto){
                        flag2=false;
                        comparador(c,line.charAt(j));
                        
                        if(flag2){
                            break;
                        }
                    }
                    
                    try {
                        verificarErro(i+1,j+1);
                    }catch (CustomException e){
                        e.printStackTrace();
                    }
                }
            }
            
            if (flag1){
                
                System.out.println("O arquivo carregado nao contem nenhum caracter invalido.");
            }
            
            a.close();
            
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
}