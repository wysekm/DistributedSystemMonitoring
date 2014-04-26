package pl.edu.agh.dsm.front.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sakushu on 2014-04-26.
 */
public class MetricModelSet {
    private List<MetricModel> modelSet = new ArrayList<MetricModel>();

    public List<MetricModel> getModelSet() {
        return modelSet;
    }

    public void setModelSet(List<MetricModel> modelSet) {
        this.modelSet = modelSet;
    }
}
