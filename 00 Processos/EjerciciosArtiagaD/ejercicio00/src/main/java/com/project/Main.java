package com.project;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

		// Crear un executor amb un pool de 2 fils
        ExecutorService executor = Executors.newFixedThreadPool(2);

        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            tasks.add(new task(i));
        }

        for (Runnable task : tasks){
            executor.execute(task);
        }

        executor.shutdown();
    }
}