package wang.cn.com.optimize.ui.home.effect.viewpager;

import javax.inject.Inject;

import wang.cn.com.optimize.R;
import wang.cn.com.optimize.base.BasePresenter;
import wang.cn.com.optimize.ui.home.effect.tantan.TanTanContract;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-07
 * @time: 16:19
 */
public class ViewPagerLayoutManagerPresenter extends BasePresenter<ViewPagerLayoutManagerContract.view>
        implements ViewPagerLayoutManagerContract.Presenter{

    private int[] imgs = {R.mipmap.img_video_1,R.mipmap.img_video_2};
    private int[] videos = {R.raw.video_1,R.raw.video_2};

    @Inject
    public ViewPagerLayoutManagerPresenter() {

    }

    @Override
    public void getViewPagerLayoutManagerDatas() {

        mView.setViewPagerLayoutManagerDatas(imgs,videos);
    }
}
