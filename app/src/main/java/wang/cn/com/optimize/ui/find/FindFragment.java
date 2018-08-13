package wang.cn.com.optimize.ui.find;

import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;

import butterknife.BindView;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.base.BaseFragment;
import wang.cn.com.optimize.ui.find.circlefriends.CircleFriendsActivity;
import wang.cn.com.optimize.ui.home.effect.gallery.GalleryActivity;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-10
 * @time: 19:04
 */
public class FindFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_circle_friends)
    TextView tv_circle_friends;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView(View view) {
        tv_circle_friends.setOnClickListener(this);
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
            default:
                break;
        }
    }
}
