package wang.cn.com.optimize.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import wang.cn.com.optimize.ui.hot.childpager.first.FirstPagerFragment;
import wang.cn.com.optimize.ui.hot.childpager.second.SecondPagerFragment;
import wang.cn.com.optimize.ui.hot.childpager.thrid.ThridPagerFragment;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-07-26
 * @time: 16:37
 */
public class HotPagerAdapter extends FragmentPagerAdapter{

    private String[] mTitles;

    public HotPagerAdapter(FragmentManager fm,String... titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1){
            return SecondPagerFragment.newInstance();
        }
        if (position == 2){
            return ThridPagerFragment.newInstance();
        }
        return FirstPagerFragment.newInstance();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
