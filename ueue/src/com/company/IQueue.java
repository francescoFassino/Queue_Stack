package com.company;
/**
 * Interface for the <em>queue</em> data structure storing {@link Object}
 * references.
 *
 * @author francesco.fassino
 * @version 0.1
 * @see IStack
 */
public interface IQueue {
    void enqueue(Object o);

    Object dequeue();

    Object front();

    int size();

    boolean isEmpty();
}
