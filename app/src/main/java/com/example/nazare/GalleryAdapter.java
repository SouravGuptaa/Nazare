package com.example.nazare;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ItemViewHolder> {

    private final ArrayList list;
    private final String MUSIC = "music";
    private final String VIDEO = "video";
    private final String  IMAGE= "image";
    private final String TYPE ;
    private final Context context;

    public GalleryAdapter(ArrayList list,String type ,Context context) {
        this.list = list;
        this.TYPE = type;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_card,parent,false);
        return new ItemViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        int mPosition = position;
        if (TYPE.equals(IMAGE)){
            ImageModel imageModel = (ImageModel) list.get(position);
            holder.tvObject.setText(imageModel.getTitle());
            holder.tvObjects.setText(imageModel.getSize()+"kb");

            holder.cvCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String data = imageModel.getData();
                    Intent intent = new Intent(context, GalleryViewActivity.class);
                    intent.putExtra("data",data);
                    intent.putExtra("type",IMAGE);
                    intent.putExtra("list",list);
                    intent.putExtra("position",mPosition);
                    context.startActivity(intent);

                }
            });
        } else if (TYPE.equals(MUSIC)) {
            MusicModel musicModel = (MusicModel) list.get(mPosition);
            holder.tvObject.setText(musicModel.getTitle());
            holder.tvObjects.setText(musicModel.getArtist());

            holder.cvCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String data = musicModel.getData();
                    Intent intent = new Intent(context, GalleryViewActivity.class);
                    intent.putExtra("data",data);
                    intent.putExtra("type",MUSIC);
                    intent.putExtra("list",list);
                    intent.putExtra("position",mPosition);
                    context.startActivity(intent);

                }
            });
        }else{
            VideoModel videoModel = (VideoModel) list.get(position);
            holder.tvObject.setText(videoModel.getTitle());
            holder.tvObjects.setText(videoModel.getSize()+"kb");

            holder.cvCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String data = videoModel.getData();
                    Intent intent = new Intent(context, GalleryViewActivity.class);
                    intent.putExtra("data",data);
                    intent.putExtra("type",VIDEO);
                    intent.putExtra("list",list);
                    intent.putExtra("position",mPosition);
                    context.startActivity(intent);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public CardView cvCard;
        public TextView tvObject;
        public TextView tvObjects;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cvCard = itemView.findViewById(R.id.gallerycardview);
            tvObject = itemView.findViewById(R.id.objectname);
            tvObjects =  itemView.findViewById(R.id.objectnames);

        }

    }
}