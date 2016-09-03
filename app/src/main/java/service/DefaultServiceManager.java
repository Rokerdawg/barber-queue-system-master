package service;
import com.firebase.client.Firebase;
import com.firebase.ui.auth.core.FirebaseAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.queue.shop.barber.barbershopqueuesystem.R;

import model.Customer;

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
    @Override
    public void addCustomerToDB(Customer customer) {
    }
    // TODO - implement remove customer to DB
    @Override
    public void removeCustomerFromDB() {

    }
    // TODO - implement refresh DB
    @Override
    public void refreshDB() {

    }

    @Override
    public void storeUserRegistration() {


    }

    @Override
    public void loginUser() {

    }


}
