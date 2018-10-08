package wang.cn.com.optimize.ui.find.calendar;

import java.util.List;

import wang.cn.com.optimize.base.BaseContract;
import wang.cn.com.optimize.bean.Data;
import wang.cn.com.optimize.ui.find.circlefriends.CircleFriendsContract;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-21
 * @time: 11:32
 */
public class CalendarContract {
    interface view extends BaseContract.BaseView {
        void setCalendarDatas();
    }

    interface Presenter extends BaseContract.BasePresenter<CalendarContract.view> {
        void getCalendarDatas();
    }
}
