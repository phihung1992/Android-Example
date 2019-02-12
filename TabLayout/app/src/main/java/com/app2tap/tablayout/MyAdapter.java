package com.app2tap.tablayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Project name: TabLayout
 * Author: app2tap
 * Created date: 4/8/2017.
 */

public class MyAdapter extends FragmentStatePagerAdapter {
    private String listTab[] = {"Fragment 1", "Fragment 2"};
    private FirstFragment mFirstFragment;
    private SecondFragment mSecondFragment;

    public MyAdapter(FragmentManager fm) {
        super(fm);
        mFirstFragment = new FirstFragment();
        mSecondFragment = new SecondFragment();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return mFirstFragment;
        } else if (position == 1) {
            return mSecondFragment;
        } else {
            //TODO nothing
        }
        return null;
    }

    @Override
    public int getCount() {
        return listTab.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
