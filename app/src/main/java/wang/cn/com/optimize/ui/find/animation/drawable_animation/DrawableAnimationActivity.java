package wang.cn.com.optimize.ui.find.animation.drawable_animation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import wang.cn.com.optimize.R;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-10-16
 * @time: 14:28
 */
public class DrawableAnimationActivity extends AppCompatActivity {

    private ImageView imgDemo;
    private TextView tvDo;
    private TextView tvStop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_drawable);

        imgDemo = findViewById(R.id.img_demo);
        tvDo = findViewById(R.id.tv_do);
        tvStop = findViewById(R.id.tv_stop);

        tvDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*AnimationDrawable animationDrawable = new AnimationDrawable();
                for (int i = 1; i < 8; i++) {
                    int id = getResources().getIdentifier("run" + i, "drawable",
                            getPackageName());
                    Drawable drawable = getDrawable(id);
                    if (null != drawable) {
                        animationDrawable.addFrame(drawable, 100);
                    }
                }
                imgDemo.setImageDrawable(animationDrawable);
                animationDrawable.start();*/

                imgDemo.setImageResource(R.drawable.run);
                AnimationDrawable rocketAnimation = (AnimationDrawable) imgDemo.getDrawable();
                rocketAnimation.start();
            }
        });

        tvStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationDrawable animationDrawable = (AnimationDrawable) imgDemo.getDrawable();
                if (animationDrawable.isRunning()) {
                    animationDrawable.stop();
                }
            }
        });

    }
}
