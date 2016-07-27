package com.barber.shop.queue.system.adapter;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.barber.shop.queue.system.views.fragment.LoginFragment;
import com.barber.shop.queue.system.views.fragment.RegisterFragment;

/**
 * Created by rmcguigan on 27/07/2016.
 */
public class LoginPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;

    public LoginPagerAdapter(android.support.v4.app.FragmentManager fragmentManager){
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return LoginFragment.newInstance(0, "Page # 1");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return RegisterFragment.newInstance(1, "Page # 2");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}
