package com.barber.shop.queue.system.views.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.barber.shop.queue.system.views.fragment.LoginFragment;
import com.barber.shop.queue.system.views.fragment.RegisterFragment;
import com.queue.shop.barber.barbershopqueuesystem.R;

import java.util.ArrayList;
import java.util.List;

public class LoginRegisterActivity extends AppCompatActivity
        implements RegisterFragment.OnRegisterFragmentInteractionListener,
        LoginFragment.OnLoginFragmentInteractionListener {

    Toolbar mToolbar;
    ViewPager mVpPager;
    CollapsingToolbarLayout mCollapsingToolbar;
    TabLayout mTabs;
    ViewPagerAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        initViews();
        setUpTabs();
        setUpViewpager();
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.login_register_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbar.setTitle("Derry Barbers");
    }

    private void setUpViewpager() {

        mVpPager = (ViewPager) findViewById(R.id.login_viewpager);
        vpAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        vpAdapter.addFragment(new LoginFragment(), "Login");
        vpAdapter.addFragment(new RegisterFragment(), "Register");
        mVpPager.setAdapter(vpAdapter);
    }

    private void setUpTabs() {
        mTabs = (TabLayout)findViewById(R.id.login_signup_tabs);
        mTabs.post(new Runnable() {
            @Override
            public void run() {
                mTabs.setupWithViewPager(mVpPager);
            }
        });
    }

     class ViewPagerAdapter extends FragmentPagerAdapter{

        private final List <Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onLoginFragmentInteraction(boolean response) {

    }

    @Override
    public void onRegisterFragmentInteraction(Uri uri) {

    }
}

