package com.example.movieappmvvmretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.movieappmvvmretrofit.Utils.Credentials;
import com.example.movieappmvvmretrofit.Utils.MovieApi;
import com.example.movieappmvvmretrofit.adapter.MovieRecyclerViewAdapter;
import com.example.movieappmvvmretrofit.adapter.OnMovieListener;
import com.example.movieappmvvmretrofit.models.MovieModel;
import com.example.movieappmvvmretrofit.request.Servicey;
import com.example.movieappmvvmretrofit.response.MovieSearchResponse;
import com.example.movieappmvvmretrofit.viewmodel.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {

     //Sefore we run our app ,we need to add the network security config


    RecyclerView recyclerView;

    MovieRecyclerViewAdapter  movieRecyclerViewAdapter;

    //viewModel
    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);


       recyclerView= findViewById(R.id.recyclerView);

       movieListViewModel= new ViewModelProvider(this).get(MovieListViewModel.class);


        //testing the methods
        ConfigerRecyclerView();

       //calling the observers
        ObserveAnyChange();

        searchMovieApi("jack",1);
    }

    //observing any data change
    private void ObserveAnyChange(){
        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                //observing any data chnage
                if (movieModels!=null){
                    for (MovieModel movieModel:movieModels){
                        //getting the data bin the log
                        Log.v("Tag","onChanged "+movieModel.getRelease_date());

                        movieRecyclerViewAdapter.setmMovies(movieModels);
                    }
                }

            }
        });
    }

    /**4**/
    private void searchMovieApi(String query,int pageNumber){
        movieListViewModel.searchMovieApi(query,pageNumber);
    }

    //5-Intializing recyclerView and adding data to it
    private void ConfigerRecyclerView(){
        //Live Data can,t be passed via the constructor
        movieRecyclerViewAdapter=new MovieRecyclerViewAdapter(this);
        recyclerView.setAdapter(movieRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void onMovieClick(int position) {
        Toast.makeText(this, "the position"+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCatyegoryClick(String cetegory) {

    }

//    private void getRetrofitResponse() {
//        MovieApi movieApi= Servicey.getMovieApi();
//
//        Call<MovieSearchResponse> responseCall=movieApi
//                .searchMovie(
//                        Credentials.API_KEY,
//                        "ack",
//                        1
//                );
//
//        responseCall.enqueue(new Callback<MovieSearchResponse>() {
//            @Override
//            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
//                if (response.code()==200)
//                {
//                    Log.v("Tag","this response:"+response.body().toString());
//
//                    List<MovieModel> movies=new ArrayList<>(response.body().getMovies());
//                    for (MovieModel movie : movies){
//                        Log.v("Tag","the list "+movie.getRelease_date());
//                    }
//                }else
//                {
//                    try {
//                        Log.v("Tag","rerror"+response.errorBody().toString());
//                    }catch (Exception e){
//
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
//
//            }
//        });
//    }
//    private void GetRetrofitResponseAccordingToId(){
//        MovieApi movieApi=Servicey.getMovieApi();
//
//        Call<MovieModel> responseCallSeasrch=movieApi.searchMoviebyId(
//                550,
//                Credentials.API_KEY
//        );
//
//        responseCallSeasrch.enqueue(new Callback<MovieModel>() {
//            @Override
//            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
//                if (response.code()==200){
//                    MovieModel movie=response.body();
//                    Log.v("Tag",movie.getTitle());
//                }else {
//                    try {
//                        Log.v("Tag","error "+response.errorBody().toString());
//                    }catch (Exception e){
//                       e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieModel> call, Throwable t) {
//
//            }
//        });
//
//    }


}