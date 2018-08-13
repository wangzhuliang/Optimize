package wang.cn.com.optimize.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import wang.cn.com.optimize.R;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-07-19
 * @time: 16:23
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyHolder>{

    private Context mContext;
    private List<Integer> mDatas;
    private ViewGroup mParent;

    public GalleryAdapter(Context mContext, List<Integer> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        this.mParent = recyclerView;
        super.onAttachedToRecyclerView(recyclerView);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_gallery, parent, false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        // 需要增加此代码修改每一页的宽高
        //GalleryAdapterHelper.newInstance().setItemLayoutParams(mParent,
        // holder.itemView, position, getItemCount());
        holder.mView.setImageResource(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        public final ImageView mView;

        public MyHolder(View itemView) {
            super(itemView);
            mView = itemView.findViewById(R.id.iv_photo);
        }
    }

    /**
     * 获取position位置的resId
     * @param position
     * @return
     */
    public int getResId(int position) {
        return mDatas == null ? 0 : mDatas.get(position);
    }
}
