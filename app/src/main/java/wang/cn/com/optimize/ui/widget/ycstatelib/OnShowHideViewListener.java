package wang.cn.com.optimize.ui.widget.ycstatelib;

import android.view.View;

/**
 * @author: wangZL
 * @description: 为状态View显示隐藏监听事件
 * @projectName: Optimize
 * @date: 2018-10-10
 * @time: 11:05
 */
public interface OnShowHideViewListener {

    /**
     * show
     * @param view  view
     * @param id  view对象id
     */
    void onShowView(View view, int id);


    /**
     *  hide
     * @param view view
     * @param id  view对应id
     */
    void onHideView(View view, int id);
}
