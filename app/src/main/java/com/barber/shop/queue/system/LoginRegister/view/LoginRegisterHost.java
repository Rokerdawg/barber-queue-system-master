package com.barber.shop.queue.system.LoginRegister.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.barber.shop.queue.system.BarberQueueApplication;
import com.barber.shop.queue.system.LoginRegister.interfaces.ILoginView;
import com.barber.shop.queue.system.LoginRegister.presenter.LoginPresenter;
import com.barber.shop.queue.system.LoginRegister.presenter.RegisterPresenter;
import com.barber.shop.queue.system.adapter.SignInChoicePagerAdapter;
import com.barber.shop.queue.system.LoginRegister.view.RegisterView.OnRegisterFragmentInteractionListener;
import com.facebook.FacebookSdk;
import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.queue.shop.barber.barbershopqueuesystem.R;

import com.barber.shop.queue.system.model.Customer;

public class LoginRegisterHost extends AppCompatActivity
        implements ILoginView.LoginViewStuff, ILoginView.RegisterViewStuff,
        LoginView.Communicator,
        OnRegisterFragmentInteractionListener{

    private static final String TAG = LoginRegisterHost.class.getSimpleName();
    ViewPager mVpPager;
    TabLayout mTabs;
    SignInChoicePagerAdapter vpAdapter;
    AppBarLayout mAppBarLayout;
    RegisterPresenter registerPresenter;
    LoginPresenter loginPresenter;
    public FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login_register);
        initViews();
        setUpTabs();
        setUpViewpager();
        initPresenters();
        hideStatusBar();

        mAuth = ((BarberQueueApplication)getApplication()).getFirebaseAuth();
        ((BarberQueueApplication)getApplication()).checkUserLogin(LoginRegisterHost.this);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        Firebase.setAndroidContext(this);

    }

    public FirebaseAuth getAuth(){
        return mAuth;
    }

    @Override
    public void showProgressBar() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgressBar() {
        progressDialog.dismiss();
    }

    public void initPresenters() {
        registerPresenter = new RegisterPresenter(this);
        // todo add attach to provide access between presenter and view


        loginPresenter = new LoginPresenter();
        loginPresenter.attach(this);
    }

    private void hideStatusBar() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
    }

    private void initViews() {
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void setUpViewpager() {
        mVpPager = (ViewPager) findViewById(R.id.login_viewpager);
        vpAdapter = new SignInChoicePagerAdapter(getSupportFragmentManager());
        vpAdapter.addFragment(new LoginView(), "Login");
        vpAdapter.addFragment(new RegisterView(), "Register");
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




    @Override
    public void onRegisterFragmentInteraction(Customer customer) {
//        registeredCustObject = customer;
    }

    @Override
    public void onLoginFragmentInteraction(Customer response) {
        loginPresenter.loginUserByEmail(response);
    }
}

