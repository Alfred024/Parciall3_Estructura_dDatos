
package exe;

public class Exe {
    int  a_matriz[][] = {{0,1,0,0,0,0,0}, 
                         {1,0,0,1,0,0,1}, 
                         {0,0,0,0,1,1,0}, 
                         {0,1,0,0,1,1,1},
                         {0,0,1,1,0,1,0},
                         {0,0,1,1,1,0,1},
                         {0,1,0,1,0,1,0}};
    int ruta[] = new int[a_matriz.length];
    int a_nivel, a_EI=5, a_EF=0, a_EA;
    Pila a_oPila;
    Cola queue;
    
    public static void main(String[] args) {
        Exe oGrafos = new Exe();
        oGrafos.procesa();
   }

   void procesa(){
    queue = new Cola();
    boolean banderaExito=false;
    Nodo temp;
    queue.add(a_EI,0, a_matriz.length);
    do{
        temp = queue.delete();
        a_EA=temp.value;
        a_nivel=temp.nivel;
        ruta[a_nivel]=a_EA;

        while(queue != null){
            temp = queue.delete();
            a_EA=temp.value;
            a_nivel=temp.nivel;
            temp.ruta[a_nivel]=a_EA;

            if(a_EA==a_EF){
                queue=null;
            }
        }

        if(a_EA==a_EF){
            banderaExito=true;
        }else{
            expandir(a_EA,a_nivel+1);
        }
    }while(queue!=null && !banderaExito);

    if(banderaExito){
        System.out.println("Si hay rutaa: ");
        int index;
        for(index=0; index<=a_nivel; index++){
            System.out.print("["+ruta[index]+"]");
        }
    }else{
        System.out.println("No hay ruta");
    }        
   }

   void expandir(int p_vertice, int p_nivelHijo){
    int v_columna;    
    for(v_columna=0; v_columna<a_matriz.length; v_columna++){
        if(a_matriz[p_vertice][v_columna]==1 && No_en_ruta(v_columna)){
            a_oPila.push(v_columna,p_nivelHijo);
        }
    }
   }
   boolean No_en_ruta(int p_vertice){
    int v_posi=0;
    while(v_posi<a_nivel && ruta[v_posi]!=p_vertice){
        v_posi++;
    }
    if(ruta[v_posi]!=p_vertice)
        return true;
    else return false;
   }
   void completarRuta(){
    
   }

}
class Pila{
    nodo a_nodoTope;
    Pila(){
        a_nodoTope=null;
    }
    void push(int p_datoN, int p_nivNodo){
        nodo v_nTemp;
        v_nTemp = new nodo(p_datoN, p_nivNodo);
        if(a_nodoTope==null)
            a_nodoTope = v_nTemp;
        else{
            v_nTemp.a_nextN=a_nodoTope;
            a_nodoTope=v_nTemp;
        }
    }
    nodo pop(){
        nodo v_aux;
        v_aux=a_nodoTope;
        if(a_nodoTope!=null)
            a_nodoTope=a_nodoTope.a_nextN;
        return v_aux;
    }
}
class nodo{
    int a_info;
    int a_nivel;
    nodo a_nextN;
    nodo(int p_nodoinf, int p_nivNodo){
        a_info=p_nodoinf;
        a_nextN=null;
        a_nivel=p_nivNodo;
        
    }
}


 class Nodo{
     int value;
     int nivel;
     int ruta[];
     Nodo next;

     public Nodo(int value, int nivel, int grafoLength) {
         this.value = value;
         this.nivel = nivel;
         this.ruta = new int[grafoLength];
         this.next = null;
     }
 }

 class Cola{
     Nodo start;
     Nodo end;

     public Cola() {
         this.start = null;
         this.end = null;
     }
    
     public void add(int value, int nivel, int grafoLength){
         Nodo nuevoNodo = new Nodo(value, nivel, grafoLength);
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


//  boolean No_en_ruta2(int verticeActual){
//     int index = 0;
//     while(index < a_nivel && verticeActual != ruta[index]){
//         index++;
//     }
//     if(ruta[index] == verticeActual){
//         return false;
//     }else{
//         return true;
//     }
// }