package com.example.movies.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.movies.models.Result;

public class MoviewDataSourceFactory extends DataSource.Factory<Integer, Result> {
    private String sort_criterial;
    private MutableLiveData<MovieDataSource> liveData;
    private MovieDataSource dataSource;

    public MoviewDataSourceFactory(String sort_criterial) {
        this.sort_criterial = sort_criterial;
        liveData=new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource<Integer, Result> create() {
        dataSource=new MovieDataSource(sort_criterial);
        liveData=new MutableLiveData<>();
        liveData.postValue(dataSource);
        return dataSource;
    }
}
