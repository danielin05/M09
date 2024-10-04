package com.project;

import java.util.concurrent.*;
import java.util.Arrays;

public class Main {

    
    private static double suma;
    private static double mitjana;
    private static double desviacio;

    public static void main(String[] args) {

        double[] dades = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("Tots els càlculs han acabat. Mostrant els resultats...");
                System.out.println("Suma: " + suma);
                System.out.println("Mitjana: " + mitjana);
                System.out.println("Desviació estàndard: " + desviacio);
            }
        });

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable tasca1 = () -> {
            try {
                System.out.println("Calculant la suma...");
                Thread.sleep(1000);
                suma = Arrays.stream(dades).sum();
                System.out.println("Suma calculada.");
                barrier.await(); 
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        Runnable tasca2 = () -> {
            try {
                System.out.println("Calculant la mitjana...");
                Thread.sleep(2000);
                mitjana = Arrays.stream(dades).average().orElse(0);
                System.out.println("Mitjana calculada.");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        Runnable tasca3 = () -> {
            try {
                System.out.println("Calculant la desviació estàndard...");
                Thread.sleep(3000);
                double mitjanaTemp = Arrays.stream(dades).average().orElse(0);
                double variancia = Arrays.stream(dades)
                        .map(d -> Math.pow(d - mitjanaTemp, 2))
                        .average()
                        .orElse(0);
                desviacio = Math.sqrt(variancia);
                System.out.println("Desviació estàndard calculada.");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        executor.submit(tasca1);
        executor.submit(tasca2);
        executor.submit(tasca3);

        executor.shutdown();
    }
}
