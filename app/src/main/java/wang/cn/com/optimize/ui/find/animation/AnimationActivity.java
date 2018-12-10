package wang.cn.com.optimize.ui.find.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;

import wang.cn.com.optimize.R;
import wang.cn.com.optimize.ui.find.animation.drawable_animation.DrawableAnimationActivity;
import wang.cn.com.optimize.ui.find.animation.property_animation.PropertyAnimationActivity;
import wang.cn.com.optimize.ui.find.animation.view_animation.CommonViewAnimationActivity;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-10-16
 * @time: 13:56
 */
public class AnimationActivity extends AppCompatActivity {

    private TextView viewAnim;
    private TextView drawableAnim;
    private TextView propertyAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        viewAnim = findViewById(R.id.view_anim);
        drawableAnim = findViewById(R.id.drawable_anim);
        propertyAnim = findViewById(R.id.property_anim);

        viewAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(CommonViewAnimationActivity.class);
            }
        });

        drawableAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(DrawableAnimationActivity.class);
            }
        });

        propertyAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(PropertyAnimationActivity.class);
            }
        });
    }
}
