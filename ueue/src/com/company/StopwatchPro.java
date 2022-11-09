package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class StopwatchPro extends Stopwatch implements IStopwatchPro{
    ArrayList<String> partials = new ArrayList<>();

    public StopwatchPro(String name){
        super(name);
        this.maxactions = this.maxactions + 1;
    }

    @Override
    public void runClock() {
         /*
        la variabile choose permette all'utente di interagire con il cronometro, le viene assegnato il valore 6
        per entrare in un ciclo while che impedirà all'utente di assegnare alla variabile choose un valore a cui non
        corrisponde un metodo
         */
        System.out.println("*****" + Stopwatch + "*****");
        System.out.println("-digita 1 per avviare il cronometro" + "\n" +
                "-digita 2 per stoppare il cronometro" + "\n" +
                "-digita 3 per mettere in pausa il cronometro" + "\n" +
                "-digita 4 per rimuovere la pausa al cronometro" + "\n" +
                "-digita 5 per resettare il cronometro" + "\n" +
                "-digita 6 per smettere di usare il cronometro");
        //la seguente variabile inizializza un oggetto che permetterà all'utente di assegnare valori da tastiera
        Scanner keyboard = new Scanner(System.in);

        //variabile per far smettere all'utente di usare l'interfaccia (tramite l'uscita dal ciclo while seguente)
        boolean exit = false;
        int max = maxactions - 1;
        while (!exit) {

            Print();

            //ciclo while che impedirà all'utente di assegnare a choose un valore non consentito
            while (maxactions > max) {
                maxactions = keyboard.nextInt();
            }

            //funzione che analizza il valore scelto dall'utente e gli attribuisce una funzione
            switch (maxactions) {
                case 1 : start();
                    break;
                case 2 :
                    /*
                    il metodo stop ritorna il tempo in millisecondi quindi durante la stampa viene diviso per 1000
                    in modo tale da stampare il tempo in secondi
                     */
                    stop();
                    break;
                case 3 :
                    /*
                    il metodo pausa ritorna il tempo in millisecondi quindi durante la stampa viene diviso per 1000
                    in modo tale da stampare il tempo in secondi
                     */
                    pause();
                    break;
                case 4 : resume();
                    break;
                case 5 : reset();
                    break;
                case 6 : delete();
                        break;
                case 7 :  exit = true;
                    break;
            }
            /*
            choose viene riportato a 6 per rientrare nel ciclo while precedente che permette all'utente di scegliere
            quale azione compiere
             */
            this.maxactions = max + 1;
        }
    }
    public int[] convertTime(){
        int[] time = new int[4];

        if (this.actualtime > 3600000){
            time[0] = (int) Math.floor((this.actualtime / 3600000.0));
            time[1] = (int) this.actualtime -  (time[1] * 60000);
        }
        if (this.actualtime > 60000){
            time[1] = (int) Math.floor((this.actualtime / 60000.0));
            time[2] = (int) this.actualtime -  (time[1] * 60000);
            while (time[1] >= 60){
                time[1] = time[1] - 60;
            }
        }

        if (this.actualtime > 1000) {
            time[2] = (int) Math.floor((this.actualtime / 1000.0));
            time[3] = (int) this.actualtime - (time[2] * 1000);
            while (time[2] >= 60){
               time[2] = time[2] - 60;
            }
        }

        return time;
    }

    @Override
    public void Print() {
        convertTime();
        int[] time = convertTime();
        System.out.println("*****" + Stopwatch + "*****");
        System.out.println("tempo: " + time[0] + ":" + time[1] + ":" + time[2] + "." + time[3]);
        System.out.println("parziali: " + "\n" + this.partials);
        System.out.print("stato: ");
        if (getPaused){
            System.out.print("in pausa");
        } else if (getStarted) {
            System.out.print("sta andando avanti");
        } else if (getResetted){
            System.out.print("è stato resettato");
        } else {
            System.out.print("lo devi avviare");
        }
        System.out.println();
    }

    @Override
    public void pause() {
        if (!getPaused && getStarted){
            /*
            viene calcolato inizialmente il tempo in cui è stato messo in pausa tramite la formula vista precedentemente
            per poi segnalare che il cronometro sia stato messo in pausa
             */
            this.actualtime = (long) (System.currentTimeMillis() - time - phasetime);
            System.out.println("è stato messo in pausa");
            this.getPaused = true;
            //viene salvato il momento in millisecondi in cui il cronometro viene messo in pausa
            this.millispause = System.currentTimeMillis();
            partials.add(convertTime()[0] + ":" + convertTime()[1] + ":" + convertTime()[2] + "." + convertTime()[3]);
        } else {
            if (getPaused)
                System.out.println("devi prima togliere la pausa per metterla un'altra volta");

            if (!getStarted)
                System.out.println("devi prima far iniziare il cronometro per metterlo in pausa");
        }

    }

    @Override
    public void stop() {
        super.stop();
        partials.removeAll(partials);
    }

    @Override
    public void reset() {
        super.reset();
        partials.removeAll(partials);
    }

    public void delete(){
        int position = partials.size() + 1;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("in che posizione si trova il parziale da eliminare?");
        while (position > partials.size())
          position = keyboard.nextInt();
        partials.remove(position);
        System.out.println("eliminato");
    }
}
