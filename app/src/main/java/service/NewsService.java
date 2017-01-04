package service;

import bean.News;
import bean.Result;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by renlijie on 16/12/27.
 */

public interface NewsService {

    /**
     * 请求新闻
     * @param type
     * @param key
     * @return
     */
    //post请求
    @FormUrlEncoded
    @POST("toutiao/index?")
    Observable<Result<News>> getTopNews(@Field("type") String type, @Field("key") String key);

}
