package fragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by renlijie on 16/12/28.
 */

public class BaseFragment extends Fragment {

    /**
     * Toast
     * @param content  toast内容
     */
    public void doToast(String content){
        Toast.makeText(getActivity(),content,Toast.LENGTH_SHORT).show();
    }

}
