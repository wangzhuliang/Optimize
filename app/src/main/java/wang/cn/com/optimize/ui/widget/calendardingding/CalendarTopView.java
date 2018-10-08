package wang.cn.com.optimize.ui.widget.calendardingding;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-21
 * @time: 15:58
 */
public interface CalendarTopView {

    int[] getCurrentSelectPositon();

    int getItemHeight();

    void setCaledarTopViewChangeListener(CaledarTopViewChangeListener listener);
}
