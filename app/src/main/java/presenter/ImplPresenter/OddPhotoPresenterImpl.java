package presenter.ImplPresenter;

import android.util.Log;

import bean.OddPhotoResult;
import bean.OddPhotos;
import presenter.IPresenter.IOddPhotoPresenter;
import presenter.IView.IOddPhotoFragment;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import service.OddPhotoService;
import util.httpUtil.RetrofitUtils;

/**
 * Created by renlijie on 17/1/3.
 */

public class OddPhotoPresenterImpl extends BasePresenter implements IOddPhotoPresenter {

    private IOddPhotoFragment iOddPhotoFragment;

    public OddPhotoPresenterImpl(IOddPhotoFragment iOddPhotoFragment){

        this.iOddPhotoFragment = iOddPhotoFragment;

    }

    /**
     * 获取趣图数据
     * @param appKey
     * @param page
     * @param pageSize
     */
    @Override
    public void getOddPhotos(String appKey, int page, int pageSize) {
        Log.i("TAG","getOddPhotos");
        Subscription s = RetrofitUtils.getmOddPhotoRetrofit().create(OddPhotoService.class).getOddPhotos(appKey,page,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( new Observer<OddPhotoResult<OddPhotos>>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    iOddPhotoFragment.hidProgressDialog();
                                    iOddPhotoFragment.showError(e.getMessage().toString());
                                }

                                @Override
                                public void onNext(OddPhotoResult<OddPhotos> listOddPhotoResult) {
                                    iOddPhotoFragment.updateOddPhotoList(listOddPhotoResult);
                                    iOddPhotoFragment.hidProgressDialog();
                                }
                            }
                );
        addSubscription(s);
    }
}
