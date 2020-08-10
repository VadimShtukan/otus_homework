package vadim.shtukan.otus.architect.finelproject.Notification.Component;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import vadim.shtukan.otus.architect.finelproject.KafkaModel.DocumentKafka;
import vadim.shtukan.otus.architect.finelproject.KafkaModel.UserKafka;

@Component
public class KafkaListenerComponent {

    @KafkaHandler
    @KafkaListener(id = "Notification1", topics = { "user.new" })
    public void userNewEttn(UserKafka userKafka) {
        //TODO send email
        System.out.println("Send email for new user " + userKafka.getId());
    }

    @KafkaHandler
    @KafkaListener(id = "Notification2", topics = { "document.ettn.addBillingError" })
    public void addBillingError(DocumentKafka documentKafka) {
        //TODO send email
        System.out.println("Send email for error document addBillingError: " + documentKafka.getId());
    }

    @KafkaHandler
    @KafkaListener(id = "Notification3", topics = { "document.ettn.registrationCbdError" })
    public void registrationCbdError(DocumentKafka documentKafka) {
        //TODO send email
        System.out.println("Send email for error document registrationCbdError: " + documentKafka.getId());
    }
    
}
