package wang.cn.com.optimize.ui.find.animation.view_animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import wang.cn.com.optimize.R;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-10-15
 * @time: 10:50
 */
public class CommonViewAnimationActivity extends AppCompatActivity {

    private ImageView viewDemo;
    private TextView tvAlpha;
    private TextView tvRotate;
    private TextView tvScale;
    private TextView tvTranslate;
    private TextView tvAnimationSet;
    private TextView tvAnimationXml;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_common_view);

        viewDemo = findViewById(R.id.view_demo);
        tvAlpha = findViewById(R.id.tv_alpha);
        tvRotate = findViewById(R.id.tv_rotate);
        tvScale = findViewById(R.id.tv_scale);
        tvTranslate = findViewById(R.id.tv_translate);
        tvAnimationSet = findViewById(R.id.tv_animation_set);
        tvAnimationXml = findViewById(R.id.tv_animation_xml);

        //渐变透明度动画效果
        tvAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewDemo.startAnimation(getAlphaAnimation());

            }
        });

        //画面转移旋转动画效果
        tvRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewDemo.startAnimation(getRotateAnimation());

            }
        });

        //渐变尺寸伸缩动画效果
        tvScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewDemo.startAnimation(getScaleAnimation());

            }
        });

        //画面转换位置移动动画效果
        tvTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewDemo.startAnimation(getTranslateAnimation());

            }
        });

        //一个持有其它动画元素 alpha、scale、translate、rotate 或者其它 set 元素的容器
        tvAnimationSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationSet innerAnimationSet = new AnimationSet(true);
                innerAnimationSet.setInterpolator(new BounceInterpolator());

                innerAnimationSet.setStartOffset(1000);
                innerAnimationSet.addAnimation(getScaleAnimation());
                innerAnimationSet.addAnimation(getTranslateAnimation());

                AnimationSet animationSet = new AnimationSet(true);
                animationSet.setInterpolator(new LinearInterpolator());

                animationSet.addAnimation(getAlphaAnimation());
                animationSet.addAnimation(getRotateAnimation());
                animationSet.addAnimation(innerAnimationSet);

                viewDemo.startAnimation(animationSet);
            }
        });

        tvAnimationXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(
                        CommonViewAnimationActivity.this, R.anim.view_animation);
                viewDemo.startAnimation(animation);
            }
        });

    }

    private int getWidth() {
        return viewDemo.getWidth();
    }

    private int getHeight() {
        return viewDemo.getHeight();
    }


    public Animation getAlphaAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f,0f);
        //动画持续时间，毫秒为单位
        alphaAnimation.setDuration(2000);
        //重复次数
        alphaAnimation.setRepeatCount(1);
        //控件动画结束时是否保持动画最后的状态
        alphaAnimation.setFillAfter(true);
        //控件动画结束时是否还原到开始动画前的状态
        alphaAnimation.setFillBefore(false);
        //重复类型有两个值，reverse表示倒序回放，restart表示从头播放
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        //setInterpolator(Interpolator)设定插值器（指定的动画效果，譬如回弹等）
        //setStartOffset(long)调用start函数之后等待开始运行的时间，单位为毫秒
        return alphaAnimation;
    }


    public Animation getRotateAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f,
                getWidth() / 2, getHeight() / 2);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatCount(1);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setFillBefore(false);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        return rotateAnimation;
    }

    public Animation getScaleAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 2f,
                1f, 2f,
                getWidth() / 2, getHeight() / 2);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setRepeatCount(2);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setFillBefore(false);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        return scaleAnimation;
    }

    public Animation getTranslateAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0,
                getWidth() * 2,
                0, getHeight() * 2);
        translateAnimation.setDuration(2000);
        translateAnimation.setRepeatCount(2);
        translateAnimation.setFillAfter(true);
        translateAnimation.setFillBefore(false);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        return translateAnimation;
    }
}
