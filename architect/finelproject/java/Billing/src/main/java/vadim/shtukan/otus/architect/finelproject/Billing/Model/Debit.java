package vadim.shtukan.otus.architect.finelproject.Billing.Model;

public class Debit {
    private String userId;
    private String companyId;
    private String ettnId;
    private Double amount;

    public Debit(EttnXml ettnXml, Double amount) {
        this.amount = amount;
        this.userId = ettnXml.getOwnerUserId();
        this.companyId = ettnXml.getOwnerCompanyId();
        this.ettnId = ettnXml.getId();
    }

    public Debit() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getEttnId() {
        return ettnId;
    }

    public void setEttnId(String ettnId) {
        this.ettnId = ettnId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
