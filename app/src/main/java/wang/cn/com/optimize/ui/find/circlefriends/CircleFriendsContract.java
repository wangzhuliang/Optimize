package wang.cn.com.optimize.ui.find.circlefriends;

import java.util.List;

import wang.cn.com.optimize.base.BaseContract;
import wang.cn.com.optimize.bean.Data;
import wang.cn.com.optimize.ui.hot.childpager.thrid.ThridPagerContract;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-13
 * @time: 16:44
 */
public class CircleFriendsContract {

    interface view extends BaseContract.BaseView {
        void setCircleFriendsDatas(List<Data> dataList);
    }

    interface Presenter extends BaseContract.BasePresenter<CircleFriendsContract.view> {
        void getCircleFriendsDatas();
    }
}
