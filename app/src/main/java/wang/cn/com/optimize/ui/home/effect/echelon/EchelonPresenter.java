package wang.cn.com.optimize.ui.home.effect.echelon;

import javax.inject.Inject;

import wang.cn.com.optimize.R;
import wang.cn.com.optimize.base.BasePresenter;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-07
 * @time: 14:07
 */
public class EchelonPresenter extends BasePresenter<EchelonContract.view> implements EchelonContract.Presenter {

    @Inject
    public EchelonPresenter() {

    }

    private int[] icons = {R.mipmap.header_icon_1,R.mipmap.header_icon_2,
            R.mipmap.header_icon_3,R.mipmap.header_icon_4};
    private int[] bgs = {R.mipmap.bg_1,R.mipmap.bg_2,R.mipmap.bg_3,R.mipmap.bg_4};
    private String[] nickNames = {"左耳近心","凉雨初夏","稚久九栀","半窗疏影"};
    private String[] descs = {
            "回不去的地方叫故乡 没有根的迁徙叫流浪...",
            "人生就像迷宫，我们用上半生找寻入口，用下半生找寻出口",
            "原来地久天长，只是误会一场",
            "不是故事的结局不够好，而是我们对故事的要求过多",
    };

    @Override
    public void getEchelonDatas() {
        mView.setEchelonDatas(icons, bgs, nickNames, descs);
    }
}
