package com.barber.shop.queue.system.views.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.barber.shop.queue.system.adapter.SignInChoicePagerAdapter;
import com.barber.shop.queue.system.util.DisableCoordinatorLayout;
import com.barber.shop.queue.system.views.fragment.LoginFragment;
import com.barber.shop.queue.system.views.fragment.RegisterFragment;
import com.barber.shop.queue.system.views.fragment.RegisterFragment.OnRegisterFragmentInteractionListener;
import com.firebase.client.Firebase;
import com.queue.shop.barber.barbershopqueuesystem.R;

import model.Customer;
import service.DefaultServiceManager;

public class LoginRegisterActivity extends AppCompatActivity
        implements OnRegisterFragmentInteractionListener,
        LoginFragment.OnLoginFragmentInteractionListener {

    ViewPager mVpPager;
    DisableCoordinatorLayout mCollapsingToolbar;
    TabLayout mTabs;
    SignInChoicePagerAdapter vpAdapter;
    AppBarLayout mAppBarLayout;
    TextView mChooseText;
    Customer registeredCustObject;
    DefaultServiceManager serviceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        initViews();
        setUpTabs();
        setUpViewpager();
        hideStatusBar();
        serviceManager = new DefaultServiceManager();
        Firebase.setAndroidContext(this);

    }

    private void hideStatusBar() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
    }

    private void initViews() {
        mCollapsingToolbar = (DisableCoordinatorLayout) findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbar.setPassScrolling(false);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        mChooseText = (TextView) findViewById(R.id.choose_textview);
        chooseSignInClickEvent();
    }

    private void chooseSignInClickEvent() {
        mChooseText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAppBarLayout.setExpanded(false, true);
            }
        });
    }

    private void setUpViewpager() {
        mVpPager = (ViewPager) findViewById(R.id.login_viewpager);
        vpAdapter = new SignInChoicePagerAdapter(getSupportFragmentManager());
        vpAdapter.addFragment(new LoginFragment(), "Login");
        vpAdapter.addFragment(new RegisterFragment(), "Register");
        mVpPager.setAdapter(vpAdapter);
    }

    private void setUpTabs() {
        mTabs = (TabLayout) findViewById(R.id.login_signup_tabs);
        mTabs.post(new Runnable() {
            @Override
            public void run() {
                mTabs.setupWithViewPager(mVpPager);
            }
        });
    }

    public void sendUserRegistration(Customer customer){
        serviceManager.addCustomerToDB(customer);
    }


    @Override
    public void onLoginFragmentInteraction(boolean response) {

    }

    @Override
    public void onRegisterFragmentInteraction(Customer customer) {
//        registeredCustObject = customer;
    }
}

