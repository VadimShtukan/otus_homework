package vadim.shtukan.otus.architect.finelproject.Document.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import vadim.shtukan.otus.architect.finelproject.Document.Controller.EttnController;
import vadim.shtukan.otus.architect.finelproject.Document.Controller.SecurityController;
import vadim.shtukan.otus.architect.finelproject.Document.Model.EttnXml;
import vadim.shtukan.otus.architect.finelproject.Document.Model.User;
import vadim.shtukan.otus.architect.finelproject.Document.Model.UserGroup;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("ettn")
public class EttnRestController {
    @Autowired
    private EttnController ettnController;

    @Autowired
    private SecurityController securityController;

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @PutMapping("/recipient/{ettnId}")
    public EttnXml addRecipientInfo(@PathVariable String ettnId, @RequestBody EttnXml ettnXml, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        //todo move securityController.getUser to filter
        User user = securityController.getUser(httpServletRequest);
        //todo check user right in object user

        return ettnController.addRecipientInfoInEttn(ettnId, ettnXml);
    }

    @PutMapping("/transporter/{ettnId}")
    public EttnXml addTransporterInfo(@PathVariable String ettnId, @RequestBody EttnXml ettnXml, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        //todo move securityController.getUser to filter
        User user = securityController.getUser(httpServletRequest);
        //todo check user right in object user

        return ettnController.addTransporterInfoInEttn(ettnId, ettnXml);
    }

    @RequestMapping("originator")
    @PostMapping
    public EttnXml createXmlOriginator(@RequestBody EttnXml ettnXml, HttpServletRequest httpRequest) throws JsonProcessingException {
        //todo move securityController.getUser to filter
        User user = securityController.getUser(httpRequest);
        //todo check user right in object user

        return ettnController.addNewEttn(ettnXml, user);
    }


    @GetMapping("/{ettnId}")
    public EttnXml getXmlById(@PathVariable String ettnId, HttpServletRequest httpRequest) throws JsonProcessingException {
        //todo move securityController.getUser to filter
        User user = securityController.getUser(httpRequest);
        //todo check user right in object user

        EttnXml ettnXml = ettnController.getXml(ettnId);

        //todo check - имеет ли право этот пользователь запрашивать этот документ?

        return ettnXml;
    }

    //todo delete it
    @GetMapping("test")
    public void test(){
        kafkaTemplate.send("userGroup", new UserGroup("123"));
    }
}
