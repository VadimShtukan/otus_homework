package vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Model.Fusion;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FusionAuth_UserRegistration {

    private FusionAuth_Registration registration;

    private Boolean sendSetPasswordEmail;

    private Boolean skipVerification;

    private FusionAuth_User user;

    public FusionAuth_Registration getRegistration() {
        return registration;
    }

    public void setRegistration(FusionAuth_Registration registration) {
        this.registration = registration;
    }

    public Boolean getSendSetPasswordEmail() {
        return sendSetPasswordEmail;
    }

    public void setSendSetPasswordEmail(Boolean sendSetPasswordEmail) {
        this.sendSetPasswordEmail = sendSetPasswordEmail;
    }

    public Boolean getSkipVerification() {
        return skipVerification;
    }

    public void setSkipVerification(Boolean skipVerification) {
        this.skipVerification = skipVerification;
    }

    public FusionAuth_User getUser() {
        return user;
    }

    public void setUser(FusionAuth_User user) {
        this.user = user;
    }
}
