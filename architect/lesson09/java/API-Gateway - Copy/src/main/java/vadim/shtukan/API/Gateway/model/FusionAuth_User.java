package vadim.shtukan.API.Gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.swing.text.StyledEditorKit;
import java.util.PrimitiveIterator;

public class FusionAuth_User {
    @JsonProperty("email")
    private String email;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("mobilePhone")
    private String mobilePhone;
    @JsonProperty("passwordChangeRequired")
    private Boolean passwordChangeRequired;
    @JsonProperty("usernameStatus")
    private String usernameStatus;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("password")
    private String password;

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("username")
    public String getUserName() {
        return userName;
    }

    @JsonProperty("username")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty("usernameStatus")
    public String getUsernameStatus() {
        return usernameStatus;
    }

    @JsonProperty("usernameStatus")
    public void setUsernameStatus(String usernameStatus) {
        this.usernameStatus = usernameStatus;
    }

    @JsonProperty("passwordChangeRequired")
    public Boolean getPasswordChangeRequired() {
        return passwordChangeRequired;
    }

    @JsonProperty("passwordChangeRequired")
    public void setPasswordChangeRequired(Boolean passwordChangeRequired) {
        this.passwordChangeRequired = passwordChangeRequired;
    }

    @JsonProperty("mobilePhone")
    public String getMobilePhone() {
        return mobilePhone;
    }
    @JsonProperty("mobilePhone")
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

}
