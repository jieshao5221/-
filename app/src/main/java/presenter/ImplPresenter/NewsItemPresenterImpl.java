package presenter.ImplPresenter;

import android.util.Log;

import com.google.gson.Gson;

import bean.News;
import bean.Result;
import presenter.IPresenter.INewsItemPresenter;
import presenter.IView.INewsItemFragment;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import service.NewsService;
import util.httpUtil.RetrofitUtils;

/**
 * Created by renlijie on 16/12/27.
 */

public class NewsItemPresenterImpl extends BasePresenter implements INewsItemPresenter {

    private INewsItemFragment iNewsItemFragment;

    public NewsItemPresenterImpl(INewsItemFragment iNewsItemFragment){
        this.iNewsItemFragment = iNewsItemFragment;
    }

    /**
     * 获取新闻
     * @param type
     * @param appKey
     */
    @Override
    public void getNewsData(String type, String appKey) {
        Subscription s = RetrofitUtils.getRetrofit().create(NewsService.class).getTopNews(type, appKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<News>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iNewsItemFragment.hidProgressDialog();
                        iNewsItemFragment.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Result<News> newsResult) {
                        iNewsItemFragment.hidProgressDialog();
                        if(newsResult.result.newsList.size() <=0){
                            iNewsItemFragment.showError("暂无数据");
                            return;
                        }
                        iNewsItemFragment.updateList(newsResult);
                        Log.i("TAG",new Gson().toJson(newsResult));
                    }
                });
        addSubscription(s);
    }

}
