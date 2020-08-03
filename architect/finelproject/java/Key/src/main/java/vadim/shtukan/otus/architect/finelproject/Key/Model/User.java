package vadim.shtukan.otus.architect.finelproject.Key.Model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class User {
    @Id
    private String id;
    private Company company;
    private String email;
    private String firstName;
    private String lastName;
    private String mobilePhone;
    private String regKeyId;
    private List<UserGroup> UserGroupList;

    public String getRegKeyId() {
        return regKeyId;
    }

    public void setRegKeyId(String regKeyId) {
        this.regKeyId = regKeyId;
    }

    public String[] getUserGroupIdList(){
        String[] userGroupIdArray = new String[this.getUserGroupList().size()];

        int i =0;
        for(UserGroup item: this.getUserGroupList()){
            userGroupIdArray[i] = item.getId();
            i++;
        }
        return userGroupIdArray;
    }

    public List<UserGroup> getUserGroupList() {
        return UserGroupList;
    }

    public void setUserGroupList(List<UserGroup> userGroupList) {
        UserGroupList = userGroupList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
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

    public PayloadJwt toPayloadJwt() {
        PayloadJwt payloadJwt = new PayloadJwt();
        payloadJwt.setUserGroupList(this.getUserGroupList());
        payloadJwt.setUserId(this.getId());
        payloadJwt.setCompany(this.getCompany());
        payloadJwt.setUserName(this.getLastName() + " " + this.getFirstName());

        return payloadJwt;
    }
}
