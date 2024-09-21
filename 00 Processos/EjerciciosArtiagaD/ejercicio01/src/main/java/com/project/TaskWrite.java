package com.project;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TaskWrite implements TaskStrategy {
    @Override
    public void execute(ConcurrentHashMap<String, Integer> sharedData) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1000);
        sharedData.put("prestamo", 5000);
        sharedData.put("intereses", 2);
        sharedData.put("fecha de inicio", 10);
        sharedData.put("fecha de fin", 13);

        System.err.println("---------- Informacion de operacion bancaria ----------");
        System.out.println("Importe: " + sharedData.get("prestamo") + " Intereses: " + sharedData.get("intereses"));
        System.out.println("Fecha de inicio: " + sharedData.get("fecha de inicio") + " Fecha de fin: " + sharedData.get("fecha de fin"));
    }
}
