package com.example.w22comp1011gcw10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalDouble;

public class StreamExperiment {
    public static void main(String[] args) {
        ApiResponse apiResponse = APIUtility.getMoviesFromOMDB("star wars");
        ArrayList<Movie> movies = new ArrayList<>();
        movies.addAll(Arrays.asList(apiResponse.getSearch()));

        OptionalDouble avgYear = movies.stream()                              //stream of Movie objects
                                    .map(movie -> movie.getYear())            //converted to a stream of String that hold the year
                                    .mapToInt(year -> Integer.parseInt(year)) //converted to stream of Integer's
                                    .average();                               //return the average year

        System.out.printf("The average year is: %.1f%n", avgYear.isPresent()?avgYear.getAsDouble():-1);

        System.out.println(movies.stream()
                                .filter(movie -> movie.getYearAsInt()< 2000)
                                .toList());

        System.out.println("The amount of movies in the variable 'movies' is "+movies.size());
    }
}
