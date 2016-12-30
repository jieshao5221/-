package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiao.rlj.myapplication.R;

import java.util.ArrayList;

import Const.Const;
import adapter.NewsItemAdapter;
import bean.News;
import bean.NewsItem;
import bean.Result;
import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import presenter.IView.INewsItemFragment;
import presenter.ImplPresenter.NewsItemPresenterImpl;

/**
 * Created by renlijie on 16/12/28.
 */

public class NewsItemFragment extends BaseFragment implements INewsItemFragment, SwipeRefreshLayout.OnRefreshListener {

    private NewsItemPresenterImpl newsPresenter = new NewsItemPresenterImpl(this);

    private String newsType; //新闻类型

    private NewsItemAdapter adapter;

    private LinearLayoutManager linearLayoutManager;

    private ArrayList<NewsItem> oldNewsItems = new ArrayList<>();

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private Unbinder unbinder;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindColor(R.color.colorPrimary)
    int color; //进度条颜色

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_news_item,container,false);
        newsType = getArguments().getString("type");
        unbinder = ButterKnife.bind(this,v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        initView();
        showProgressDialog();
        getData();
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 初始化视图
     */
    private void initView() {

        swipeRefreshLayout.setColorSchemeColors(color);
        swipeRefreshLayout.setOnRefreshListener(this);

        adapter = new NewsItemAdapter(getActivity());
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(onScrollListener);


    }

    /**
     * 请求数据
     */
    public void getData(){
        newsPresenter.getNewsData(newsType,Const.APP_KEY);
    }

    /**
     * 更新数据
     * @param newsResult
     */
    @Override
    public void updateList(Result<News> newsResult) {
        setItemShowType(newsResult.result.newsList);
        adapter.addItems(newsResult.result.newsList);
    }

    /**
     * 新闻显示方式 0、3. 大图  1、2 小图在左  4、5 小图在右
     * @param list
     */
    public void setItemShowType(ArrayList<NewsItem> list){

        for(int i = 0; i < list.size(); i++){
            if(i%6 == 0 || i%6 ==3){
                list.get(i).showType  = 0;
            }else if(i%6 == 1 || i%6 == 2){
                list.get(i).showType  = 1;
            }
            else if(i%6 == 4 || i%6 == 5){
                list.get(i).showType  = 2;
            }
        }
    }


    @Override
    public void showProgressDialog() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hidProgressDialog() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        newsPresenter.unsubscribe();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        getData();
    }


    /**
     * 因为开放api没有分页，所以无论上拉还是下拉都采用加载同一数据
     */
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            if(lastVisibleItemPosition + 1 == adapter.getItemCount()){
                if(!swipeRefreshLayout.isRefreshing()){
                    getData();
                }
            }
        }
    };


}
