package com.example.movies.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.Fragments.MovieDetailsFragment;
import com.example.movies.MainActivity;
import com.example.movies.R;
import com.example.movies.models.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.movies.constant.Constant.IMAGE_SIZE;
import static com.example.movies.constant.Constant.IMAGE_URL;

public class SimilarMoviesAdapter extends RecyclerView.Adapter<SimilarMoviesAdapter.MyViewHolder>{
    private List<Result> movies;
    private Context context;

    public SimilarMoviesAdapter(List<Result> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public SimilarMoviesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.similar_movies_item, viewGroup, false);
        return new SimilarMoviesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SimilarMoviesAdapter.MyViewHolder viewHolder, int i) {
        final Result movie = movies.get(i);

        String imageUrl = IMAGE_URL + IMAGE_SIZE + movie.getPosterPath();
        Picasso.get().load(imageUrl).into(viewHolder.iv_poster);

        String title = movie.getTitle() + " (" + movie.getReleaseDate().substring(0,4) + ")";
        viewHolder.tv_title.setText(title);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction frag_trans = ((MainActivity) context).getSupportFragmentManager().beginTransaction();
                frag_trans.replace(R.id.mainActivity, new MovieDetailsFragment(movie));
                frag_trans.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_poster;
        TextView tv_title;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv_poster = itemView.findViewById((R.id.iv_poster));
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}
