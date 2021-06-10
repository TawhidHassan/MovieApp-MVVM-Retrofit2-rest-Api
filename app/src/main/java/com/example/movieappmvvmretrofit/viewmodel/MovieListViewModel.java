package com.example.movieappmvvmretrofit.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieappmvvmretrofit.models.MovieModel;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    //this class is used for view Model

    //LiveData
    MutableLiveData<List<MovieModel>> mMovies=new MutableLiveData<>();



    //constructor
    public MovieListViewModel() {

    }


    public LiveData<List<MovieModel>> getMovies(){
        return mMovies;
    }



}
