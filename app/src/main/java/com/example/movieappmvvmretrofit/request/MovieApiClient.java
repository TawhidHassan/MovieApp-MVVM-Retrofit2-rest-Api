package com.example.movieappmvvmretrofit.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieappmvvmretrofit.AppExecutors;
import com.example.movieappmvvmretrofit.Utils.MovieApi;
import com.example.movieappmvvmretrofit.models.MovieModel;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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


    public void searchMovieApi(){

        final Future myHandler= AppExecutors.getInstance().networkIo().submit(new Runnable() {
            @Override
            public void run() {
                //retrieve data from api

            }
        });

        AppExecutors.getInstance().networkIo().schedule(new Runnable() {
            @Override
            public void run() {
                //canceling the retrofit call
                myHandler.cancel(true);
            }
        },5000, TimeUnit.MICROSECONDS);


    }




}
