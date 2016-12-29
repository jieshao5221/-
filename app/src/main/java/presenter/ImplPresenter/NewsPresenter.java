package presenter.ImplPresenter;

import android.os.Bundle;

import java.util.ArrayList;

import fragment.NewsItemFragment;

/**
 * Created by renlijie on 16/12/28.
 */

public class NewsPresenter {

    private ArrayList<String> typeList;
    private ArrayList<NewsItemFragment> newsItemFragments;

    /**
     * 初始化新闻类型
     * @return
     */
    public ArrayList<String> initNewsTypeList(){

        typeList = new ArrayList<>();
        typeList.removeAll(typeList);
        typeList.add("top");
        typeList.add("shehui");
        typeList.add("guonei");
        typeList.add("guoji");
        typeList.add("yule");
        typeList.add("tiyu");
        typeList.add("junshi");
        typeList.add("keji");
        typeList.add("caijing");
        typeList.add("shishang");

        return typeList;

    }

    /**
     * 设置新闻类型页
     */
    public ArrayList<NewsItemFragment> initNewsFragments(){
        newsItemFragments = new ArrayList<>();
        NewsItemFragment newsItemFragment = null;
        Bundle bundle = null;

        for (int i = 0; i<typeList.size();i++){
            newsItemFragment = new NewsItemFragment();
            bundle = new Bundle();
            bundle.putString("type",typeList.get(i));
            newsItemFragment.setArguments(bundle);
            newsItemFragments.add(newsItemFragment);
        }
        return newsItemFragments;
    }
}
