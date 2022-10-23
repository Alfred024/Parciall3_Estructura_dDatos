
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
    
    int nivel=0,EI=0,EF=5,EA;
    int rutaAux[];
    int indexNodoArray;
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
                System.out.print(rutaAux[vueltas]+",");
            }
        }else{
            System.out.println("No hay ruta ;(");
        }
    }
    
    void expandir(int verticeActual, int nivel, int rutaVerticeActual[]){
        int columna;
        for (columna = 0; columna < grafo2.length; columna++) {
            if(grafo2[verticeActual][columna] == 1 && !está_En_Ruta(columna, rutaVerticeActual)){
                queue.add(columna, nivel, rutaVerticeActual);
            }
        }
    }

    
    boolean está_En_Ruta(int verticeActual, int rutaVertice[]){
        boolean condicion = false;
        int index = 0;
        while(index < rutaVertice.length){
            if(rutaVertice[index] == verticeActual){
                condicion = true;
                index = rutaVertice.length;
            }
            index++;
        }
        return condicion;
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
