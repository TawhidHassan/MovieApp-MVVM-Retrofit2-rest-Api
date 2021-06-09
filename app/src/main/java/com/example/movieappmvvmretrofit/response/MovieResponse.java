package com.example.movieappmvvmretrofit.response;

import com.example.movieappmvvmretrofit.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//this class is for requesting single movie request
public class MovieResponse {

    // 1- finding movie object
    @SerializedName("results")
    @Expose
    private MovieModel movie;

    public MovieModel getMovie(){
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
