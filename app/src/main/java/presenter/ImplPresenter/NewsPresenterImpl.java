package presenter.ImplPresenter;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qiao.rlj.myapplication.MyApplication;

import bean.News;
import bean.Result;
import presenter.IPresenter.INewsPresenter;
import rx.Observer;
import service.NewsService;
import util.httpUtil.RetrofitUtils;

/**
 * Created by renlijie on 16/12/27.
 */

public class NewsPresenterImpl extends BasePresenter implements INewsPresenter {

    @Override
    public void getNewsData(String type, String appKey) {
        NewsService newsService = RetrofitUtils.getRetrofit().create(NewsService.class);
        setSubscribe(newsService.getTopNews(type, appKey), new Observer<Result<News>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Result<News> newsResult) {
                Toast.makeText(MyApplication.getContext(),newsResult.message,Toast.LENGTH_SHORT).show();
                Log.i("TAG",newsResult.message);
                Gson gson = new Gson();
                Log.i("TAG", gson.toJson(newsResult.result));
            }
        });
    }
}
