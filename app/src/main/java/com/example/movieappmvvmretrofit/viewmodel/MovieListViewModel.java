package com.example.movieappmvvmretrofit.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieappmvvmretrofit.models.MovieModel;
import com.example.movieappmvvmretrofit.repository.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    //this class is used for view Model

    private MovieRepository movieRepository;



    //constructor
    public MovieListViewModel() {
        movieRepository=MovieRepository.getInstance();
    }


    public LiveData<List<MovieModel>> getMovies(){
        return movieRepository.getMovies();
    }

    /**3**/
    //calling method in viewmodel
    public void searchMovieApi(String query,int pageNumber){
        movieRepository.searchmovieApi(query,pageNumber);
    }


    public void SearchNextPage(){
        movieRepository.searchNextPage();
    }


}
