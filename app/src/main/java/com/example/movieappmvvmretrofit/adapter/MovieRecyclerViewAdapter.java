package com.example.movieappmvvmretrofit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieappmvvmretrofit.R;
import com.example.movieappmvvmretrofit.models.MovieModel;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

     List<MovieModel> mMovies;
     OnMovieListener onMovieListener;

    public MovieRecyclerViewAdapter(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);

        return new MovieViewHolder(view,onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder)holder).title.setText(mMovies.get(position).getTitle());
        ((MovieViewHolder)holder).releaseDate.setText(mMovies.get(position).getRelease_date());
        ((MovieViewHolder)holder).duration.setText("lang: "+mMovies.get(position).getOriginal_language());
        //vote avarage is over 10,and our rating bar is over 5 starts: dividing by 2
        ((MovieViewHolder)holder).ratingBar.setRating(mMovies.get(position).getVote_average()/2);
        //ImageView Glide
        Glide.with(((MovieViewHolder) holder).imageView.getContext())
                .load("https://image.tmdb.org/t/p/w500/"+mMovies.get(position).getPoster_path())
                .into(((MovieViewHolder)holder).imageView);
    }

    @Override
    public int getItemCount() {
        if (mMovies!=null){
          return   mMovies.size();
        }
        return 0;
    }

    public void setmMovies(List<MovieModel> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }
}
