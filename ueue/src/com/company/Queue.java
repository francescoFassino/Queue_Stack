package com.company;

public class Queue implements IQueue{

    Object[] queue;

    public Queue(){
        queue = new Object[0];
    }

    /**
     * meotodo che rialloca l'array
     * se il parametro boolean è true l'array verrà aumentato di 1
     * se il parametro boolean è false l'array verrà diminuito di 1
     * @param bool
     * @return
     */
    private Object[] reAlloc(boolean bool){
        Object[] newArray;
        if(bool){
            newArray = new Object[queue.length + 1];
        }else{
            newArray = new Object[queue.length - 1];
        }
        return newArray;
    }

    /**
     * metodo che aggiunge alla coda un oggetto o
     * @param o
     */
    public void enqueue(Object o){
        reAlloc(true);
    }

    /**
     * metodo che toglie dalla coda l'oggetto o
     * @return
     */
    public Object dequeue(){

    }

    /**
     * meotodo che prende il primo elemento della coda
     * @return
     */
    public Object front(){

    }

    /**
     * metodo che restituisce la lunghezza della coda
     * @return
     */
    public int size(){
        return queue.length;
    }

    /**
     * metodo che verifica se la coda è vuota e ritorna true, altrimenti false
     * @return
     */
    public boolean isEmpty(){

    }


}
