package pl.edu.agh.dsm.front.models;

import pl.edu.agh.dsm.front.interfaces.HasId;
import pl.edu.agh.dsm.front.interfaces.HasName;

/**
 * Created by Sakushu on 2014-04-26.
 */

public class MonitorsModel implements HasName, HasId {
    private String name;
    private String id;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public MonitorsModel(String name, String id){
        setName(name);
        setId(id);
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
