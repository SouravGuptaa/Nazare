package com.example.nazare;

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

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final ArrayList<VideoModel> videoList;
    public final Context context;

    public VideoAdapter(ArrayList<VideoModel> videoList,Context context) {
        this.videoList = videoList;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.video_card,parent,false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoModel videoModel = videoList.get(position);
        holder.tvVideo.setText(videoModel.getTitle());
        holder.cvCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = videoModel.getData();
                Intent intent = new Intent(context,VideoPlayerActivity.class);
                intent.putExtra("data",data);
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        public CardView cvCard;
        public TextView tvVideo;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            cvCard = itemView.findViewById(R.id.videocard);
            tvVideo = itemView.findViewById(R.id.videosong);
        }


    }
}