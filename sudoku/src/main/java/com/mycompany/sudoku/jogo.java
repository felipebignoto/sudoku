package com.mycompany.sudoku;

import java.util.Random;
import javax.swing.JOptionPane;

public class jogo {

    private final int TAMANHO_MAXIMO = 9;
    private int[][] matr = new int[9][9];
    private final int[][] tabuleiro = new int[9][9];
    private static boolean REINICIAR = true;

    public void main(String[] args) {

       criandoJogo();
    }
    
    private void criandoJogo() {
        while(REINICIAR == true)  {
            int x = menuInicial();
            if (x == 0 || x == 1) {
                inicioJogo(x);
                System.out.println("Seu jogo incial eh: ");
                imprime(tabuleiro);
                jogadas();

            } else {
                JOptionPane.showMessageDialog(null, "Saindo");
            }
            JOptionPane.showMessageDialog(null, "Jogo finalizado!");
        }
    }
    
    private boolean verificaJogo(){
        
        boolean verifica = false;
        boolean semErro = true;
 
        for (int linha = 0; linha < TAMANHO_MAXIMO; linha++) {
            for (int coluna = 0; coluna < TAMANHO_MAXIMO; coluna++) {
                
                if(matr[linha][coluna] != 0){
                    int auxiliar = matr[linha][coluna];
                    matr[linha][coluna] =0;
                    if (numeroNaPosicao(linha, coluna) == true) {
                        verifica = true;
                        matr[linha][coluna] = auxiliar;
                        JOptionPane.showMessageDialog(null, "Jogo invalido, erro na posicao: (" + linha + "," + coluna + ").");
                        semErro = false;
                        break;
                    }
                      
                    if (numeroNaLinha(auxiliar, linha)) {
                        verifica = true;
                        matr[linha][coluna] = auxiliar;
                        JOptionPane.showMessageDialog(null, "Jogo invalido, erro na linha: (" + linha + "," + coluna + ").");
                        semErro = false;
                        break;
                    }

                    if (numeroNoBloco(auxiliar, linha, coluna)) {
                        verifica = true;
                        matr[linha][coluna] = auxiliar;
                        JOptionPane.showMessageDialog(null, "Jogo invalido, erro no bloco: (" + linha + "," + coluna + ").");
                        semErro = false;
                        break;
                    }
                    
                    if (numeroNaColuna(auxiliar, coluna)) {
                        verifica = true;
                        matr[linha][coluna] = auxiliar;
                        JOptionPane.showMessageDialog(null, "Jogo invalido, erro na coluna: (" + linha + "," + coluna + ").");
                        semErro = false;
                        break;
                    }
                    matr[linha][coluna] =0;
                }
            }

            if (verifica) {
                break;
            }
            
        }
        
        return semErro;
    }
    
