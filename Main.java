//Trabajar con referencias es de las mejores opciones en el momento de 
//manipular grandes volúmenes de datos.

import java.lang.Math;

public class Main {

    public static int counter;

    public static void main(String[] args) {
        
        //Se fabrica la lista enlazada declarando un head (creaLista)
        //El método creaLista enlaza consecutivamente n elementos
        //utilizando la referencia next.
        //creaLista recibe como parámetro un objeto de tipo ListNode.
        ListNode listNode = creaLista(3);

        //El método getLista retorna hasta 30 elementos de la lista.
        //Recibe como parámetro un objeto de tipo ListNode.
        System.out.println("LISTA ORIGINAL: "+getLista(listNode));

        //El método invertListNode retorna un objeto de tipo ListNode.
        //Recibe como parámetro el objeto listNode head que se requiere
        //invertir.
        listNode=invertListNode1(listNode);
        System.out.println("LISTA INVERTIDA: "+getLista(listNode));

        //El método toString en la clase ListNode retorna los valores
        //de val y next.val.
        //Localizo el elemento en la mitad de la lista.
        ListNode mitad=findMidListNode(listNode);
        System.out.println("MITAD DE LISTA: "+mitad.toString());

        //El método deleteDuplicatesListNode elimina duplicados.
        //Recibe como parámetro el primer nodo de tipo ListNode.
        addLastListNode(listNode, 15);
        addLastListNode(listNode, 15);
        addLastListNode(listNode, 15);
        addLastListNode(listNode, 33);
        addLastListNode(listNode, 15);
        addLastListNode(listNode, 33);
        addLastListNode(listNode, 33);
        System.out.println("LISTA CON DUPLICADOS: "+getLista(listNode));

        deleteDuplicatesListNode(listNode);
        System.out.println("LISTA SIN DUPLICADOS: "+getLista(listNode));
    }

    public static ListNode creaLista(int elements){
        //hago un linkeo de un determinado número de elementos.
        if(elements<=0){
            elements=10;
        }

        ListNode firstElement = new ListNode((int)(Math.random()*100));
        ListNode previousElement;

        previousElement=firstElement;
        
        for(int x=0;x<elements-1;++x){
            ListNode currentElement=new ListNode((int)(Math.random()*100));
            previousElement.next=currentElement;
            previousElement=currentElement;
        }

        return firstElement;
    }

    public static String getLista(ListNode listNode){
        //Concateno hasta 30 elementos de la lista en un string.
        ListNode currentElement = listNode;
        String list="";
        
        for(int x=0;x<30&&currentElement!=null;++x){
            list=list+currentElement.val+",";
            currentElement=currentElement.next;
        }

        return list;
    }

    public static ListNode invertListNode1(ListNode oldFirstListNode){
        ListNode newFirstListNode;
        ListNode bufferListNode;

        bufferListNode=null;

        for(;oldFirstListNode.next!=null;){
            newFirstListNode=oldFirstListNode;
            oldFirstListNode=oldFirstListNode.next;
            newFirstListNode.next=bufferListNode;
            bufferListNode=newFirstListNode;
        }

        oldFirstListNode.next=bufferListNode;
        
        return oldFirstListNode;
    }

    public static ListNode invertListNode(ListNode oldFirstListNode){
        ListNode oldReference;
        ListNode oldPreviousLast;
        ListNode oldLast;
        ListNode newFirst;
        boolean isFirstElement;

        //Parto de la premisa de que mi último elemento de la anterior lista
        //es mi primer elemento de la nueva lista.
        newFirst=oldFirstListNode;

        //Esta bandera apoya más adelante a determinar mi nuevo primer elemento.
        isFirstElement=true;

        //Mientras tengo elemento referenciados a mi primera lista, itero.
        //Hace las veces de un stack.
        while(oldFirstListNode.next!=null) {

            oldLast=oldFirstListNode;
            oldPreviousLast=oldFirstListNode;
            oldReference=oldFirstListNode;

            //Hago shift de la lista, hasta encontrar el último y
            //penúltimo elemento de la lista anterior.
            while(oldReference.next!=null) {
                oldPreviousLast=oldReference;
                oldLast=oldReference.next;
                oldReference=oldReference.next;
            }

            //Capturo mi nuevo primer elemento, es decir, el
            //último elemento de la lista original
            if(isFirstElement){
                isFirstElement=false;
                newFirst=oldLast;
            }
    
            //Linkeo en el orden inverso, es decir, de último a penúltimo
            //elemento.
            oldLast.next=oldPreviousLast;

            //Esta sentencia cobra mayor importancia cuando se trata del
            // primer elemento de la lista antigua.
            oldPreviousLast.next=null;
        }
        
        return newFirst;
    }

