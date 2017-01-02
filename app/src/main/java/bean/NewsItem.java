package bean;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

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

@Entity
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

    @Id
    public String uniquekey;//唯一标识

    public String type;//类型1

    public String realtype;//类型2

    public int showType;//展示方式 0. 大图 1.小图左 2.小图右

@Generated(hash = 1596068178)
public NewsItem(String title, String date, String authorName, String thumbnailPics, String thumbnailPics02,
        String thumbnailPics03, String url, String uniquekey, String type, String realtype, int showType) {
    this.title = title;
    this.date = date;
    this.authorName = authorName;
    this.thumbnailPics = thumbnailPics;
    this.thumbnailPics02 = thumbnailPics02;
    this.thumbnailPics03 = thumbnailPics03;
    this.url = url;
    this.uniquekey = uniquekey;
    this.type = type;
    this.realtype = realtype;
    this.showType = showType;
}

@Generated(hash = 1697690472)
public NewsItem() {
}

public String getTitle() {
    return this.title;
}

public void setTitle(String title) {
    this.title = title;
}

public String getDate() {
    return this.date;
}

public void setDate(String date) {
    this.date = date;
}

public String getAuthorName() {
    return this.authorName;
}

public void setAuthorName(String authorName) {
    this.authorName = authorName;
}

public String getThumbnailPics() {
    return this.thumbnailPics;
}

public void setThumbnailPics(String thumbnailPics) {
    this.thumbnailPics = thumbnailPics;
}

public String getThumbnailPics02() {
    return this.thumbnailPics02;
}

public void setThumbnailPics02(String thumbnailPics02) {
    this.thumbnailPics02 = thumbnailPics02;
}

public String getThumbnailPics03() {
    return this.thumbnailPics03;
}

public void setThumbnailPics03(String thumbnailPics03) {
    this.thumbnailPics03 = thumbnailPics03;
}

public String getUrl() {
    return this.url;
}

public void setUrl(String url) {
    this.url = url;
}

public String getUniquekey() {
    return this.uniquekey;
}

public void setUniquekey(String uniquekey) {
    this.uniquekey = uniquekey;
}

public String getType() {
    return this.type;
}

public void setType(String type) {
    this.type = type;
}

public String getRealtype() {
    return this.realtype;
}

public void setRealtype(String realtype) {
    this.realtype = realtype;
}

public int getShowType() {
    return this.showType;
}

public void setShowType(int showType) {
    this.showType = showType;
}

}
