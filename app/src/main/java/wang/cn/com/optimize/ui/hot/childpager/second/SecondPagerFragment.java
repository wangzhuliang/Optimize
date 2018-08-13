package wang.cn.com.optimize.ui.hot.childpager.second;

import android.view.View;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.base.BaseDaggerFragment;
import wang.cn.com.optimize.ui.hot.childpager.second.magnify.MagnifyActivity;
import wang.cn.com.optimize.ui.widget.vr.VRImageView;
import wang.cn.com.optimize.ui.widget.vr.VRManager;
import wang.cn.com.optimize.ui.widget.vr.VRTransFormation;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-07
 * @time: 17:12
 */
public class SecondPagerFragment extends BaseDaggerFragment<SecondPagerPresenter>
        implements SecondPagerContract.view {

    public static SecondPagerFragment newInstance() {
        SecondPagerFragment fragment = new SecondPagerFragment();
        return fragment;
    }

    @BindView(R.id.vr_image)
    VRImageView vrImageView;

    private String url;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second_pager;
    }

    @Override
    protected void initView(View view) {

        if (mPresenter != null){
            mPresenter.getSecondPagerDatas();
        }

         vrImageView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                MagnifyActivity.startActivityWithAnimation(getActivity(), url, vrImageView);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setSecondPagerDatas(final String s) {
        vrImageView.post(new Runnable() {
            @Override public void run() {
                //获取控件大小，作为拉伸参数
                int width = vrImageView.getWidth();
                int height = vrImageView.getHeight();

                Picasso.get().load(s)
                        .transform(new VRTransFormation(width, height))
                        .into(vrImageView);

                url = s;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        VRManager.getInstance().register(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        VRManager.getInstance().unregister(getActivity());
    }
}
