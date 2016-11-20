package service;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;

import com.barber.shop.queue.system.model.Customer;

/**
 * Created by rmcguigan on 26/07/2016.
 */
public interface ServiceManager {

    DatabaseReference getDBRootReference();
    DatabaseReference getDBChildReference(String branchName);
    Firebase getDBConnection();

    interface RegisterUser{
        void sendUserRegistrationDetails(Customer customer);
    }

    interface LoginUser {
        void loginUserByEmail(Customer customer);
    }
}
