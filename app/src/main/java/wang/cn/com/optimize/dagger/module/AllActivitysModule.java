package wang.cn.com.optimize.dagger.module;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import wang.cn.com.optimize.dagger.module.activitys.HomeDetailActivityModule;
import wang.cn.com.optimize.dagger.scope.PerActivity;
import wang.cn.com.optimize.ui.home.homedetail.HomeDetailActivity;

/**
 * @author woong
 */
@Module()
public abstract class AllActivitysModule {
    @PerActivity
    @ContributesAndroidInjector(modules = HomeDetailActivityModule.class)
    abstract HomeDetailActivity contributeTopicDetailActivityInjector();

}
