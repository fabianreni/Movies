package com.example.movies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.movies.data.MoviewDataSourceFactory;
import com.example.movies.models.Result;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.example.movies.constant.Constant.NUMBER_OF_THREAD_FIVE;
import static com.example.movies.constant.Constant.PAGE_LOAD_SIZE_HINT;
import static com.example.movies.constant.Constant.PAGE_SIZE;
import static com.example.movies.constant.Constant.Prefetch_Distance;
/*public class MainViewModel extends ViewModel {
    private  String sort_criteria;
    private LiveData<PagedList<Result>> listLiveData;

    public MainViewModel(String sort_criteria) {
        this.sort_criteria = sort_criteria;
        Executor executor= Executors.newFixedThreadPool(NUMBER_OF_THREAD_FIVE);

        MoviewDataSourceFactory sourceFactory=new MoviewDataSourceFactory(sort_criteria);

        PagedList.Config config=new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(PAGE_LOAD_SIZE_HINT)
                .setPageSize(PAGE_SIZE)
                .setPrefetchDistance(Prefetch_Distance)
                .build();
        listLiveData=new LivePagedListBuilder<>(sourceFactory,config)
                .setFetchExecutor(executor)
                .build();
    }
    public LiveData<PagedList<Result>> geLlistLiveData(){
        return listLiveData;
    }

}*/

public class MainViewModel extends ViewModel {

    private String sort_criteria;
    private LiveData<PagedList<Result>> listLiveData;

    public MainViewModel(String sort_criteria) {
        this.sort_criteria = sort_criteria;

        Executor executor = Executors.newFixedThreadPool(NUMBER_OF_THREAD_FIVE);

        MoviewDataSourceFactory sourceFactory = new MoviewDataSourceFactory(sort_criteria);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(PAGE_LOAD_SIZE_HINT)
                .setPageSize(PAGE_SIZE)
                .setPrefetchDistance(Prefetch_Distance)
                .build();

        listLiveData = new LivePagedListBuilder<>(sourceFactory, config)
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Result>> getListLiveData() {
        return listLiveData;
    }
}
