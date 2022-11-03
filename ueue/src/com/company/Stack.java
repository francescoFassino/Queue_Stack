package com.company;

public class Stack implements IStack{
    private Object[] stack;

    public Stack(){
        this.stack = new Object[0];
    }

    @Override
    public void push(Object o) {
        Object[] support = stack;
        stack = new Object[support.length + 1];
        for (int i = 0; i < support.length; i++){
            stack[i] = support[i];
        }
        stack[support.length] = o;
    }

    @Override
    public Object pop() {
        Object[] support = stack;
        stack = new Object[support.length - 1];
        for (int i = 0; i < stack.length; i++){
            stack[i] = support[i];
        }
        return support[support.length - 1];
    }

    @Override
    public Object top() {
        return null;
    }

    @Override
    public int size() {
        return this.stack.length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}