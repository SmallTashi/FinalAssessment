package com.mredrock.tashi.finalexam.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mredrock.tashi.finalexam.R;

import cn.jzvd.JZVideoPlayerStandard;

class ViewHolderPool {
    static class PageOneViewHolder extends RecyclerView.ViewHolder {
        JZVideoPlayerStandard one_Video_left;
        TextView oneTitle_left;
        TextView oneTag_left;
        TextView oneDuration_left;


        PageOneViewHolder(View itemView) {
            super(itemView);
            one_Video_left = itemView.findViewById(R.id.item_top_image_left);
            oneTitle_left = itemView.findViewById(R.id.item_name_left);
            oneTag_left = itemView.findViewById(R.id.item_tag_left);
            oneDuration_left = itemView.findViewById(R.id.item_duration_left);
        }
    }

    static class PageTwoViewHolder extends RecyclerView.ViewHolder {
        JZVideoPlayerStandard twoVideo;
        TextView twoTitle;
        TextView twoTime;
        TextView twoDescription;
        int pos;
        TextView textView;
        ImageView imageView;

        PageTwoViewHolder(View itemView,int pos) {
            super(itemView);
            this.pos = pos;
            if(pos!=0){
                twoVideo = itemView.findViewById(R.id.three_item_video_player);
                twoTitle = itemView.findViewById(R.id.three_item_title);
                twoDescription = itemView.findViewById(R.id.three_item_detail);
                twoTime = itemView.findViewById(R.id.three_duration);
            }else {
                textView = itemView.findViewById(R.id.select);
                imageView = itemView.findViewById(R.id.two_bg);
            }

        }

    }


    static class PageThreeViewHolder extends RecyclerView.ViewHolder {
        JZVideoPlayerStandard threeVideo;
        TextView threeTitle;
        TextView threeDetail;


        PageThreeViewHolder(View itemView) {
            super(itemView);
            threeVideo = itemView.findViewById(R.id.three_item_video_player);
            threeTitle = itemView.findViewById(R.id.three_item_title);
            threeDetail = itemView.findViewById(R.id.three_item_detail);
        }
    }

    static class CommentItemViewHolder extends RecyclerView.ViewHolder{
        ImageView head_left;
        ImageView head_right;
        TextView chat_left;
        TextView chat_right;

        public CommentItemViewHolder(View itemView) {
            super(itemView);
            head_left = itemView.findViewById(R.id.comment_head_left);
            head_right = itemView.findViewById(R.id.comment_head_right);
            head_right.setVisibility(View.INVISIBLE);
            chat_left = itemView.findViewById(R.id.comment_chat_left);
            chat_right =itemView.findViewById(R.id.comment_chat_right);
            chat_right.setVisibility(View.INVISIBLE);
        }
    }
}