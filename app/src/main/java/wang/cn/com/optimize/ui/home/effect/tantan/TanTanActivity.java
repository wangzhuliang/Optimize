package wang.cn.com.optimize.ui.home.effect.tantan;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.adapter.CardAdapter;
import wang.cn.com.optimize.base.BaseActivity;
import wang.cn.com.optimize.base.BaseDaggerActivity;
import wang.cn.com.optimize.bean.CardBean;
import wang.cn.com.optimize.ui.widget.tantan.CardLayoutManager;
import wang.cn.com.optimize.ui.widget.tantan.CardSetting;
import wang.cn.com.optimize.ui.widget.tantan.CardTouchHelperCallback;
import wang.cn.com.optimize.utils.tantan.ReItemTouchHelper;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-07-17
 * @time: 16:19
 */
public class TanTanActivity extends BaseDaggerActivity<TanTanPresenter>
        implements View.OnClickListener, TanTanContract.view{

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.turn_left)
    Button mLeftButton;
    @BindView(R.id.turn_right)
    Button mRightButton;

    ReItemTouchHelper mReItemTouchHelper;
    private CardSetting setting;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tan_tan;
    }

    @Override
    protected void initView() {
        setting = new CardSetting();
        if (mPresenter != null){
            mPresenter.getTanTanDatas();
        }
    }

    @Override
    protected void initData() {
        mLeftButton.setOnClickListener(this);
        mRightButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.turn_left:
                mReItemTouchHelper.swipeManually(ReItemTouchHelper.LEFT);
                break;
            case R.id.turn_right:
                mReItemTouchHelper.swipeManually(ReItemTouchHelper.RIGHT);
                break;
            default:
                break;
        }
    }

    @Override
    public void setTanTanDatas(List<CardBean> list) {
        CardTouchHelperCallback helperCallback = new CardTouchHelperCallback(recyclerView, list,setting);
        mReItemTouchHelper = new ReItemTouchHelper(helperCallback);
        CardLayoutManager layoutManager = new CardLayoutManager(mReItemTouchHelper, setting);
        recyclerView.setLayoutManager(layoutManager);
        CardAdapter cardAdapter = new CardAdapter(list);
        recyclerView.setAdapter(cardAdapter);
    }
}
