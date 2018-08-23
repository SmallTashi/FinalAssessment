package com.mredrock.tashi.finalexam.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mredrock.tashi.finalexam.R;

import cn.jzvd.JZVideoPlayerStandard;
class ViewHolderPool {
    static class PageOneViewHolder extends RecyclerView.ViewHolder {
        JZVideoPlayerStandard videoPlayerStandard;
        TextView title;
        TextView detail;

        PageOneViewHolder(View itemView) {
            super(itemView);
            videoPlayerStandard = (JZVideoPlayerStandard) itemView.findViewById(R.id.item_video_player);
            title =(TextView) itemView.findViewById(R.id.item_title);
            detail =(TextView) itemView.findViewById(R.id.item_detail);
        }
    }
    class PageTwoViewHolder extends RecyclerView.ViewHolder {

        public PageTwoViewHolder(View itemView) {
            super(itemView);
        }
    }



}
