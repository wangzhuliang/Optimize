package wang.cn.com.optimize.ui.home.effect.tantan;

import java.util.List;

import wang.cn.com.optimize.base.BaseContract;
import wang.cn.com.optimize.bean.CardBean;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-07
 * @time: 16:06
 */
public class TanTanContract {
    interface view extends BaseContract.BaseView {
        void setTanTanDatas(List<CardBean> list);
    }

    interface Presenter extends BaseContract.BasePresenter<TanTanContract.view> {
        void getTanTanDatas();
    }
}
