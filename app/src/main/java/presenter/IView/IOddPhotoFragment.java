package presenter.IView;

import bean.OddPhotoResult;
import bean.OddPhotos;

/**
 * Created by renlijie on 17/1/3.
 */

public interface IOddPhotoFragment extends IBaseFragment{

    void updateOddPhotoList(OddPhotoResult<OddPhotos> oddPhotoResult);

}
