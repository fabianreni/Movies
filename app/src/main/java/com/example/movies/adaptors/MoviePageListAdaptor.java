package com.example.movies.adaptors;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.models.Result;

public class MoviePageListAdaptor extends PagedListAdapter<Result,MoviePageListAdaptor.MViewModel> {
    protected MoviePageListAdaptor() {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MViewModel holder, int position) {

    }
    public  static DiffUtil.ItemCallback<Result> diffCallback=
            new DiffUtil.ItemCallback<Result>() {
                @Override
                public boolean areItemsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
                    return oldItem.getId()==newItem.getId();
                }
                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
                    return oldItem.equals(newItem);
                }
            };
    public class MViewModel extends RecyclerView.ViewHolder {
       // private VideoItemsBinding itemsBinding;
        public MViewModel(@NonNull View itemView) {
            super(itemView);
        }
    }
}