    public static ListNode findMidListNode(ListNode firstListNode){
        ListNode referenceListNode;
        int y;
        int x;

        referenceListNode=firstListNode;
        x=0;
        y=0;

        //Se contabiliza el número de elementos enlazados.
        for(;referenceListNode.next!=null;++x){
            referenceListNode=referenceListNode.next;
        }

        //Calculo el punto medio.
        x=x/2;

        //Regreso al inicio de la lista.
        referenceListNode=firstListNode;

        //Avanzo en la lista hasta el punto medio.
        for(;y<x;++y){
            referenceListNode=referenceListNode.next;
        }

        return referenceListNode;
    }

    public static void deleteDuplicatesListNode(ListNode firstListNode){
        ListNode targetReferenceListNode;
        ListNode iteratorReferenceListNode;
        ListNode prevIterReferenceListNode;
        ListNode bufferListNode;
        boolean movimientos;

        //Este do-while apoya a recorrer la lista desde el principio, siempre que
        //haya algún movimiento. 
        do{

            //Me apoyo de un apuntador auxiliar targetReferenceListNode para no
            //perder mi referencia inicial.
            targetReferenceListNode=firstListNode;

            //Asumo que no habrá movimiento en este shift de la lista.
            movimientos=false;
        
            //Este primer for determina el elemento de la lista objetivo a comparar.
            for(;targetReferenceListNode.next!=null;){
                //Defino prevIterReferenceListNode para no perder la referencia de mi
                //penúltimo elemento evaluado.
                prevIterReferenceListNode=targetReferenceListNode;
                iteratorReferenceListNode=targetReferenceListNode.next;

                //Este segundo for itera a partir del elemento siguiente del for objetivo.
                for(;iteratorReferenceListNode.next!=null;){
                    //Comparo cada elemento de la lista vs el objetivo previamente planteado. 
                    if(targetReferenceListNode.val==iteratorReferenceListNode.val){
                        //Dejo que el stackTrace se ocupe del elemento repetido.
                        prevIterReferenceListNode.next=iteratorReferenceListNode.next;
                        //Pongo mi bandera de movimientos en true
                        movimientos=true;
                    }
                    //muevo mis referencias "un paso adelante"
                    prevIterReferenceListNode=prevIterReferenceListNode.next;
                    iteratorReferenceListNode=iteratorReferenceListNode.next;
                }

                //Declaro un buffer para observar un caso particular.
                //El caso particula es cuando existen dos elementos repetidos consecutivos
                //y además de consecutivos, se presentan al final de la lista.
                bufferListNode=targetReferenceListNode;
                targetReferenceListNode=targetReferenceListNode.next;

                //Si se presenta el caso peculiar previamente planteado,
                //Dejo que el stackTrace se ocupe del repetido.
                if(targetReferenceListNode.next==null&&targetReferenceListNode.val==bufferListNode.val){
                    bufferListNode.next=null;
                }
            }
        }while(movimientos);
    }

    public static void addLastListNode(ListNode firstListNode,int val){
        //Este método apoya a hacer la operación adición sobre la lista enlazada.
        ListNode referenceListNode;

        //Siempre me apoyo de otro apuntador para no perder la referencia.
        //En un inicio, mi apuntador de apoyo referenceListNode apunta hacia 
        //el primer elemento de la lista.
        referenceListNode=firstListNode;

        //Recorro la lista en búsqueda del último elemento.
        for(;referenceListNode.next!=null;){
            referenceListNode=referenceListNode.next;
        }

        //Referencío una nueva instancia de ListNode al next de mi último elemento.
        referenceListNode.next=new ListNode(val);
    }

}
//GRACIAS.