package stukan.vadim.otus.architect.lesson22.domaim;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Product {
    @Id
    @JsonIgnore
    private String id;

    private String uuid;

    private String name;
    private String description;
    private String property;
    private String barcode;
    private Boolean caching = true;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Boolean getCaching() {
        return caching;
    }

    public void setCaching(Boolean caching) {
        this.caching = caching;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", property='" + property + '\'' +
                ", barcode='" + barcode + '\'' +
                '}';
    }
}
