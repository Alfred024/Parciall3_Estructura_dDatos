
package deep.first.search;

public class DeepFirstSearch {
    
    int matriz[][];
    int ruta[];
    int nivel;
    int EstadoIni, EstadoFin;
    
    public static void main(String[] args) {
       DeepFirstSearch x = new DeepFirstSearch();
       
    }
    
    void read(int tamaño){
        int matriz[][] = new int[tamaño][tamaño];
        int ruta[] = new int[tamaño];
        nivel = 0;
        
        for (int filas = 0; filas < tamaño; filas++) {
            for (int columnas = 0; columnas < tamaño; columnas++) {
                matriz[filas][columnas] = 0; //---> valores por teclado
            }
        }
    }
    
    void procesar(){
        Pila stack = new Pila();
        Nodo temp;
        stack.PUSH(EstadoIni, 0);
        
        do{
            //Este método debe retornar un nodo
            temp = stack.POP2();
            EstadoIni = temp.value;
            nivel = temp.nivelP;
        }while(stack.tope != null);
    }
    
    void expandir(int vertice, int novelDeHijos){
        
    }
}

class Nodo{
    int value;
    int nivelP;
    Nodo next;

    public Nodo(int value, int nivelP) {
        this.value = value;
        this.next = null;
        this.nivelP = nivelP;
    }
    
}


class Pila{
    Nodo tope;
    
    public void PUSH(int value, int nivel){
        Nodo nuevoNodo = new Nodo(value, nivel);
        if(tope == null){
            tope = nuevoNodo;
        }else{
            nuevoNodo.next = tope;
            tope = nuevoNodo;
        }
    }
    public int POP(){
        if(tope == null){
            return -1;
        }else{
            Nodo nodoPOP = tope;
            tope = tope.next;
            return nodoPOP.value;
        }
    }
    public Nodo POP2(){
        if(tope == null){
            return null;
        }else{
            Nodo nodoPOP = tope;
            tope = tope.next;
            return nodoPOP;
        }
    }
    public void FREE(){
        do{
           System.out.println(tope.value);
           tope = tope.next;
        }while(tope != null);
    }
    
}
