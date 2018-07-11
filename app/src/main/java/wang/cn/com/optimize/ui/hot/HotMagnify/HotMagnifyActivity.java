package wang.cn.com.optimize.ui.hot.HotMagnify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.transition.AutoTransition;
import android.transition.ChangeImageTransform;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.base.BaseActivity;
import wang.cn.com.optimize.ui.widget.VRImageView;
import wang.cn.com.optimize.ui.widget.VRManager;
import wang.cn.com.optimize.ui.widget.VRTransFormation;

public class HotMagnifyActivity extends BaseActivity {

    private static final String COVER_URL = "cover_url";
    private static final String OFFSET_X = "OFFSET_X";
    private static final String OFFSET_Y = "OFFSET_Y";
    private static final long TRANSITIONS_DURATION = 1000L;   //做动画的时长

    /**
     * 屏幕宽度和高度
     */
    private int mScreenWidth;
    private int mScreenHeight;

    /**
     * 上一个页面 GyroscopeImageView 的宽度和高度
     */
    private int mOriginWidth;
    private int mOriginHeight;

    /**
     * Loading 覆盖图
     */
    private String mCoverUrl;
    @BindView(R.id.vr_magnify)
    ImageView vr_magnify;

    /**
     * 用于做 Transitions 动画的 ViewGroup
     */
    @BindView(R.id.container_layout)
    ViewGroup container_layout;

    /**
     * @param cover_url Loading 图 url
     * @param vrImageView 想要做动画的 GyroscopeImageView
     */
    public static void startActivityWithAnimation(final Activity activity, String cover_url,
                                                  VRImageView vrImageView) {
        final Intent intent = new Intent(activity, HotMagnifyActivity.class);
        intent.putExtra(COVER_URL, cover_url);
        // 获取控件位置信息
        final Rect rect = new Rect();
        vrImageView.getGlobalVisibleRect(rect);
        intent.setSourceBounds(rect);
        intent.putExtra(OFFSET_X, vrImageView.getOffsetX());
        intent.putExtra(OFFSET_Y, vrImageView.getOffsetY());
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hot_magnify;
    }

    @Override
    protected void initView() {
        mCoverUrl = getIntent().getStringExtra(COVER_URL);
    }

    @Override
    protected void initData() {
        initial();
    }

    /**
     * 设置动画最初场景
     */
    private void initial() {
        // 获取上一个界面传入的控件位置信息
        Rect rect = getIntent().getSourceBounds();

        if (rect != null) {
            // 获取上一个界面中，控件的宽度和高度
            mOriginWidth = rect.right - rect.left;
            mOriginHeight = rect.bottom - rect.top;

            // 设置 ImageView 的位置，使其和上一个界面中图片的位置重合
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(mOriginWidth, mOriginHeight);
            params.setMargins(rect.left, rect.top, rect.right, rect.bottom);
            vr_magnify.setLayoutParams(params);
            vr_magnify.setScaleType(ImageView.ScaleType.MATRIX);

            //拿到上个界面图片的偏移量
            final float offsetX = getIntent().getFloatExtra(OFFSET_X, 0);
            final float offsetY = getIntent().getFloatExtra(OFFSET_Y, 0);

            Picasso.get()
                    .load(mCoverUrl)
                    .transform(new VRTransFormation(mOriginWidth, mOriginHeight))
                    .into(new Target() {
                        @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            vr_magnify.setImageBitmap(bitmap);
                            Matrix matrix = vr_magnify.getImageMatrix();
                            int width = bitmap.getWidth();
                            int height = bitmap.getHeight();
                            //保持上个界面图片的偏移量
                            matrix.setTranslate((int) ((mOriginWidth - width) * 0.5f + 0.5f) + offsetX,
                                    (int) ((mOriginHeight - height) * 0.5f + 0.5f) + offsetY);
                            vr_magnify.setImageMatrix(matrix);
                        }

                        @Override public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                        }

                        @Override public void onPrepareLoad(Drawable placeHolderDrawable) {
                        }
                    });

            //开始入场动画
            runEnterAnim();
        } else {
            //没有动画效果
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(mScreenWidth, mScreenHeight);
            vr_magnify.setLayoutParams(params);
            vr_magnify.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.get().load(mCoverUrl).into(vr_magnify);
        }
    }

    /**
     * 模拟入场动画
     */
    private void runEnterAnim() {
        getScreenSize();
        vr_magnify.post(new Runnable() {
            @Override public void run() {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(mScreenWidth, mScreenHeight);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ChangeImageTransform changeImageTransform = new ChangeImageTransform();
                    AutoTransition autoTransition = new AutoTransition();
                    changeImageTransform.setDuration(TRANSITIONS_DURATION);
                    autoTransition.setDuration(TRANSITIONS_DURATION);
                    TransitionSet transitionSet = new TransitionSet();
                    transitionSet.addTransition(autoTransition);
                    transitionSet.addTransition(changeImageTransform);
                    TransitionManager.beginDelayedTransition(container_layout, transitionSet);
                    vr_magnify.setLayoutParams(params);
                    vr_magnify.setScaleType(ImageView.ScaleType.CENTER_CROP);
                } else {
                    vr_magnify.setLayoutParams(params);
                    vr_magnify.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
            }
        });
    }

    /**
     * 获取屏幕尺寸
     */
    private void getScreenSize() {
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        mScreenHeight = getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    public void onResume() {
        super.onResume();
        VRManager.getInstance().register(HotMagnifyActivity.this);
    }

    @Override
    public void onPause() {
        super.onPause();
        VRManager.getInstance().unregister(HotMagnifyActivity.this);
    }
}
