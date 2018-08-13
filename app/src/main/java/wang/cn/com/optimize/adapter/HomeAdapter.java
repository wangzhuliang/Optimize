package wang.cn.com.optimize.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import wang.cn.com.optimize.R;
import wang.cn.com.optimize.base.Constants;
import wang.cn.com.optimize.bean.HomeBean;
import wang.cn.com.optimize.ui.home.HomeFragment;

/**
 * @author wangZL
 */
public class HomeAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<HomeBean.PostsBean> mPostsBean = new ArrayList<>();
    private HomeOnClickListener homeOnClickListener;
    private View mHeaderView;
    private View mMiddleOneView;
    private View mMiddleTwoView;

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public void setMiddleOneView(View middleView) {
        mMiddleOneView = middleView;
    }

    public void setMiddleTwoView(View middleView) {
        mMiddleTwoView = middleView;
    }

    public HomeAdapter(Context context, HomeFragment homeFragment) {
        mContext = context;
        homeOnClickListener = (HomeOnClickListener) homeFragment;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return Constants.WANGZERO;
        }
        if (position == 1) {
            return Constants.WANGONE;
        }
        if (position == 2) {
            return Constants.WANGTWO;
        }
        return Constants.ACQUIESCENT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == Constants.WANGZERO){
            return new HeaderHolder(mHeaderView);
        }
        if (mMiddleOneView != null && viewType == Constants.WANGONE) {
            return new MiddleOneHolder(mMiddleOneView);
        }
        if (mMiddleTwoView != null && viewType == Constants.WANGTWO) {
            return new MiddleTwoHolder(mMiddleTwoView);
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home,
                parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (getItemViewType(position) == Constants.ACQUIESCENT) {
            Glide.with(mContext).load("http://cms.youlin365.com" +
                    mPostsBean.get(position-3).getImage())
                    .into(((HomeViewHolder) holder).iv_home);

            ((HomeViewHolder) holder).iv_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    homeOnClickListener.onBtnCilck(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mPostsBean.size()+3;
    }

    public void updateTopics(boolean clear, HomeBean homeBeans) {
        if (!clear) {
            mPostsBean.clear();
        }

        mPostsBean.addAll(homeBeans.getPosts());
        notifyDataSetChanged();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_home;

        public HomeViewHolder(View itemView) {
            super(itemView);
            iv_home = itemView.findViewById(R.id.iv_home);
        }
    }

    class HeaderHolder extends RecyclerView.ViewHolder{

        public HeaderHolder(View itemView) {
            super(itemView);

        }
    }

    class MiddleOneHolder extends RecyclerView.ViewHolder{

        public MiddleOneHolder(View itemView) {
            super(itemView);

        }
    }

    class MiddleTwoHolder extends RecyclerView.ViewHolder{

        public MiddleTwoHolder(View itemView) {
            super(itemView);

        }
    }

    public interface HomeOnClickListener {
        void onBtnCilck(View view, int position);
        void onUpDate();
    }

}
