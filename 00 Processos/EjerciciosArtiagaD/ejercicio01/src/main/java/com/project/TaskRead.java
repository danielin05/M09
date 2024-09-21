package com.project;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TaskRead implements TaskStrategy {
    @Override
    public void execute(ConcurrentHashMap<String, Integer> sharedData) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(7000);
        System.out.println("Importe a devolver: " + sharedData.get("prestamo"));
    }
}
