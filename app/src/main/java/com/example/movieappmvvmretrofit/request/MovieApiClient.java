package com.example.movieappmvvmretrofit.request;

import android.net.wifi.hotspot2.pps.Credential;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movieappmvvmretrofit.AppExecutors;
import com.example.movieappmvvmretrofit.Utils.Credentials;
import com.example.movieappmvvmretrofit.Utils.MovieApi;
import com.example.movieappmvvmretrofit.models.MovieModel;
import com.example.movieappmvvmretrofit.response.MovieSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieApiClient {

    //LiveData
    private MutableLiveData<List<MovieModel>> mMovies;
    private static MovieApiClient instance;

    /** making Global runable */
    private RetrieveMoviesRunable retrieveMoviesRunable;


    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        mMovies = new MutableLiveData<>();
    }


    public LiveData<List<MovieModel>> getMovies() {
        return mMovies;
    }


    /**1**/
    //this method that we are going to call through the classes
    public void searchMovieApi(String query, int pageNumber) {

        if (retrieveMoviesRunable!=null){
            retrieveMoviesRunable=null;
        }

        retrieveMoviesRunable=new RetrieveMoviesRunable(query,pageNumber);

        final Future myHandler = AppExecutors.getInstance().networkIo().submit(retrieveMoviesRunable);

        AppExecutors.getInstance().networkIo().schedule(new Runnable() {
            @Override
            public void run() {
                /** canceling the retrofit call**/
                myHandler.cancel(true);
            }
        }, 300, TimeUnit.MILLISECONDS);

    }

    /**
     * retrieve data from restApi(retrofit) by runable class
     **/
    //we have 2 type of queries : the Id & Search Queries
    private class RetrieveMoviesRunable implements Runnable {

        private String query;
        private int pageNumber;
        Boolean cancelRequest;

        public RetrieveMoviesRunable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {

            //geting the response mobject
            try{

                MovieApi movieApi= Servicey.getMovieApi();
                Call<MovieSearchResponse> responseCall=movieApi
                        .searchMovie(
                                Credentials.API_KEY,
                                query,
                                pageNumber
                        );
                responseCall.enqueue(new Callback<MovieSearchResponse>() {
                    @Override
                    public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                        if (cancelRequest) {
                            return;
                        }
                        if (response.code()==200){
                            List<MovieModel>list=new ArrayList<>(response.body().getMovies());
                            if (pageNumber==1){
                                //sending data to live data
                                //postValue : used for background thread
                                //setValue : not for background thread
                                mMovies.postValue(list);
                            }else {
                                List<MovieModel>currentMovires=mMovies.getValue();
                                currentMovires.addAll(list);
                                mMovies.postValue(currentMovires);
                            }
                        }else {
                            String error=response.errorBody().toString();
                            Log.v("tag","error: "+error);
                            mMovies.postValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }




        }

        //search methods/query
        private Call<MovieSearchResponse> getMovies(String query, int pageNumber){
            return Servicey.getMovieApi().searchMovie(
                    Credentials.API_KEY,
                    query,
                    pageNumber
            );
        }

        private void cancelRequest(){
            Log.v("Tag","cancelling search request ");
            cancelRequest=true;
        }


    }


}
