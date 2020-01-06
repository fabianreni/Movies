package com.example.movies.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.MainActivity;
import com.example.movies.R;
import com.example.movies.models.ImageResult;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.movies.constant.Constant.IMAGE_SIZE;
import static com.example.movies.constant.Constant.IMAGE_URL;

public class ImagesAdapter extends RecyclerView.Adapter<com.example.movies.adaptors.ImagesAdapter.MyViewHolder> {

        //kep bealitasa a film reszlet oldalan

        private List<ImageResult> images;
        private Context context;
        private View rootView;

        public ImagesAdapter(List<ImageResult> images, Context context, View rootView) {
            this.images = images;
            this.context = context;
            this.rootView = rootView;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.images_item, viewGroup, false);
            return new com.example.movies.adaptors.ImagesAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
            ImageResult image = images.get(i);

            final String imageUrl = IMAGE_URL + IMAGE_SIZE + image.getFilePath();
            Picasso.get().load(imageUrl).into(viewHolder.image);

            viewHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ImageButton imgBtn_closeImage = rootView.findViewById(R.id.bt_imgClose);
                    final ImageView iv_fullImage = rootView.findViewById(R.id.iv_fullImage);

                    ((MainActivity) context).getSupportActionBar().hide();
                    imgBtn_closeImage.setVisibility(View.VISIBLE);
                    iv_fullImage.setVisibility(View.VISIBLE);
                    Picasso.get().load(imageUrl).into(iv_fullImage);

                    imgBtn_closeImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imgBtn_closeImage.setVisibility(View.GONE);
                            iv_fullImage.setVisibility(View.GONE);
                            ((MainActivity) context).getSupportActionBar().show();
                        }
                    });
                }
            });
        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView image;
            public MyViewHolder(View itemView) {
                super(itemView);
                image = itemView.findViewById((R.id.image));
            }
        }
    }


