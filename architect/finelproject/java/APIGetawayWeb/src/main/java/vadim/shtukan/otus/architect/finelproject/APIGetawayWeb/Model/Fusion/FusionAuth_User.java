package vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Model.Fusion;

import com.fasterxml.jackson.annotation.JsonProperty;
import vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Model.Company;

public class FusionAuth_User {

    private String email;

    private String firstName;

    private String lastName;

    private String mobilePhone;

    private Boolean passwordChangeRequired;

    private String usernameStatus;

    private String userName;

    private String password;

    public String getEmail() {
        return email;
    }

    public Company data;

    public Company getData() {
        return data;
    }

    public void setData(Company data) {
        this.data = data;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Boolean getPasswordChangeRequired() {
        return passwordChangeRequired;
    }

    public void setPasswordChangeRequired(Boolean passwordChangeRequired) {
        this.passwordChangeRequired = passwordChangeRequired;
    }

    public String getUsernameStatus() {
        return usernameStatus;
    }

    public void setUsernameStatus(String usernameStatus) {
        this.usernameStatus = usernameStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
