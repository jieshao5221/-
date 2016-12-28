package presenter.IPresenter;

import rx.Observable;
import rx.Observer;

/**
 * Created by renlijie on 16/12/27.
 */

public interface IBasePresenter {
    /**
     * 订阅
     * @param observable
     * @param observer
     * @param <T>
     */
    public  <T> void setSubscribe(Observable<T> observable, Observer<T> observer);
    /**
     * 取消订阅
     */
    public void unsubscribe();
}
