package presenter.ImplPresenter;

import presenter.IPresenter.IBasePresenter;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by renlijie on 16/12/27.
 */

public class BasePresenter implements IBasePresenter {

    CompositeSubscription compositeSubscription = null;

    /**
     * 订阅
     * @param observable
     * @param observer
     * @param <T>
     */

    @Override
    public <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
            Subscription subscription = observable.subscribeOn(Schedulers.io())
                    .subscribeOn(Schedulers.newThread()) //子线程访问网络
                    .subscribeOn(AndroidSchedulers.mainThread()) //回调到主线程
                    .subscribe(observer);
            if(null == compositeSubscription){
                compositeSubscription = new CompositeSubscription();
            }
            compositeSubscription.add(subscription); //将订阅添加到compositeSubscription
    }

    /**
     * 取消订阅
     */
    @Override
    public void unsubscribe() {
        if (null != compositeSubscription){
            compositeSubscription.unsubscribe();
        }
    }
}
