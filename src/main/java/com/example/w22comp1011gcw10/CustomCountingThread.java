package com.example.w22comp1011gcw10;

public class CustomCountingThread extends Thread{
//Thred has a method called run
    public void run(){
        for (int i=1 ; i<=10; i++)
            System.out.println(this.getName() + " i=" +i);

        //call an API
        APIUtility.getMoviesFromJSONFile("apiResponse.json");
        //perform a long DB query
    }
}
