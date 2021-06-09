package com.example.movieappmvvmretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.movieappmvvmretrofit.Utils.Credentials;
import com.example.movieappmvvmretrofit.Utils.MovieApi;
import com.example.movieappmvvmretrofit.models.MovieModel;
import com.example.movieappmvvmretrofit.request.Servicey;
import com.example.movieappmvvmretrofit.response.MovieSearchResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       btn= findViewById(R.id.button);
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               getRetrofitResponse();
           }
       });
    }

    private void getRetrofitResponse() {
        MovieApi movieApi= Servicey.getMovieApi();

        Call<MovieSearchResponse> responseCall=movieApi
                .searchMovie(
                        Credentials.API_KEY,
                        "ack",
                        "1"
                );

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code()==200)
                {
                    Log.v("Tag","this response:"+response.body().toString());

                    List<MovieModel> movies=new ArrayList<>(response.body().getMovies());
                    for (MovieModel movie : movies){
                        Log.v("Tag","the list "+movie.getRelease_date());
                    }
                }else
                {
                    try {
                        Log.v("Tag","rerror"+response.errorBody().toString());
                    }catch (Exception e){

                    }
                }

            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
    }
}