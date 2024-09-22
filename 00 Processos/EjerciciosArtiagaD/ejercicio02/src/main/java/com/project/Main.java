package com.project;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        CompletableFuture<Integer> validacio = CompletableFuture.supplyAsync(() -> {
            System.out.println("Validant les dades de la solicitud");
            return 150;
        });

        CompletableFuture<Integer> modificacio = validacio.thenApply(result -> {
            System.out.println("Modificant les dades");
            return result / 10;
        });

        try {
            Integer finalResult = modificacio.get();
            System.out.println("Resultat final = " + finalResult);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}