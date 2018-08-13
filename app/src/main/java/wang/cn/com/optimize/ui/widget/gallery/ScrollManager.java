package wang.cn.com.optimize.ui.widget.gallery;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

import wang.cn.com.optimize.utils.OsUtil;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-07-19
 * @time: 11:16
 */
public class ScrollManager {

    private GalleryRecyclerView galleryRecyclerView;

    private int mPosition = 0;

    /**
     * 使偏移量为左边距 + 左边Item的可视部分宽度
     */
    private int mConsumeX = 0;
    private int mConsumeY = 0;

    private int slideDirct = SLIDE_RIGHT;

    /**
     * 左滑
     */
    private static final int SLIDE_LEFT = 1;
    /**
     * 右滑
     */
    private static final int SLIDE_RIGHT = 2;
    /**
     * 上滑
     */
    private static final int SLIDE_TOP = 3;
    /**
     * 下滑
     */
    private static final int SLIDE_BOTTOM = 4;

    public ScrollManager(GalleryRecyclerView galleryRecyclerView) {
        this.galleryRecyclerView = galleryRecyclerView;
    }

    /**
     * 初始化SnapHelper
     * @param helper
     */
    public void initSnapHelper(int helper) {
        switch (helper) {
            case GalleryRecyclerView.LinearySnapHelper:
                LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
                mLinearSnapHelper.attachToRecyclerView(galleryRecyclerView);
                break;
            case GalleryRecyclerView.PagerSnapHelper:
                PagerSnapHelper mPagerSnapHelper = new PagerSnapHelper();
                mPagerSnapHelper.attachToRecyclerView(galleryRecyclerView);
                break;
            default:
                break;
        }
    }

    /**
     * 监听RecyclerView的滑动
     */
    public void initScrollListener() {
        GalleryScrollerListener mScrollerListener = new GalleryScrollerListener();
        galleryRecyclerView.addOnScrollListener(mScrollerListener);
    }

    public void updateConsume() {
        mConsumeX += OsUtil.dpToPx(galleryRecyclerView.getDecoration().mLeftPageVisibleWidth +
                galleryRecyclerView.getDecoration().mPageMargin * 2);
        mConsumeX += OsUtil.dpToPx(galleryRecyclerView.getDecoration().mLeftPageVisibleWidth +
                galleryRecyclerView.getDecoration().mPageMargin * 2);
    }

    public class GalleryScrollerListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE){

            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (galleryRecyclerView.getOrientation() == LinearLayoutManager.HORIZONTAL) {
                onHorizontalScroll(recyclerView, dx);
            } else {
                onVerticalScroll(recyclerView, dy);
            }
        }
    }

    /**
     * 垂直滑动
     * @param recyclerView
     * @param dy
     */
    private void onVerticalScroll(final RecyclerView recyclerView, int dy) {
        mConsumeY += dy;

        if (dy > 0) {
            slideDirct = SLIDE_BOTTOM;
        } else {
            slideDirct = SLIDE_TOP;
        }

        // 让RecyclerView测绘完成后再调用，避免GalleryAdapterHelper.mItemHeight的值拿不到
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                int shouldConsumeY = galleryRecyclerView.getDecoration().mItemConsumeY;
                // 获取当前的位置
                int itemPosition = galleryRecyclerView.getLinearLayoutManager().
                        findFirstVisibleItemPosition();
                int position = getPosition(mConsumeY, shouldConsumeY);
                mPosition = position == 0 ? 0 : itemPosition + 1;

                if (position != 0) {
                    position = itemPosition + 1;
                }

                // 位置浮点值（即总消耗距离 / 每一页理论消耗距离 = 一个浮点型的位置值）
                float offset = (float) mConsumeY / (float) shouldConsumeY;
                // 避免offset值取整时进一，从而影响了percent值
                if (offset >= itemPosition + 1 && slideDirct == SLIDE_BOTTOM) {
                    return;
                }
                // 获取当前页移动的百分值
                float percent = offset - ((int) offset);

                // 设置动画变化
                galleryRecyclerView.getAnimManager().setAnimation(recyclerView, position, percent);
            }
        });
    }

    /**
     * 水平滑动
     *
     * @param recyclerView
     * @param dx
     */
    private void onHorizontalScroll(final RecyclerView recyclerView, final int dx) {
        mConsumeX += dx;

        if (dx > 0) {
            // 右滑
            slideDirct = SLIDE_RIGHT;
        } else {
            // 左滑
            slideDirct = SLIDE_LEFT;
        }

        // 让RecyclerView测绘完成后再调用，避免GalleryAdapterHelper.mItemWidth的值拿不到
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                int shouldConsumeX = galleryRecyclerView.getDecoration().mItemConsumeX;
                // 获取当前的位置
                int itemPosition = galleryRecyclerView.getLinearLayoutManager().
                        findFirstVisibleItemPosition();
                int position = getPosition(mConsumeX, shouldConsumeX);
                mPosition = position == 0 ? 0 : itemPosition + 1;

                if (position != 0) {
                    position = itemPosition + 1;
                }

                // 位置浮点值（即总消耗距离 / 每一页理论消耗距离 = 一个浮点型的位置值）
                float offset = (float) mConsumeX / (float) shouldConsumeX;

                // 获取当前页移动的百分值
                float percent = offset - ((int) offset);

                // 设置动画变化
                galleryRecyclerView.getAnimManager().setAnimation(recyclerView, ((int) offset), percent);
            }
        });
    }

    /**
     * 获取位置
     *
     * @param mConsumeX      实际消耗距离
     * @param shouldConsumeX 理论消耗距离
     * @return
     */
    private int getPosition(int mConsumeX, int shouldConsumeX) {
        float offset = (float) mConsumeX / (float) shouldConsumeX;
        // 四舍五入获取位置
        return Math.round(offset);
    }

    public int getPosition() {
        return mPosition;
    }
}
