package com.example.movieappmvvmretrofit.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieappmvvmretrofit.models.MovieModel;

import java.util.List;

public class MovieRepository {
    //this class acting as repository

    private static MovieRepository instance;

    public static MovieRepository getInstance(){
        if (instance==null){
            instance=new MovieRepository();
        }

        return instance;
    }

    //LiveData
    private MutableLiveData<List<MovieModel>> mMovies;

    private MovieRepository(){
        mMovies=new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return  mMovies;
    }


}
