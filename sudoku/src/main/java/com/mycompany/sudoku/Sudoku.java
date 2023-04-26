/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sudoku;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import java.util.Random;
/**
 *
 * @author felip
 */
public class Sudoku {
    
    private static final int TAMANHO_MAXIMO = 9;
    
    public static void main(String[] args) {
        
        
        int x = menuInicial();
        if(x == 1){//selecionou o jogo aleatorio
            
            int matr[][] = criaMatriz();
            String quantidadeString = JOptionPane.showInputDialog( "Digite a quantidade de números para serem sorteados: ");
            int quantidade = Integer.parseInt( quantidadeString);
            
            for(int i =0; i<quantidade; i++){
                
                Random aleatorio = new Random();
                int valor = aleatorio.nextInt(TAMANHO_MAXIMO) + 1;
                int linha = aleatorio.nextInt(TAMANHO_MAXIMO) + 1;
                int coluna = aleatorio.nextInt(TAMANHO_MAXIMO) + 1;
                matr[linha][coluna] = valor;
            }
        }
        
        else{
            if(x == 2){//selecionou o definir jogo 
                //int matr[][] = criaMatriz();
            }
            else{//não irá iniciar o jogo
               JOptionPane.showMessageDialog(null, "Saindo");
            }
        }
        
    
    }
    
    private static boolean numeroNaLinha(int [][] matr, int valor, int linha){
        for(int i =0; i<TAMANHO_MAXIMO; i++){
            if(matr[linha][i] == valor){
                return true;
            }
        }
        return false;
    }
    
    private static boolean numeroNaColuna(int [][] matr, int valor, int coluna){
        for(int i =0; i<TAMANHO_MAXIMO; i++){
            if(matr[i][coluna] == valor){
                return true;
            }
        }
        return false;
    } 
    
    private static boolean numeroNoBloco(int [][] matr, int valor, int linha, int coluna){
        int inicioBlocoLinha = linha - linha%3;
        int inicioBlocoColuna = coluna - coluna%3;
        
        return false;
    }   
    
    public static int[][] criaMatriz(){
           int [][] matr1 = {
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0}
            };
           
           return matr1;
    }
    
    private static int menuInicial(){
        JCheckBox check = new JCheckBox("Confirmar");
        Object[] options = {check,"Jogo aleatório", "Definir jogo"};
        int x = JOptionPane.showOptionDialog(null, "Confime e selecione uma opção:", "Olá, seja bem vindo ao sudoku!",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        
        if(check.isSelected()){
            //System.out.println("Selecionei o " + options[x]);
            return x;
        }
        else{
            return 0;
        }
    }
}
