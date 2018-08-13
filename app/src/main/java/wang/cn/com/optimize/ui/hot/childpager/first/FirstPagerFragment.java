package wang.cn.com.optimize.ui.hot.childpager.first;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.adapter.FirstPagerAdapter;
import wang.cn.com.optimize.base.BaseDaggerFragment;
import wang.cn.com.optimize.bean.ImageBean;
import wang.cn.com.optimize.event.ScrollTopEvent;
import wang.cn.com.optimize.ui.hot.childpager.first.moveimage.MoveImageActivity;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-07-26
 * @time: 16:50
 */
public class FirstPagerFragment extends BaseDaggerFragment<FirstPagerPresenter>
        implements FirstPagerContract.view{

    public static FirstPagerFragment newInstance() {
        //Bundle args = new Bundle();
        //args.putString(ARG_TYPE, title);
        FirstPagerFragment fragment = new FirstPagerFragment();
        //fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private FirstPagerAdapter firstPagerAdapter;
    int mClickPosition = -1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first_pager;
    }

    @Override
    protected void initView(View view) {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }

    @Override
    protected void initData() {

        if (mPresenter != null){
            mPresenter.getFristPagerDatas();
        }

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int pos = parent.getChildAdapterPosition(view);
                outRect.top = (pos / 2 == 0) ? 0 : 2;
                if (pos % 2 == 0) {
                    outRect.right = 1;
                }else {
                    outRect.left = 1;
                }
            }
        });

        firstPagerAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mClickPosition = position;
                Intent intent = new Intent(getActivity(), MoveImageActivity.class);
                Rect globalRect = new Rect();
                view.getGlobalVisibleRect(globalRect);
                intent.putExtra("global_rect", new int[]{globalRect.left,
                        globalRect.top, globalRect.right, globalRect.bottom, view.getHeight()});
                intent.putExtra("video_index", position);
                //判定点击的是不是最后一行的item
                intent.putExtra("is_last_row", mClickPosition >= firstPagerAdapter.getData().size() - 2);
                intent.putExtra("image",position);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ScrollTopEvent event) {
        if (event.isScroll) {
            moveToPosition((LinearLayoutManager)recyclerView.getLayoutManager(), mClickPosition);
        }
    }

    private void moveToPosition(LinearLayoutManager layoutManager, int selectedPosition) {
        int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
        if (selectedPosition - firstVisiblePosition >= 0) {
            int top = recyclerView.getChildAt(selectedPosition - firstVisiblePosition).getTop();
            recyclerView.scrollBy(0, top);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setFristPagerDatas(List<ImageBean> datas) {
        firstPagerAdapter = new FirstPagerAdapter(R.layout.first_image_item, datas);
        recyclerView.setAdapter(firstPagerAdapter);
    }
}
