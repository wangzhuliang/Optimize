package wang.cn.com.optimize.ui.home.effect.tantan;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import wang.cn.com.optimize.base.BasePresenter;
import wang.cn.com.optimize.bean.CardBean;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-07
 * @time: 16:07
 */
public class TanTanPresenter extends BasePresenter<TanTanContract.view>
        implements TanTanContract.Presenter {

    private String u1 = "http://n.sinaimg.cn/translate/20161024/WRsW-fxwztru6973377.jpg";
    private String u2 = "http://img02.tooopen.com/images/20151122/tooopen_sy_149199661189.jpg";
    private String u3 = "http://gmimg.geimian.com/pic/2015/04/20150419_213113_920.jpg";
    private String u4 = "http://pic.qiantucdn.com/58pic/11/84/20/04s58PICiYA.jpg";
    private String u5 = "http://img02.tooopen.com/images/20160122/tooopen_sy_155234647714.jpg";
    private String u6 = "http://seopic.699pic.com/photo/50007/5448.jpg_wh1200.jpg";
    private String u7 = "https://thumbs.dreamstime.com/b/%E6%8A%BD%E8%B1%A1%E6%B2%B9%E7%94%BB-15920804.jpg";

    @Inject
    public TanTanPresenter() {

    }

    @Override
    public void getTanTanDatas() {

        List<CardBean> list = new ArrayList<>();
        CardBean cardBean = new CardBean();
        cardBean.setUrl(u1);
        cardBean.setTitle("first card");
        CardBean cardBean1 = new CardBean();
        cardBean1.setUrl(u2);
        cardBean1.setTitle("second card");
        CardBean cardBean2 = new CardBean();
        cardBean2.setUrl(u3);
        cardBean2.setTitle("third card");
        CardBean cardBean3 = new CardBean();
        cardBean3.setUrl(u4);
        cardBean3.setTitle("fourth card");
        CardBean cardBean4 = new CardBean();
        cardBean4.setUrl(u5);
        cardBean4.setTitle("fifth card");
        CardBean cardBean5 = new CardBean();
        cardBean5.setUrl(u6);
        cardBean5.setTitle("sixth card");
        CardBean cardBean6 = new CardBean();
        cardBean6.setUrl(u7);
        cardBean6.setTitle("seventh card");
        list.add(cardBean);
        list.add(cardBean1);
        list.add(cardBean2);
        list.add(cardBean3);
        list.add(cardBean4);
        list.add(cardBean5);
        list.add(cardBean6);

        mView.setTanTanDatas(list);
    }
}
