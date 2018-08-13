package wang.cn.com.optimize.ui.hot.childpager.first;

import java.util.List;

import wang.cn.com.optimize.base.BaseContract;
import wang.cn.com.optimize.bean.ImageBean;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-07
 * @time: 16:54
 */
public class FirstPagerContract {

    interface view extends BaseContract.BaseView {
        void setFristPagerDatas(List<ImageBean> datas);
    }

    interface Presenter extends BaseContract.BasePresenter<FirstPagerContract.view> {
        void getFristPagerDatas();
    }
}
