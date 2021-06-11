package com.example.movieappmvvmretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieappmvvmretrofit.models.MovieModel;

public class MovieDetailsActivity extends AppCompatActivity {

    TextView title,overview;
    RatingBar ratingBar;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        title=findViewById(R.id.titleTextId);
        overview=findViewById(R.id.overViewId);
        ratingBar=findViewById(R.id.ratingBar2);
        imageView=findViewById(R.id.detailImageId);
        
        getDatafromIntent();
    }

    private void getDatafromIntent() {
        if (getIntent().hasExtra("movie")){
            MovieModel movieModel= getIntent().getParcelableExtra("movie");
            Log.v("Tag",movieModel.getTitle());

            title.setText(movieModel.getTitle());
            overview.setText(movieModel.getMovie_overview());
            ratingBar.setRating(movieModel.getVote_average()/2);

            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500"+movieModel.getPoster_path())
                    .into(imageView);
        }
    }
}