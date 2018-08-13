package wang.cn.com.optimize.ui.home.effect.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.adapter.GalleryAdapter;
import wang.cn.com.optimize.base.BaseDaggerActivity;
import wang.cn.com.optimize.ui.widget.gallery.AnimManager;
import wang.cn.com.optimize.ui.widget.gallery.GalleryRecyclerView;
import wang.cn.com.optimize.utils.BlurBitmapUtil;
import wang.cn.com.optimize.utils.DLog;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-07-19
 * @time: 14:09
 */
public class GalleryActivity extends BaseDaggerActivity<GralleryPresenter>
        implements GalleryRecyclerView.OnItemClickListener, GralleryContract.view{

    @BindView(R.id.rv_list)
    GalleryRecyclerView mRecyclerView;
    @BindView(R.id.rl_container)
    RelativeLayout mContainer;

    private Map<String, Drawable> mTSDraCacheMap = new HashMap<>();
    private static final String KEY_PRE_DRAW = "key_pre_draw";

    /**
     * 获取虚化背景的位置
     */
    private int mLastDraPosition = -1;

    private GalleryAdapter galleryAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gallery;
    }

    @Override
    protected void initView() {

        DLog.setDebug(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));

    }

    @Override
    protected void initData() {

        if (mPresenter != null) {
            mPresenter.getGralleryDatas();
        }

        // 设置滑动速度（像素/s）
        mRecyclerView.initFlingSpeed(9000)
                // 设置页边距和左右图片的可见宽度，单位dp
                .initPageParams(0, 60)
                // 设置切换动画的参数因子
                .setAnimFactor(0.15f)
                // 设置切换动画类型，目前有AnimManager.ANIM_BOTTOM_TO_TOP和目前有AnimManager.ANIM_TOP_TO_BOTTOM
                .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP)
                // 设置点击事件
                .setOnItemClickListener(GalleryActivity.this);

        // 背景高斯模糊 & 淡入淡出
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    setBlurImage();
                }
            }
        });
        setBlurImage();
    }

    /**
     * 设置背景高斯模糊
     */
    public void setBlurImage() {
        GalleryAdapter adapter = (GalleryAdapter) mRecyclerView.getAdapter();
        final int mCurViewPosition = mRecyclerView.getScrolledPosition();

        if (adapter == null || mRecyclerView == null || mCurViewPosition == mLastDraPosition) {
            return;
        }
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                // 获取当前位置的图片资源ID
                int resourceId = ((GalleryAdapter) mRecyclerView.getAdapter()).getResId(mCurViewPosition);
                // 将该资源图片转为Bitmap
                Bitmap resBmp = BitmapFactory.decodeResource(getResources(), resourceId);
                // 将该Bitmap高斯模糊后返回到resBlurBmp
                Bitmap resBlurBmp = BlurBitmapUtil.blurBitmap(mRecyclerView.getContext(), resBmp, 15f);
                // 再将resBlurBmp转为Drawable
                Drawable resBlurDrawable = new BitmapDrawable(resBlurBmp);
                // 获取前一页的Drawable
                Drawable preBlurDrawable = mTSDraCacheMap.get(KEY_PRE_DRAW) == null ? resBlurDrawable : mTSDraCacheMap.get(KEY_PRE_DRAW);

                /* 以下为淡入淡出效果 */
                Drawable[] drawableArr = {preBlurDrawable, resBlurDrawable};
                TransitionDrawable transitionDrawable = new TransitionDrawable(drawableArr);
                mContainer.setBackgroundDrawable(transitionDrawable);
                transitionDrawable.startTransition(500);

                // 存入到cache中
                mTSDraCacheMap.put(KEY_PRE_DRAW, resBlurDrawable);
                // 记录上一次高斯模糊的位置
                mLastDraPosition = mCurViewPosition;
            }
        });
    }

    /***
     * 测试数据
     * @return List<Integer>
     */
    /*public List<Integer> getDatas() {
        TypedArray ar = getResources().obtainTypedArray(R.array.test_arr);
        final int[] resIds = new int[ar.length()];
        for (int i = 0; i < ar.length(); i++) {
            resIds[i] = ar.getResourceId(i, 0);
        }
        ar.recycle();
        List<Integer> tDatas = new ArrayList<>();
        for (int resId : resIds) {
            tDatas.add(resId);
        }
        return tDatas;
    }*/

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(GalleryActivity.this, "position=" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setGralleryDatas(List<Integer> datas) {
        galleryAdapter = new GalleryAdapter(GalleryActivity.this, datas);
        mRecyclerView.setAdapter(galleryAdapter);
    }
}
