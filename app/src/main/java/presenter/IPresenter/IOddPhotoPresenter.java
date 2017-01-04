package presenter.IPresenter;

/**
 * Created by renlijie on 17/1/3.
 */

public interface IOddPhotoPresenter extends IBasePresenter{

    void getOddPhotos(String appKey,int page,int pageSize);

}
