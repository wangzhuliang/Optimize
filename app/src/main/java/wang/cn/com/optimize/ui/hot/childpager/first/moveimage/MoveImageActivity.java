package wang.cn.com.optimize.ui.hot.childpager.first.moveimage;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;

import java.nio.Buffer;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.base.BaseActivity;
import wang.cn.com.optimize.event.ScrollTopEvent;
import wang.cn.com.optimize.ui.widget.MoveImageRelativeLayout;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-07-31
 * @time: 15:15
 */
public class MoveImageActivity extends SupportActivity {

    @BindView(R.id.iv_bg)
    ImageView imageView;
    @BindView(R.id.rl_drag)
    MoveImageRelativeLayout moveImageRelativeLayout;

    int[] images = {R.drawable.beauty1,R.drawable.beauty2,R.drawable.beauty3,R.drawable.beauty4,
            R.drawable.beauty5,R.drawable.beauty6,R.drawable.beauty7,R.drawable.beauty8,
            R.drawable.beauty9,R.drawable.beauty10,R.drawable.beauty11,R.drawable.beauty12};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_image);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            int[] arrays = intent.getIntArrayExtra("global_rect");
            boolean a  = intent.getBooleanExtra("is_last_row",false);
            moveImageRelativeLayout.setIsLastRow(a);
            moveImageRelativeLayout.setOriginView(arrays[0], arrays[1],
                    arrays[2] - arrays[0], arrays[3] - arrays[1], arrays[4]);
            moveImageRelativeLayout.startAnimation();
            Bundle bundle = intent.getExtras();
            int b = bundle.getInt("image");
            imageView.setImageResource(images[b]);
            //imageView.setBackgroundResource(R.drawable.beauty1);
        }

        moveImageRelativeLayout.setOnVideoDragListener(new MoveImageRelativeLayout.OnVideoDragListener() {
            @Override
            public void onStartDrag() {

            }

            @Override
            public void onReleaseDrag(boolean isRestoration) {
                if (!isRestoration) {
                    imageView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onEnterAnimationEnd(boolean isOutOfBound) {
                EventBus.getDefault().post(new ScrollTopEvent(isOutOfBound));
            }

            @Override
            public void onExitAnimationEnd() {
                finish();
            }

            @Override
            public void onRestorationAnimationEnd() {

            }
        });
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }
}
