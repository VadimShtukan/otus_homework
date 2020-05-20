package vadim.shtukan.API.Gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import vadim.shtukan.API.Gateway.model.*;

@Configuration
public class FusionAuthSerialisation {
    public static FusionAuth_Login serialisat(FusionAuth_Login fusionAuth_login, String applicationId){
        fusionAuth_login.setApplicationId(applicationId);
        return fusionAuth_login;
    }

    public static FusionAuth_RegistrationUser serialisat(UserForRegistration userForRegistration, String applicationId){
        FusionAuth_RegistrationUser result = new FusionAuth_RegistrationUser();

        FusionAuth_Registration fusionAuth_registration = new FusionAuth_Registration();
        fusionAuth_registration.setApplicationId(applicationId);
        fusionAuth_registration.setUserName(userForRegistration.getUserName());
        fusionAuth_registration.setUsernameStatus("ACTIVE");
        result.setFusionAuth_registration(fusionAuth_registration);

        result.setSendSetPasswordEmail(false);

        result.setSkipVerificationl(true);

        FusionAuth_User fusionAuth_user = new FusionAuth_User();
        fusionAuth_user.setPassword(userForRegistration.getPassword());
        fusionAuth_user.setUserName(userForRegistration.getUserName());
        fusionAuth_user.setUsernameStatus("ACTIVE");
        fusionAuth_user.setPasswordChangeRequired(false);
        fusionAuth_user.setFirstName(userForRegistration.getFirstName());
        fusionAuth_user.setLastName(userForRegistration.getLastName());
        fusionAuth_user.setEmail(userForRegistration.getEmail());
        result.setFusionAuth_user(fusionAuth_user);

        return result;

    }

    public static User serialisat(FusionAuth_UserUser fusionAuth_user) {
        User result = new User();
        result.setEmail(fusionAuth_user.getFusionAuth_user().getEmail());
        result.setFirstName(fusionAuth_user.getFusionAuth_user().getFirstName());
        result.setLastName(fusionAuth_user.getFusionAuth_user().getLastName());
        result.setPhone(fusionAuth_user.getFusionAuth_user().getMobilePhone());
        result.setUserName(fusionAuth_user.getFusionAuth_user().getUserName());

        return result;
    }

    public static FusionAuth_UserUser serialisat(User user) {
        FusionAuth_UserUser result = new FusionAuth_UserUser();

        FusionAuth_User fusionAuth_user = new FusionAuth_User();
        fusionAuth_user.setUserName(user.getUserName());
        fusionAuth_user.setUsernameStatus("ACTIVE");
        fusionAuth_user.setPasswordChangeRequired(false);
        fusionAuth_user.setFirstName(user.getFirstName());
        fusionAuth_user.setLastName(user.getLastName());
        fusionAuth_user.setEmail(user.getEmail());
        fusionAuth_user.setMobilePhone(user.getPhone());
        result.setFusionAuth_user(fusionAuth_user);

        return result;
    }
}
