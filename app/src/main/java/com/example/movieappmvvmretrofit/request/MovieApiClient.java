package com.example.movieappmvvmretrofit.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieappmvvmretrofit.Utils.MovieApi;
import com.example.movieappmvvmretrofit.models.MovieModel;

import java.util.List;

public class MovieApiClient {

    //LiveData
    private MutableLiveData<List<MovieModel>> mMovies;

    private static MovieApiClient instance;

    public static MovieApiClient getInstance() {
        if (instance==null){
            instance=new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient(){
        mMovies=new MutableLiveData<>();
    }


    public LiveData<List<MovieModel>> getMovies() {
        return mMovies;
    }
}
