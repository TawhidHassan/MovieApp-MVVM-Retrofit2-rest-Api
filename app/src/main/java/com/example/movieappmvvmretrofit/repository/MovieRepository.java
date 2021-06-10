package com.example.movieappmvvmretrofit.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieappmvvmretrofit.Utils.MovieApi;
import com.example.movieappmvvmretrofit.models.MovieModel;
import com.example.movieappmvvmretrofit.request.MovieApiClient;

import java.util.List;

public class MovieRepository {
    //this class acting as repository
    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    public static MovieRepository getInstance(){
        if (instance==null){
            instance=new MovieRepository();
        }

        return instance;
    }

    //LiveData

    private MovieRepository(){
        movieApiClient= MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return  movieApiClient.getMovies();
    }
}
