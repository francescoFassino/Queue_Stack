package com.company;


public interface IStopwatchPro extends IStopwatch {

    //converte il tempo dal formato in millisecondi al formatp in hh:mm:ss.mmm
    int[] convertTime();

    //cancella uno dei parziali salvati
    void delete();

}
