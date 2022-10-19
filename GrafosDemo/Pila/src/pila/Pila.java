/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;


    
public class Pila {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

class Nodo{
    int value;
    Nodo next;

    public Nodo(int value) {
        this.value = value;
        this.next = null;
    }
    
}


class Pila_Dinamica{
    Nodo tope;
    
    public void PUSH(int value){
        Nodo nuevoNodo = new Nodo(value);
        if(tope == null){
            tope = nuevoNodo;
        }else{
            nuevoNodo.next = tope;
            tope = nuevoNodo;
        }
    }
    
    public int POP(){
        if(tope == null){
            System.out.println("Pila vacía 2");
            return -1;
        }else{
            Nodo nodoPOP = tope;
            tope = tope.next;
            return nodoPOP.value;
        }
    }
    
    public void FREE(){
        do{
           System.out.println(tope.value);
           tope = tope.next;
        }while(tope != null);
    }
    
    public void showData(){
        if(tope == null){
            System.out.println("Pila vacía");
        }else{
            Nodo auxiliar = tope;
            
            while(auxiliar.next != null){
                System.out.println("["+auxiliar.value+"]");
                auxiliar = auxiliar.next;
            }System.out.println("["+auxiliar.value+"]");
        }
    }
    
}