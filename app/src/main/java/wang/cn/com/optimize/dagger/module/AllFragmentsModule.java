package wang.cn.com.optimize.dagger.module;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import wang.cn.com.optimize.dagger.module.fragments.FirstPagerFragmentModule;
import wang.cn.com.optimize.dagger.module.fragments.HomeFragmentModule;
import wang.cn.com.optimize.dagger.module.fragments.SecondPagerFragmentModule;
import wang.cn.com.optimize.dagger.module.fragments.ThridPagerFragmentModule;
import wang.cn.com.optimize.dagger.scope.PerFragment;
import wang.cn.com.optimize.ui.home.HomeFragment;
import wang.cn.com.optimize.ui.hot.HotFragment;
import wang.cn.com.optimize.ui.hot.childpager.first.FirstPagerFragment;
import wang.cn.com.optimize.ui.hot.childpager.second.SecondPagerFragment;
import wang.cn.com.optimize.ui.hot.childpager.thrid.ThridPagerFragment;

/**
 * @author woong
 */
@Module()
public abstract class AllFragmentsModule {

    @PerFragment
    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
    abstract HomeFragment contributeHomeFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector(modules = FirstPagerFragmentModule.class)
    abstract FirstPagerFragment contributeFirstPagerFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector(modules = SecondPagerFragmentModule.class)
    abstract SecondPagerFragment contributeSecondPagerFragmentInjector();

    @PerFragment
    @ContributesAndroidInjector(modules = ThridPagerFragmentModule.class)
    abstract ThridPagerFragment contributeThridPagerFragmentInjector();
}
