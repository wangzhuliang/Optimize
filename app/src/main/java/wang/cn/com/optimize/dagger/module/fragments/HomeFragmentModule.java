package wang.cn.com.optimize.dagger.module.fragments;


import dagger.Module;
import dagger.Provides;
import wang.cn.com.optimize.adapter.HomeAdapter;
import wang.cn.com.optimize.dagger.scope.PerFragment;
import wang.cn.com.optimize.ui.home.HomeFragment;

/**
 * Created by wong on 2018/3/9.
 */

@PerFragment
@Module
public class HomeFragmentModule {
    @Provides
    static HomeAdapter provideTopicAdapter(HomeFragment homeFragment) {
        return new HomeAdapter(homeFragment.getActivity(),homeFragment);
    }
}
