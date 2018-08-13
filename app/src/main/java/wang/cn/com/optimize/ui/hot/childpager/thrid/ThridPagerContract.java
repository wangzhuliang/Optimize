package wang.cn.com.optimize.ui.hot.childpager.thrid;

import java.util.List;

import wang.cn.com.optimize.base.BaseContract;
import wang.cn.com.optimize.bean.Data;
import wang.cn.com.optimize.ui.hot.childpager.second.SecondPagerContract;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-08
 * @time: 11:14
 */
public class ThridPagerContract {

    interface view extends BaseContract.BaseView {
        void setThridPagerDatas(List<Data> dataList);
    }

    interface Presenter extends BaseContract.BasePresenter<ThridPagerContract.view> {
        void getThridPagerDatas();
    }
}
