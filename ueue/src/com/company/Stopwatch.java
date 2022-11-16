package com.company;

import java.util.Scanner;

public class Stopwatch implements IStopwatch{
    //nome del microfono
    String Stopwatch = "cracrapu";

    //numero di azioni eseguibili
    int maxactions = 7;

    //la variabile actualtime serve a tenere conto del tempo da stampare
    long actualtime = 0;
    //la variabile time serve a salvare i millisecondi in cui si avvia il cronometro
    double time;

    /*
    le variabili get started e get paused servono a tenere conto di quando il cronometro
    è in funzione (getStarted) o in pausa(getPaused)
    */
    boolean getStarted = false;
    boolean getPaused = false;

    //la variabile get resetted segna
    boolean getResetted = false;
    //la variabile millispause serve a salvare i millisecondi in cui l'utente pausa il cronometro
    double millispause = 0;

    //la variabile phasetime serve a calcolare i millisecondi totali in cui il cronometro è stato in pausa
    double phasetime = 0;

    public Stopwatch(String name){
        this.Stopwatch = name;
    }


    //il metodo Print() stampa tutti i dettagli del cronometro
    public void Print(){
        System.out.println("*****" + Stopwatch + "*****");
        System.out.println("tempo: " + getTime());
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
    //il metodo runClock() fa da interfaccia per l'utente per permettere il funzionamento del cronometro
    public void runClock(){
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
                case 6 :  exit = true;
                        break;
            }
            /*
            choose viene riportato a 6 per rientrare nel ciclo while precedente che permette all'utente di scegliere
            quale azione compiere
             */
            this.maxactions = max + 1;
        }
    }

    //il metodo start avvia il cronometro
    public void start(){
        /*
        se il cronometro non è già stato avviato o è stato recentemente bloccato allora verrà avviato,
        in caso contrario stamperà un messaggio di errore
         */
        if (!getStarted) {
            this.millispause = 0;
            this.phasetime = 0;

            /*
            viene salvato sulla variabile time il momento in cui è stato avviato il cronometro e tramite
            una variabile di tipo boolean viene segnalato che il cronometro sia in esecuzione
             */
            this.time = System.currentTimeMillis();
            this.getStarted = true;
        }

    }

    /*
    il metodo stop ferma il cronometro del tutto e riporta il tempo (in millisecondi) in cui è stato bloccato,
    una volta bloccato il cronometro non può ripartire dal momento in cui è stato fermato ma dovrà ripartire da 0
    con il comando start
     */
    public void stop(){
        /*
        se il cronometro è stato avviato e non è stato già bloccato o messo in pausa allora il metodo eseguirà la sua
        funzione, in caso contrario analizzerà il motivo per cui non può essere eseguito e lo comunicherà all'utente
         */
        if (getStarted && !getPaused) {
            /*la seguente variabile calcola il valore da ritornare successivamente, viene eseguito prima il calcolo
            sottraendo al valore attuale dei millisecondi il valore dei millisecondi d'inizio e il tempo totale in cui
            è stato messo in pausa il cronometro, successivamente avviene una stampa che segnala che il cronometro sia
            stato fermato, a seguire viene segnalato che il cronometro sia stato messo in pausa e in fine viene ritornando
            il valore calcolato precedentemente (si calcola per prima cosa il valore per una maggiore precisione del
            tempo di fermata)
             */
            this.actualtime = (long) (System.currentTimeMillis() - time - phasetime);
            this.getStarted = false;
        }
    }
    /*
    il metodo pause mette in pausa il cronometro e ne riporta il tempo (in millisecondi) in cui è stato messo in pausa,
    una volta fermato il cronometro può ripartire dal momento in cui è stato fermato tramite il comando Unpause
     */
    public void pause(){
        if (!getPaused && getStarted){
            /*
            viene calcolato inizialmente il tempo in cui è stato messo in pausa tramite la formula vista precedentemente
            per poi segnalare che il cronometro sia stato messo in pausa
             */
            this.actualtime = (long) (System.currentTimeMillis() - time - phasetime);
            this.getPaused = true;
            //viene salvato il momento in millisecondi in cui il cronometro viene messo in pausa
            this.millispause = System.currentTimeMillis();
        }
    }


    //il metodo unpause fa uscire il cronometro dal momento di pausa riprendendo dal tempo in cui è stato fermato
    public void resume(){
        /*
        il metodo eseguirà la sua funzione se è stato messo in pausa e se sia già stato avviato, altrimenti stamperà
        il messaggio di errore corrispondente al problema
        */
        if(getPaused && getStarted){
            /*
            il metodo incrementa il tempo in cui è stato messo in pausa calcolando il tempo totale di tutte le pause, in caso
            sia stato resettato non verrà incrementato ma verranno azzerati tutti i valori
             */
            if (!getResetted) {
                this.phasetime = phasetime + (System.currentTimeMillis() - millispause);
            } else {
                this.actualtime = 0;
                this.time = System.currentTimeMillis();
                this.phasetime = 0;
                this.millispause = 0;
                this.getResetted = false;
            }
            //segnala che il cronometro non sia più in pausa
            this.getPaused = false;
        }
    }

    //il metodo reset azzera il cronometro
    public void reset(){
        if (getPaused && getStarted && !getResetted) {
            this.getResetted = true;
        }

    }

    public long getTime(){
        return this.actualtime;
    }
}