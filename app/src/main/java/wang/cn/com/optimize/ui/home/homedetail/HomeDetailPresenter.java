package wang.cn.com.optimize.ui.home.homedetail;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import wang.cn.com.optimize.App;
import wang.cn.com.optimize.base.BasePresenter;
import wang.cn.com.optimize.bean.HomeBean;
import wang.cn.com.optimize.domain.RxSchedulers;
import wang.cn.com.optimize.domain.apiservice.WangApiService;
import wang.cn.com.optimize.ui.home.HomeContract;

public class HomeDetailPresenter extends BasePresenter<HomeDetailContract.View> implements HomeDetailContract.Presenter{

    @Inject
    public HomeDetailPresenter() {

    }

    @Override
    public void getHomeDetails() {
        mView.showLoading();
        App.apiService(WangApiService.class)
                .apiHomeDetail()
                .compose(RxSchedulers.<HomeBean>io_main())
                .compose(mView.<HomeBean>bindToLife())
                .subscribe(new Consumer<HomeBean>() {
                    @Override
                    public void accept(HomeBean homeBean) throws Exception {
                        mView.hideLoading();
                        if (homeBean != null) {
                            mView.updateHomeDetailData(homeBean);
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
