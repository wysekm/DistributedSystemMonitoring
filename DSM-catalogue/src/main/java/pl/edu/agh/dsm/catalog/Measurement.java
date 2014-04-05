package pl.edu.agh.dsm.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

public class Measurement{


    private String name;
    private String some;

    public Measurement(String name, String some) {
        this.name = name;
        this.some = some;
    }

    public Measurement() {
    }


    public String getName() {
        return name;
    }

    public String getSome() {
        return some;
    }
}

