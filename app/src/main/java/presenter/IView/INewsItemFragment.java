package presenter.IView;


import bean.News;
import bean.Result;

/**
 * Created by renlijie on 16/12/28.
 */

public interface INewsItemFragment extends IBaseFragment{
    void updateList(Result<News> newsResult);
}
