package presenter.ImplPresenter;

import presenter.IPresenter.IBasePresenter;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by renlijie on 16/12/27.
 */

public class BasePresenter implements IBasePresenter {

    CompositeSubscription compositeSubscription = null;

    /**
     * 添加订阅
     * @param s
     */
    protected void addSubscription(Subscription s) {
        if (this.compositeSubscription == null) {
            this.compositeSubscription = new CompositeSubscription();
        }
        this.compositeSubscription.add(s);
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
