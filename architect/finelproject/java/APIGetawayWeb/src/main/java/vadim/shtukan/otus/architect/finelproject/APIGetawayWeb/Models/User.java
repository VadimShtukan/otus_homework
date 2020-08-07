package vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Models;

import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.ArrayList;
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

    public User() {
    }

    public User(DecodedJWT decodedJWT) {
        this.company = new Company();
        this.company.setId(decodedJWT.getClaim("companyId").asString());
        this.company.setName(decodedJWT.getClaim("companyName").asString());
        this.company.setEgrpo(decodedJWT.getClaim("companyEgrpo").asString());
        this.setId(decodedJWT.getClaim("userId").asString());
        this.setFirstName(decodedJWT.getClaim("userName").asString());

        String[] userGroupArray  = decodedJWT.getClaim("userGroupIdList").asArray(String.class);
        this.userGroupList = new ArrayList<>();

        for(String item: userGroupArray){
            this.userGroupList.add(new UserGroup(item));
        }
        return;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

//    public PayloadJwt toPayloadJwt() {
//        PayloadJwt payloadJwt = new PayloadJwt();
//        payloadJwt.setUserGroupList(this.getUserGroupList());
//        payloadJwt.setUserId(this.getId());
//        payloadJwt.setCompany(this.getCompany());
//        payloadJwt.setUserName(this.getLastName() + " " + this.getFirstName());
//
//        return payloadJwt;
//    }
}
