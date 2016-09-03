package service;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;

import model.Customer;

/**
 * Created by rmcguigan on 26/07/2016.
 */
public interface ServiceManager {

    DatabaseReference getDBRootReference();
    DatabaseReference getDBChildReference(String branchName);
    Firebase getDBConnection();
    void addCustomerToDB(Customer customer);
    void removeCustomerFromDB();
    void refreshDB();
    void storeUserRegistration();
    void loginUser();
}
