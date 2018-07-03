package wang.cn.com.optimize.ui.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.ajguan.library.EasyRefreshLayout;
import javax.inject.Inject;
import butterknife.BindView;
import wang.cn.com.optimize.Adapter.HomeAdapter;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.base.BaseDaggerFragment;
import wang.cn.com.optimize.bean.HomeBean;
import wang.cn.com.optimize.ui.home.homedetail.HomeDetailActivity;
import wang.cn.com.optimize.ui.hot.HotFragment;

public class HomeFragment extends BaseDaggerFragment<HomePresenter>
        implements HomeContract.View,HomeAdapter.HomeOnClickListener{

    @BindView(R.id.topic_refresh_layout)
    EasyRefreshLayout homeRefreshLayout;
    @BindView(R.id.topic_recycler_view)
    RecyclerView homeRecyclerView;

    @Inject
    HomeAdapter mHomeAdapter;

    private String order;
    private long exitTime = 0;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeRecyclerView.setAdapter(mHomeAdapter);
    }

    @Override
    protected void initData() {
        order = "1";
        if (mPresenter != null) {
            mPresenter.getHomeNews(order);
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
    public void onBtnCilck(View view, int position) {
        //swichToChildFragment(new HotFragment(),true);
        HomeDetailActivity.startHomeDetailActivity(getActivity(),String.valueOf(position));
    }

    public void refreshTopicData() {
        order = "";
        homeRecyclerView.scrollToPosition(0);
        if (mPresenter != null) {
            mPresenter.getHomeNews(order);
        }
    }
}
