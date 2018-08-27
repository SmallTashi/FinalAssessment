package com.mredrock.tashi.finalexam.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.mredrock.tashi.finalexam.R;
import com.mredrock.tashi.finalexam.data.DetailData;
import com.mredrock.tashi.finalexam.data.HotNewsData;
import com.mredrock.tashi.finalexam.data.OtherData;
import com.mredrock.tashi.finalexam.pageone.PageOneContract;
import com.mredrock.tashi.finalexam.pagethree.PageThreeContract;
import com.mredrock.tashi.finalexam.pagetwo.PageTwoContract;
import com.mredrock.tashi.finalexam.tools.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

public class PageRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private int flag;
    public static final int PAGE_ONE = 222;
    public static final int PAGE_THREE = 444;
    public static final int PAGE_TWO = 333;
    public static final int COMMENT = 555;
    private int pos = 0;
    private List<String> s  = new ArrayList<>();
    private List<HotNewsData.DataListBean> dataListBeanList;
    private List<OtherData.ContListBean> otherContList;
    private List<OtherData.ContListBean> hotListBeanList;
    private List<HotNewsData.ContListBean> hotContList = new ArrayList<>();
    private List<DetailData.PostInfoBean.ChildListBean> commentDataList;
    private OnItemClickListener mOnItemClickListener = null;

    public PageRecyclerAdapter(List<?> list, int f) {
        this.flag = f;
        hotListBeanList = new ArrayList<>();
        switch (f) {
            case PAGE_ONE:
                otherContList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    OtherData.ContListBean bean = (OtherData.ContListBean) list.get(i);
                    s.add(bean.getContId());
                    otherContList.add(bean);
                }
                break;
            case PAGE_TWO:
                for (int i = 0; i < list.size(); i++) {
                    OtherData.ContListBean hot = (OtherData.ContListBean) list.get(i);
                    s.add(hot.getContId());
                    hotListBeanList.add(hot);
                }
                break;
            case PAGE_THREE:
                dataListBeanList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    HotNewsData.DataListBean data = (HotNewsData.DataListBean) list.get(i);
                    dataListBeanList.add(data);
                }
                hotContList.addAll(dataListBeanList.get(pos).getContList());
                for (int i = 0; i < hotContList.size(); i++) {
                    s.add(hotContList.get(i).getContId());
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

    public void refreshOne(PageOneContract.callback callback,OtherData data){
        otherContList.clear();
        s.clear();
        List<OtherData.ContListBean> oneList = data.getContList();
        for (int i = 0; i < oneList.size(); i++) {
            OtherData.ContListBean bean = oneList.get(i);
            s.add(0,bean.getContId());
            otherContList.add(bean);
        }

        notifyItemRangeInserted(0,otherContList.size());
        notifyDataSetChanged();
        callback.isFinish();
    }

    public void refreshTwo(PageTwoContract.callback callback , OtherData data){
        hotListBeanList.clear();
        s.clear();
        List<OtherData.ContListBean> twoList = data.getContList();
        for (int i = 0; i < twoList.size(); i++) {
            OtherData.ContListBean bean = twoList.get(i);
            s.add(bean.getContId());
            hotListBeanList.add(0, bean);
        }

        notifyItemRangeInserted(0,hotListBeanList.size());
        notifyDataSetChanged();
        callback.isFinish();
    }

    public void refreshThree(PageThreeContract.callback callback , HotNewsData data){
        dataListBeanList.clear();
        s.clear();
        pos++;
        List<HotNewsData.DataListBean> threeList = data.getDataList();
        dataListBeanList.addAll(threeList);
        hotContList.clear();
        if(pos>=16){
            pos=5;
        }
        List<HotNewsData.ContListBean> bean = new ArrayList<>();
        bean=dataListBeanList.get(pos++).getContList();
        for (int i = 0; i < bean.size(); i++) {
            s.add(bean.get(i).getContId());
        }
        hotContList.addAll(0, bean);
        notifyItemRangeInserted(0,hotContList.size());
        notifyDataSetChanged();
        callback.isFinish();
    }


    public void loadMoreOne(PageOneContract.callback callback,OtherData data){
        List<OtherData.ContListBean> oneList = data.getContList();
        otherContList.addAll(oneList);
        for (int i = 0; i < oneList.size(); i++) {
            s.add(oneList.get(i).getContId());
        }
        notifyItemRangeInserted(0,otherContList.size());
        notifyDataSetChanged();
        callback.isFinish();
        }
    public void loadMoreTwo(PageTwoContract.callback callback,OtherData data){
        List<OtherData.ContListBean>twoList=data.getContList();
        hotListBeanList.addAll(twoList);
        for (int i = 0; i < twoList.size(); i++) {
            s.add(twoList.get(i).getContId());
        }
        notifyItemRangeInserted(0,hotListBeanList.size());
        notifyDataSetChanged();
        callback.isFinish();
    }

    public void loadMoreThree(PageThreeContract.callback callback){
        int currentNumber = hotContList.size();
        pos++;
        if(pos>=16){
            pos=5;
        }
        HotNewsData.DataListBean more = dataListBeanList.get(pos);
        hotContList.addAll(more.getContList());
        for (int i = 0; i < more.getContList().size(); i++) {
            s.add(more.getContList().get(i).getContId());
        }
        notifyItemRangeInserted(0,hotContList.size()-currentNumber);
        notifyDataSetChanged();
        callback.isFinish();
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (flag == PAGE_ONE) {
            final OtherData.ContListBean leftBean = otherContList.get(position);
            ViewHolderPool.PageOneViewHolder one = (ViewHolderPool.PageOneViewHolder) holder;
            one.itemView.setTag(leftBean.getContId());
            Glide.with(one.itemView.getContext())
                    .load(leftBean.getPic())
                    .centerCrop()
                    .placeholder(one.itemView.getResources().getColor(R.color.white))
                    .into(one.one_Video_left);
            one.one_Video_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(leftBean.getContId());
                }
            });
            one.oneTitle_left.setText(leftBean.getName());
            one.oneTag_left.setText(leftBean.getNodeInfo().getName());
            one.oneDuration_left.setText(leftBean.getDuration());

        } else if (PAGE_TWO == flag) {
            final OtherData.ContListBean bean = hotListBeanList.get(position);
            final ViewHolderPool.PageTwoViewHolder two = (ViewHolderPool.PageTwoViewHolder) holder;
            two.itemView.setTag(bean.getContId());
            if (position != 0) {
                Glide.with(two.itemView.getContext())
                        .load(bean.getPic())
                        .centerCrop()
                        .placeholder(two.itemView.getResources().getColor(R.color.white))
                        .into(two.twoVideo);
                two.twoTitle.setText(bean.getName());
                two.twoVideo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(bean.getContId());
                    }
                });
                two.twoDescription.setText(bean.getNodeInfo().getName());
                two.twoTime.setVisibility(View.VISIBLE);
                two.twoTime.setText(bean.getDuration());

            } else {
                two.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.askCamora();
                    }
                });
                two.textView.setText("精选爆料");
                two.imageView.getResources().getDrawable(R.drawable.iv_login_normal_background);
            }
        } else if (PAGE_THREE == flag) {
            final HotNewsData.ContListBean bean = hotContList.get(position);
            ViewHolderPool.PageThreeViewHolder item = (ViewHolderPool.PageThreeViewHolder) holder;
            item.itemView.setTag(bean.getContId());
            item.threeVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(bean.getContId());
                }
            });
            Glide.with(item.itemView.getContext())
                    .load(bean.getPic())
                    .centerCrop()
                    .placeholder(item.itemView.getResources().getColor(R.color.white))
                    .into(item.threeVideo);
            item.threeTitle.setText(bean.getName());
            item.threeVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(bean.getContId());
                }
            });
            item.threeDetail.setText(bean.getNodeInfo().getName());

        } else if(flag==COMMENT){
            DetailData.PostInfoBean.ChildListBean bean = commentDataList.get(position);
            ViewHolderPool.CommentItemViewHolder item = (ViewHolderPool.CommentItemViewHolder) holder;
            Glide.with(item.itemView.getContext())
                    .load(bean.getUserInfo().getPic())
                    .transform(new GlideRoundTransform(item.itemView.getContext(),10))
                    .centerCrop()
                    .placeholder(item.itemView.getResources().getColor(R.color.white))
                    .into(item.head_left);
            item.time.setText(bean.getPubTime());
            item.chat_left.setText(bean.getUserInfo().getNickname());
            item.head_left.setScaleType(ImageView.ScaleType.CENTER);
            item.comment.setText(bean.getContent());
        }

    }

    @Override
    public int getItemCount() {
        if (flag == PAGE_ONE) {
            return otherContList.size();
        } else if (flag == PAGE_TWO) {
            return hotListBeanList.size();
        }else if(flag==COMMENT){
            return commentDataList.size();
        }
        else {
            return hotContList.size();
        }
    }

    //define interface
    public interface OnItemClickListener {
        void onItemClick(String s);
        void askCamora();

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
