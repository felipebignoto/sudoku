/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sudoku;
import javax.swing.JOptionPane;
import java.util.Random;
import java.util.*;
/**
 *
 * @author felip
 */
public class Sudoku {
    
    private static final int TAMANHO_MAXIMO = 9;
    
    public static void main(String[] args) {
       
        int x = menuInicial();
        if(x == 0 || x == 1){
            int matr[][] = inicioJogo(x);
            
        }else{
            JOptionPane.showMessageDialog(null, "Saindo");
        }
        JOptionPane.showMessageDialog(null, "Jogo finalizado!");
    } 
    
    private static int[][] inicioJogo(int x){
        if(x == 0){//selecionou o jogo aleatorio
            int matr[][] = criaMatriz();
            String quantidadeString = JOptionPane.showInputDialog( "Digite a quantidade de números para serem sorteados[0-60]: ");
            try{
                int quantidade = Integer.parseInt(quantidadeString);
                int valor, linha, coluna;
                if(quantidade <= 60 && quantidade >=0){
                    for (int i = 0; i < quantidade; i++) {
                        for (;;) {
                            Random aleatorio = new Random();
                            valor = aleatorio.nextInt((TAMANHO_MAXIMO - 1) + 1) + 1;
                            linha = aleatorio.nextInt((TAMANHO_MAXIMO - 1 - 0) + 1) + 0;
                            coluna = aleatorio.nextInt((TAMANHO_MAXIMO - 1 - 0) + 1) + 0;
                            if (ehValido(matr, valor, linha, coluna) == true) {
                                matr[linha][coluna] = valor;
                                break;
                            }
                        }
                    }
                    imprime(matr);
                    //String mensagem = Arrays.deepToString(matr);
                    //JOptionPane.showMessageDialog(null, "Seu jogo incial eh: " + mensagem);
                    return matr;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Entrada inválida, finalizando..");
                   
                }
            }catch(Exception err){
                JOptionPane.showMessageDialog(null, "Entrada inválida, finalizando..");
            }
            
            
        }
        else{//selecionou o definir jogo
            
        }
        
        return ;
    }
    
    private static boolean numeroNaPosicao(int [][] matr,int linha, int coluna){
        if(matr[linha][coluna] != 0){
            return true;
        }
        return false;
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
        for(int i = inicioBlocoLinha; i < inicioBlocoLinha + 3; i++ ){
            for(int j = inicioBlocoColuna; j < inicioBlocoColuna +3; j++){
                if(matr[i][j] == valor){
                    return true;
                }
            }
        }
        return false;
    }   
    
    private static boolean ehValido(int [][] matr, int valor, int linha, int coluna){
        return !numeroNaLinha(matr, valor, linha) && !numeroNaColuna(matr, valor, coluna) && !numeroNoBloco(matr, valor, linha, coluna) && !numeroNaPosicao(matr, linha, coluna);
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
        Object[] options = {"Jogo aleatório", "Definir jogo"};
        int x = JOptionPane.showOptionDialog(null, "Selecione uma opção:", "Olá, seja bem vindo ao sudoku!",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return x;
    }
    
    private static void imprime(int matr[][]){
        for(int i = 0; i < TAMANHO_MAXIMO; i++){
                if(i%3 == 0 && i != 0 ){
                    System.out.println("---------");
                }
                for(int j = 0; j< TAMANHO_MAXIMO; j++){
                    if(j%3 == 0 && j != 0){
                        System.out.print("|");
                    }
                    System.out.print(matr[i][j] + " ");
                }
                System.out.println();
            }
    }
}
