package com.example.movies.adaptors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.Fragments.ContainerFragment;
import com.example.movies.Fragments.FirstPageFragment;
import com.example.movies.Fragments.MovieDetailsFragment;
import com.example.movies.MainActivity;
import com.example.movies.R;
import com.example.movies.databinding.VideoItemBinding;
import com.example.movies.models.Result;
import com.squareup.picasso.Picasso;

import static com.example.movies.constant.Constant.IMAGE_SIZE;
import static com.example.movies.constant.Constant.IMAGE_URL;

public class MoviePageListAdaptor extends PagedListAdapter<Result,MoviePageListAdaptor.MViewModel> {
    private Context context;

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

    public MoviePageListAdaptor(Context context) {
        super(diffCallback);
        this.context = context;
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
    public void onBindViewHolder(@NonNull MViewModel holder, final int position) {
        holder.bind(getItem(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=((MainActivity)context).getSupportFragmentManager().beginTransaction();
                //fragmentTransaction.replace(R.id.mainActivity, new ContainerFragment());
                fragmentTransaction.replace(R.id.mainActivity, new MovieDetailsFragment(getItem(position)));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
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
