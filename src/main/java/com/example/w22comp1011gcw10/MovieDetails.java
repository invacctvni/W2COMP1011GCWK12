package com.example.w22comp1011gcw10;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieDetails {
    @SerializedName("Title")
    private String title;

    @SerializedName("Released")
    private String releaseDate;

    @SerializedName("Runtime")
    private String runtime;

    @SerializedName("Genre")
    private String genre;

    @SerializedName("Writer")
    private String writer;

    @SerializedName("Language")
    private String language;

    @SerializedName("Poster")
    private String posterURL;

    @SerializedName("Actors")
    private String actors;

    @SerializedName("Ratings")
    private ArrayList<Rating> ratings;

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getWriter() {
        return writer;
    }

    public String getLanguage() {
        return language;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public String getActors() {
        return actors;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }
}
