package bean;

/**
 * 趣图
 * Created by renlijie on 17/1/2.
 */

public class OddPhoto {
    public String content;
    public String hashId;
    public String unixtime;
    public String updatetime;
    public String url;

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof OddPhoto){
            return this.hashId.equals(((OddPhoto) obj).hashId);
        }
        return false;
    }
}
