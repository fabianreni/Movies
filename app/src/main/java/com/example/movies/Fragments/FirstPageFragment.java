package com.example.movies.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.adaptors.MoviePageListAdaptor;
import com.example.movies.models.Result;
import com.example.movies.viewmodels.MainViewModel;
import com.example.movies.viewmodels.MainViewModelFactory;


public class FirstPageFragment extends Fragment {
   // private BottomNavigationView mMainNav;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MoviePageListAdaptor adaptor;
    private MainViewModel viewModel;
    //private String sort_criteria="popular";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_first_page, container, false);

        String sort_criteria = "popular";
        viewModel = ViewModelProviders.of(this, new MainViewModelFactory(sort_criteria)).get(MainViewModel.class);
        recyclerView=(RecyclerView) v.findViewById(R.id.movie_list);
        linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        adaptor=new MoviePageListAdaptor();
        recyclerView.setAdapter(adaptor);
        viewModel.getListLiveData().observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                if(results != null){
                    Log.d("kereshiba","result.size: " + results.size());
                    adaptor.submitList(results);
                }
            }
        });
        return v;
    }


}
