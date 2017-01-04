package bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renlijie on 17/1/3.
 */

public class OddPhotoResult<T> {
    @SerializedName("error_code")
    public int errorCode;
    public String reason;
    public T result;
}
