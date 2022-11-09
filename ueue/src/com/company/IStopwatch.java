package com.company;

public interface IStopwatch {
    void start();
    void stop();
    void pause();
    void resume();
    void reset();
    long getTime();
}
