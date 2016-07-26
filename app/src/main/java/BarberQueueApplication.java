import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by rmcguigan on 26/07/2016.
 */
public class BarberQueueApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        // other setup code
    }
}
