package vadim.shtukan.otus.architect.finelproject.Document.Controller;

import io.prometheus.client.Histogram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import vadim.shtukan.otus.architect.finelproject.Document.Domain.DocumentStatus;
import vadim.shtukan.otus.architect.finelproject.Document.Domain.EttnStage;
import vadim.shtukan.otus.architect.finelproject.Document.Domain.EttnXml;
import vadim.shtukan.otus.architect.finelproject.KafkaDomain.DocumentKafka;
import vadim.shtukan.otus.architect.finelproject.Document.Domain.User;
import vadim.shtukan.otus.architect.finelproject.Document.Repository.EttnRepository;

import java.util.NoSuchElementException;

@Controller
public class EttnController {
    private static final Histogram ettnControllerLatency = Histogram
            .build()
            .buckets(0.01, 0.05, 0.1, 0.2, 0.3, 0.4, 0.5, 1, 2)
            .labelNames("EttnController")
            .name("key_ett_controller_latency")
            .help("Время, которое затрачивается на операции с документами.")
            .register();
    @Autowired
    EttnRepository ettnRepository;

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public EttnXml addNewEttn(EttnXml ettnXml, User user) {
        Histogram.Timer requestTimer;
        requestTimer = ettnControllerLatency.labels("addNewEttn").startTimer();

        ettnXml.addUser(user);
        ettnXml.setStatus(DocumentStatus.WAIT_FOR_REGISTRATION_PROVIDER.toString());
        ettnXml.setStage(EttnStage.ORIGINATOR.toString());
        ettnXml.setStatusDescription(DocumentStatus.WAIT_FOR_REGISTRATION_PROVIDER.getDescription());
        //todo decode and encode Base64 xml
        //todo проверить сам xml
        //todo проверить подпись в XML перевозчика

        ettnXml = ettnRepository.save(ettnXml);
        kafkaTemplate.send("document.ettn.create", new DocumentKafka(ettnXml.getId()));

        requestTimer.observeDuration();

        return ettnXml;
    }

    @Cacheable(value = "ettn", key = "#id")
    public EttnXml getXml(String id) {
        Histogram.Timer requestTimer;
        requestTimer = ettnControllerLatency.labels("getXml").startTimer();

        EttnXml ettnXml = ettnRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Ettn not founded. Id: " + id));
        requestTimer.observeDuration();
        
        return ettnXml;
    }

    @CacheEvict(value = "ettn", key = "#id")
    public EttnXml addTransporterInfoInEttn(String id, EttnXml ettnXml) {
        //todo выполнить все проверки
        EttnXml ettnXmlNew = this.getXml(id);

        ettnXmlNew.setXml(ettnXml.getXml());
        ettnXmlNew.setStage(EttnStage.TRANSPORTER.toString());

        ettnXml = ettnRepository.save(ettnXmlNew);
        kafkaTemplate.send("document.ettn.addTransporter", new DocumentKafka(ettnXml.getId()));
        return ettnXml;
    }

    @CacheEvict(value = "ettn", key = "#id")
    public EttnXml addRecipientInfoInEttn(String id, EttnXml ettnXml) {
        //todo выполнить все проверки
        EttnXml ettnXmlNew = this.getXml(id);

        ettnXmlNew.setXml(ettnXml.getXml());
        ettnXmlNew.setStage(EttnStage.RECIPIENT.toString());

        //TODO kafka!
        ettnXml = ettnRepository.save(ettnXmlNew);
        kafkaTemplate.send("document.ettn.addRecipient", new DocumentKafka(ettnXml.getId()));
        return ettnXml;
    }

    @CacheEvict(value = "ettn", key = "#id")
    public EttnXml changeStatusEttn(String id, DocumentStatus documentStatus, String description) {
        EttnXml ettnXml = this.getXml(id);
        ettnXml.setStatus(documentStatus.toString());
        ettnXml.setStatusDescription(description);

        return ettnRepository.save(ettnXml);
    }
}
