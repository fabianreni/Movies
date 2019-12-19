package com.example.movies.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory{
private String sort_criterial;

    public MainViewModelFactory(String sort_criterial){
    this.sort_criterial=sort_criterial;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> ModelClass){
    return  (T) new MainViewModel(sort_criterial);
}
}
