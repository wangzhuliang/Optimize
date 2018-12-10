package wang.cn.com.optimize.ui.find.coordinatortablayout;

import android.support.annotation.IdRes;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.ImageView;

import wang.cn.com.optimize.ui.widget.wangcoordinatyorwidget.nested.ShopInfoDetailLayout;

/**
 * @author: wangZL
 * @description: 分为三个状态 滑动 正常 拉伸
 * nestedViewPager 导致的快速下拉到底 页面到底了但是时机处理没到底的异常 偶现
 * @projectName: Optimize
 * @date: 2018-10-22
 * @time: 17:52
 */
public class AppBarLayoutScrollBehavior extends AppBarLayout.Behavior{

    public static final String TAG = AppBarLayoutScrollBehavior.class.getSimpleName();

    private OnAppBarListener mListener;

    private boolean mIsBeingDragged = false;
    private boolean mInited = false;

    private CoordinatorLayout mParent;
    private AppBarLayout mAppBarLayout;

    /**
     * title相关
     */
    private View mTitleSearchBtn;
    private View mTitlePindanBtn;
    private View mTitleSearchTextView;
    private ConstraintLayout mTitleLayout;
    private ConstraintLayout mTitleBarLayout;
    private ImageView mTitleBlurFrontLayout;
    private View mTitleBottomView;

    /**
     * 店铺顶部
     */
    private View mShopHeaderView;

    /**
     * 店铺图片
     */
    private View mImageViewShop;
    private View mImageViewShopLike;

    /**
     * 店铺相关信息 折叠展示
     */
    private ShopInfoDetailLayout mShopInfoLayout;

    /**
     * 底部 viewpager导航栏
     */
    //private FixedIndicatorView mIndicatorView;

    /**
     * 底部收起按钮
     */
    private ImageView mHeadsUpArrow;


    public interface OnAppBarListener {
        void onTitleBarItemClick(@IdRes int id);

        void initEnd(int miniTopHeight);
    }
}
