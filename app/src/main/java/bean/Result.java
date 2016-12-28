package bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renlijie on 16/12/27.
 */

public class Result<T> {

    /**
     * 返回结果说明
     */
    @SerializedName("reason")
    public String message;

    /**
     * 返回数据
     */
    public T result;

}
