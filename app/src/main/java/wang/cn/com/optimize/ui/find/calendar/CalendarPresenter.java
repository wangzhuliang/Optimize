package wang.cn.com.optimize.ui.find.calendar;

import javax.inject.Inject;

import wang.cn.com.optimize.base.BaseDaggerActivity;
import wang.cn.com.optimize.base.BasePresenter;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-21
 * @time: 11:33
 */
public class CalendarPresenter extends BasePresenter<CalendarContract.view>
        implements CalendarContract.Presenter {

    @Inject
    public CalendarPresenter() {

    }

    @Override
    public void getCalendarDatas() {
        mView.setCalendarDatas();
    }
}
