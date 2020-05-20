package vadim.shtukan.API.Gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FusionAuth_Login {
    @JsonProperty("loginId")
    private String loginId;
    @JsonProperty("password")
    private String password;
    @JsonProperty("applicationId")
    private String applicationId;
    @JsonProperty("ipAddress")
    private String ipAddress;

    @JsonProperty("ipAddress")
    public String getIpAddress() {
        return ipAddress;
    }

    @JsonProperty("ipAddress")
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @JsonProperty("applicationId")
    public String getApplicationId() {
        return applicationId;
    }

    @JsonProperty("applicationId")
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("loginId")
    public String getLoginId() {
        return loginId;
    }

    @JsonProperty("loginId")
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
