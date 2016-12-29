package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import presenter.IView.INewsItemFragment;
import presenter.ImplPresenter.NewsItemPresenterImpl;

/**
 * Created by renlijie on 16/12/28.
 */

public class NewsItemFragment extends BaseFragment implements INewsItemFragment{

    private NewsItemPresenterImpl newsPresenter = new NewsItemPresenterImpl(this);

    private String newsType; //新闻类型

    private NewsItemAdapter adapter;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private Unbinder unbinder;

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
        getData(newsType);
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView() {
        adapter = new NewsItemAdapter(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    /**
     * 请求数据
     * @param newsType
     */
    public void getData(String newsType){
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

    }

    @Override
    public void hidProgressDialog() {

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
}
