package wang.cn.com.optimize.ui.hot;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import butterknife.BindView;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.adapter.HotPagerAdapter;
import wang.cn.com.optimize.base.BaseFragment;

/**
 * @author wangZL
 */
public class HotFragment extends BaseFragment {

    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

        viewPager.setAdapter(new HotPagerAdapter(getChildFragmentManager(),
                "first","second","third","forth","other"));

        tabLayout.setupWithViewPager(viewPager);

    }

}
