package wang.cn.com.optimize.ui.widget.calendardingding;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-21
 * @time: 16:02
 */
public interface CaledarAdapter {
    View getView(View convertView, ViewGroup parentView, CalendarBean bean);
}
