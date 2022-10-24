
package árbol_binario2;

public class Árbol_Binario2 {

    public static void main(String[] args) {
        Árbol x = new Árbol();
        x.in(100);x.in(200);x.in(50);
        x.in(25); x.in(65);x.in(35);
        x.in(60); x.in(52); x.in(64);
        x.in(68); x.in(62); x.in(61);
        x.in(63);
        
        
        System.out.println("\nárbol");
        System.out.println("Raíz: "+x.raiz.value); //100
        System.out.println(x.raiz.der.value); //200
        System.out.println(x.raiz.izq.value); //50
        System.out.println(x.raiz.izq.izq.value); //25
        System.out.println(x.raiz.izq.der.value); //65
        System.out.println(x.raiz.izq.der.der.value); //68
        System.out.println(x.raiz.izq.izq.der.value); //35
        System.out.println(x.raiz.izq.der.izq.value); //60
        System.out.println(x.raiz.izq.der.izq.izq.value); //52
        System.out.println(x.raiz.izq.der.izq.der.value);//64
        
        /*System.out.println("\nCASO #1");
        x.out(64);*/
        
        System.out.println("\nCASO #2");
        x.out(65);
        System.out.println(x.raiz.izq.der.value); //64
        System.out.println(x.raiz.izq.der.izq.der.value); //62
        
    }
    
}

class Nodo{
    Nodo izq, der;
    int value;

    public Nodo(int value){
        this.value = value;
        this.izq = null;
        this.der = null;
    }
}

class Árbol{
    Nodo raiz;

    void in(int value){
        Nodo newNodo = new Nodo(value);
        if(raiz == null){
            raiz = newNodo;
        }else{
            Nodo temp = buscarNodo(value);
            if(value < temp.value){
                temp.izq = newNodo;
            }else{
                temp.der = newNodo;
            }
        }
    }

    Nodo buscarNodo(int searched){
        Nodo vigilante=raiz;
        Nodo aux = raiz;
        while(aux != null && aux.value!=searched){
            vigilante = aux;
            if(searched < aux.value){
                aux = aux.izq;
            }else{
                aux = aux.der;
            }
        }
        return vigilante;
    }

    Nodo anteOpost(Nodo partida){
        Nodo resultado=partida;
        if(resultado.izq != null){
            resultado = resultado.izq;
            while(resultado.der!=null){
                resultado = resultado.der;
            }
        }else{
            resultado = resultado.der;
            while(resultado.izq!=null){
                resultado = resultado.izq;
            }
        }
        return resultado;
    }

    void out(int toDelete){
        Nodo temp = buscarNodo(toDelete);
        boolean izquierda;
        
        if(casoHojas(temp, toDelete)){
            if(toDelete < temp.value){
                temp.izq = null;
            }else{
                temp.der = null;
            }
        }else{
            //CASO #2: Ignora si es que el nodo a borrar tiene uno o más hijos, lo que hace es buscar el antecesor(o postdecesor, en caso que no haya camino a la izquierda)
            //y reemplaza elvalor de este (antecesor/postdecesor) en el vértice actual, además que lleva el control de ese vértice reeemplazo, por si tenía hijos, reasignar
            //los punteros de manera que la figura del árbol siga la estructura lógica
            Nodo replace;
            if(toDelete < temp.value){
                temp = temp.izq;
            }else{
                temp = temp.der;
            }
            replace = anteOpost(temp);
            
            if(replace.value < temp.value){
                izquierda = true; //---> Buscó al antecesor
            }else{
                izquierda = false; //---> Buscó al postdecesor
            }
            
            Nodo nuevasRamas = buscarNodo(replace.value);
            if(izquierda){
                nuevasRamas.der = replace.izq;
            }else{
                nuevasRamas.izq = replace.der;
            }
            temp.value = replace.value;
        }
        
    }

    boolean casoHojas(Nodo padre, int toDelete){
        boolean esHoja = false;
        if(toDelete < padre.value){
            if(padre.izq.izq == null && padre.izq.der==null){
                esHoja = true;
            }
        }else{
            if(padre.der.izq == null && padre.der.der==null){
                esHoja = true;
            }
        }
        return esHoja;
    }

    /*boolean esMenor(Nodo padre){
        boolean izquierda = false;
        if()
        return izquierda;
    }*/
    
}



///CASO ELIMINAR RAÍZ (Incompleto)
        /*if(temp == raiz){
            Nodo replace = anteOpost(temp);//valor = 200
            temp = buscarNodo(replace.value);
            //Hay que analizar si el valor del real buscado viene de izquierda o de derecha
            if(replace.value<temp.value){
                temp.izq = temp.izq.der; //Postdecesor
            }else{ 
                temp.der = temp.der.izq; //Antecesor
            }
            raiz.value = replace.value;
        }*/

/*System.out.println("\nMétodo OUT");
        x.out(100);
        System.out.println("Raíz: "+x.raiz.value); //55
        System.out.println(x.raiz.izq.der.value); //53
        System.out.println(x.raiz.izq.der.izq.value);//52
        //System.out.println(x.raiz.izq.der.izq.izq.value);//null
        System.out.println(x.raiz.izq.der.der.value);//54*/