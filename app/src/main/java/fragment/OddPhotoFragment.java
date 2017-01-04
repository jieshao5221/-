package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.qiao.rlj.myapplication.R;

import Const.Const;
import adapter.OddPhotoAdapter;
import bean.OddPhotoResult;
import bean.OddPhotos;
import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import presenter.IView.IOddPhotoFragment;
import presenter.ImplPresenter.OddPhotoPresenterImpl;
import view.SpaceItemDecoration;

/**
 * 趣图页面
 * Created by renlijie on 17/1/3.
 */

public class OddPhotoFragment extends BaseFragment implements IOddPhotoFragment, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.odd_photo_rv)
    RecyclerView recyclerView;
    @BindView(R.id.odd_photo_empty_view)
    LinearLayout emptyView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindColor(R.color.colorPrimary)
    int color; //进度条颜色
    @BindDimen(R.dimen.news_margin)
    int space;

    int page = 1;
    int pageSize = 10;

    OddPhotoPresenterImpl presenter;
    LinearLayoutManager linearLayoutManager;
    OddPhotoAdapter adapter;

    public OddPhotoFragment(){}
    private static OddPhotoFragment oddPhotoFragment;

    public static OddPhotoFragment getInstance(){
        if(null == oddPhotoFragment){
            oddPhotoFragment = new OddPhotoFragment();
        }
        return oddPhotoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_odd_photo,null);
        ButterKnife.bind(this,view);
        initView();
        showProgressDialog();
        getData();
        return view;
    }

    private void initView() {
        presenter = new OddPhotoPresenterImpl(this);

        swipeRefreshLayout.setColorSchemeColors(color);
        swipeRefreshLayout.setOnRefreshListener(this);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new OddPhotoAdapter(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(listener);
        recyclerView.addItemDecoration(new SpaceItemDecoration(space));

    }

    /**
     * 请求数据
     */
    private void getData(){
        presenter.getOddPhotos(Const.QUTU_APP_KEY,page,pageSize);
    }

    /**
     * 更新列表
     * @param oddPhotoResult
     */
    @Override
    public void updateOddPhotoList(OddPhotoResult<OddPhotos> oddPhotoResult) {
        adapter.addItems(oddPhotoResult.result.oddPhotos);
    }

    @Override
    public void showProgressDialog() {
        if(emptyView.getVisibility() == View.VISIBLE){
            emptyView.setVisibility(View.GONE);
        }
        swipeRefreshLayout.setRefreshing(true);

    }

    @Override
    public void hidProgressDialog() {
        if(emptyView.getVisibility() == View.VISIBLE){
            emptyView.setVisibility(View.GONE);
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String error) {
        emptyView.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        getData();
    }

    private RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            if(lastVisibleItemPosition + 1 == adapter.getItemCount()){
                if(!swipeRefreshLayout.isRefreshing()){
                    page++;
                    getData();
                }
            }
        }
    };

}
