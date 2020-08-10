package vadim.shtukan.otus.architect.finelproject.EttnGovSender.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import vadim.shtukan.otus.architect.finelproject.EttnGovSender.Controller.EttnGovSenderController;
import vadim.shtukan.otus.architect.finelproject.KafkaModel.DocumentKafka;

@Component
public class KafkaListenerComponent {

    @Autowired
    private EttnGovSenderController ettnGovSender;

    @KafkaHandler
    @KafkaListener(id = "EttnGovSender1", topics = { "document.ettn.waitCbdRegistration" })
    public void createEttn(DocumentKafka documentKafka) {
        //todo change to logger
        System.out.println("Wait Cbd Registration: " + documentKafka.getId());

        ettnGovSender.ettGovRegistration(documentKafka.getId());
    }
}
