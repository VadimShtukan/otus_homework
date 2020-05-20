package vadim.shtukan.API.Gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FusionAuth_Registration {
    @JsonProperty("applicationId")
    private String applicationId;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("usernameStatus")
    private String usernameStatus;
    @JsonProperty("id")
    private String id;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("usernameStatus")
    public String getUsernameStatus() {
        return usernameStatus;
    }

    @JsonProperty("usernameStatus")
    public void setUsernameStatus(String usernameStatus) {
        this.usernameStatus = usernameStatus;
    }

    @JsonProperty("username")
    public String getUserName() {
        return userName;
    }

    @JsonProperty("username")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty("applicationId")
    public String getApplicationId() {
        return applicationId;
    }
    @JsonProperty("applicationId")
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
