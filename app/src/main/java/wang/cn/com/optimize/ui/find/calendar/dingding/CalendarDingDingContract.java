package wang.cn.com.optimize.ui.find.calendar.dingding;

import wang.cn.com.optimize.base.BaseContract;
import wang.cn.com.optimize.ui.find.calendar.CalendarContract;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-21
 * @time: 16:43
 */
public class CalendarDingDingContract {

    interface view extends BaseContract.BaseView {
        void setCalendarDingDingDatas();
    }

    interface Presenter extends BaseContract.BasePresenter<CalendarDingDingContract.view> {
        void getCalendarDingDingDatas();
    }
}
