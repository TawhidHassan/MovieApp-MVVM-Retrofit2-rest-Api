package com.example.movieappmvvmretrofit.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieappmvvmretrofit.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    //wedgit
    TextView title,releaseDate,duration;
    ImageView imageView;
    RatingBar ratingBar;


    //listner
    OnMovieListener onMovieListener;

    public MovieViewHolder(@NonNull View itemView,OnMovieListener onMovieListener) {
        super(itemView);

        this.onMovieListener=onMovieListener;

        title=itemView.findViewById(R.id.movie_title);
        releaseDate=itemView.findViewById(R.id.releaseDate);
        duration=itemView.findViewById(R.id.duration_text);
        imageView=itemView.findViewById(R.id.imageView);
        ratingBar=itemView.findViewById(R.id.ratingBar);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onMovieListener.onMovieClick(getAdapterPosition());
    }
}
