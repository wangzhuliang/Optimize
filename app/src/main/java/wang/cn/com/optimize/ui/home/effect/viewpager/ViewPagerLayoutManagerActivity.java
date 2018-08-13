package wang.cn.com.optimize.ui.home.effect.viewpager;

import android.media.MediaPlayer;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import butterknife.BindView;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.adapter.ViewPagerLayoutManagerAdapter;
import wang.cn.com.optimize.base.BaseActivity;
import wang.cn.com.optimize.base.BaseDaggerActivity;
import wang.cn.com.optimize.ui.myinterface.OnViewPagerListener;
import wang.cn.com.optimize.ui.widget.ViewPagerLayoutManager;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-07-16
 * @time: 12:04
 */
public class ViewPagerLayoutManagerActivity extends BaseDaggerActivity<ViewPagerLayoutManagerPresenter>
        implements ViewPagerLayoutManagerContract.view {

    private static final String TAG = "Activity";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ViewPagerLayoutManager viewPagerLayoutManager;
    private ViewPagerLayoutManagerAdapter viewPagerLayoutManagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_pager_layout_manager;
    }

    @Override
    protected void initView() {
        viewPagerLayoutManager = new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(viewPagerLayoutManager);
    }

    @Override
    protected void initData() {

        if (mPresenter != null){
            mPresenter.getViewPagerLayoutManagerDatas();
        }

        viewPagerLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                Log.e(TAG,"onInitComplete");
                playVideo(0);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                Log.e(TAG,"释放位置:"+position +" 下一页:"+isNext);
                int index = 0;
                if (isNext){
                    index = 0;
                }else {
                    index = 1;
                }
                releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                Log.e(TAG,"选中位置:"+position+"  是否是滑动到底部:"+isBottom);
                playVideo(0);
            }
        });
    }

    private void playVideo(int position) {
        View itemView = mRecyclerView.getChildAt(0);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final RelativeLayout rootView = itemView.findViewById(R.id.root_view);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        videoView.start();
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                mediaPlayer[0] = mp;
                mp.setLooping(true);
                imgThumb.animate().alpha(0).setDuration(200).start();
                return false;
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        });
        imgPlay.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = true;
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()){
                    imgPlay.animate().alpha(1f).start();
                    videoView.pause();
                    isPlaying = false;
                }else {
                    imgPlay.animate().alpha(0f).start();
                    videoView.start();
                    isPlaying = true;
                }
            }
        });
    }

    private void releaseVideo(int index){
        View itemView = mRecyclerView.getChildAt(index);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        videoView.stopPlayback();
        imgThumb.animate().alpha(1).start();
        imgPlay.animate().alpha(0f).start();
    }

    @Override
    public void setViewPagerLayoutManagerDatas(int[] imgs, int[] videos) {
        viewPagerLayoutManagerAdapter = new ViewPagerLayoutManagerAdapter(this,imgs,videos);
        mRecyclerView.setAdapter(viewPagerLayoutManagerAdapter);
    }
}
