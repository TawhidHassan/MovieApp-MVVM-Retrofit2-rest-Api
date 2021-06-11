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

    private String mQuery;
    private int mPageNumber;


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

    /**2**/
    //calling the method from MovieApiClient
    public void searchmovieApi(String query,int pageNumber){

        mQuery=query;
        mPageNumber=pageNumber;
        movieApiClient.searchMovieApi(query,pageNumber);
    }

    public void searchNextPage(){
        searchmovieApi(mQuery,mPageNumber+1);
    }

}
