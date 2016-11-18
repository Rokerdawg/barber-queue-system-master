package service;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.queue.shop.barber.barbershopqueuesystem.R;

import com.barber.shop.queue.system.model.Customer;

import static com.barber.shop.queue.system.BarberQueueApplication.*;

/**
 * Created by rmcguigan on 26/07/2016.
 */
public class DefaultServiceManager implements ServiceManager {

    @Override
    public DatabaseReference getDBRootReference() {
        // Returns a reference to the root of the firebase json tree
        return FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public DatabaseReference getDBChildReference(String branchName) {
        return getDBRootReference().child(branchName);
    }

    @Override
    public Firebase getDBConnection() {
        final Firebase myFirebaseRef = new Firebase(getInstance().
                getString(R.string.firebase_connection_url));
       return myFirebaseRef;

    }

}
