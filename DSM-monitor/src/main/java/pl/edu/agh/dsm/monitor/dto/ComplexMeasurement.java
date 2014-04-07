package pl.edu.agh.dsm.monitor.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ComplexMeasurement {

    //TODO change to URL http://127.0.0.1:8081/measurements/359a2051-ab94-40f6-b89a-f4ffec4da2a7",
    UUID measurement;
    String type;
    List<ComplexMeasurementParam> params;


}
