package wang.cn.com.optimize.ui.home.effect.viewpager;

import java.util.List;

import wang.cn.com.optimize.base.BaseContract;
import wang.cn.com.optimize.bean.CardBean;
import wang.cn.com.optimize.ui.home.effect.tantan.TanTanContract;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-07
 * @time: 16:15
 */
public class ViewPagerLayoutManagerContract {

    interface view extends BaseContract.BaseView {
        void setViewPagerLayoutManagerDatas(int[] imgs, int[] videos);
    }

    interface Presenter extends BaseContract.BasePresenter<ViewPagerLayoutManagerContract.view> {
        void getViewPagerLayoutManagerDatas();
    }
}
