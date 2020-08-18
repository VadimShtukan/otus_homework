package vadim.shtukan.otus.architect.finelproject.Document.Domain;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class EttnXml implements Serializable {
    private static final long serialVersionUID = 7156526077883281623L;

    @Id
    private String id;
    //todo Change to Enum
    private String stage;
    private String ownerUserId;
    private String ownerCompanyId;
    //todo Change to Enum
    private String status;
    private String statusDescription;
    private String xml;
    private String cbdId;

    public String getCbdId() {
        return cbdId;
    }

    public void setCbdId(String cbdId) {
        this.cbdId = cbdId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getOwnerCompanyId() {
        return ownerCompanyId;
    }

    public void setOwnerCompanyId(String ownerCompanyId) {
        this.ownerCompanyId = ownerCompanyId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public void addUser(User user) {
        this.ownerCompanyId = user.getCompany().getId();
        this.ownerUserId = user.getId();
    }
}
