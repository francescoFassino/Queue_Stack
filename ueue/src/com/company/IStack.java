package com.company;

/**
 * Interface for the <em>stack</em> data structure storing {@link Object}
 * references.
 *
 * @version 0.1
 * @author Michele Schimd
 *
 * @see IQueue
 */

public interface IStack {
    void push(Object o);
    Object pop();
    Object top();
    int size();
    boolean isEmpty();
}
