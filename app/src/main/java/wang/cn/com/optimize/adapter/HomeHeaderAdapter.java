package wang.cn.com.optimize.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import wang.cn.com.optimize.bean.HomeBean;



/**
 * Created by hahaha on 2017/6/20.
 */

public class HomeHeaderAdapter extends PagerAdapter {

    private HomeBean mData;
    private Activity mContext;

    public HomeHeaderAdapter(HomeBean data, Activity context) {
        this.mData = data;
        this.mContext = context;
    }

    /** 返回有多少页 */
    @Override
    public int getCount() {
        // 返回一个这么大的值是为了实现无限循环
        return mData.getPosts().size() * 1000;
    }

    /** 用于判断ViewPager是否可以复用 */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        // 固定写法
        return view == object;
    }

    /**
     * 跟ListView中的Adpater中的getView方法类似，用于创建一个Item
     * @param container ViewPager
     * @param position 要生成item的位置
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        position = position % mData.getPosts().size();
        Glide.with(mContext).load("http://cms.youlin365.com"+
                mData.getPosts().get(position).getImage())
                .into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        // 把一个item添加到ViewPager容器
        container.addView(imageView);
        return imageView;
    }

    /**
     * 销毁一个Item
     * @param container ViewPager
     * @param position 要销毁item的位置
     * @param object instantiateItem方法的返回值
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

}
