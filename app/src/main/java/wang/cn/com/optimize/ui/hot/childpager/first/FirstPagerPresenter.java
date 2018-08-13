package wang.cn.com.optimize.ui.hot.childpager.first;

import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import wang.cn.com.optimize.App;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.base.BasePresenter;
import wang.cn.com.optimize.bean.ImageBean;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-07
 * @time: 16:56
 */
public class FirstPagerPresenter extends BasePresenter<FirstPagerContract.view>
        implements FirstPagerContract.Presenter{

    private String[] texts = {"就这样莫名奇妙地爱上你",
            "你一个微笑我陷入昏迷",
            "翻来覆去找不到逻辑",
            "心跳的声音带着我慢慢靠近",
            "莫名奇妙的爱上你",
            "就是那么的不可理喻oh no",
            "就这样我爱上你",
            "突然变的忧郁莫名奇妙叹息",
            "就连打个喷嚏都想你",
            "最爱吃的东西最爱看的电影",
            "想要把它通通带给你",
            "爱上你"};
    private int[] resIds;

    @Inject
    public FirstPagerPresenter() {

    }

    @Override
    public void getFristPagerDatas() {
        List<ImageBean> datas = new ArrayList<>();
        TypedArray ar = App.getAppContext().getResources().obtainTypedArray(R.array.test_arr);
        resIds = new int[ar.length()];
        for (int i = 0; i < ar.length(); i++) {
            resIds[i] = ar.getResourceId(i, 0);
        }
        ar.recycle();
        for (int i = 0; i < texts.length; i++){
            ImageBean imageBean = new ImageBean();
            imageBean.setName(texts[i]);
            imageBean.setImage(resIds[i]);
            datas.add(imageBean);
        }

        mView.setFristPagerDatas(datas);
    }
}
