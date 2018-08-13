package wang.cn.com.optimize.ui.home.effect.echelon;

import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.adapter.EchelonAdapter;
import wang.cn.com.optimize.base.BaseActivity;
import wang.cn.com.optimize.base.BaseContract;
import wang.cn.com.optimize.base.BaseDaggerActivity;
import wang.cn.com.optimize.ui.widget.EchelonLayoutManager;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-07-13
 * @time: 17:13
 */
public class EchelonActivity extends BaseDaggerActivity<EchelonPresenter>
        implements EchelonContract.view{

    @BindView(R.id.rv_echelon)
    RecyclerView recyclerView;

    private EchelonLayoutManager echelonLayoutManager;
    private EchelonAdapter echelonAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_echelon;
    }

    @Override
    protected void initView() {
        echelonLayoutManager = new EchelonLayoutManager(EchelonActivity.this);
        recyclerView.setLayoutManager(echelonLayoutManager);
    }

    @Override
    protected void initData() {
        if (mPresenter != null) {
            mPresenter.getEchelonDatas();
        }
    }

    @Override
    public void setEchelonDatas(int[] icons, int[] bgs, String[] nickNames, String[] descs) {
        echelonAdapter = new EchelonAdapter(EchelonActivity.this,icons,bgs,nickNames,descs);
        recyclerView.setAdapter(echelonAdapter);
    }
}
