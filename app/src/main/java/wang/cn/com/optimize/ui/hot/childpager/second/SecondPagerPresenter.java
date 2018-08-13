package wang.cn.com.optimize.ui.hot.childpager.second;

import javax.inject.Inject;

import wang.cn.com.optimize.base.BasePresenter;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-07
 * @time: 17:16
 */
public class SecondPagerPresenter extends BasePresenter<SecondPagerContract.view>
        implements SecondPagerContract.Presenter{

    private String IMAGE_URL =
            "http://vrlab-public.ljcdn.com//release//vradmin//1000000020129136//images//FF41C450.png";

    @Inject
    public SecondPagerPresenter() {

    }

    @Override
    public void getSecondPagerDatas() {

        mView.setSecondPagerDatas(IMAGE_URL);

    }
}
