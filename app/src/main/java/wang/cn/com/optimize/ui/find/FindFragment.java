package wang.cn.com.optimize.ui.find;

import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;

import butterknife.BindView;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.base.BaseFragment;
import wang.cn.com.optimize.ui.find.animation.AnimationActivity;
import wang.cn.com.optimize.ui.find.animation.view_animation.CommonViewAnimationActivity;
import wang.cn.com.optimize.ui.find.calendar.CalendarActivity;
import wang.cn.com.optimize.ui.find.circlefriends.CircleFriendsActivity;
import wang.cn.com.optimize.ui.find.coordinatortablayout.WangCoordinatorTabLayoutActivity;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-10
 * @time: 19:04
 */
public class FindFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_circle_friends)
    TextView tvCircleFriends;
    @BindView(R.id.tv_calendar)
    TextView tvCalendar;
    @BindView(R.id.tv_animation)
    TextView tvAnimation;
    @BindView(R.id.tv_coordinator)
    TextView tvCoordinator;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView(View view) {
        tvCircleFriends.setOnClickListener(this);
        tvCalendar.setOnClickListener(this);
        tvAnimation.setOnClickListener(this);
        tvCoordinator.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_circle_friends:
                ActivityUtils.startActivity(CircleFriendsActivity.class);
                break;
            case R.id.tv_calendar:
                ActivityUtils.startActivity(CalendarActivity.class);
                break;
            case R.id.tv_animation:
                ActivityUtils.startActivity(AnimationActivity.class);
                getActivity().overridePendingTransition(R.anim.animation_activity_enter, R.anim.animation_activity_exit);
                break;
            case R.id.tv_coordinator:
                ActivityUtils.startActivity(WangCoordinatorTabLayoutActivity.class);
                break;
            default:
                break;
        }
    }
}
