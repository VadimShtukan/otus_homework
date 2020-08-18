package vadim.shtukan.otus.architect.finelproject.KafkaDomain;

public class UserKafka {
    private String id;

    public UserKafka() {
    }

    public UserKafka(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
