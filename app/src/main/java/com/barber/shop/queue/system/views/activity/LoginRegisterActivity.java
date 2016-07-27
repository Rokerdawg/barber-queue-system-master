package com.barber.shop.queue.system.views.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.barber.shop.queue.system.adapter.LoginPagerAdapter;
import com.barber.shop.queue.system.views.fragment.LoginFragment;
import com.barber.shop.queue.system.views.fragment.RegisterFragment;
import com.queue.shop.barber.barbershopqueuesystem.R;

public class LoginRegisterActivity extends AppCompatActivity implements RegisterFragment.OnRegisterFragmentInteractionListener,
        LoginFragment.OnLoginFragmentInteractionListener {

    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        initViews();

    }

    private void initViews() {
        ViewPager vpPager = (ViewPager) findViewById(R.id.login_viewpager);
        adapterViewPager = new LoginPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
    }

    @Override
    public void onLoginFragmentInteraction(boolean response) {

    }

    @Override
    public void onRegisterFragmentInteraction(Uri uri) {

    }
}

