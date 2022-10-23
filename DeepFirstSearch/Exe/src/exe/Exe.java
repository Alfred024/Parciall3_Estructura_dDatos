
package exe;



public class Exe {
    int  grafo[][] =    {{0,1,0,0,0,0,0}, 
                         {1,0,0,1,0,0,1}, 
                         {0,0,0,0,1,1,0}, 
                         {0,1,0,0,1,1,1},
                         {0,0,1,1,0,1,0},
                         {0,0,1,1,1,0,1},
                         {0,1,0,1,0,1,0}};
    int ruta[] = new int[grafo.length];
    int nivel, EI=5, EF=0, EA;
    Pila stack;
    
    public static void main(String[] args) {
        Exe x = new Exe();
        x.procesa();
   }

   void procesa(){
    stack=new Pila();
    boolean banderaExito=false;
    nodo temp;
    int tempRuta;
    stack.push(EI,0);

    do{
        temp=stack.pop();
        EA=temp.value;
        nivel=temp.nivel;
        ruta[nivel]=EA;
        if(EA==EF)
            banderaExito=true;
        else
            expandir(EA,nivel+1);
    }while(stack.tope!=null && !banderaExito);

    if(banderaExito){
        System.out.println("Si hay rutaa: ");
        for(tempRuta=0; tempRuta<=nivel; tempRuta++){
            System.out.print(ruta[tempRuta]+",");
        }
    }else{
        System.out.println("No hay ruta");
    }       

   }
   void expandir(int verticeActual, int nivelActual){
    int columnaGrafo;    
    for(columnaGrafo=0; columnaGrafo<grafo.length; columnaGrafo++){
        if(grafo[verticeActual][columnaGrafo]==1 && No_en_ruta2(columnaGrafo)){
            stack.push(columnaGrafo,nivelActual);
        }
    }
   }
   boolean No_en_ruta(int verticeActual){
    int index=0;
    while(index<nivel && ruta[index]!=verticeActual){
        index++;
    }
    if(ruta[index]!=verticeActual){
         return true;
    }else{
        return false;
    }
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
class Pila{
    nodo tope;
    Pila(){
        tope=null;
    }
    void push(int value, int nivel){
        nodo temp;
        temp = new nodo(value, nivel);
        if(tope==null)
            tope = temp;
        else{
            temp.next=tope;
            tope=temp;
        }
    }
    nodo pop(){
        nodo temp;
        temp = tope;
        if(tope!=null)
            tope=tope.next;
        return temp;
    }
}
class nodo{
    int value;
    int nivel;
    nodo next;
    nodo(int value, int nivel){
        this.value=value;
        this.next=null;
        this.nivel=nivel;
        
    }
}