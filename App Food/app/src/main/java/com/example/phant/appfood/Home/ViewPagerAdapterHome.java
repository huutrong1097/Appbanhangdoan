package com.example.phant.appfood.Home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapterHome extends FragmentPagerAdapter{
    private List<Fragment> list = new ArrayList<>();


    public ViewPagerAdapterHome(FragmentManager fm) {
        super(fm);
        list.add(new MenuFragment());
        list.add(new AboutFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
