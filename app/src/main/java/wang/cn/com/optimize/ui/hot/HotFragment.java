package wang.cn.com.optimize.ui.hot;

import android.view.View;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import wang.cn.com.optimize.Adapter.HomeAdapter;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.base.BaseFragment;
import wang.cn.com.optimize.ui.hot.HotMagnify.HotMagnifyActivity;
import wang.cn.com.optimize.ui.widget.VRImageView;
import wang.cn.com.optimize.ui.widget.VRManager;
import wang.cn.com.optimize.ui.widget.VRTransFormation;

public class HotFragment extends BaseFragment{

    @BindView(R.id.vr_image)
    VRImageView vrImageView;

    private static final String IMAGE_URL =
            "http://vrlab-public.ljcdn.com//release//vradmin//1000000020129136//images//FF41C450.png";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView(View view) {
        vrImageView.post(new Runnable() {
            @Override public void run() {
                //获取控件大小，作为拉伸参数
                int width = vrImageView.getWidth();
                int height = vrImageView.getHeight();

                Picasso.get().load(IMAGE_URL)
                        .transform(new VRTransFormation(width, height))
                        .into(vrImageView);
            }
        });
    }

    @Override
    protected void initData() {

        vrImageView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                HotMagnifyActivity.startActivityWithAnimation(getActivity(), IMAGE_URL, vrImageView);

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
