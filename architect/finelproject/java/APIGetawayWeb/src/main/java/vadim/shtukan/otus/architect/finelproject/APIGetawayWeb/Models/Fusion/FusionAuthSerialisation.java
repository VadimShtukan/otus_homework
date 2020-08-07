package vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Models.Fusion;

import org.springframework.context.annotation.Configuration;
import vadim.shtukan.otus.architect.finelproject.APIGetawayWeb.Models.UserRegistration;

@Configuration
public class FusionAuthSerialisation {

    public static FusionAuth_UserRegistration serialise(UserRegistration userRegistration, String applicationId){
        FusionAuth_UserRegistration result = new FusionAuth_UserRegistration();

        FusionAuth_Registration fusionAuth_registration = new FusionAuth_Registration();
        fusionAuth_registration.setApplicationId(applicationId);
        fusionAuth_registration.setUserName(userRegistration.getFirstName());
        fusionAuth_registration.setUsernameStatus("ACTIVE");
        result.setRegistration(fusionAuth_registration);

        result.setSendSetPasswordEmail(false);

        result.setSkipVerification(true);

        FusionAuth_User fusionAuth_user = new FusionAuth_User();
        fusionAuth_user.setPassword(userRegistration.getPassword());
        fusionAuth_user.setUserName(userRegistration.getFirstName());
        fusionAuth_user.setUsernameStatus("ACTIVE");
        fusionAuth_user.setPasswordChangeRequired(false);
        fusionAuth_user.setFirstName(userRegistration.getFirstName());
        fusionAuth_user.setLastName(userRegistration.getLastName());
        fusionAuth_user.setEmail(userRegistration.getEmail());
        fusionAuth_user.setMobilePhone(userRegistration.getMobilePhone());
        fusionAuth_user.setData(userRegistration.getCompany());
        result.setUser(fusionAuth_user);

        return result;
    }
}
