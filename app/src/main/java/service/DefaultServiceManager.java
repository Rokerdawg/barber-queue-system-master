package service;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.queue.shop.barber.barbershopqueuesystem.R;

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
        return new Firebase(getInstance()
                .getString(R.string.fb_connection_url));
    }
    // TODO - implement add customer to DB
    @Override
    public void addCustomerToDB() {

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
