
package grafosdemo;

import java.util.Stack;

public class GrafosDemo {

    public static void main(String[] args) {
       
        Stack pila = new Stack();
       int Matriz[][] = {{0,1,1,0,0},
                         {1,0,0,1,0},
                         {1,0,0,1,0},
                         {0,1,1,0,1},
                         {0,0,0,1,0}};
    }
}

class Vertice{
    String nombre; 
    int numVertice; 
    public Vertice(String x) { 
        nombre = x; numVertice = -1; 
    } 
    public String nomVertice(){ 
        return nombre; 
    } 
    // true, si dos vértices son iguales 
    public boolean equals(Vertice n) { 
        return nombre.equals (n .nombre); 
    }
    public void asigVert(int n){
        numVertice = n;
    }
    @Override
    public String toString(){ 
        return nombre + " (" + numVertice + ")"; 
    }
    
}
class Grafo{
    boolean MatrizAdyacencia[][];
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