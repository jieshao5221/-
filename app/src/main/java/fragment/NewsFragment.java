package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiao.rlj.myapplication.R;

import java.util.ArrayList;

import adapter.NewsFragmentAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import presenter.ImplPresenter.NewsPresenter;

/**
 * 新闻整体页
 * Created by renlijie on 16/12/28.
 */

public class NewsFragment extends BaseFragment{

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    NewsPresenter newsPresenter;
    ArrayList<String> list;
    ArrayList<NewsItemFragment> fragments;

    private static NewsFragment newsFragment;

    public static  NewsFragment getInstance(){
        if (newsFragment == null){
            newsFragment = new NewsFragment();
        }
        return newsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_news,container,false);
        ButterKnife.bind(this,v);
        setAdapter();
        return v;
    }

    /**
     * 为viewpager设置数据
     */
    public void setAdapter(){
        newsPresenter = new NewsPresenter();
        list = newsPresenter.initNewsTypeList();
        fragments = newsPresenter.initNewsFragments();
        NewsFragmentAdapter adapter = new NewsFragmentAdapter(getFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(10);
        tabLayout.setupWithViewPager(viewPager); //将tabLayout和viewPager关联起来
        setTabText();
    }

    /**
     * tablayout设置数据
     */
    public void setTabText(){
        setText(0,"头条");
        setText(1,"社会");
        setText(2,"国内");
        setText(3,"国际");
        setText(4,"娱乐");
        setText(5,"体育");
        setText(6,"军事");
        setText(7,"科技");
        setText(8,"财经");
        setText(9,"时尚");
    }

    /**
     * 设置选项卡
     * @param type
     */
    public void setText(int index,String type){
        tabLayout.getTabAt(index).setText(type);
    }

}
