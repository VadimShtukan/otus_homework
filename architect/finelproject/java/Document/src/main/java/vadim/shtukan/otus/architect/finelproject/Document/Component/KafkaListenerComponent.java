package vadim.shtukan.otus.architect.finelproject.Document.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import vadim.shtukan.otus.architect.finelproject.Document.Controller.EttnController;
import vadim.shtukan.otus.architect.finelproject.KafkaModel.DocumentKafka;
import vadim.shtukan.otus.architect.finelproject.KafkaModel.UserKafka;
import vadim.shtukan.otus.architect.finelproject.Document.Models.UserGroup;

import static vadim.shtukan.otus.architect.finelproject.Document.Models.DocumentStatus.*;

//todo delete it
@Component
public class KafkaListenerComponent {
    @Autowired
    private EttnController ettnController;

    @KafkaHandler
    @KafkaListener(id = "Document", topics = { "userGroup" })
    public void userGroup(UserGroup userGroup) {

        System.out.println("Received Document: " + userGroup.getId());
    }

    @KafkaHandler
    @KafkaListener(id = "Document1", topics = { "document.ettn.create" })
    public void createEttn(DocumentKafka documentKafka) {

        System.out.println("Create Document: " + documentKafka.getId());
    }

    @KafkaHandler
    @KafkaListener(id = "Document2", topics = { "document.ettn.addTransporter" })
    public void addTransporterEttn(DocumentKafka documentKafka) {

        System.out.println("addTransporter: " + documentKafka.getId());
    }

    @KafkaHandler
    @KafkaListener(id = "Document3", topics = { "document.ettn.addRecipient" })
    public void addRecipientEttn(DocumentKafka documentKafka) {

        System.out.println("addRecipient: " + documentKafka.getId());
    }

    @KafkaHandler
    @KafkaListener(id = "Document4", topics = { "user.new" })
    public void userNewEttn(UserKafka userKafka) {

        System.out.println("add user: " + userKafka.getId());
    }

    @KafkaHandler
    @KafkaListener(id = "Document5", topics = { "document.ettn.addBillingOk" })
    public void addBillingOk(DocumentKafka documentKafka) {
        ettnController.changeStatusEttn(documentKafka.getId(), WAIT_FOR_CBD_REGISTRATION, documentKafka.getDescription());
        //TODO send KAFKA to CBD
        System.out.println("document.ettn.addBillingOk: " + documentKafka.getId());
    }

    @KafkaHandler
    @KafkaListener(id = "Document6", topics = { "document.ettn.addBillingError" })
    public void addBillingError(DocumentKafka documentKafka) {
        ettnController.changeStatusEttn(documentKafka.getId(), REGISTRATION_PROVIDER_FAILED, documentKafka.getDescription());

        System.out.println("document.ettn.addBillingError: " + documentKafka.getId());
    }


    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        System.out.println("Received unknown: " + object);
    }
}
