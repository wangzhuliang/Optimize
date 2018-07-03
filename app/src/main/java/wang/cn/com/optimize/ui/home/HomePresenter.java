package wang.cn.com.optimize.ui.home;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import wang.cn.com.optimize.App;
import wang.cn.com.optimize.base.BasePresenter;
import wang.cn.com.optimize.bean.HomeBean;
import wang.cn.com.optimize.domain.ApiManager;
import wang.cn.com.optimize.domain.RxSchedulers;
import wang.cn.com.optimize.domain.apiservice.WangApiService;
import wang.cn.com.optimize.resp.HomeResp;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter{

    @Inject
    public HomePresenter() {

    }

    @Override
    public void getHomeNews(final String order) {

        App.apiService(WangApiService.class)
                .apiHome(order)
                .compose(RxSchedulers.<HomeBean>io_main())
                .compose(mView.<HomeBean>bindToLife())
                .subscribe(new Consumer<HomeBean>() {
                    @Override
                    public void accept(HomeBean homeBean) throws Exception {
                        if (homeBean != null) {
                            mView.updateHomeData(order,homeBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFailed();
                    }
                });

        /*ApiManager.getR().create(WangApiService.class).Home()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        if (homeBean != null) {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }
}
