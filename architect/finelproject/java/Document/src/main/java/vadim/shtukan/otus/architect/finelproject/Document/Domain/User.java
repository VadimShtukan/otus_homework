package vadim.shtukan.otus.architect.finelproject.Document.Domain;

import java.util.List;

public class User {


    private String id;
    private Company company;
    private String email;
    private String firstName;
    private String lastName;
    private String mobilePhone;
    private String serialNumber;
    private List<UserGroup> userGroupList;
    private String [] userGroupIdList;

    public String[] getUserGroupIdList() {
        return userGroupIdList;
    }

    public void setUserGroupIdList(String[] userGroupIdList) {
        this.userGroupIdList = userGroupIdList;
    }

    public User() {
    }


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }


    public List<UserGroup> getUserGroupList() {
        return userGroupList;
    }

    public void setUserGroupList(List<UserGroup> userGroupList) {
        this.userGroupList = userGroupList;
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

}
