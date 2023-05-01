/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudoku;

import java.util.Random;
import javax.swing.JOptionPane;

public class jogo {
    
    private final int TAMANHO_MAXIMO = 9;
    private final int[][] matr = new int[9][9];
    
    public void main(String[] args) {
       
        int x = menuInicial();
        if(x == 0 || x == 1){
            inicioJogo(x);
            imprime();
            jogadas();
            
        }else{
            JOptionPane.showMessageDialog(null, "Saindo");
        }
        JOptionPane.showMessageDialog(null, "Jogo finalizado!");
    } 
    
    private void jogadas(){
        Object[] options = {"Adicionar jogada", "Remover jogada", "Verificar", "Sair"};
        int linha, coluna, valor,x;

        do{
            x = JOptionPane.showOptionDialog(null, "Selecione uma opção:", "Menu!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            switch (x) {
                case 0: //adicionar jogada
                    String valoresParaAdicionar = JOptionPane.showInputDialog("Digite os valores no formato ([linha],[coluna],[valor]): ");
                    linha = Character.getNumericValue(valoresParaAdicionar.charAt(1));
                    coluna = Character.getNumericValue(valoresParaAdicionar.charAt(3));
                    valor = Character.getNumericValue(valoresParaAdicionar.charAt(5));
                    matr[linha][coluna] = valor;
                    System.out.println();
                    imprime();
                    break;

                case 1: // remover jogada
                   
                    String valoreParaRemover = JOptionPane.showInputDialog("Digite os valores no formato ([linha],[coluna]): ");
                    linha = Character.getNumericValue(valoreParaRemover.charAt(1));
                    coluna = Character.getNumericValue(valoreParaRemover.charAt(3));
                    matr[linha][coluna] = 0;
                    System.out.println();
                    imprime();
                    break;

                case 2://verificar
                    int resultadoVerificacao = verifica();
                    System.out.println(resultadoVerificacao);
                    if(resultadoVerificacao == 0){
                        JOptionPane.showMessageDialog(null, "Verificado, pode continuar");
                    }
                    else if(resultadoVerificacao == 1){
                        JOptionPane.showMessageDialog(null, "Verificado, erro na linha ");
                        x = 3;
                    }
                    else if(resultadoVerificacao == 2){
                        JOptionPane.showMessageDialog(null, "Verificado, erro na coluna ");
                        x = 3;
                    } else{
                        JOptionPane.showMessageDialog(null, "Verificado, erro no bloco ");
                        x = 3;
                    }
                   
                    
                    break;

                case 3://sair por opção
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    break;

                default://sair por entrada inválida
                    if (x != 3) {
                        JOptionPane.showMessageDialog(null, "Saindo...");
                    }
                    break;
            }
        } while (x ==0 || x==1 || x==2);
    }
    
    private int verifica(){
        int x =0;
        for(int linha = 0; linha < TAMANHO_MAXIMO; linha++){
            for(int coluna =0; coluna  <TAMANHO_MAXIMO; coluna++){
                if(matr[linha][coluna] != 0){
                    x = teste(linha, coluna);
                    if(x != 0){
                        break;
                    }
                }
            }
        }
        return x;
    }
    
    private int teste(int linha, int coluna){
        int x = 0;
        
        for(int c = 0; c<TAMANHO_MAXIMO; c++){
            if(matr[linha][coluna] == matr[linha][c] && matr[linha][c] != 0){
                x = 1;
                System.out.println(linha + " " + coluna);
                return x;
                
            }
        }
        
        for(int l = 0; l<TAMANHO_MAXIMO; l++){
            if(matr[linha][coluna] == matr[l][coluna] && matr[l][coluna] != 0){
                x = 2;
                return x;
            }
        }
        
        int inicioBlocoLinha = linha - linha%3;
        int inicioBlocoColuna = coluna - coluna%3;
        for(int i = inicioBlocoLinha; i < inicioBlocoLinha + 3; i++ ){
            for(int j = inicioBlocoColuna; j < inicioBlocoColuna +3; j++){
                if(matr[linha][coluna] == matr[i][j] && matr[i][j] != 0){
                    x = 3;
                    return x;
                }
            }
        }
        
        return x;
    }
    
    /*
    private boolean verificacao(){
        for(int linha =0; linha < TAMANHO_MAXIMO; linha++){
            for(int coluna = 0; coluna < TAMANHO_MAXIMO; coluna++){
                if(matr[linha][coluna]==0){
                    for(int numeroTeste = 1; numeroTeste <= TAMANHO_MAXIMO; numeroTeste++){
                        if(ehValido(numeroTeste, linha, coluna)){
                            matr[linha][coluna] = numeroTeste;
                            
                            if(verificacao()){
                                return true;
                            }
                            else{
                                matr[linha][coluna] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    */
    private void  Finaljogo(){
        
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
                }
                else{
                    JOptionPane.showMessageDialog(null, "Entrada inválida, finalizando..");
                }
            }catch(Exception err){
                JOptionPane.showMessageDialog(null, "Entrada inválida, finalizando...");
            }
            
            
        }
        else{//selecionou o definir jogo
            criaMatriz();
            String valoresInicias = JOptionPane.showInputDialog( "Digite os valores inicias no formato ([linha],[coluna],[valor]: ");
            int tamanho = valoresInicias.length();
            int quantidadeDeValores = tamanho/7;
            int valor, linha, coluna;
            for(int i = 0; i<quantidadeDeValores;i++){
                linha = Character.getNumericValue(valoresInicias.charAt(7*i + 1));  
                coluna = Character.getNumericValue(valoresInicias.charAt(7*i + 3)); 
                valor = Character.getNumericValue(valoresInicias.charAt(7*i + 5)); 
                if(ehValido(valor, linha, coluna) == true){
                    matr[linha][coluna] = valor;
                }
            }
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
