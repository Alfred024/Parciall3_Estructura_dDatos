
package exe;

public class Exe {
    
    //int grafo[][] = {};
    int grafo[][] =     {{0,1,1,0,0}, 
                         {1,0,0,0,0}, 
                         {1,0,0,1,1}, 
                         {0,0,1,0,1},
                         {0,0,1,1,0}};
    
    
    int grafo2[][] =    {{0,1,0,0,0,0,0}, 
                         {1,0,0,1,0,0,1}, 
                         {0,0,0,0,1,1,0}, 
                         {0,1,0,0,1,1,1},
                         {0,0,1,1,0,1,0},
                         {0,0,1,1,1,0,1},
                         {0,1,0,1,0,1,0}};
    
    
    int ruta[] = new int[grafo.length];
    int rutaBacktarck[] = new int[grafo.length];
    int nivel=0,EI=2,EF=6,EA;
    Pila stack;

    public static void main(String[] args) {
        Exe DPS = new Exe();
        DPS.búsquedaDPS();
    }

    void llenarMatriz(){
        /*Debe establecer la longitud de la matriz para luego llenarla, además que también le pasamos el nodo inicial y el final*/
    }
    
    void búsquedaDPS(){
        boolean rutaEncontrada = false;
        stack = new Pila();
        Nodo auxiliar;
        stack.PUSH(EA, nivel);

        do{
            auxiliar = stack.POP();
            EA = auxiliar.value;
            nivel = auxiliar.nivel;
            ruta[nivel] = EA;
            if(EA == EF){
                rutaEncontrada = true;
            }else{
                expandir(EA, nivel+1);
            }
        }while(stack.tope != null && !rutaEncontrada);

        if(rutaEncontrada){
            System.out.println("Sí hay ruta :)");
            int vueltas;
            for (vueltas = 0; vueltas < grafo.length; vueltas++) {
                System.out.println(ruta[vueltas]+"-->");
            }
        }else{
            System.out.println("No está en ruta ;(");
        }
    }

    void expandir(int verticeActual, int nivel){
        int vueltas;
        for (vueltas = 0; vueltas < grafo.length; vueltas++) {
            if(grafo[verticeActual][vueltas] == 1 && No_en_ruta(verticeActual)){
                stack.PUSH(vueltas, nivel);
            }
        }
    }

    boolean está_enRuta(int verticeBuscado){
        int indexRuta=0;
        boolean loEncontré=false;

        for (indexRuta = 0; indexRuta < nivel; indexRuta++) {
            if(verticeBuscado == ruta[indexRuta]){
                loEncontré = true;
            }
        }
        return loEncontré;
    }
    
    boolean No_en_ruta(int p_vertice){
    int v_posi=0;
    while(v_posi<nivel && ruta[v_posi]!=p_vertice){
        v_posi++;
    }
    if(ruta[v_posi]!=p_vertice)
        return true;
    else return false;

   }
   
   /*Al parecer sí funciona este método*/
   boolean No_en_ruta2(int verticeActual){
       int index = 0;
       while(index < nivel && verticeActual != ruta[index]){
           index++;
       }
       if(ruta[index] == verticeActual){
           return false;
       }else{
           return true;
       }
   }
    

}

class Nodo{
    int value;
    int nivel;
    Nodo next;
    public Nodo(int value, int nivel) {
        this.value = value;
        this.next = null;
        this.nivel = nivel;
    }
}

class Pila{
    Nodo tope;
    void PUSH(int value, int nivel){
        Nodo nuevoNodo = new Nodo(value, nivel);
        if(tope == null){
            tope = nuevoNodo;
        }else{
            nuevoNodo.next = tope;
            tope = nuevoNodo;
        }
    }
    Nodo POP(){
        if(tope == null){
            return null;
        }else{
            Nodo nodoPOP = tope;
            tope = tope.next;
            return nodoPOP;
        }
    }

    
}
