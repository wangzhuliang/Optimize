package wang.cn.com.optimize.ui.widget.tantan;


import wang.cn.com.optimize.utils.tantan.ReItemTouchHelper;

/**
 * Created by linchen on 2018/2/6.
 * mail: linchen@sogou-inc.com
 */

public class CardSetting {
    public static final int DEFAULT_SHOW_ITEM = 3;

    public static final float DEFAULT_SCALE = 0.1f;

    public static final int DEFAULT_TRANSLATE = 14;

    public static final float DEFAULT_ROTATE_DEGREE = 15f;
    private OnSwipeCardListener mListener;

    public int getShowCount() {
        return DEFAULT_SHOW_ITEM;
    }

    /**
     * 卡片缩放递减的值，默认为0.1f：
     */
    public float getCardScale() {
        return DEFAULT_SCALE;
    }

    /**
     * 卡片展示数量：
     */
    public int getCardTranslateDistance() {
        return DEFAULT_TRANSLATE;
    }

    /**
     * 移动过程中最大卡片旋转值：
     */
    public float getCardRotateDegree() {
        return DEFAULT_ROTATE_DEGREE;
    }

    /**
     * 上下左右滑动控制，默认四个方向都可以滑动：
     */
    public int getSwipeDirection() {
        return ReItemTouchHelper.LEFT | ReItemTouchHelper.RIGHT
                | ReItemTouchHelper.UP | ReItemTouchHelper.DOWN;
    }

    /**
     * 上下左右滑出控制，默认四个方向都可以滑出:
     */
    public int couldSwipeOutDirection() {
        return ReItemTouchHelper.LEFT | ReItemTouchHelper.RIGHT
                | ReItemTouchHelper.UP | ReItemTouchHelper.DOWN;
    }

    /**
     * 控制滑动可以滑出的阈值，默认是RecyclerView的宽度*0.3f：
     */
    public float getSwipeThreshold() {
        return 0.3f;
    }

    /**
     * 是否开启硬件加速：
     */
    public boolean enableHardWare() {
        return true;
    }

    /**
     * 是否循环，默认为循环：
     */
    public boolean isLoopCard() {
        return true;
    }

    /**
     * 控制滑出时间：
     */
    public int getSwipeOutAnimDuration() {
        return 400;
    }

    /**
     * 修改卡片堆叠方式，默认为从下往上：
     */
    public int getStackDirection() {
        return ReItemTouchHelper.DOWN;
    }

    public void setSwipeListener(OnSwipeCardListener listener) {
        mListener = listener;
    }

    public OnSwipeCardListener getListener() {
        return mListener;
    }
}
