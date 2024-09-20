package com.project;

public class Main {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> sharedData = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Crear i executar les tasques amb estratègies específiques
        executor.execute(new Task(new TaskWrite(), sharedData));
        executor.execute(new Task(new TaskModify(), sharedData));
        executor.execute(new Task(new TaskRead(), sharedData));
    }
}