package com.example.w22comp1011gcw10;

public class ThreadExperiment {
    public static void main(String[] args) {
        CustomCountingThread cct1 = new CustomCountingThread();
        CustomCountingThread cct2 = new CustomCountingThread();
        CustomCountingThread cct3 = new CustomCountingThread();

        for (int i=1 ; i<=10; i++)
            System.out.println(Thread.currentThread().getName() + " i=" +i);

        cct1.start();  //Thread 0
        cct2.start();  //Thread 1
        cct3.start();  //Thread 2
    }
}
