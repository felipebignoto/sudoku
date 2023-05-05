package com.mycompany.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JOptionPane;

public class jogo {

    private final int TAMANHO_MAXIMO = 9;
    private final int[][] matr = new int[9][9];
    private final int[][] tabuleiro = new int[9][9];

    public void main(String[] args) {

        int x = menuInicial();
        if (x == 0 || x == 1) {
            inicioJogo(x);
            System.out.println("Tabuleiro: ");
            imprime(matr);
            
            jogadas();
            

        } else {
            JOptionPane.showMessageDialog(null, "Saindo");
        }
        JOptionPane.showMessageDialog(null, "Jogo finalizado!");
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
                    matr[linha][coluna] = valor;
                    System.out.println();
                    imprime(matr);
                    break;

                case 1: // remover jogada
                    String valoreParaRemover = JOptionPane.showInputDialog("Digite os valores no formato ([linha],[coluna]): ");
                    linha = Character.getNumericValue(valoreParaRemover.charAt(1));
                    coluna = Character.getNumericValue(valoreParaRemover.charAt(3));
                    matr[linha][coluna] = 0;
                    System.out.println();
                    imprime(matr);
                    break;

                case 2://verificar
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
        } while (x == 0 || x == 1 || x == 2);
    }

    private void inicioJogo(int x) {
        if (x == 0) {//selecionou o jogo aleatorio
            criaMatriz(matr);
            
            String quantidadeString = JOptionPane.showInputDialog("Digite a quantidade de números para serem sorteados[0-60]: ");
            try {
                int quantidade = Integer.parseInt(quantidadeString);
                //int valor, linha, coluna;
                if (quantidade <= 60 && quantidade >= 0) {
                    if (quantidade >= 9) {
                        //Prencho a diagonal toda
                        preenchDiagonal(9);
                        solucaoSudoku();
                    }
                    else{
                        // Preenche diagonal principal do tabuleiro com o numero passado de números aleatórios de 1 a 9 
                        preenchDiagonal(quantidade);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Entrada inválida, pois o numero eata fora do intervalo. O jogo sera inicaido com o tabuleiro vazio.");
                }
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Erro: Nenhuma opção selecionda. O jogo sera inicaido com o tabuleiro vazio.");
            }

        } else {//selecionou o definir jogo
            criaMatriz(matr);
            String valoresInicias = JOptionPane.showInputDialog("Digite os valores inicias no formato ([linha],[coluna],[valor]: ");
            try {
                int tamanho = valoresInicias.length();
                int quantidadeDeValores = tamanho / 7;
                int valor, linha, coluna;
                for (int i = 0; i < quantidadeDeValores; i++) {
                    linha = Character.getNumericValue(valoresInicias.charAt(7 * i + 1));
                    coluna = Character.getNumericValue(valoresInicias.charAt(7 * i + 3));
                    valor = Character.getNumericValue(valoresInicias.charAt(7 * i + 5));
                    if (ehValido(valor, linha, coluna) == true) {
                        matr[linha][coluna] = valor;
                    }
                }
            }catch(Exception err){
                JOptionPane.showMessageDialog(null, "Erro: Entrada inválida, iniciando o jogo vazio.");
            }
        }
    }
    
    private void criaTabuleiro(int quantidade){
        criaMatriz(tabuleiro);
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
        int linha = -1 , coluna = -1;
        while (verifica == false) {
            for ( linha = 0; linha < TAMANHO_MAXIMO; linha++) {
                for ( coluna = 0; coluna < TAMANHO_MAXIMO; coluna++) {
                    if (matr[linha][coluna] == 0) {
                        verifica = true;
                    }
                }
            }
        }
        
        if(linha == -1){
            return true;
        }
        
        for(int numero = 1; numero <= 9; numero++){
            if(ehValido(numero, linha, coluna) == true){
                return true;
            }
            
            matr[linha][coluna] = 0;
        }
        return false;
    }
    
    private void preenchDiagonal(int valor){
        int indice = 0;
        while(indice < valor){
            Random aleatorio = new Random();
            valor = aleatorio.nextInt((TAMANHO_MAXIMO - 1) + 1) + 1;
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

    public void criaMatriz(int[][] tabuleiro) {

        for (int i = 0; i < TAMANHO_MAXIMO; i++) {
            for (int j = 0; j < TAMANHO_MAXIMO; j++) {
                tabuleiro[i][j] = 0;
            }
        }
    }
    
    private static int menuInicial() {
        Object[] options = {"Jogo aleatório", "Definir jogo"};
        int x = JOptionPane.showOptionDialog(null, "Selecione uma opção:", "Olá, seja bem vindo ao sudoku!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return x;
    }

    private void imprime(int[][] tabuleiro) {
        for (int i = 0; i < TAMANHO_MAXIMO; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("---------");
            }
            for (int j = 0; j < TAMANHO_MAXIMO; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("|");
                }
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
    //--------Funções não usadas-------------------

/*
    
 private boolean preencherTabuleiro(int[][] tabuleiro, int linha, int coluna) {
        if (coluna == 9) {
            coluna = 0;
            linha++;
            if (linha == 9) {
                return true; // Tabuleiro preenchido com sucesso!
            }
        }

        if (tabuleiro[linha][coluna] != 0) {
            return preencherTabuleiro(tabuleiro, linha, coluna + 1);
        }

        ArrayList<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numeros.add(i);
        }
        Collections.shuffle(numeros);

        for (int i = 0; i < numeros.size(); i++) {
            int num = numeros.get(i);
            if (ehValido(num, linha, coluna)) {
                tabuleiro[linha][coluna] = num;
                if (preencherTabuleiro(tabuleiro, linha, coluna + 1)) {
                    return true;
                }
            }
        }

        tabuleiro[linha][coluna] = 0;
        return false;
    }    

private boolean verificacaoTotalAuxiliar(int[][] matrizTeste) {

        for (int l = 0; l < TAMANHO_MAXIMO; l++) {
            for (int c = 0; c < TAMANHO_MAXIMO; c++) {
                if (matrizTeste[l][c] == 0) {
                    for (int numeroTeste = 1; numeroTeste <= TAMANHO_MAXIMO; numeroTeste++) {
                        if (ehValido(numeroTeste, l, c)) {
                            matrizTeste[l][c] = numeroTeste;
                            if (verificacaoTotalAuxiliar(matrizTeste) == true) {
                                return true;
                            } else {
                                matrizTeste[l][c] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean verificacaoTotal(int valor, int linha, int coluna) {
        int[][] matrizTeste = new int[9][9];
        //matrizTeste = matr;
        for (int l = 0; l < TAMANHO_MAXIMO; l++) {
            for (int c = 0; c < TAMANHO_MAXIMO; c++) {
                matrizTeste[l][c] = matr[l][c];
            }
        }

        matrizTeste[linha][coluna] = valor;
        //System.out.println("passou aqui" + verificacaoTotalAuxiliar(matrizTeste));
        return verificacaoTotalAuxiliar(matrizTeste);
    }
    
    private boolean preencherTabuleiro(int linha, int coluna) {
        System.out.println("Entrou na funcao preencher");
        if (coluna == TAMANHO_MAXIMO) {
            coluna = 0;
            linha++;
            if (linha == TAMANHO_MAXIMO) {
                return true; // Tabuleiro preenchido com sucesso!
            }
        }

        if (matr[linha][coluna] != 0) {
            return preencherTabuleiro(linha, coluna + 1);
        }

        Random rand = new Random();
        int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < numeros.length; i++) {
            int indiceAleatorio = rand.nextInt(numeros.length);
            int num = numeros[indiceAleatorio];
            System.out.println(num);
            numeros[indiceAleatorio] = numeros[i];
            if (ehValido(num, linha, coluna)) {
                matr[linha][coluna] = num;
                System.out.println( matr[linha][coluna]);
                if (preencherTabuleiro(linha, coluna + 1) == true) {
                    System.out.println("Entrou no if de preencher");
                    return true;
                }
            }
        }

        matr[linha][coluna] = 0;
        return false;
    }
    
    private boolean preencherTabuleiro(int[][] tabuleiro, int linha, int coluna) {
        if (coluna == TAMANHO_MAXIMO) {
            coluna = 0;
            linha++;
            if (linha == TAMANHO_MAXIMO) {
                return true; // Tabuleiro preenchido com sucesso!
            }
        }

        if (matr[linha][coluna] != 0) {
            return preencherTabuleiro(tabuleiro, linha, coluna + 1);
        }

        ArrayList<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {//Adiciono os numeros na lista
            numeros.add(i);
        }
        Collections.shuffle(numeros);//embaralho a lista

        for (int i = 0; i < numeros.size(); i++) {
            int num = numeros.get(i);
            if (ehValido(num, linha, coluna) == true) {
                tabuleiro[linha][coluna] = num;
                if (preencherTabuleiro(tabuleiro,linha, coluna + 1) == true) {
                    return true;
                }
            }
        }
        tabuleiro[linha][coluna] = 0;
        for(int i = 0; i < 9; i++) {
                if (i % 3 == 0 && i != 0) {
                    System.out.println("---------");
                }
                for (int j = 0; j < 9; j++) {
                    if (j % 3 == 0 && j != 0) {
                        System.out.print("|");
                    }
                    System.out.print(tabuleiro[i][j] + " ");
                }
                System.out.println();
            }
        return false;
    }

    // Preenche diagonal principal do tabuleiro com 9 números aleatórios de 1 a 9
                        for (int indice = 0; indice < TAMANHO_MAXIMO; indice++) {
                            for (;;) {
                                Random aleatorio = new Random();
                                valor = aleatorio.nextInt((TAMANHO_MAXIMO - 1) + 1) + 1;
                                if (ehValido(valor, indice, indice) == true) {
                                    gabarito[indice][indice] = valor;
                                    break;
                                }
                            }
                        }
                        // Preenche o restante do tabuleiro usando backtracking
                       preencherTabuleiro(gabarito, 0, 0);
                        /*
                        for(int q = 0; q < quantidade - 9; q++ ){
                             Random aleatorio = new Random();
                                valor = aleatorio.nextInt((TAMANHO_MAXIMO - 1) + 1) + 1;
                                linha = aleatorio.nextInt((TAMANHO_MAXIMO - 1 - 0) + 1) + 0;
                                coluna = aleatorio.nextInt((TAMANHO_MAXIMO - 1 - 0) + 1) + 0;
                                if (preencherTabuleiro(matr, linha, coluna) == true) {
                                    matr[linha][coluna] = valor;
                                    break;
                                }
                        }
                        */
                        /*
                        for (int i = 0; i < quantidade - 9; i++) {
                            for (;;) {
                                Random aleatorio = new Random();
                                valor = aleatorio.nextInt((TAMANHO_MAXIMO - 1) + 1) + 1;
                                linha = aleatorio.nextInt((TAMANHO_MAXIMO - 1 - 0) + 1) + 0;
                                coluna = aleatorio.nextInt((TAMANHO_MAXIMO - 1 - 0) + 1) + 0;
                                if (ehValido(valor, linha, coluna) == true) {
                                    matr[linha][coluna] = valor;
                                    break;
                                }
                            }
                        }
                        */


