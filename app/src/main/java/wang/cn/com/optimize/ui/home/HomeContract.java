package wang.cn.com.optimize.ui.home;

import java.util.ArrayList;

import wang.cn.com.optimize.base.BaseContract;
import wang.cn.com.optimize.bean.HomeBean;

public class HomeContract {

    interface View extends BaseContract.BaseView {
        void updateHomeData(String order, HomeBean homeBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getHomeNews(String order);
    }
}
