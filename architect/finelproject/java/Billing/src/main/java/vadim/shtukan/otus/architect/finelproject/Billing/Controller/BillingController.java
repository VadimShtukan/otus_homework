package vadim.shtukan.otus.architect.finelproject.Billing.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import vadim.shtukan.otus.architect.finelproject.Billing.Model.Debit;
import vadim.shtukan.otus.architect.finelproject.Billing.Model.EttnXml;
import vadim.shtukan.otus.architect.finelproject.Billing.RestController.DocumentRestController;
import vadim.shtukan.otus.architect.finelproject.KafkaModel.DocumentKafka;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BillingController {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Autowired
    private DocumentRestController documentRestController;

    Map<String, Debit> debit = new HashMap<String,Debit>();

    public void calcCreatedEttnById(String ettnId) {

        EttnXml ettnXml = documentRestController.getEttnById(ettnId);

        if(!this.isCompanyActive(ettnXml.getOwnerCompanyId())){
            this.sendCancelEttn(ettnXml);
            return;
        }

        this.addDebit(ettnXml);

        this.sendOkEttn(ettnXml);
        //todo Transaction - Catch errors and delete credit
    }

    private void sendOkEttn(EttnXml ettnXml) {
        kafkaTemplate.send("document.ettn.addBillingOk", new DocumentKafka(ettnXml.getId(), "OK"));
    }

    private void addDebit(EttnXml ettnXml) {
        //todo to Mysql/Postgresql and add companyId
        debit.put(ettnXml.getId(), new Debit(ettnXml, 10.00));
    }

    private void sendCancelEttn(EttnXml ettnXml) {
        kafkaTemplate.send("document.ettn.addBillingError", new DocumentKafka(ettnXml.getId(),"Компания заблокировано (нет денег на счету)"));
    }

    private boolean isCompanyActive(String ownerCompanyId) {
        //todo check company
        if(!ownerCompanyId.equals("13eaad53-0a81-4e70-907a-80743f0a2fe0")) return true;
        return false;
    }

    public void rollbackCalcCreatedEttnById(String ettnId) {
        this.deleteDebit(ettnId);
    }

    private void deleteDebit(String ettnId) {
        //todo to Mysql/Postgresql and add companyId
        debit.remove(ettnId);
    }

    public Debit getDebitByCompany(String companyId) {
        //todo to Mysql/Postgresql
        Debit result = new Debit();
        result.setAmount(0.00);

        for (Map.Entry<String, Debit> pair:debit.entrySet()) {
            if(pair.getValue().getCompanyId().equals(companyId)){}
            result.setAmount(result.getAmount() + pair.getValue().getAmount());
        }

        return result;
    }
}
