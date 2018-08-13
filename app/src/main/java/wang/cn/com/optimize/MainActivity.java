package wang.cn.com.optimize;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import java.util.ArrayList;
import butterknife.BindView;
import wang.cn.com.optimize.base.BaseActivity;
import wang.cn.com.optimize.ui.find.FindFragment;
import wang.cn.com.optimize.ui.home.HomeFragment;
import wang.cn.com.optimize.ui.hot.HotFragment;
import wang.cn.com.optimize.ui.mine.MineFragment;
import wang.cn.com.optimize.ui.widget.TitleBarLayout;

/**
 * @author wangZL
 */
public class  MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener{

    @BindView(R.id.title_bar)
    TitleBarLayout titleBar;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    private HomeFragment mHomeFragment;
    private HotFragment mHotFragment;
    private MineFragment mMineFragment;
    private FindFragment mFindFragment;
    private long exitTime = 0;
    private ArrayList<String> mTitleStrs = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initFragment();

        BarUtils.setStatusBarVisibility(this, true);
        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.new_blue), 1);
        titleBar.setTitleBarBgColor(getResources().getColor(R.color.new_blue));
        titleBar.setTitleColor(getResources().getColor(R.color.b7));
    }

    @Override
    protected void initData() {

        //设置导航栏模式
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_DEFAULT);
        //MODE_SHIFTING
        //设置导航栏背景模式
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //BACKGROUND_STYLE_RIPPLE
        //BACKGROUND_STYLE_STATIC,加上这个字体颜色可以修改
        TextBadgeItem numberBadgeItem = new TextBadgeItem();
        ShapeBadgeItem shapeBadgeItem = new ShapeBadgeItem();

        //添加标记
        numberBadgeItem.setBorderWidth(4)
                .setBackgroundColorResource(R.color.new_blue)
                .setText("1" )
                .setHideOnSelect(true);

        shapeBadgeItem.setShape(ShapeBadgeItem.SHAPE_STAR_5_VERTICES)
                .setShapeColorResource(R.color.new_blue)
                .setGravity(Gravity.TOP | Gravity.END)
                .setHideOnSelect(true);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_topic_home_white,"Home").setActiveColorResource(R.color.new_blue))
                //.setInActiveColorResource(R.color.deep_gray)
                //.addItem(new BottomNavigationItem(R.mipmap.iv_assess_fridge,"Books").setActiveColorResource(R.color.pink))
                .addItem(new BottomNavigationItem(R.mipmap.ic_news_hot_white,"Hot").setActiveColorResource(R.color.new_blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_find_white,"Find").setActiveColorResource(R.color.new_blue))
                //.setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.ic_mine_white,"Mine").setActiveColorResource(R.color.new_blue))
                //.setBadgeItem(shapeBadgeItem))
                .setFirstSelectedPosition(0)
                .initialise();

        titleBar.setTitle(mTitleStrs.get(0));
        setDefaultFragment();//设置默认导航栏

        bottomNavigationBar.setTabSelectedListener(this);

    }
    /**
     * 设置默认导航栏
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, mHomeFragment);
        transaction.commit();
    }

    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mHotFragment = new HotFragment();
        mMineFragment = new MineFragment();
        mFindFragment = new FindFragment();
        mFragments.add(mHomeFragment);
        mFragments.add(mHotFragment);
        mFragments.add(mFindFragment);
        mFragments.add(mMineFragment);
        mTitleStrs.add(getString(R.string.tab_info));
        mTitleStrs.add(getString(R.string.tab_hot));
        mTitleStrs.add(getString(R.string.tab_find));
        mTitleStrs.add(getString(R.string.tab_mine));
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 1000) {
            ToastUtils.showShort(R.string.exit_program);
            exitTime = System.currentTimeMillis();
            //if () {
               // mHomeFragment.refreshTopicData();
            //} else if () {
                //mNewsFragment.refreshNewsData();
            //}
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onTabSelected(int position) {
        titleBar.setTitle(mTitleStrs.get(position));
        FragmentManager fm = this.getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, mFragments.get(position));
        transaction.commit();// 事务提交
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
