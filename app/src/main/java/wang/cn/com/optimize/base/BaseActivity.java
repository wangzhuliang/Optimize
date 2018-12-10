package wang.cn.com.optimize.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.ns.yc.ycstatelib.StateLayoutManager;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wang.cn.com.optimize.R;

/**
 * @author wang
 */
public abstract class BaseActivity extends RxAppCompatActivity implements BaseContract.BaseView{

    KProgressHUD mKProgressHUD;
    private Unbinder unbinder;

    protected StateLayoutManager statusLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wang_base_view);
        initStatusLayout();
        initBaseView();
        //int layoutId = getLayoutId();
        //setContentView(layoutId);
        unbinder = ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract void initStatusLayout();

    private void initBaseView() {
        LinearLayout ll_main = (LinearLayout) findViewById(R.id.ll_main);
        ll_main.addView(statusLayoutManager.getRootLayout());
    }
    //protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetworkUtils.isConnected()) {
            showNoNet();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showLoading() {
        mKProgressHUD = KProgressHUD.create(this);
        mKProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

    @Override
    public void hideLoading() {
        if (mKProgressHUD != null) {
            mKProgressHUD.dismiss();
        }
    }

    @Override
    public void showSuccess() {
        statusLayoutManager.showContent();
    }

    @Override
    public void showFailed() {
        ToastUtils.showShort(R.string.request_api_failed);
        statusLayoutManager.showError();
    }

    @Override
    public void showNoNet() {
        ToastUtils.showShort(R.string.network_connect_error);
        statusLayoutManager.showNetWorkError();
    }

    @Override
    public void onRetry() {
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

}
