package com.barber.shop.queue.system.LoginRegister.presenter;

import com.barber.shop.queue.system.LoginRegister.interfaces.ILoginView;
import com.barber.shop.queue.system.model.Customer;
import service.ServiceManager;

/**
 * Created by rmcg2 on 18/11/2016.
 */

public class RegisterPresenter implements ServiceManager.RegisterUser{

    ILoginView.RegisterViewStuff mView;

    public RegisterPresenter(ILoginView.RegisterViewStuff mView) {
        this.mView = mView;
    }

    @Override
    public void sendUserRegistrationDetails(Customer customer) {

    }
}
