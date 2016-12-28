package bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by renlijie on 16/12/27.
 */

public class News {

    public String stat; //状态码

    @SerializedName("data")
    public ArrayList<NewsItem> newsList;
}
