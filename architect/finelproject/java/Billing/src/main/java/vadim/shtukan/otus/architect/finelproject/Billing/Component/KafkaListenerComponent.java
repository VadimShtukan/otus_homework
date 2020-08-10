package vadim.shtukan.otus.architect.finelproject.Billing.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import vadim.shtukan.otus.architect.finelproject.Billing.Controller.BillingController;
import vadim.shtukan.otus.architect.finelproject.KafkaModel.DocumentKafka;

@Component
public class KafkaListenerComponent {

    @Autowired
    private BillingController billingController;

    @KafkaHandler
    @KafkaListener(id = "Billing1", topics = { "document.ettn.create" })
    public void createEttn(DocumentKafka documentKafka) {
        //todo change to logger
        System.out.println("Create Document: " + documentKafka.getId());

        billingController.calcCreatedEttnById(documentKafka.getId());
    }

    @KafkaHandler
    @KafkaListener(id = "Billing2", topics = { "document.ettn.registrationCbdError" })
    public void registrationCbdError(DocumentKafka documentKafka) {
        billingController.rollbackCalcCreatedEttnById(documentKafka.getId());

        System.out.println("document.ettn.registrationCbdError: " + documentKafka.getId());
    }
}
