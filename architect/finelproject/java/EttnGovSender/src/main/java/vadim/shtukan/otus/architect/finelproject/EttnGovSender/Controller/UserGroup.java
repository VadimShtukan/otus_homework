package vadim.shtukan.otus.architect.finelproject.EttnGovSender.Controller;

public class UserGroup {
    private String name;
    private String id;

    public UserGroup() {
    }

    public UserGroup(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
