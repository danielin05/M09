package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {

    
    static class ParkingLot {
        private final Semaphore semaphore;

        public ParkingLot(int capacitat) {
            semaphore = new Semaphore(capacitat);
        }

        public void entraCotxe(String cotxe) {
            try {
                if (semaphore.availablePermits() == 0) {
                    System.out.println(cotxe + " està esperant perquè l'aparcament està ple.");
                }
                semaphore.acquire(); 
                System.out.println(cotxe + " ha entrat a l'aparcament.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void surtCotxe(String cotxe) {
            semaphore.release(); 
            System.out.println(cotxe + " ha sortit de l'aparcament.");
        }
    }

    public static void main(String[] args) {

        ParkingLot parking = new ParkingLot(3);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 1; i <= 10; i++) {
            final String cotxe = "Cotxe " + i;
                executor.submit(() -> {
                parking.entraCotxe(cotxe); 
                try {
                                Thread.sleep((long) (Math.random() * 5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                parking.surtCotxe(cotxe); 
            });
        }

        executor.shutdown();
    }
}
