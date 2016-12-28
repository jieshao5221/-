package bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renlijie on 16/12/27.
 * "title": "巫山云雨枉断肠：女摄影师Erika Lust记录的性爱",
 * "date": "2016-06-13 10:31",
   "author_name": "POCO摄影",
   "thumbnail_pic_s": "http://09.imgmini.eastday.com/mobile/20160613/20160613103108_7b015493398e7fd13dda3a5c
        e315b1c8_1_mwpm_03200403.jpeg",
   "thumbnail_pic_s02": "http://09.imgmini.eastday.com/mobile/20160613/20160613103108_7b015493398e7fd13dda3a5ce315
        b1c8_1_mwpl_05500201.jpeg",
   "thumbnail_pic_s03": "http://09.imgmini.eastday.com/mobile/20160613/20160613103108_7b015493398e7fd13dda3a5ce315
        b1c8_1_mwpl_05500201.jpeg",
   "url": "http://mini.eastday.com/mobile/160613103108379.html?qid=juheshuju",
   "uniquekey": "160613103108379",
   "type": "头条",
   "realtype": "娱乐"
 */

public class NewsItem {

    public String title;//标题

    public String date;//日期

    @SerializedName("author_name")
    public String authorName;//作者名

    @SerializedName("thumbnail_pic_s")
    public String thumbnailPics;//图片1

    @SerializedName("thumbnail_pic_s02")
    public String thumbnailPics02;//图片2

    @SerializedName("thumbnail_pic_s03")
    public String thumbnailPics03;//图片3

    public String url;//新闻链接

    public String uniquekey;//唯一标识

    public String type;//类型1

    public String realtype;//类型2

}
