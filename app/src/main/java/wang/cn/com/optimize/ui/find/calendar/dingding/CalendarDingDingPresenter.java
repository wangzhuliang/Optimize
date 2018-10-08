package wang.cn.com.optimize.ui.find.calendar.dingding;

import javax.inject.Inject;

import wang.cn.com.optimize.base.BasePresenter;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-21
 * @time: 16:44
 */
public class CalendarDingDingPresenter extends BasePresenter<CalendarDingDingContract.view>
        implements CalendarDingDingContract.Presenter{

    @Inject
    public CalendarDingDingPresenter() {

    }

    @Override
    public void getCalendarDingDingDatas() {
        mView.setCalendarDingDingDatas();
    }
}
