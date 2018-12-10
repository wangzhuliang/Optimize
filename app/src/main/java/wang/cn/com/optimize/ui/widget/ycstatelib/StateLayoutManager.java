package wang.cn.com.optimize.ui.widget.ycstatelib;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.view.ViewStub;



/**
 * @author: wangZL
 * @description: 状态管理器
 * @projectName: Optimize
 * @date: 2018-10-10
 * @time: 10:38
 */
public class StateLayoutManager {

    final Context context;
    final ViewStub netWorkErrorVs;
    final int netWorkErrorRetryViewId;
    final ViewStub emptyDataVs;
    final int emptyDataRetryViewId;
    final ViewStub errorVs;
    final int errorRetryViewId;
    final int loadingLayoutResId;
    final int contentLayoutResId;
    final int retryViewId;
    final int emptyDataIconImageId;
    final int emptyDataTextTipId;
    final int errorIconImageId;
    final int errorTextTipId;
    final AbsViewStubLayout errorLayout;
    final AbsViewStubLayout emptyDataLayout;

    final StateFrameLayout rootFrameLayout;
    final OnShowHideViewListener onShowHideViewListener;
    final OnRetryListener onRetryListener;

    public static Builder newBuilder(Context context) {
        return new Builder(context);
    }

    StateLayoutManager(Builder builder) {
        this.context = builder.context;
        this.loadingLayoutResId = builder.loadingLayoutResId;
        this.netWorkErrorVs = builder.netWorkErrorVs;
        this.netWorkErrorRetryViewId = builder.netWorkErrorRetryViewId;
        this.emptyDataVs = builder.emptyDataVs;
        this.emptyDataRetryViewId = builder.emptyDataRetryViewId;
        this.errorVs = builder.errorVs;
        this.errorRetryViewId = builder.errorRetryViewId;
        this.contentLayoutResId = builder.contentLayoutResId;
        this.onShowHideViewListener = builder.onShowHideViewListener;
        this.retryViewId = builder.retryViewId;
        this.onRetryListener = builder.onRetryListener;
        this.emptyDataIconImageId = builder.emptyDataIconImageId;
        this.emptyDataTextTipId = builder.emptyDataTextTipId;
        this.errorIconImageId = builder.errorIconImageId;
        this.errorTextTipId = builder.errorTextTipId;
        this.errorLayout = builder.errorLayout;
        this.emptyDataLayout = builder.emptyDataLayout;

        //创建帧布局
        rootFrameLayout = new StateFrameLayout(this.context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootFrameLayout.setLayoutParams(layoutParams);

        //设置状态管理器
        //rootFrameLayout.setStatusLayoutManager(this);
    }

    public static final class Builder {

        private Context context;
        private int loadingLayoutResId;
        private int contentLayoutResId;
        /**
         * ViewStub 是一个轻量级的View，没有尺寸，不绘制任何东西
         * 实现View的延迟加载，避免资源的浪费，减少渲染时间，在需要的时候才加载View
         * ViewStub所要替代的layout文件中不能有<merge>标签
         * ViewStub在加载完后会被移除，或者说是被加载进来的layout替换掉了
         */
        private ViewStub netWorkErrorVs;
        private int netWorkErrorRetryViewId;
        private ViewStub emptyDataVs;
        private int emptyDataRetryViewId;
        private ViewStub errorVs;
        private int errorRetryViewId;
        private int retryViewId;
        private int emptyDataIconImageId;
        private int emptyDataTextTipId;
        private int errorIconImageId;
        private int errorTextTipId;
        private AbsViewStubLayout errorLayout;
        private AbsViewStubLayout emptyDataLayout;
        private OnShowHideViewListener onShowHideViewListener;
        private OnRetryListener onRetryListener;

        Builder(Context context) {
            this.context = context;
        }

        /**
         * 自定义加载布局
         */
        public Builder loadingView(@LayoutRes int loadingLayoutResId) {
            this.loadingLayoutResId = loadingLayoutResId;
            return this;
        }

        /**
         * 自定义网络错误布局
         */
        public Builder netWorkErrorView(@LayoutRes int newWorkErrorId) {
            netWorkErrorVs = new ViewStub(context);
            netWorkErrorVs.setLayoutResource(newWorkErrorId);
            return this;
        }

        /**
         * 自定义加载空数据布局
         */
        public Builder emptyDataView(@LayoutRes int noDataViewId) {
            emptyDataVs = new ViewStub(context);
            emptyDataVs.setLayoutResource(noDataViewId);
            return this;
        }

        /**
         * 自定义加载错误布局
         */
        public Builder errorView(@LayoutRes int errorViewId) {
            errorVs = new ViewStub(context);
            errorVs.setLayoutResource(errorViewId);
            return this;
        }

        /**
         * 自定义加载内容正常布局
         */
        public Builder contentView(@LayoutRes int contentLayoutResId) {
            this.contentLayoutResId = contentLayoutResId;
            return this;
        }

        public Builder errorLayout(AbsViewStubLayout errorLayout) {
            this.errorLayout = errorLayout;
            this.errorVs = errorLayout.getLayoutVs();
            return this;
        }

        public Builder emptyDataLayout(AbsViewStubLayout emptyDataLayout) {
            this.emptyDataLayout = emptyDataLayout;
            this.emptyDataVs = emptyDataLayout.getLayoutVs();
            return this;
        }

        public Builder netWorkErrorRetryViewId(@LayoutRes int netWorkErrorRetryViewId) {
            this.netWorkErrorRetryViewId = netWorkErrorRetryViewId;
            return this;
        }

        public Builder emptyDataRetryViewId(@LayoutRes int emptyDataRetryViewId) {
            this.emptyDataRetryViewId = emptyDataRetryViewId;
            return this;
        }

        public Builder errorRetryViewId(@LayoutRes int errorRetryViewId) {
            this.errorRetryViewId = errorRetryViewId;
            return this;
        }

        public Builder retryViewId(@LayoutRes int retryViewId) {
            this.retryViewId = retryViewId;
            return this;
        }

        public Builder emptyDataIconImageId(@LayoutRes int emptyDataIconImageId) {
            this.emptyDataIconImageId = emptyDataIconImageId;
            return this;
        }

        public Builder emptyDataTextTipId(@LayoutRes int emptyDataTextTipId) {
            this.emptyDataTextTipId = emptyDataTextTipId;
            return this;
        }

        public Builder errorIconImageId(@LayoutRes int errorIconImageId) {
            this.errorIconImageId = errorIconImageId;
            return this;
        }

        public Builder errorTextTipId(@LayoutRes int errorTextTipId) {
            this.errorTextTipId = errorTextTipId;
            return this;
        }

        /**
         * 为状态View显示隐藏监听事件
         * @param listener                  listener
         * @return
         */
        public Builder onShowHideViewListener(OnShowHideViewListener listener) {
            this.onShowHideViewListener = listener;
            return this;
        }

        /**
         * 为重试加载按钮的监听事件
         * @param onRetryListener           listener
         * @return
         */
        public Builder onRetryListener(OnRetryListener onRetryListener) {
            this.onRetryListener = onRetryListener;
            return this;
        }

        /**
         * 创建对象
         * @return
         */
        /*public StateLayoutManager build() {
            return new StateLayoutManager(this, context);
        }*/

    }

}
