package presenter.IPresenter;

/**
 * Created by renlijie on 16/12/27.
 */

public interface INewsItemPresenter extends IBasePresenter{
    /**
     * 获取新闻
     * @param type
     * @param appKey
     */
    void getNewsData(String type,String appKey);
}
