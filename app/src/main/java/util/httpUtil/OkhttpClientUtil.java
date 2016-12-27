package util.httpUtil;


import com.qiao.rlj.myapplication.MyApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by renlijie on 16/12/26.
 */

public class OkhttpClientUtil {

    private static OkHttpClient mOkHttpClient;

    //设置缓存目录
    private static File cacheFileDir = new File(MyApplication.getContext().getCacheDir().getAbsolutePath(),"MyCache");
    private static Cache cache = new Cache(cacheFileDir,10*1024*1024);

    /**
     * 获取okHttp实例
     * @return
     */
    public static OkHttpClient getOkhttpClient(){

        if (mOkHttpClient == null){

            mOkHttpClient  = new OkHttpClient.Builder()
                    //设置缓存目录
                    .cache(cache)
                    //设置请求读写的超时时间
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(30,TimeUnit.SECONDS)
                    .readTimeout(30,TimeUnit.SECONDS)
                    .build();
        }

        return mOkHttpClient;
    }
}
