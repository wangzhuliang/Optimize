package wang.cn.com.optimize.ui.hot.childpager.second;

import java.util.List;

import wang.cn.com.optimize.base.BaseContract;
import wang.cn.com.optimize.bean.ImageBean;
import wang.cn.com.optimize.ui.hot.childpager.first.FirstPagerContract;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-07
 * @time: 17:14
 */
public class SecondPagerContract {

    interface view extends BaseContract.BaseView {
        void setSecondPagerDatas(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<SecondPagerContract.view> {
        void getSecondPagerDatas();
    }

}
