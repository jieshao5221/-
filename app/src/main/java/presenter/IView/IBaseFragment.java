package presenter.IView;

/**
 * Created by renlijie on 16/12/28.
 */

public interface IBaseFragment {
    void showProgressDialog();

    void hidProgressDialog();

    void showError(String error);
}
