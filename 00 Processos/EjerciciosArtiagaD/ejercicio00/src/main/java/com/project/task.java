package com.project;

class task implements Runnable {
    private final int taskId;

    public task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Executant Task " + taskId);
    }
}
