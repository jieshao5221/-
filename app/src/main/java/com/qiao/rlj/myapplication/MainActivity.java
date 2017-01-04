package com.qiao.rlj.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import fragment.NewsFragment;
import fragment.OddPhotoFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    /** BindView */
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;//底部导航

    @BindView(R.id.fragment_container)
    FrameLayout container;//fragment容器

    /** BindResource */
    @BindString(R.string.news)
    String news;
    @BindString(R.string.odd_photo)
    String girls;
    @BindString(R.string.mine)
    String mine;

    private Fragment currentFragment;
    private final static String newsFragment = "newsFragment";
    private final static String oddPhotoFragment = "oddPhotoFragment";
    private FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initViews();
        switchFragment(newsFragment);
    }

    private void initViews() {

        //初始化底部导航栏
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setActiveColor(R.color.colorPrimary);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp,news))
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp,girls))
                .addItem(new BottomNavigationItem(R.mipmap.ic_favorite_white_24dp,mine))
                .setFirstSelectedPosition(0)
                .setTabSelectedListener(this)
                .initialise();

        manager = getSupportFragmentManager();

    }

    /**底部导航栏监听器*/
    @Override
    public void onTabSelected(int position) {
        switch (position){
            case 0:
                switchFragment(newsFragment);
                break;
            case 1:
                switchFragment(oddPhotoFragment);
                break;
            case 2:
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 切换fragment
     * @param tag
     */
    private void switchFragment(String tag){
        Fragment fragment = manager.findFragmentByTag(tag);
        if (fragment == null){
            switch (tag){
                case newsFragment:
                    fragment = NewsFragment.getInstance();
                    if(currentFragment != null){
                        manager.beginTransaction().hide(currentFragment).add(R.id.fragment_container,fragment,tag).commit();
                    }else {
                        manager.beginTransaction().add(R.id.fragment_container,fragment,tag).commit();
                    }

                    currentFragment = fragment;

                    break;
                case oddPhotoFragment:
                    fragment = OddPhotoFragment.getInstance();
                    if(currentFragment != null){
                        manager.beginTransaction().hide(currentFragment).add(R.id.fragment_container,fragment,tag).commit();
                    }else {
                        manager.beginTransaction().add(R.id.fragment_container,fragment,tag).commit();
                    }
                    currentFragment = fragment;
                    break;
            }
        }else if (fragment.isHidden()){
            manager.beginTransaction().hide(currentFragment).show(fragment).commit();
            currentFragment = fragment;
        }

    }




}
