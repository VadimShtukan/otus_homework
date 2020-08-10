package vadim.shtukan.otus.architect.finelproject.Billing.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import vadim.shtukan.otus.architect.finelproject.Billing.Controller.BillingController;
import vadim.shtukan.otus.architect.finelproject.Billing.Controller.SecurityController;
import vadim.shtukan.otus.architect.finelproject.Billing.Model.Debit;
import vadim.shtukan.otus.architect.finelproject.Billing.Model.EttnXml;
import vadim.shtukan.otus.architect.finelproject.Billing.Model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
@RequestMapping("billing")
public class DocumentRestController {
    @Value("${app.services.document}")
    private String documentHost;

    @Autowired
    private SecurityController securityController;

    @Autowired
    private BillingController billingController;

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/debit/{companyId}")
    public Debit getDebitByCompanyId(@PathVariable String companyId, HttpServletRequest httpRequest) throws JsonProcessingException {
        //todo move securityController.getUser to filter
        User user = securityController.getUser(httpRequest);
        //todo check user right in object user

        //todo check - имеет ли право этот пользователь запрашивать этот документ?

        return billingController.getDebitByCompany(companyId);
    }


    public EttnXml getEttnById(String ettnId){

        ResponseEntity<EttnXml> response = restTemplate.exchange(documentHost + "/ettn/" + ettnId,
                HttpMethod.GET, this.getEntity(), EttnXml.class);

        return response.getBody();
    }

    private HttpEntity<String> getEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        //todo set real UserId for Billing ID
        headers.set("X-Auth-User", "{\"id\":\"5f2aa09b5e72392453f97193\",\"company\":{\"id\":\"14eaad53-0a81-4e70-907a-80743f0a2fe0\",\"egrpo\":\"998\",\"name\":\"Lockman\"},\"email\":null,\"firstName\":\"Krajcik Georgette\",\"lastName\":null,\"mobilePhone\":null,\"serialNumber\":null,\"userGroupList\":[{\"name\":null,\"id\":\"0\"},{\"name\":null,\"id\":\"1\"},{\"name\":null,\"id\":\"2\"}],\"userGroupIdList\":[\"0\",\"1\",\"2\"]}");
        //todo add JWT

        return new HttpEntity<String>(headers);
    }
}
