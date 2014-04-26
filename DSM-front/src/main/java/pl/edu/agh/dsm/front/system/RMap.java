package pl.edu.agh.dsm.front.system;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Sakushu on 2014-04-26.
 */
public class RMap {
    private HashMap map;

    public RMap(HashMap map){
        this.map = map;
    }

    public RMap step(String key){
        return new RMap((HashMap)this.map.get(key));
    }

    public String getString(String key){
        return (String)this.map.get(key);
    }

    public Object get(String key){
        return this.map.get(key);
    }

    public List getAsList(String key){
        return (List)this.map.get(key);
    }
}
