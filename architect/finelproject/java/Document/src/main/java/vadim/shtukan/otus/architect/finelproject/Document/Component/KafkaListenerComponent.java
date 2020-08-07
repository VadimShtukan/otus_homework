package vadim.shtukan.otus.architect.finelproject.Document.Component;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import vadim.shtukan.otus.architect.finelproject.Document.Models.UserGroup;

@Component
public class KafkaListenerComponent {

    @KafkaHandler
    @KafkaListener(id = "Document", topics = { "userGroup" })
    public void userGroup(UserGroup userGroup) {

        //todo topiv - document.ettn.create
        //todo String
        System.out.println("Received Document: " + userGroup.getId());
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        System.out.println("Received unknown: " + object);
    }
}
