package vadim.shtukan.otus.architect.finelproject.Document.Model;

public enum  DocumentStatus {
    WAIT_FOR_REGISTRATION_PROVIDER("Ожидается регистрация у провайдера"),
    REGISTRATION_PROVIDER_FAILED(""),
    WAIT_FOR_CBD_REGISTRATION("Ожидает регистрацию в ЦБД"),
    CBD_REGISTRATION_ERROR(""),
    CBD_REGISTRATED("Зарегистрирован в ЦБД");
    ;


    String description;


    DocumentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
