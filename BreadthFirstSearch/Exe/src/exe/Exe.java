
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
    int rutaAux[];
    int indexNodoArray;
    Nodo nodosNivelActual[] = new Nodo[grafo.length];
    Cola queue;

    public static void main(String[] args) {
        Exe BFS = new Exe();
        BFS.búsquedaBFS();
    }
    
    void búsquedaBFS(){
        Nodo auxiliar;
        boolean rutaEncontrada = false;
        queue = new Cola();
        queue.add(EI, nivel, rutaAux);
        
        do{
            auxiliar = queue.delete();
            EA = auxiliar.value;
            nivel = auxiliar.nivel;
            rutaAux = auxiliar.rutaVertice;
            
            if(EA == EF){
                rutaEncontrada = true;
            }else{
                expandir(EA,nivel+1, rutaAux);
            }
        }while(queue.start != null && !rutaEncontrada);
        
        
        if(rutaEncontrada){
            System.out.println("Sí hay ruta :D");
            int vueltas;
            for (vueltas = 0; vueltas < nivel+1; vueltas++) {
                System.out.print(rutaAux[vueltas]+", ");
            }
        }else{
            System.out.println("No hay ruta ;(");
        }
    }
    
    void expandir(int verticeActual, int nivel, int rutaVerticeActual[]){
        int columna;
        for (columna = 0; columna < grafo.length; columna++) {
            if(grafo[verticeActual][columna] == 1 && No_en_ruta(grafo[verticeActual][columna], rutaVerticeActual)){
                queue.add(grafo[verticeActual][columna], nivel, rutaVerticeActual);
            }
        }
    }

    boolean No_en_ruta(int verticeActual, int rutaVertice[]){
        int index=1; //Empieza en uno porque se salta la primera posición ,ya que esta representa al primer primer nodo
        while(index<rutaVertice.length && rutaVertice[index]!=verticeActual){
            index++;
        }
        if(rutaVertice[index] == verticeActual){
           return false;
        }else{
            return true;
        }
    }
    
    boolean está_enRuta(int vertice, int rutaVertice[]){
        int index;
        boolean condicion=false;
        for (index = 0; index < rutaVertice.length; index++) {
            if(vertice == rutaVertice[index]){
                index =rutaVertice.length+1;
            }
            System.out.println("jasda");
        }
        
        return index == rutaVertice.length+1;
    }
    
}   


class Nodo{
    int value;
    int nivel;
    int rutaVertice[];
    Nodo next;

    public Nodo(int value, int nivel, int ruta[]) {
        this.value = value;
        this.nivel = nivel;
        this.next = null;
        this.rutaVertice = new int[nivel+1];
        int vueltas;
        if(nivel != 0){
            for(vueltas = 0; vueltas < ruta.length; vueltas++) {
                rutaVertice[vueltas] = ruta[vueltas];
            }rutaVertice[nivel] = value;
        }else{
            rutaVertice[nivel] = value;
        }
    }
}

class Cola{
    Nodo start;
    Nodo end;

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
    
    public Nodo delete(){
        if(start == null){
            return null;
        }else{
            Nodo temp = start;
            start = start.next;
            return temp;
        }
    }
}
