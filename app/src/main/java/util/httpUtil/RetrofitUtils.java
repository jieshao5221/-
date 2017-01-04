package util.httpUtil;

import Const.Const;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by renlijie on 16/12/26.
 */

public abstract class RetrofitUtils {


    private static Retrofit mRetrofit;
    private static Retrofit mOddPhotoRetrofit;
    private static OkHttpClient mOkHttpClient;

    /**
     * 获取NewsRetrofit实例
     * @return
     */
    public static Retrofit getRetrofit(){

        if(mRetrofit == null){

            if(null == mOkHttpClient){
                mOkHttpClient = OkhttpClientUtil.getOkhttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Const.API_SERVER + "/") //设置baseUrl
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();

        }

        return mRetrofit;
    }

    /**
     * 获取NewsRetrofit实例
     * @return
     */
    public static Retrofit getmOddPhotoRetrofit(){

        if(mOddPhotoRetrofit == null){

            if(null == mOkHttpClient){
                mOkHttpClient = OkhttpClientUtil.getOkhttpClient();
            }
            mOddPhotoRetrofit = new Retrofit.Builder()
                    .baseUrl(Const.ODD_PHOTO_SERVER + "/") //设置baseUrl
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }
        return mOddPhotoRetrofit;
    }
}
