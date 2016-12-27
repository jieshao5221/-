package bean;

/**
 * Created by renlijie on 16/12/27.
 */

public class Result<T> {

    /**
     * 返回结果说明
     */
    public String message;

    /**
     * 返回错误码
     */
    public int errorCode;

    /**
     * 返回数据
     */
    public T result;

}
