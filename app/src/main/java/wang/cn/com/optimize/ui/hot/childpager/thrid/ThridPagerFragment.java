package wang.cn.com.optimize.ui.hot.childpager.thrid;

import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.adapter.MessageAdapter;
import wang.cn.com.optimize.base.BaseDaggerFragment;
import wang.cn.com.optimize.bean.Data;
import wang.cn.com.optimize.ui.widget.imagewatcher.ImageWatcher;
import wang.cn.com.optimize.ui.widget.imagewatcher.MessagePicturesLayout;
import wang.cn.com.optimize.ui.widget.imagewatcher.SpaceItemDecoration;
import wang.cn.com.optimize.ui.widget.imagewatcher.WangRecyclerView;
import wang.cn.com.optimize.utils.GlideImageWatcherLoader;
import wang.cn.com.optimize.utils.Utils;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-08
 * @time: 11:17
 */
public class ThridPagerFragment extends BaseDaggerFragment<ThridPagerPresenter>
        implements ThridPagerContract.view ,MessagePicturesLayout.Callback,
        ImageWatcher.OnPictureLongPressListener{

    public static ThridPagerFragment newInstance() {
        ThridPagerFragment fragment = new ThridPagerFragment();
        return fragment;
    }

    @BindView(R.id.recycler_view)
    WangRecyclerView recyclerView;
    @BindView(R.id.v_image_watcher)
    ImageWatcher imageWatcher;

    private MessageAdapter adapter;
    private boolean isTranslucentStatus = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_thrid_pager;
    }

    @Override
    protected void initView(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new SpaceItemDecoration(getActivity()).
                setSpace(14).setSpaceColor(0xFFECECEC));
        recyclerView.setAdapter(adapter = new MessageAdapter(getActivity()).
                setPictureClickCallback(this));
    }

    @Override
    protected void initData() {
        if (mPresenter != null){
            mPresenter.getThridPagerDatas();
        }

        // **************   xml 方式加载  ********
        // 如果不是透明状态栏，你需要给ImageWatcher标记 一个偏移值，以修正点击ImageView查看的启动动画的Y轴起点的不正确
        //imageWatcher.setTranslucentStatus(!isTranslucentStatus ? Utils.calcStatusBarHeight(getActivity()) : 0);
        imageWatcher.setTranslucentStatus(240);
        // 配置error图标 如果不介意使用lib自带的图标，并不一定要调用这个API
        imageWatcher.setErrorImageRes(R.mipmap.error_picture);
        // 长按图片的回调，你可以显示一个框继续提供一些复制，发送等功能
        imageWatcher.setOnPictureLongPressListener(this);
        imageWatcher.setLoader(new GlideImageWatcherLoader());
        imageWatcher.setOnStateChangedListener(new ImageWatcher.OnStateChangedListener() {
            @Override
            public void onStateChangeUpdate(ImageWatcher imageWatcher, ImageView clicked, int position,
                                            Uri uri, float animatedValue, int actionTag) {
                Log.e("IW", "onStateChangeUpdate [" + position + "][" + uri +
                        "][" + animatedValue + "][" + actionTag + "]");
            }

            @Override
            public void onStateChanged(ImageWatcher imageWatcher, int position, Uri uri, int actionTag) {
                if (actionTag == ImageWatcher.STATE_ENTER_DISPLAYING) {
                    Toast.makeText(getActivity(), "点击了图片 [" + position + "]" + uri + "",
                            Toast.LENGTH_SHORT).show();
                } else if (actionTag == ImageWatcher.STATE_EXIT_HIDING) {
                    Toast.makeText(getActivity(), "退出了查看大图", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Utils.fitsSystemWindows(isTranslucentStatus, findViewById(R.id.v_fit));

    }

    @Override
    public void setThridPagerDatas(List<Data> dataList) {
        adapter.set(dataList);
    }

    @Override
    public void onThumbPictureClick(ImageView i, SparseArray<ImageView> imageGroupList,
                                    List<Uri> urlList) {
        imageWatcher.show(i, imageGroupList, urlList);
    }

    @Override
    public void onPictureLongPress(ImageView v, Uri uri, int pos) {
        Toast.makeText(v.getContext().getApplicationContext(),
                "长按了第" + (pos + 1) + "张图片", Toast.LENGTH_SHORT).show();
    }



}
