package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {

    static class WebPage {
        private final Semaphore semaphore;

        public WebPage(int maxConnexions) {
            semaphore = new Semaphore(maxConnexions); 
        }

            public void connectUser(String user) {
            try {
                if (semaphore.availablePermits() == 0) {
                    System.out.println(user + " està esperant perquè s'han superat les connexions màximes.");
                }
                semaphore.acquire(); 
                System.out.println(user + " s'ha connectat a la pàgina.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

            public void disconnectUser(String user) {
            semaphore.release(); 
            System.out.println(user + " s'ha desconnectat de la pàgina.");
        }
    }

    public static void main(String[] args) {

            WebPage webPage = new WebPage(3);

            ExecutorService executor = Executors.newFixedThreadPool(10);

            for (int i = 1; i <= 10; i++) {
            final String user = "Usuari " + i;
                    executor.submit(() -> {
                webPage.connectUser(user); 
                try {
                                    Thread.sleep((long) (Math.random() * 5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                webPage.disconnectUser(user); 
            });
        }

            executor.shutdown();
    }
}
