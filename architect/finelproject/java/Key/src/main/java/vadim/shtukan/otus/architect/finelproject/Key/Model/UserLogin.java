package vadim.shtukan.otus.architect.finelproject.Key.Model;

public class UserLogin {
    private String userId;
    private String jwt;

    public UserLogin(String userId, String jwt) {
        this.userId = userId;
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
