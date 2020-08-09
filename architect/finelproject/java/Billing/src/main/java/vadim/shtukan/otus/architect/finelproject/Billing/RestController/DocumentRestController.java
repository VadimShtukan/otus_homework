package vadim.shtukan.otus.architect.finelproject.Billing.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import vadim.shtukan.otus.architect.finelproject.Billing.Model.EttnXml;

import java.util.Arrays;

@Controller
public class DocumentRestController {
    @Value("${app.services.document}")
    private String documentHost;

    private RestTemplate restTemplate = new RestTemplate();

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
