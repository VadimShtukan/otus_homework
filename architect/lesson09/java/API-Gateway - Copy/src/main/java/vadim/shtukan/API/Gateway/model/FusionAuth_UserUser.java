package vadim.shtukan.API.Gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FusionAuth_UserUser {
    @JsonProperty("user")
    FusionAuth_User fusionAuth_user;

    @JsonProperty("user")
    public FusionAuth_User getFusionAuth_user() {
        return fusionAuth_user;
    }
    @JsonProperty("user")
    public void setFusionAuth_user(FusionAuth_User fusionAuth_user) {
        this.fusionAuth_user = fusionAuth_user;
    }
}
