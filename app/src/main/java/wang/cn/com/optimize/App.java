package wang.cn.com.optimize;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.blankj.utilcode.util.Utils;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.DaggerAppCompatActivity;
import dagger.android.support.HasSupportFragmentInjector;
import wang.cn.com.optimize.dagger.component.DaggerAppComponent;
import wang.cn.com.optimize.domain.ApiManager;

public class App extends Application implements HasSupportFragmentInjector,HasActivityInjector{

    private static App mInstance;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjectorActivity;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjectorSupportFragment;

    private ApiManager mApiManager = null;

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static <T> T apiService(Class<T> clz) {
        return getInstance().mApiManager.getService(clz);
    }

    public static App getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mApiManager = new ApiManager();
        Utils.init(mInstance);
        DaggerAppComponent.create().inject(this);
        initThirdService();
    }

    /**
     * 初始化一些三方服务
     */
    private void initThirdService() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                //设置线程的优先级；不与主线程抢资源
                //ZXingLibrary.initDisplayOpinion(appContext);
            }
        }.start();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjectorActivity;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjectorSupportFragment;
    }
}