    private boolean verificaSeJogoEstaCompleto(){
        for(int i = 0; i< TAMANHO_MAXIMO; i++){
            for(int j =0; j< TAMANHO_MAXIMO; j++){
                if(tabuleiro[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    private void jogadas() {
        Object[] options = {"Adicionar jogada", "Remover jogada", "Verificar", "Sair"};
        int linha, coluna, valor, x;

        do {
            x = JOptionPane.showOptionDialog(null, "Selecione uma opção:", "Menu!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            switch (x) {
                case 0: //adicionar jogada
                    String valoresParaAdicionar = JOptionPane.showInputDialog("Digite os valores no formato ([linha],[coluna],[valor]): ");
                    linha = Character.getNumericValue(valoresParaAdicionar.charAt(1));
                    coluna = Character.getNumericValue(valoresParaAdicionar.charAt(3));
                    valor = Character.getNumericValue(valoresParaAdicionar.charAt(5));
                    tabuleiro[linha][coluna] = valor;
                    System.out.println("tabulerio:");
                    imprime(tabuleiro);
                    break;

                case 1: // remover jogada
                    String valoreParaRemover = JOptionPane.showInputDialog("Digite os valores no formato ([linha],[coluna]): ");
                    linha = Character.getNumericValue(valoreParaRemover.charAt(1));
                    coluna = Character.getNumericValue(valoreParaRemover.charAt(3));
                    tabuleiro[linha][coluna] = 0;
                    System.out.println("tabulerio:");
                    imprime(tabuleiro);
                    break;

                case 2://verificar
                    //copio o tabuleiro para a matriz
                    for(int i = 0; i<TAMANHO_MAXIMO;i++){
                        for(int j =0; j< TAMANHO_MAXIMO;j++){
                            matr[i][j] = tabuleiro[i][j];
                        }
                    }
                    System.out.println("tabulerio:");
                    imprime(tabuleiro);
                    //verifico se a matr tem solução
                    if (solucaoSudoku() == true ) {
                        if (verificaSeJogoEstaCompleto() == true && verificaJogo() == true) {
                            x = 4;
                            JOptionPane.showMessageDialog(null, "Parabens, voce ganhou!!!");
                            //Chamo a nova função
                            Object[] opcao = {"Inicar novo jogo", "Sair"};
                            int opcaoEscolhida = JOptionPane.showOptionDialog(null, "*Selecione uma opção:", "Menu!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcao, opcao[0]);
                            if(opcaoEscolhida != 0){
                                REINICIAR = false;
                                
                            }
                        }
                        else if(verificaJogo() == true){
                            JOptionPane.showMessageDialog(null, "tabuleiro verificado e tem solucao.");
                        }else{
                            JOptionPane.showMessageDialog(null, "tabuleiro invalido.");
                        }
                    }
                    else{
                        verificaJogo();
                        JOptionPane.showMessageDialog(null, "tabuleiro verificado e nao tem solucao.");
                    }
                    break;

                case 3://sair por opção
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    REINICIAR = false;
                    break;

                default://sair por entrada inválida
                    if (x != 3) {
                        JOptionPane.showMessageDialog(null, "Saindo...");
                    }
                    
                    break;
            }
        } while ((x == 0 || x == 1 || x == 2));
    }

    private void inicioJogo(int x) {
        if (x == 0) {//selecionou o jogo aleatorio
            criaMatriz(matr);
            
            String quantidadeString = JOptionPane.showInputDialog("Digite a quantidade de números para serem sorteados[0-81]: ");
            try {
                int quantidade = Integer.parseInt(quantidadeString);
                if (quantidade <= 81 && quantidade >= 0) {
                    preenchDiagonal();
                    criaMatriz(tabuleiro);
                    solucaoSudoku();
                    criaTabuleiro(quantidade);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Entrada inválida, pois o numero eata fora do intervalo. O jogo sera inicaido com o tabuleiro vazio.");
                }
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Erro: Nenhuma opção selecionda. O jogo sera iniciado com o tabuleiro vazio.");
            }

        } else {//selecionou o definir jogo
            criaMatriz(matr);
            String valoresInicias = JOptionPane.showInputDialog("Digite os valores inicias no formato ([linha],[coluna],[valor]: ");
            try {
                int tamanho = valoresInicias.length();
                int quantidadeDeValores = tamanho / 7;
                int valor, linha, coluna;
                criaMatriz(tabuleiro);
                for (int i = 0; i < quantidadeDeValores; i++) {
                    linha = Character.getNumericValue(valoresInicias.charAt(7 * i + 1));
                    coluna = Character.getNumericValue(valoresInicias.charAt(7 * i + 3));
                    valor = Character.getNumericValue(valoresInicias.charAt(7 * i + 5));
                    if (ehValido(valor, linha, coluna) == true) {
                        tabuleiro[linha][coluna] = valor;
                    }
                }
            }catch(Exception err){
                JOptionPane.showMessageDialog(null, "Erro: Entrada inválida, iniciando o jogo vazio.");
            }
        }
    }
    
    private void criaTabuleiro(int quantidade){
        sorteaTabuleiro(quantidade);
    }
    
    private void sorteaTabuleiro(int quantidade){
        int contador = 0;
        while(contador < quantidade){
            Random aleatorio = new Random();
            int coluna = aleatorio.nextInt((TAMANHO_MAXIMO - 1 - 0) + 1) + 0;
            int linha = aleatorio.nextInt((TAMANHO_MAXIMO - 1 - 0) + 1) + 0;
            if(tabuleiro[linha][coluna] == 0){
                contador++;
                tabuleiro[linha][coluna] = matr[linha][coluna];
            }
        }
    }
    
    private boolean solucaoSudoku(){
        
        boolean verifica = false;
        int linha;
        int coluna= 0;
        for (linha = 0; linha < TAMANHO_MAXIMO; linha++) {
            for (coluna = 0; coluna < TAMANHO_MAXIMO ; coluna++) {
                if (matr[linha][coluna] == 0) {
                    verifica = true;
                    break;
                }
            }
            if(verifica==true){
                    break;
            }
        }
        
        if(verifica == false){
            return true;
        }
        for(int numero = 1; numero <= 9; numero++){

            if(ehValido(numero, linha, coluna)==true){
                matr[linha][coluna] = numero;

                if(solucaoSudoku()==true){
                    return true;
                }
            }
            
            matr[linha][coluna] = 0;
        }
        return false;
    }
    
    private void preenchDiagonal(){
        int indice = 0;
        while(indice < TAMANHO_MAXIMO){
            Random aleatorio = new Random();
            int valor = aleatorio.nextInt((TAMANHO_MAXIMO - 1) + 1) + 1;
            if(ehValido(valor, indice, indice) == true){
                matr[indice][indice] = valor;
                indice++;
            }
        }
    }

    private boolean numeroNaPosicao(int linha, int coluna) {
        if (matr[linha][coluna] != 0) {
            return true;
        }
        return false;
    }

    private boolean numeroNaLinha(int valor, int linha) {
        for (int i = 0; i < TAMANHO_MAXIMO; i++) {
            if (matr[linha][i] == valor) {
                return true;
            }
        }
        return false;
    }

    private boolean numeroNaColuna(int valor, int coluna) {
        for (int i = 0; i < TAMANHO_MAXIMO; i++) {
            if (matr[i][coluna] == valor) {
                return true;
            }
        }
        return false;
    }

    private boolean numeroNoBloco(int valor, int linha, int coluna) {
        int inicioBlocoLinha = linha - linha % 3;
        int inicioBlocoColuna = coluna - coluna % 3;
        for (int i = inicioBlocoLinha; i < inicioBlocoLinha + 3; i++) {
            for (int j = inicioBlocoColuna; j < inicioBlocoColuna + 3; j++) {
                if (matr[i][j] == valor) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean ehValido(int valor, int linha, int coluna) {
        return !numeroNaLinha(valor, linha) && !numeroNaColuna(valor, coluna) && !numeroNoBloco(valor, linha, coluna) && !numeroNaPosicao(linha, coluna);
    }

    public void criaMatriz(int[][] matriz) {

        for (int i = 0; i < TAMANHO_MAXIMO; i++) {
            for (int j = 0; j < TAMANHO_MAXIMO; j++) {
                matriz[i][j] = 0;
            }
        }
    }
    
    private static int menuInicial() {
        Object[] options = {"Jogo aleatório", "Definir jogo"};
        int x = JOptionPane.showOptionDialog(null, "Selecione uma opção:", "Olá, seja bem vindo ao sudoku!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if(x != 0 && x!=1) {
            REINICIAR = false;
        }
        return x;
    }

    private void imprime(int[][] matriz) {
        for (int i = 0; i < TAMANHO_MAXIMO; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("---------");
            }
            for (int j = 0; j < TAMANHO_MAXIMO; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("|");
                }
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
 
