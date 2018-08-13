package wang.cn.com.optimize.ui.myinterface;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-07-16
 * @time: 11:19
 */
public interface OnViewPagerListener {

    /**
     * 初始化完成
     */
    void onInitComplete();

    /**
     * 释放的监听
     * @param isNext
     * @param position
     */
    void onPageRelease(boolean isNext,int position);

    /**
     * 选中的监听以及判断是否滑动到底部
     * @param position
     * @param isBottom
     */
    void onPageSelected(int position,boolean isBottom);
}
