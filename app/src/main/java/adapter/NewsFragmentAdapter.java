package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import fragment.NewsItemFragment;

/**
 * Created by renlijie on 16/12/28.
 */

public class NewsFragmentAdapter extends FragmentStatePagerAdapter {

    private ArrayList<NewsItemFragment> fragmentList;


    public NewsFragmentAdapter(FragmentManager fm, ArrayList<NewsItemFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
