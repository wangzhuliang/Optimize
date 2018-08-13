package wang.cn.com.optimize.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ajguan.library.EasyRefreshLayout;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import butterknife.BindView;
import wang.cn.com.optimize.adapter.HomeAdapter;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.adapter.HomeHeaderAdapter;
import wang.cn.com.optimize.base.BaseDaggerFragment;
import wang.cn.com.optimize.base.Constants;
import wang.cn.com.optimize.bean.HomeBean;
import wang.cn.com.optimize.ui.home.effect.echelon.EchelonActivity;
import wang.cn.com.optimize.ui.home.effect.gallery.GalleryActivity;
import wang.cn.com.optimize.ui.home.effect.tantan.TanTanActivity;
import wang.cn.com.optimize.ui.home.effect.viewpager.ViewPagerLayoutManagerActivity;
import wang.cn.com.optimize.ui.home.homedetail.HomeDetailActivity;
import wang.cn.com.optimize.ui.widget.NoticeView;

/**
 * @author wangZL
 */
public class HomeFragment extends BaseDaggerFragment<HomePresenter>
        implements HomeContract.View,HomeAdapter.HomeOnClickListener{

    @BindView(R.id.topic_refresh_layout)
    EasyRefreshLayout homeRefreshLayout;
    @BindView(R.id.topic_recycler_view)
    RecyclerView homeRecyclerView;

    @Inject
    HomeAdapter mHomeAdapter;

    private LinearLayout lDots;
    private ViewPager viewPager;
    private HomeBean homeBeanHeader;
    private NoticeView noticeView;

    private String order;
    private long exitTime = 0;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.WANGZERO:
                    showNextPage();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeRecyclerView.setAdapter(mHomeAdapter);
        //首轮轮播
        setHeader(homeRecyclerView);
        //按钮栏
        setMiddleOne(homeRecyclerView);
        //新闻栏
        setMiddleTwo(homeRecyclerView);
    }

    @Override
    protected void initData() {
        order = "1";
        if (mPresenter != null) {
            mPresenter.getHomeNews(order);
            mPresenter.getHeaderNews(order);
        }
        homeRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                if (mPresenter != null) {
                    order = String.valueOf(Integer.parseInt(order)+1);
                    mPresenter.getHomeNews(order);
                }
            }

            @Override
            public void onRefreshing() {
                if (mPresenter != null) {
                    order = "1";
                    mPresenter.getHomeNews(order);
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            homeRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    // 监听屏幕滑动状态-当控件可见时，执行动画
                    Point p = new Point();
                    getActivity().getWindowManager().getDefaultDisplay().getSize(p);
                    int screenWidth = p.x;
                    int screenHeight = p.y;
                    Rect rect = new Rect(0, 0, screenWidth, screenHeight);
                    int[] location = new int[2];
                    /*looperTextView.getLocationInWindow(location);
                    if (looperTextView.getLocalVisibleRect(rect)) {
                        // 控件在屏幕可见区域
                        looperTextView.setTipList(generateTips());
                    } else {
                        // 控件已不在屏幕可见区域（已滑出屏幕）
                    }*/
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        homeRefreshLayout.loadMoreComplete();
    }

    @Override
    public void updateHomeData(String order, HomeBean homeBean) {
        homeRefreshLayout.refreshComplete();
        homeRefreshLayout.loadMoreComplete();
        if (order.equals("1")) {
            mHomeAdapter.updateTopics(false, homeBean);
        }else {
            mHomeAdapter.updateTopics(true, homeBean);
        }
        //mLastOrder = String.valueOf(topicMos.get(topicMos.size() - 1).order);
    }

    @Override
    public void updateHeaderData(String order, HomeBean homeBeanHeaderNew) {
        homeBeanHeader = homeBeanHeaderNew;
        viewPager.addOnPageChangeListener(listener);
        viewPager.setAdapter(new HomeHeaderAdapter(homeBeanHeaderNew,getActivity()));
        initDot();
        // 初始化默认选择第0页
        changeDotAndDesc(0);
        // 滑到一半的地方
        viewPager.setCurrentItem(viewPager.getAdapter().getCount() / 2);
        // 3秒钟后显示下一页
        handler.sendEmptyMessageDelayed(Constants.WANGZERO, 3000);
    }

    public void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.item_home_header,
                view, false);
        lDots =header.findViewById(R.id.ll_dots);
        viewPager =header.findViewById(R.id.vp_home);
        mHomeAdapter.setHeaderView(header);
    }

    public void setMiddleOne(RecyclerView view) {
        View middleOne = LayoutInflater.from(getActivity()).inflate(R.layout.item_home_middle_one,
                view, false);
        TextView effectOne = middleOne.findViewById(R.id.tv_effect_one);
        TextView effectTwo = middleOne.findViewById(R.id.tv_effect_two);
        TextView effectThree = middleOne.findViewById(R.id.tv_effect_three);
        TextView effectFour = middleOne.findViewById(R.id.tv_effect_four);
        effectOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(EchelonActivity.class);
            }
        });
        effectTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(ViewPagerLayoutManagerActivity.class);
            }
        });
        effectThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(TanTanActivity.class);
            }
        });
        effectFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ActivityUtils.startActivity(GalleryActivity.class);
            }
        });
        mHomeAdapter.setMiddleOneView(middleOne);
    }

    public void setMiddleTwo(RecyclerView view) {
        View middleTwo = LayoutInflater.from(getActivity()).inflate(R.layout.item_home_middle_two,
                view, false);
        noticeView = middleTwo.findViewById(R.id.notice_view);
        noticeView.addNotice(generateTips());
        noticeView.startFlipping();
        mHomeAdapter.setMiddleTwoView(middleTwo);
    }


    @Override
    public void onBtnCilck(View view, int position) {
        HomeDetailActivity.startHomeDetailActivity(getActivity(),String.valueOf(position));
    }

    @Override
    public void onUpDate() {

    }

    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {

        /**当某一页被选择的时候 */
        @Override
        public void onPageSelected(int position) {
            changeDotAndDesc(position);
        }

        /**当页面滑动的时候  */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

        /**当页面滑动状态发生改变的时候  */
        @Override
        public void onPageScrollStateChanged(int state) { }
    };

    /** 显示下一页 */
    public void showNextPage() {
        // 获取ViewPager当前显示的是哪一页
        int currentItem = viewPager.getCurrentItem();
        viewPager.setCurrentItem(currentItem + 1);
        // 3秒钟后显示下一页
        handler.sendEmptyMessageDelayed(Constants.WANGZERO, 3000);
    }

    /**
     * 改变指定位置的描述和点
     * @param position
     */
    public void changeDotAndDesc(int position) {

        position = position % lDots.getChildCount();

        for (int i = 0; i < lDots.getChildCount(); i++) {
            // 把position位置的点设置成selected状态，其它位置的dot设置成未选择状态
            lDots.getChildAt(i).setSelected(i == position);
        }
    }

    /** 初始化ViewPager底部的点 */
    private void initDot() {
        double b = ScreenUtils.getScreenWidth();
        b = b/1080;
        for (int i = 0; i < homeBeanHeader.getPosts().size(); i++) {
            int _5dp = (int) (20*b);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(_5dp, _5dp);
            params.leftMargin = _5dp;
            View dot = new View(getActivity());
            dot.setLayoutParams(params);
            dot.setBackgroundResource(R.drawable.shape_dot_selector);
            lDots.addView(dot);
        }
    }

    private List<String> generateTips() {
        List<String> tips = new ArrayList<>();
        tips.add("讲真的会不会是我");
        tips.add("被鬼迷心窍了");
        tips.add("敷衍了太多我怎么不难过");
        tips.add("要你亲口说别只剩沉默");
        tips.add("或许你早就回答了我");
        return tips;
    }



    public void refreshTopicData() {
        order = "";
        homeRecyclerView.scrollToPosition(0);
        if (mPresenter != null) {
            mPresenter.getHomeNews(order);
        }
    }
}
