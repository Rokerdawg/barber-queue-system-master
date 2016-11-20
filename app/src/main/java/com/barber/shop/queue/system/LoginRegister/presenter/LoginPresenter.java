package com.barber.shop.queue.system.LoginRegister.presenter;

import android.content.Context;

import com.barber.shop.queue.system.BarberQueueApplication;
import com.barber.shop.queue.system.LoginRegister.interfaces.BasePresenterInterface;
import com.barber.shop.queue.system.LoginRegister.interfaces.ILoginView;
import com.barber.shop.queue.system.model.Customer;
import com.google.firebase.auth.FirebaseAuth;

import service.ServiceManager;

/**
 * Created by rmcg2 on 18/11/2016.
 */

public class LoginPresenter implements BasePresenterInterface, ServiceManager.LoginUser {

    private static final String TAG = LoginPresenter.class.getSimpleName();
    ILoginView.LoginViewStuff mView;
    private FirebaseAuth mAuth;

//    public LoginPresenter(ILoginView.LoginViewStuff mView) {
//        this.mView = mView;
//    }


    @Override
    public void attach(ILoginView.LoginViewStuff view) {
        mView = view;
    }

    @Override
    public void detach() {
        mView = null;
    }

    @Override
    public void loginUserByEmail(Customer customer) {
        String email = customer.getEmailAddress();
        String password = customer.getPassword();

        BarberQueueApplication.getInstance().loginAUser((Context) mView, email, password);
    }



}
