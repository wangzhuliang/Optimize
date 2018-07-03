package wang.cn.com.optimize.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import wang.cn.com.optimize.R;
import wang.cn.com.optimize.bean.HomeBean;
import wang.cn.com.optimize.ui.home.HomeFragment;

public class HomeAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<HomeBean.PostsBean> mPostsBean = new ArrayList<>();
    private HomeOnClickListener homeOnClickListener;

    public HomeAdapter(Context context, HomeFragment homeFragment) {
        mContext = context;
        homeOnClickListener = (HomeOnClickListener) homeFragment;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Glide.with(mContext).load("http://cms.youlin365.com"+mPostsBean.get(position).getImage())
                .into(((HomeViewHolder)holder).iv_home);

        ((HomeViewHolder)holder).iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeOnClickListener.onBtnCilck(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mPostsBean.size() == 0) {
            return 0;
        }
        return mPostsBean.size();
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

    public interface HomeOnClickListener {
        void onBtnCilck(View view, int position);
    }

}
