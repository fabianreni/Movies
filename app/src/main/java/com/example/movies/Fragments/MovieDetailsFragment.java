package com.example.movies.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.movies.MainActivity;
import com.example.movies.R;
import com.example.movies.adaptors.ImagesAdapter;

import com.example.movies.adaptors.SimilarMoviesAdapter;
import com.example.movies.api.ApiClient;
import com.example.movies.api.ApiService;
import com.example.movies.constant.Constant;
import com.example.movies.models.ImageResponse;
import com.example.movies.models.ImageResult;
import com.example.movies.models.MovieResponse;
import com.example.movies.models.Result;
import com.example.movies.models.VideoResponse;
import com.example.movies.models.VideoResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieDetailsFragment extends Fragment {
    private Result movie;
    private Context context;
    private String title, viewType;

    public MovieDetailsFragment(Result movie) {
        this.movie = movie;
        this.viewType = viewType;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

        title = movie.getTitle() + " (" + movie.getReleaseDate().substring(0, 4) + ")";
        ((MainActivity) context).setTitle(title);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText(title);

        TextView tv_description = view.findViewById(R.id.tv_description);
        tv_description.setText(movie.getOverview());
        int movie_id = movie.getId();
        String api_key = Constant.APIKEY;
        ApiService service = ApiClient.getInstance().getApiService();

        Call<VideoResponse> call = service.getVideos(movie_id, api_key);
        call.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                List<VideoResult> videos = response.body().getResults();
                String key = "";
                for (VideoResult video : videos) {
                    if (video.getType().equals("Trailer")) {
                        key = video.getKey();
                        break;
                    }
                }

                WebView displayYoutubeVideo = view.findViewById(R.id.vv_trailer);
                displayYoutubeVideo.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        return false;
                    }
                });
                WebSettings webSettings = displayYoutubeVideo.getSettings();
                webSettings.setJavaScriptEnabled(true);
                displayYoutubeVideo.loadUrl("https://www.youtube.com/embed/" + key);

            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {

            }
        });

        final RecyclerView rv_images = view.findViewById(R.id.rv_images);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rv_images.setLayoutManager(layoutManager);
        rv_images.setHasFixedSize(true);

        Call<ImageResponse> call_images = service.getImages(movie_id, api_key);
        call_images.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                List<ImageResult> images = response.body().getBackdrops();

                ImagesAdapter adapter = new ImagesAdapter(images, context, view);
                rv_images.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });

        final RecyclerView rv_similar_movies = view.findViewById(R.id.rv_similarMovies);
        LinearLayoutManager layoutManager_similar_movies = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rv_similar_movies.setLayoutManager(layoutManager_similar_movies);
        rv_similar_movies.setHasFixedSize(true);

        Call<MovieResponse> call_similar_movies = service.getSimilarMovies(movie_id, api_key);
        call_similar_movies.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Result> movies = response.body().getResults();

                SimilarMoviesAdapter adapter = new SimilarMoviesAdapter(movies, context);
                rv_similar_movies.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
//

        ImageButton imgBtn_close = view.findViewById(R.id.bt_imgClose);
        imgBtn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContainerFragment containerFragment = new ContainerFragment();
                Bundle args = new Bundle();
                args.putString("fragmentToOpen", "top_movies");
                containerFragment.setArguments(args);

                FragmentTransaction frag_trans = ((MainActivity) context).getSupportFragmentManager().beginTransaction();
                frag_trans.replace(R.id.main2, containerFragment);
                frag_trans.commit();
            }
        });
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.movie_details_menu, menu);
        //super.onCreateOptionsMenu(menu, inflater);
        ((MainActivity) context).setTitle(title);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.close: {

                FragmentTransaction fragmentTransaction = ((MainActivity) context).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainActivity, new ContainerFragment());
                fragmentTransaction.replace(R.id.container, new FirstPageFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
//
//    private void savedMovieDetails(View view){
//        int movie_id = movie.getId();
//        DatabaseHelper db = new DatabaseHelper(context);
//
//        VideoResult video = db.getVideo(movie_id);
//        WebView displayYoutubeVideo = view.findViewById(R.id.vv_trailer);
//        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return false;
//            }
//        });
//        WebSettings webSettings = displayYoutubeVideo.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        displayYoutubeVideo.loadUrl("https://www.youtube.com/embed/" + video.getKey());
//
//        RecyclerView rv_images = view.findViewById(R.id.rv_images);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
//        rv_images.setLayoutManager(layoutManager);
//        rv_images.setHasFixedSize(true);
//
//        List<ImageResult> images = db.getImages(movie_id);
//        ImagesAdapter adapter = new ImagesAdapter(images, context, view);
//        rv_images.setAdapter(adapter);
//    }
//
//    private void movieDetailsFromInternet(final View view){
//
//    }
//
//}
