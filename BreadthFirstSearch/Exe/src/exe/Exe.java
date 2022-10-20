
package exe;

public class Exe {
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
    
    int nivel=0,EI=0,EF=4,EA;
    //int rutaAux[]=new int[nivel];
    int rutaAux[]={EI};
    int indexNodoArray;
    Nodo_Cola nodosNivelActual[] = new int[grafo.length];
    Cola queue;

    public static void main(String[] args) {
        Exe BFS = new Exe();
        BFS.búsquedaBPS();
    }

    void llenarMatriz(){

    }
    
    void búsquedaBPS(){
        boolean rutaEncontrada = false;
        queue = new Cola();
        Nodo_Cola auxiliar;
        //nodosNivelActual[0] = EA;
        //La ruta auxiliar la contendrá cada nodo, es necesario porque nos ayuda a tener la adminsitración de el recorrido de esa posible 
        //alternativa 
        queue.add(EA, nivel, rutaAux);

        do{
            if(queue==null){
                indexNodoArray=0;
                while(queue!=null || EA != EF){
                    auxiliar = queue.delete();
                    EA=auxiliar.value;
                    nivel=auxiliar.nivel; //¿Hacer esto es necesario??
                    //Hay que registrar que en cada Nodo que hemos encolado y vamos sacando, no esté la respuesta buscada
                    nodosNivelActual[indexNodoArray++] = auxiliar;
                }
            }

            if(EA == EF){
                rutaEncontrada = true;
            }else{
                //En el método expandir, es necesario recorrer el array nodosNivelActual, ya que como es anchura, es necesario 
                //expandir/encolar todos aquellos Nodos que no han sido explorados
                expandir(++nivel, nodosNivelActual);
            }

        }while(queue != null && !rutaEncontrada);

        if(rutaEncontrada){
            System.out.println("Sí hay ruta :)");
            int vueltas;
            for (vueltas = 0; vueltas < nivel; vueltas++) {
                System.out.println(rutaAux[vueltas]+"-->");
            }
        }else{
            System.out.println("No está en ruta ;(");
        }
    }

    //No necesita llevar la administración de un EA, porque el array de Nodos puede proporcionarnos esa información
    void expandir(int nivel, Nodo_Cola nodosEnNivel[]){
        int indexNodoArray;
        int columna;
        for (indexNodoArray = 0; indexNodoArray < nodosEnNivel.length; indexNodoArray++) {
            for (columna = 0; columna < grafo.length; columna++) {
                /*Revisamos todas las conexiones en el vertice que tenemos, si hay conexión y además, la ruta del nodo en el que estamos 
                    no tiene dicha conexión, hacemos el add*/
                if(grafo[nodosEnNivel[indexNodoArray].value][columna] == 1 && 
                No_en_ruta2(grafo[nodosEnNivel[indexNodoArray].value][columna], nodosEnNivel[indexNodoArray].rutaNodo)){
                    queue.add(grafo[nodosEnNivel[indexNodoArray].value][columna], nivel, nodosEnNivel[indexNodoArray].rutaNodo);
                }
            }
        }
    }

    /*boolean está_enRuta(int verticeBuscado){
        int indexRuta=0;
        boolean loEncontré=false;
        for (indexRuta = 0; indexRuta < nivel; indexRuta++) {
            if(verticeBuscado == ruta[indexRuta]){
                loEncontré = true;
            }
        }
        return loEncontré;
    }*/
    
    //Necesitamos más parámetros en este método para llevar la administración del lugar en el array nodo en el que estamos????
    boolean No_en_ruta(int verticeBuscado){
    int index=0;
    while(index<nivel && nodosNivelActual[index]!=verticeBuscado){
        index++;
    }
    if(nodosNivelActual[index]!=verticeBuscado){
        return true;
    }else{
        return false;
    }
   }

   boolean No_en_ruta2(int verticeBuscado, int rutaNodoActual[]){
    int index=0;
    while(index<nivel && rutaNodoActual[index]!=verticeBuscado){
        index++;
    }
    if(rutaNodoActual[index]!=verticeBuscado){
        return true;
    }else{
        return false;
    }
   }
   
}


class Nodo_Cola{
    int value;
    int nivel;
    int rutaNodo[];
    Nodo next;

    public Nodo_Cola(int value, int nivel, int ruta[]) {
        this.value = value;
        this.nivel = nivel;
        this.next = null;
        this.rutaNodo = new int[nivel+1]; 
        //Aquí sí pasa los valores del parametro ruta???
        rutaNodo = ruta;
        rutaNodo[nivel] = value;
    }
}

class Cola{
    Nodo_Cola start;
    Nodo_Cola end;

    public Cola() {
        this.start = null;
        this.end = null;
    }
    
    public void add(int value, int nivel, int ruta[]){
        Nodo nuevoNodo = new Nodo(value, nivel, ruta);
        if(start == null){
            start = end = nuevoNodo;
        }else{
            end.next = nuevoNodo;
            end = nuevoNodo;
        }
    }
    
    public Nodo_Cola delete(){
        if(start == null){
            return null;
        }else{
            Nodo temp = start;
            start = start.next;
            return temp;
        }
    }
}
