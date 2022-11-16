package com.company;

public class Main {

    private static void exampleQueue(IQueue q) {
        System.out.print("Queue: ");
        StopwatchPro stopwatch = new StopwatchPro("cronometro");
        stopwatch.start();
        for (int i = 0; i < 100000; i++){
            q.enqueue(i);
        }
        for(int i = 0; i < 50000; i++){
            q.dequeue();
        }
        for(int i = 0; i < 50000; i++){
            q.enqueue(i);
        }
        stopwatch.stop();
        System.out.println(stopwatch.PrintTime());

}

    private static void exampleStack(IStack s) {
        System.out.print("Stack: ");
        StopwatchPro stopwatch = new StopwatchPro("cronometro");
        stopwatch.start();
        for (int i = 0; i < 100000; i++){
            s.push(i);
        }
        for(int i = 0; i < 50000; i++){
            s.pop();
        }
        for(int i = 0; i < 50000; i++){
            s.push(i);
        }
        stopwatch.stop();
        System.out.println(stopwatch.PrintTime());
    }

    public static void main(String[] args) {
        IQueue q = new Queue();
        IStack s = new Stack();
        exampleQueue(q);
        exampleStack(s);
    }


}
