package vadim.shtukan.API.Gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FusionAuth_RegistrationUser {
    @JsonProperty("registration")
    private FusionAuth_Registration fusionAuth_registration;
    @JsonProperty("sendSetPasswordEmail")
    private Boolean sendSetPasswordEmail;
    @JsonProperty("skipVerification")
    private Boolean skipVerificationl;
    @JsonProperty("user")
    private FusionAuth_User fusionAuth_user;

    @JsonProperty("user")
    public FusionAuth_User getFusionAuth_user() {
        return fusionAuth_user;
    }

    @JsonProperty("user")
    public void setFusionAuth_user(FusionAuth_User fusionAuth_user) {
        this.fusionAuth_user = fusionAuth_user;
    }

    @JsonProperty("skipVerification")
    public Boolean getSkipVerificationl() {
        return skipVerificationl;
    }

    @JsonProperty("skipVerification")
    public void setSkipVerificationl(Boolean skipVerificationl) {
        this.skipVerificationl = skipVerificationl;
    }

    @JsonProperty("sendSetPasswordEmail")
    public Boolean getSendSetPasswordEmail() {
        return sendSetPasswordEmail;
    }

    @JsonProperty("sendSetPasswordEmail")
    public void setSendSetPasswordEmail(Boolean sendSetPasswordEmail) {
        this.sendSetPasswordEmail = sendSetPasswordEmail;
    }

    @JsonProperty("registration")
    public FusionAuth_Registration getFusionAuth_registration() {
        return fusionAuth_registration;
    }

    @JsonProperty("registration")
    public void setFusionAuth_registration(FusionAuth_Registration fusionAuth_registration) {
        this.fusionAuth_registration = fusionAuth_registration;
    }
}
