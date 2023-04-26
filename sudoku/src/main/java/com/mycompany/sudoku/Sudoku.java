/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sudoku;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
/**
 *
 * @author felip
 */
public class Sudoku {

    public static void main(String[] args) {
        
        //Escolhendo a opção pra iniciar o jogo
        JCheckBox check = new JCheckBox("Confirmar");
        Object[] options = {check,"Jogo aleatório", "Definir jogo"};
        int x = JOptionPane.showOptionDialog(null, "Confime e selecione uma opção:", "Olá, seja bem vindo ao sudoku!",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        
        if(check.isSelected()){
            //System.out.println("Selecionei o " + options[x]);
            if(x == 1){//selecionou o jogo aleatorio
                String quantidade = JOptionPane.showInputDialog( "Digite a quantidade de números prar de sorteado: ");
                
            }
            if(x == 2){//selecionou o definir jogo
                System.out.println("Nenhum ");
            }
        }
        else{
            System.out.println("Nenhum selecionado");
        }
    
    }
}
