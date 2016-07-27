package model;

/**
 * Created by rmcguigan on 25/07/2016.
 */
public class Customer {

    String name;
    String telephoneNumber;
    String confirmPhone;
    String emailAddress;
    String password;
    String confirmPassword;

    public Customer() {
    }

    public Customer(String name, String telephoneNumber, String confirmPhoneNumber,
                    String email, String password, String confirmPassword) {
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.confirmPhone = confirmPhoneNumber;
        this.emailAddress = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getConfirmPhone() {
        return confirmPhone;
    }

    public void setConfirmPhone(String confirmPhone) {
        this.confirmPhone = confirmPhone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
