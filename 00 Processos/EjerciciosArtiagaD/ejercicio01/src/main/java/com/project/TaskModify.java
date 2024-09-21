package com.project;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TaskModify implements TaskStrategy {
    @Override
    public void execute(ConcurrentHashMap<String, Integer> sharedData) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(3000);
        sharedData.computeIfPresent("prestamo", (prestamo, valorPrestamo) -> valorPrestamo + valorPrestamo * sharedData.get("intereses") * (sharedData.get("fecha de fin") - sharedData.get("fecha de inicio")));
        System.out.println("Calculando cambios en los intereses");
    }
}
