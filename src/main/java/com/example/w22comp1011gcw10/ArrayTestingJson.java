package com.example.w22comp1011gcw10;

public class ArrayTestingJson {
    public static void main(String[] args) {
        Movie[] movies = APIUtility.getMoviesFromJSONFileArray("arrayFirst.json");
        System.out.println(movies);
    }
}
