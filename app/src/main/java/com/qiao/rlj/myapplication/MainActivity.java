package com.qiao.rlj.myapplication;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import Const.Const;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import fragment.BlankFragment;
import presenter.ImplPresenter.NewsPresenterImpl;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    /** BindView */
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;//底部导航

    @BindView(R.id.fragment_container)
    FrameLayout container;//fragment容器

    @BindView(R.id.toolbar)
    Toolbar toolbar;//toolbar

    /** BindResource */
    @BindString(R.string.home)
    String home;
    @BindString(R.string.books)
    String books;
    @BindString(R.string.games)
    String games;
    @BindString(R.string.music)
    String music;
    @BindString(R.string.favorite)
    String favorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setDefaultFragment();
        initViews();

        NewsPresenterImpl newsPresenter = new NewsPresenterImpl();
        newsPresenter.getNewsData("top",Const.APP_KEY);

    }

    private void initViews() {

        //初始化toolbar
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);

        //初始化底部导航栏
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setActiveColor(R.color.colorPrimary);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp,home))
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp,books))
                .addItem(new BottomNavigationItem(R.mipmap.ic_music_note_white_24dp,music))
                .addItem(new BottomNavigationItem(R.mipmap.ic_videogame_asset_white_24dp,games))
                .addItem(new BottomNavigationItem(R.mipmap.ic_favorite_white_24dp,favorite))
                .setFirstSelectedPosition(0)
                .setTabSelectedListener(this)
                .initialise();
    }

    /**设置默认选中的fragment*/
    private void setDefaultFragment(){
        BlankFragment bf = new BlankFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container,bf);
        ft.commit();
    }

    /**底部导航栏监听器*/
    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }




}