package wang.cn.com.optimize.ui.find.animation.property_animation;

import android.view.animation.Interpolator;

/*******************************************************************
 *    * * * *   * * * *   *     *       Created by OCN.Yang
 *    *     *   *         * *   *       Time:2018/3/26 15:34.
 *    *     *   *         *   * *       Email address:ocnyang@gmail.com
 *    * * * *   * * * *   *     *.Yang  Web site:www.ocnyang.com
 *******************************************************************/


public class SpeedUpInterpolator implements Interpolator {
    private final float mFactor;
    private final double mDoubleFactor;

    public SpeedUpInterpolator() {
        mFactor = 1.0f;
        mDoubleFactor = 2.0;
    }

    /**
     * input  0到1.0。表示动画当前点的值，0表示开头，1表示结尾。
     * return  插值。值可以大于1超出目标值，也可以小于0突破低值。
     * @param v
     * @return
     */
    @Override
    public float getInterpolation(float v) {
        //实现核心代码块
        if (mFactor == 1.0f) {
            return v * v;
        } else {
            return (float) Math.pow(v, mDoubleFactor);
        }
    }

}
