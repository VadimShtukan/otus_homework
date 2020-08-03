package vadim.shtukan.otus.architect.finelproject.Key.Model;

import java.util.List;

public class PayloadJwt {
    private String userId;
    private String userName;
    private Company company;
    private List<UserGroup> UserGroupList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<UserGroup> getUserGroupList() {
        return UserGroupList;
    }

    public void setUserGroupList(List<UserGroup> userGroupList) {
        UserGroupList = userGroupList;
    }

    public String[] getUserGroupIdList(){
        String[] userGroupIdArray;

        if(this.getUserGroupList() == null){
            userGroupIdArray = new String[]{"0"};
            return userGroupIdArray;
        }
        userGroupIdArray = new String[this.getUserGroupList().size()];

        int i =0;
        for(UserGroup item: this.getUserGroupList()){
            userGroupIdArray[i] = item.getId();
            i++;
        }
        return userGroupIdArray;
    }
}
