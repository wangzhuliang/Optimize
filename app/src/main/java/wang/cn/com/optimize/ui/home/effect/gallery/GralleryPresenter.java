package wang.cn.com.optimize.ui.home.effect.gallery;

import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import wang.cn.com.optimize.App;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.base.BasePresenter;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-07
 * @time: 15:00
 */
public class GralleryPresenter extends BasePresenter<GralleryContract.view>
        implements GralleryContract.Presenter{

    @Inject
    public GralleryPresenter() {

    }

    @Override
    public void getGralleryDatas() {
        TypedArray ar = App.getAppContext().getResources().obtainTypedArray(R.array.test_arr);
        final int[] resIds = new int[ar.length()];
        for (int i = 0; i < ar.length(); i++) {
            resIds[i] = ar.getResourceId(i, 0);
        }
        ar.recycle();
        List<Integer> tDatas = new ArrayList<>();
        for (int resId : resIds) {
            tDatas.add(resId);
        }

        mView.setGralleryDatas(tDatas);
    }
}
