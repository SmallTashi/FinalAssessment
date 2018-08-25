package com.mredrock.tashi.finalexam.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mredrock.tashi.finalexam.Contract;
import com.mredrock.tashi.finalexam.R;
import com.mredrock.tashi.finalexam.data.DetailData;
import com.mredrock.tashi.finalexam.data.HotNewsData;
import com.mredrock.tashi.finalexam.data.OtherData;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayer;

public class PageRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int flag;
    public static final int PAGE_ONE = 222;
    public static final int PAGE_THREE = 444;
    public static final int PAGE_TWO = 333;
    public static final int COMMENT = 555;

    private int pos = 0;
    private static StringBuilder builder = new StringBuilder();


    private List<HotNewsData.DataListBean> dataListBeanList;
    private List<OtherData.ContListBean> otherContList;
    private List<OtherData.HotListBean> hotListBeanList;
    private List<HotNewsData.ContListBean> hotContList;
    private List<DetailData.PostInfoBean.ChildListBean> commentDataList;

    public PageRecyclerAdapter(List<?> list, int f) {
        this.flag = f;
        switch (f) {
            case PAGE_ONE:
                otherContList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    OtherData.ContListBean bean = (OtherData.ContListBean) list.get(i);
                    otherContList.add(bean);
                }
                break;
            case PAGE_TWO:
                hotListBeanList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    OtherData.HotListBean hot = (OtherData.HotListBean) list.get(i);
                    hotListBeanList.add(hot);
                }
                break;
            case PAGE_THREE:
                dataListBeanList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    HotNewsData.DataListBean data = (HotNewsData.DataListBean) list.get(i);
                    dataListBeanList.add(data);
                }
                break;
            case COMMENT:
                commentDataList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    DetailData.PostInfoBean.ChildListBean data = (DetailData.PostInfoBean.ChildListBean) list.get(i);
                    commentDataList.add(data);
                }
                break;
        }

    }

    public void refreshList(int flag, List<?> loadList, Contract.action action) {
        switch (flag) {
            case PAGE_ONE:
                for (int i = 0; i < loadList.size(); i++) {
                    OtherData.ContListBean bean = (OtherData.ContListBean) loadList.get(i);
                    if (bean.equals(otherContList.get(i))) {
                        action.refreshItem(false);
                    } else {
                        for (int j = 0; bean != otherContList.get(i); j++) {
                            otherContList.add(0, bean);
                        }
                        notifyItemRangeChanged(0, otherContList.size());
                    }
                }
                break;
            case PAGE_TWO:
                for (int i = 0; i < loadList.size(); i++) {
                    OtherData.HotListBean bean = (OtherData.HotListBean) loadList.get(i);
                    if (bean.equals(hotListBeanList.get(i))) {
                        action.refreshItem(false);
                    } else {
                        for (int j = 0; bean != hotListBeanList.get(i); j++) {
                            hotListBeanList.add(0, bean);
                        }
                        notifyItemRangeChanged(0, hotListBeanList.size());
                    }
                }
                break;
            case PAGE_THREE:
                for (int i = 0; i < loadList.size(); i++) {
                    HotNewsData.DataListBean bean = (HotNewsData.DataListBean) loadList.get(i);
                    if (bean.equals(dataListBeanList.get(i))) {
                        action.refreshItem(false);
                    } else {
                        dataListBeanList.add(0, bean);

                        hotContList = dataListBeanList.get(i).getContList();

                        notifyItemRangeChanged(0, dataListBeanList.size());
                    }
                }
                break;
        }
    }

    public void loadMore(int flag, List<?> more, Contract.action action) {
        int currentCount = 0;
        switch (flag) {
            case PAGE_ONE:
                currentCount = otherContList.size();
                for (int i = 0; i < more.size(); i++) {
                    OtherData.ContListBean bean = (OtherData.ContListBean) more.get(i);
                    otherContList.add(bean);
                }
                notifyItemRangeChanged(currentCount, more.size());
                break;
            case PAGE_TWO:
                currentCount = hotListBeanList.size();
                for (int i = 0; i < more.size(); i++) {
                    OtherData.HotListBean hot = (OtherData.HotListBean) more.get(i);
                    hotListBeanList.add(hot);
                }
                notifyItemRangeChanged(currentCount, more.size());
                break;
            case PAGE_THREE:
                currentCount = hotContList.size();
                for (int i = 0; i < more.size(); i++) {
                    HotNewsData.DataListBean hot = (HotNewsData.DataListBean) more.get(i);
                    hotContList.addAll(hot.getContList());
                }
                notifyItemRangeChanged(currentCount, hotContList.size() - currentCount);
                break;
        }
        action.loadMore(true);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (flag) {
            case PAGE_ONE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page_one, parent, false);
                return new ViewHolderPool.PageOneViewHolder(view);
            case PAGE_TWO:
                if (viewType == 0) {
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page_two, parent, false);
                } else {
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page_three, parent, false);
                }
                return new ViewHolderPool.PageTwoViewHolder(view, viewType);
            case PAGE_THREE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page_three, parent, false);
                return new ViewHolderPool.PageThreeViewHolder(view);
            case COMMENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_list, parent, false);
                return new ViewHolderPool.CommentItemViewHolder(view);
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_list, parent, false);
        return new ViewHolderPool.CommentItemViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (flag == PAGE_ONE) {
            return position;
        } else {
            return position;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (flag == PAGE_ONE) {
            OtherData.ContListBean leftBean = otherContList.get(position);
            ViewHolderPool.PageOneViewHolder one = (ViewHolderPool.PageOneViewHolder) holder;
            one.one_Video_left.setUp(leftBean.getLink(), JZVideoPlayer.SCREEN_WINDOW_NORMAL, "点击播放");
            Glide.with(one.itemView.getContext()).load(leftBean.getPic()).into(one.one_Video_left.thumbImageView);
            one.one_Video_left.thumbImageView.setScaleType(ImageView.ScaleType.CENTER);
            one.oneTitle_left.setText(leftBean.getName());
            one.oneTag_left.setText(leftBean.getNodeInfo().getName());
            one.oneDuration_left.setText(leftBean.getDuration());
            one.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else if (PAGE_TWO == flag) {
            OtherData.HotListBean bean = hotListBeanList.get(position);
            ViewHolderPool.PageTwoViewHolder two = (ViewHolderPool.PageTwoViewHolder) holder;
            if (position != 0) {
                two.twoVideo.setUp(bean.getLink(), JZVideoPlayer.SCREEN_WINDOW_NORMAL, "点击播放");
                Glide.with(two.itemView.getContext()).load(bean.getPic()).into(two.twoVideo.thumbImageView);
                two.twoVideo.thumbImageView.setScaleType(ImageView.ScaleType.CENTER);
                two.twoTitle.setText(bean.getName());
                two.twoDescription.setText(bean.getNodeInfo().getName());
                two.twoTime.setVisibility(View.VISIBLE);
                two.twoTime.setText(bean.getDuration());
            } else {
                two.textView.setText("精选爆料");
                two.imageView.getResources().getDrawable(R.drawable.iv_login_normal_background);
            }
        } else if (PAGE_THREE == flag) {

            HotNewsData.ContListBean bean = hotContList.get(position);
            ViewHolderPool.PageThreeViewHolder item = (ViewHolderPool.PageThreeViewHolder) holder;
            item.threeVideo.setUp(bean.getLink(), JZVideoPlayer.SCREEN_WINDOW_NORMAL, "点击播放");
            Glide.with(item.itemView.getContext()).load(bean.getPic()).into(item.threeVideo.thumbImageView);
            item.threeVideo.thumbImageView.setScaleType(ImageView.ScaleType.CENTER);
            item.threeTitle.setText(bean.getName());
            item.threeDetail.setText(bean.getNodeInfo().getName());
        } else {
            DetailData.PostInfoBean.ChildListBean bean = commentDataList.get(position);
            ViewHolderPool.CommentItemViewHolder item = (ViewHolderPool.CommentItemViewHolder) holder;
            Glide.with(item.itemView.getContext()).load(bean.getUserInfo().getPic()).into(item.head_left);
            item.head_left.setScaleType(ImageView.ScaleType.CENTER);
            builder.append(bean.getUserInfo().getNickname());
            builder.append("\n");
            builder.append(bean.getContent());
            item.chat_left.setText(builder.toString());
            builder.delete(0, builder.capacity());
        }

    }

    @Override
    public int getItemCount() {
        if (flag == PAGE_ONE) {
            return otherContList.size();
        } else if (flag == PAGE_TWO) {
            return hotListBeanList.size();
        } else {
            return hotContList.size();
        }
    }
}
