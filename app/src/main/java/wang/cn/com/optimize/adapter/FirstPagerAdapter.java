package wang.cn.com.optimize.adapter;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import wang.cn.com.optimize.R;
import wang.cn.com.optimize.bean.ImageBean;
import wang.cn.com.optimize.utils.DensityUtil;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-07-30
 * @time: 14:39
 */
public class FirstPagerAdapter extends BaseQuickAdapter<ImageBean,FirstPagerAdapter.FirstPagerHolder>{


    public FirstPagerAdapter(int layoutResId, @Nullable List<ImageBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(FirstPagerHolder helper, ImageBean item) {
        helper.addOnClickListener(R.id.imageView);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) helper.itemView);
        constraintSet.setDimensionRatio(R.id.imageView, "H," +
                DensityUtil.getScreenSize(mContext).x + ":" +
                DensityUtil.getScreenSize(mContext).y);
        ImageView imageView = helper.getView(R.id.imageView);
        //imageView.setImageResource(item.getImage());
        Picasso.get().load(item.getImage()).into(imageView);
        helper.setText(R.id.textView, item.getName() + helper.getAdapterPosition());
    }

    class FirstPagerHolder extends BaseViewHolder{

        public FirstPagerHolder(View view) {
            super(view);
        }
    }


}
