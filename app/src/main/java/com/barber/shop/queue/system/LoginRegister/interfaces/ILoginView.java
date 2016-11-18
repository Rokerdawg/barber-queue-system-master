package com.barber.shop.queue.system.LoginRegister.interfaces;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by rmcg2 on 18/11/2016.
 */

public interface ILoginView extends BaseActivityInterface{

    interface RegisterViewStuff{

    }

    interface LoginViewStuff{
        FirebaseAuth getAuth();
    }

}
