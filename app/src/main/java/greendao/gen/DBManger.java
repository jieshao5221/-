package greendao.gen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import bean.NewsItem;

/**
 * 数据库管理
 * Created by renlijie on 17/1/2.
 */

public class DBManger {
    public final static String DB_NAME = "DB_NEWS";//数据库名
    public static DBManger dbManger;

    private DaoMaster.OpenHelper helper;
    private Context context;

    public DBManger(Context context){

        this.context = context;
        helper = new DaoMaster.DevOpenHelper(context,DB_NAME,null);

    }

    public static DBManger getDbManger(Context context){

        if (dbManger == null){
            synchronized (DBManger.class){
                if (dbManger == null){
                    dbManger = new DBManger(context);
                }
            }
        }
        return dbManger;

    }

    /**
     * 获取可读的数据库
     * @return
     */
    private SQLiteDatabase getReadableDatabase(){

        if(helper == null){
            helper = new DaoMaster.DevOpenHelper(context,DB_NAME,null);
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写的数据库
     * @return
     */
    private SQLiteDatabase getWriteableDatabase(){

        if (helper == null){
            helper = new DaoMaster.DevOpenHelper(context,DB_NAME,null);
        }
        SQLiteDatabase db = helper.getWritableDatabase();
        return db;

    }

    /**
     * 插入一条新闻
     * @param item
     */
    public void insertNewsItem(NewsItem item){

        getNewsItemDao().insert(item);

    }

    /**
     * 插入一组NewsItem
     * @param items
     * @throws Exception
     */
    public void insertNewsItems(ArrayList<NewsItem> items) throws Exception {

        if (items == null){
            throw new Exception("items 为空");
        }
        getNewsItemDao().insertInTx(items);

    }

    /**
     * 删除一条新闻
     * @param item
     */
    public void deleteNewsItem(NewsItem item){

        getNewsItemDao().delete(item);

    }

    /**
     * 查询所有存储新闻
     * @return
     */
    public List<NewsItem> getNewsItems(){

        QueryBuilder qb = getNewsItemDao().queryBuilder();
        return  qb.list();

    }

    /**
     * 获取NewsItemDao
     * @return
     */
    public NewsItemDao getNewsItemDao(){

        DaoMaster daoMaster = new DaoMaster(getWriteableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        NewsItemDao newsItemDao = daoSession.getNewsItemDao();
        return newsItemDao;

    }


}
