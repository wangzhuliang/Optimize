package wang.cn.com.optimize.ui.home.effect.echelon;

import wang.cn.com.optimize.base.BaseContract;
import wang.cn.com.optimize.ui.home.HomeContract;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-07
 * @time: 14:01
 */
public class EchelonContract {

    interface view extends BaseContract.BaseView {
        void setEchelonDatas(int[] icons, int[] bgs, String[] nickNames, String[] descs);
    }

    interface Presenter extends BaseContract.BasePresenter<EchelonContract.view> {
        void getEchelonDatas();
    }
}
