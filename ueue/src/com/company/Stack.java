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
        System.arraycopy(support, 0, stack, 0, support.length);
        stack[support.length] = o;
    }

    @Override
    public Object pop() {
        if(!isEmpty()) {
            Object[] support = stack;
            stack = new Object[support.length - 1];
            System.arraycopy(support, 0, stack, 0, stack.length);
            return support[support.length - 1];
        }
        return null;
    }

    @Override
    public Object top() {
        return stack[stack.length - 1];
    }

    @Override
    public int size() {
        return this.stack.length;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }else {
            return false;
        }
    }

}
