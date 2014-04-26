package pl.edu.agh.dsm.front.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sakushu on 2014-04-26.
 */
public class ResourceModelSet {
    private List<ResourceModel> rscSet = new ArrayList<>();

    public List<ResourceModel> getRscSet() {
        return rscSet;
    }

    public void setRscSet(List<ResourceModel> rscSet) {
        this.rscSet = rscSet;
    }
}
