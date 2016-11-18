package com.barber.shop.queue.system.LoginRegister.interfaces;

/**
 * Created by rmcg2 on 18/11/2016.
 */

public interface BasePresenterInterface<T extends ILoginView.LoginViewStuff>{
        void attach(T view);
        void detach();
    }

