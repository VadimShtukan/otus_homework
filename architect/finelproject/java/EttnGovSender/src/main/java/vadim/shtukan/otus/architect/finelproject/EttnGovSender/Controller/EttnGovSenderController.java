package vadim.shtukan.otus.architect.finelproject.EttnGovSender.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import vadim.shtukan.otus.architect.finelproject.EttnGovSender.RestController.DocumentRestController;
import vadim.shtukan.otus.architect.finelproject.KafkaModel.DocumentKafka;

@Controller
public class EttnGovSenderController {
    @Autowired
    public DocumentRestController documentRestController;

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public void ettGovRegistration(String ettnId) {
        EttnXml ettnXml = documentRestController.getEttnById(ettnId);

        //todo BL

        //todo delete it
        if(!ettnXml.getOwnerCompanyId().equals("14eaad53-0a81-4e70-907a-80743f0a2fe0")){
            this.sendRegistrationError(ettnId, "Компания отправителя не найдена");
            return;
        }

        this.sendRegistrationOk(ettnId);
    }

    private void sendRegistrationOk(String ettnId) {
        kafkaTemplate.send("document.ettn.registrationCbdOk", new DocumentKafka(ettnId, "Ok"));
    }

    private void sendRegistrationError(String ettnId, String errorMessage) {
        kafkaTemplate.send("document.ettn.registrationCbdError", new DocumentKafka(ettnId, errorMessage));
    }
}
