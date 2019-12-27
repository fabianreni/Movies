package com.example.movies.adaptors;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.databinding.VideoItemBinding;
import com.example.movies.models.Result;
import com.squareup.picasso.Picasso;

import static com.example.movies.constant.Constant.IMAGE_SIZE;
import static com.example.movies.constant.Constant.IMAGE_URL;

public class MoviePageListAdaptor extends PagedListAdapter<Result,MoviePageListAdaptor.MViewModel> {
    public static DiffUtil.ItemCallback<Result> diffCallback = new DiffUtil.ItemCallback<Result>() {
        @Override
        public boolean areItemsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
            return oldItem.equals(newItem);
        }
    };

    public MoviePageListAdaptor() {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VideoItemBinding itemsBinding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.video_item,parent,false
        );
        return new MViewModel(itemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MViewModel holder, int position) {
        holder.bind(getItem(position));
    }


    public class MViewModel extends RecyclerView.ViewHolder {
        private VideoItemBinding itemsBinding;
        public MViewModel( VideoItemBinding videoItemBinding) {

            super(videoItemBinding.getRoot());
            itemsBinding=videoItemBinding;
        }

        public void bind(Result item) {
            if(item!=null){
                Log.d("kereshiba","title " + item.getTitle());
                String thumUrl=IMAGE_URL+IMAGE_SIZE+item.getBackdropPath();
                Picasso.get().load(thumUrl).into(itemsBinding.thumb);
                itemsBinding.title.setText(item.getTitle());
            }
        }
    }
}
