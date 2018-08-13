package wang.cn.com.optimize.domain.apiservice;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import wang.cn.com.optimize.bean.HomeBean;
import wang.cn.com.optimize.resp.HomeResp;

public interface WangApiService {
//+
//            "page=1"
    @GET("posts/" + "?" +
            "client_id=ghost-frontend" + "&" +
            "client_secret=122ca884710f"+"&"+"filter=tags:ying-yang-wen-zhang"+"&"+
            "limit=10" + "&" )
    Observable<HomeBean> apiHome(
            @Query("page") String page
    );

    @GET("posts/" + "?" +
            "client_id=ghost-frontend" + "&" +
            "client_secret=122ca884710f"+"&"+"filter=tags:ying-yang-wen-zhang"+"&"+
            "limit=4" + "&" )
    Observable<HomeBean> apiHomeHeader(
            @Query("page") String page
    );

    @GET("posts/" + "?" +
            "client_id=ghost-frontend" + "&" +
            "client_secret=122ca884710f"+"&"+"filter=tags:ying-yang-wen-zhang"+"&"+
            "limit=40" + "&" +
            "page=1")
    Observable<HomeBean> apiHomeDetail();
}
