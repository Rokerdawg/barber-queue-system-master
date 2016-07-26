package com.barber.shop.queue.system;

import android.app.Application;
import com.firebase.client.Firebase;
/**
 * Created by rmcguigan on 26/07/2016.
 */
public class BarberQueueApplication extends Application {

    private static BarberQueueApplication mInstance;


    public static BarberQueueApplication getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Firebase.setAndroidContext(this);

    }
}
