package wang.cn.com.optimize.dagger.module;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import wang.cn.com.optimize.dagger.module.fragments.HomeFragmentModule;
import wang.cn.com.optimize.dagger.scope.PerFragment;
import wang.cn.com.optimize.ui.home.HomeFragment;

/**
 * @author woong
 */
@Module()
public abstract class AllFragmentsModule {

    @PerFragment
    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
    abstract HomeFragment contributeTopicFragmentInjector();


}
