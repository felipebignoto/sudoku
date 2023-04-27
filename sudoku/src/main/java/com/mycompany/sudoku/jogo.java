/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudoku;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author felip
 */
public class jogo {
    
    private final int TAMANHO_MAXIMO = 9;
    private final int[][] matr = new int[9][9];
    
    public void main(String[] args) {
       
        int x = menuInicial();
        if(x == 0 || x == 1){
            inicioJogo(x);
            
        }else{
            JOptionPane.showMessageDialog(null, "Saindo");
        }
        JOptionPane.showMessageDialog(null, "Jogo finalizado!");
    } 
    
    private void inicioJogo(int x){
        if(x == 0){//selecionou o jogo aleatorio
            criaMatriz();
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
                            if (ehValido( valor, linha, coluna) == true) {
                                matr[linha][coluna] = valor;
                                break;
                            }
                        }
                    }
                    imprime();
                    //String mensagem = Arrays.deepToString(matr);
                    //JOptionPane.showMessageDialog(null, "Seu jogo incial eh: " + mensagem);
                    
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
        
        
    }
    
    private  boolean numeroNaPosicao(int linha, int coluna){
        if(matr[linha][coluna] != 0){
            return true;
        }
        return false;
    }
    
    private boolean numeroNaLinha( int valor, int linha){
        for(int i =0; i<TAMANHO_MAXIMO; i++){
            if(matr[linha][i] == valor){
                return true;
            }
        }
        return false;
    }
   
    private boolean numeroNaColuna( int valor, int coluna){
        for(int i =0; i<TAMANHO_MAXIMO; i++){
            if(matr[i][coluna] == valor){
                return true;
            }
        }
        return false;
    } 
    
    private boolean numeroNoBloco( int valor, int linha, int coluna){
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
    
    private boolean ehValido(int valor, int linha, int coluna){
        return !numeroNaLinha( valor, linha) && !numeroNaColuna( valor, coluna) && !numeroNoBloco(valor, linha, coluna) && !numeroNaPosicao( linha, coluna);
    }
    
    public void criaMatriz(){
            
           for(int i = 0; i < TAMANHO_MAXIMO; i++){
               for(int j =0; j< TAMANHO_MAXIMO; j++){
                   matr[i][j] = 0;
               }
           }
    }
    
    private static int menuInicial(){
        Object[] options = {"Jogo aleatório", "Definir jogo"};
        int x = JOptionPane.showOptionDialog(null, "Selecione uma opção:", "Olá, seja bem vindo ao sudoku!",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return x;
    }
    
    private void imprime(){
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