package vadim.shtukan.otus.architect.finelproject.Key.Models;

public class Company {
    private String id;
    private String egrpo;
    private String name;

    public Company(String id, String egrpo, String name) {
        this.id = id;
        this.egrpo = egrpo;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEgrpo() {
        return egrpo;
    }

    public void setEgrpo(String egrpo) {
        this.egrpo = egrpo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
