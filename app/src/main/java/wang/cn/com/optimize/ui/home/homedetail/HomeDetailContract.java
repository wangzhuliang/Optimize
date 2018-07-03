package wang.cn.com.optimize.ui.home.homedetail;

import wang.cn.com.optimize.base.BaseContract;
import wang.cn.com.optimize.bean.HomeBean;

public class HomeDetailContract {

    interface View extends BaseContract.BaseView {
        void updateHomeDetailData(HomeBean homeBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getHomeDetails();
    }
}
