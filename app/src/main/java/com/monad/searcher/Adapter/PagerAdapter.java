package com.monad.searcher.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.monad.searcher.Fragment.MyFragment;
import com.monad.searcher.Fragment.MyFragment2;
import com.monad.searcher.Fragment.MyFragment3;
import com.monad.searcher.Fragment.MyFragment4;


public class PagerAdapter extends FragmentStatePagerAdapter {
    int tabCount;

    public PagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MyFragment tab1 = new MyFragment();
                return tab1;
            case 1:
                MyFragment2 tab2 = new MyFragment2();
                return tab2;
            case 2:
                MyFragment3 tab3 = new MyFragment3();
                return tab3;
            case 3:
                MyFragment4 tab4 = new MyFragment4();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}