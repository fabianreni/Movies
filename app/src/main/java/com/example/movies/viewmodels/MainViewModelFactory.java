package com.example.movies.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/*public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    private String sort_criteria;

    public MainViewModelFactory(String sort_criteria){
    this.sort_criteria=sort_criteria;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> ModelClass){
    return  (T) new MainViewModel(sort_criteria);
}
}*/
public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private String sort_criteria;

    public MainViewModelFactory(String sort_criteria) {
        this.sort_criteria = sort_criteria;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(sort_criteria);
    }
}
