package vadim.shtukan.otus.architect.finelproject.Document.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import vadim.shtukan.otus.architect.finelproject.Document.Models.DocumentStatus;
import vadim.shtukan.otus.architect.finelproject.Document.Models.EttnStage;
import vadim.shtukan.otus.architect.finelproject.Document.Models.EttnXml;
import vadim.shtukan.otus.architect.finelproject.Document.Models.User;
import vadim.shtukan.otus.architect.finelproject.Document.Repository.EttnRepository;

import java.util.NoSuchElementException;

@Controller
public class EttnController {
    @Autowired
    EttnRepository ettnRepository;

    public EttnXml addNewEttn(EttnXml ettnXml, User user) {
        ettnXml.addUser(user);
        ettnXml.setStatus(DocumentStatus.WAIT_WOR_REGISTRATION_PROVIDER.toString());
        ettnXml.setStage(EttnStage.ORIGINATOR.toString());
        ettnXml.setStatusDescription(DocumentStatus.WAIT_WOR_REGISTRATION_PROVIDER.getDescription());
        //todo decode and encode Base64 xml
        //todo проверить сам xml
        //todo проверить подпись в XML перевозчика

        //todo kafka!
        return ettnRepository.save(ettnXml);
    }

    @Cacheable(value = "ettn", key = "#id")
    public EttnXml getXml(String id) {

        return ettnRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Ettn not founded. Id: " + id));
    }

    @CacheEvict(value = "ettn", key = "#id")
    public EttnXml addTransporterInfoInEttn(String id, EttnXml ettnXml) {
        //todo выполнить все проверки
        EttnXml ettnXmlNew = this.getXml(id);

        ettnXmlNew.setXml(ettnXml.getXml());
        ettnXmlNew.setStage(EttnStage.TRANSPORTER.toString());

        //todo kafka!
        return ettnRepository.save(ettnXmlNew);
    }

    @CacheEvict(value = "ettn", key = "#id")
    public EttnXml addRecipientInfoInEttn(String id, EttnXml ettnXml) {
        //todo выполнить все проверки
        EttnXml ettnXmlNew = this.getXml(id);

        ettnXmlNew.setXml(ettnXml.getXml());
        ettnXmlNew.setStage(EttnStage.RECIPIENT.toString());

        //todo kafka!
        return ettnRepository.save(ettnXmlNew);
    }
}
