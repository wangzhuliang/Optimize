package wang.cn.com.optimize.ui.home.effect.gallery;

import java.util.List;

import wang.cn.com.optimize.base.BaseContract;
import wang.cn.com.optimize.ui.home.effect.echelon.EchelonContract;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-07
 * @time: 14:52
 */
public class GralleryContract {

    interface view extends BaseContract.BaseView {
        void setGralleryDatas(List<Integer> datas);
    }

    interface Presenter extends BaseContract.BasePresenter<GralleryContract.view> {
        void getGralleryDatas();
    }

}
