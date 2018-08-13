package wang.cn.com.optimize.ui.home.homedetail;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.base.BaseDaggerActivity;
import wang.cn.com.optimize.bean.HomeBean;
import wang.cn.com.optimize.ui.widget.TitleBarLayout;

/**
 * @author wangZL
 */
public class HomeDetailActivity extends BaseDaggerActivity<HomeDetailPresenter> implements HomeDetailContract.View {

    @BindView(R.id.title_bar)
    TitleBarLayout titleBar;
    @BindView(R.id.iv_image)
    ImageView iv_image;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_text)
    TextView tv_text;

    private static final String HOME_ID = "home_id";
    private String mHomeId;

    public static void startHomeDetailActivity(Context context, String id) {
        Intent intent = new Intent(context, HomeDetailActivity.class);
        intent.putExtra(HOME_ID, id);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_detail;
    }

    @Override
    protected void initView() {
        BarUtils.setStatusBarVisibility(this, true);
        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.new_blue), 1);
        titleBar.setTitle(getString(R.string.home_detail));
        titleBar.setTitleBarBgColor(getResources().getColor(R.color.new_blue));
        titleBar.setTitleColor(getResources().getColor(R.color.b7));

        titleBar.setLeftBack(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        mHomeId = getIntent().getStringExtra(HOME_ID);

        if (mPresenter != null) {
            mPresenter.getHomeDetails();
        }
    }

    @Override
    public void updateHomeDetailData(HomeBean homeBean) {
        int i = Integer.valueOf(mHomeId);
        if (homeBean.getPosts().get(i).getTitle() != null) {
            tv_name.setText(homeBean.getPosts().get(i).getTitle());
            tv_text.setText(homeBean.getPosts().get(i).getMarkdown());
        }
        Glide.with(HomeDetailActivity.this).load("http://cms.youlin365.com"+
                homeBean.getPosts().get(i).getImage())
                .into(iv_image);
    }
}
