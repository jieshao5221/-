package service;

import bean.OddPhotoResult;
import bean.OddPhotos;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by renlijie on 17/1/3.
 */

public interface OddPhotoService {

    /**
     * 获取oddPhoto
     * @param key
     * @param page
     * @param pagesize
     * @return
     */
   @GET("joke/img/text.from")
    Observable<OddPhotoResult<OddPhotos>> getOddPhotos(@Query("key") String key, @Query("page")int page, @Query("pagesize") int pagesize);



}
