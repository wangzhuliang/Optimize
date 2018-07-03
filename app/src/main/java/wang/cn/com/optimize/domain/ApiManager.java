package wang.cn.com.optimize.domain;

import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import wang.cn.com.optimize.domain.apiservice.WangApiService;

public class ApiManager {
    private static final String BASE_URL = "https://cms.youlin365.com/ghost/api/v0.1/";

    private HashMap<Class, Retrofit> mRetrofitServiceHashMap = new HashMap<>();

    private Retrofit mApiRetrofit;
    private ConcurrentHashMap<Class, Object> cachedApis = new ConcurrentHashMap<>();
    //private PersistentCookieJar cookieJar;

    public ApiManager() {
        //cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getAppContext()));

        // init okhttp 3 logger
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        // init cache
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(55, TimeUnit.SECONDS)
                .addInterceptor(logInterceptor)
                //保存cookie
                //.cookieJar(cookieJar)
               .build();

        //onHttps(client);

        mApiRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //自定义factory
                //.addConverterFactory(StringConverterFactory.create())
                .build();

        mRetrofitServiceHashMap.put(WangApiService.class, mApiRetrofit);
    }

    public <T> void addService(Class<T> clz) {
        mRetrofitServiceHashMap.put(clz, mApiRetrofit);
    }

    public static Retrofit getR(){
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(55, TimeUnit.SECONDS)
                .addInterceptor(logInterceptor)
                //保存cookie
                //.cookieJar(cookieJar)
                .build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //自定义factory
                //.addConverterFactory(StringConverterFactory.create())
                .build();
    }

    public <T> T getService(Class<T> clz) {
        Object obj = cachedApis.get(clz);
        if (obj != null) {
            return (T) obj;
        } else {
            Retrofit retrofit = mRetrofitServiceHashMap.get(clz);
            if (retrofit != null) {
                T service = retrofit.create(clz);
                cachedApis.put(clz, service);
                return service;
            } else {
                return null;
            }
        }
    }

    //使用自定义SSLSocketFactory
    private void onHttps(OkHttpClient.Builder builder) {
        try {
            builder.sslSocketFactory(getSSLSocketFactory())
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static SSLSocketFactory getSSLSocketFactory() throws Exception {
        //创建一个不验证证书链的证书信任管理器。
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        }};

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts,
                new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        return sslContext
                .getSocketFactory();
    }
}
