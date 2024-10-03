package com.project;

import java.util.concurrent.*;

public class Main {

    // Clase per encapsular els resultats
    static class Resultats {
        String result1 = "";
        String result2 = "";
        String result3 = "";
    }

    public static void main(String[] args) {

    Resultats resultats = new Resultats();

    // Creem un CyclicBarrier per a 3 fils
    CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
        @Override
        public void run() {
            System.out.println("Tots els microserveis han acabat. Combinant els resultats...");
            String resultatFinal = resultats.result1 + " " + resultats.result2 + " " + resultats.result3;
            System.out.println("Resultat final: " + resultatFinal);
        }
    });
        // Executor per gestionar els fils
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable tasca1 = () -> {
            try {
                System.out.println("N'hem a processar dades...");
                Thread.sleep(1000);
                resultats.result1 = "Resultat de la tasca 1";
                System.out.println("Completada la tasca1");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        Runnable tasca2 = () -> {
            try {
                System.out.println("Seguim processant dades...");
                Thread.sleep(2000);
                resultats.result2 = "Resultat de la tasca 2";
                System.out.println("Completada la tasca2");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        Runnable tasca3 = () -> {
            try {
                System.out.println("Estem terminant de processar dades...");
                Thread.sleep(3000);
                resultats.result3 = "Resultat de la tasca 3";
                System.out.println("Completada la tasca3");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        // Enviem les tasques al ExecutorService
        executor.submit(tasca1);
        executor.submit(tasca2);
        executor.submit(tasca3);

        // Tanquem l'executor quan hagi acabat
        executor.shutdown();
        
    }
}