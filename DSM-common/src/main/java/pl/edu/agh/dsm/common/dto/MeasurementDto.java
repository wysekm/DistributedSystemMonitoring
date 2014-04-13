package pl.edu.agh.dsm.common.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.UUID;


@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MeasurementDto {
    @JsonIgnore
    private UUID id;
    private String resource;
    private String metric;
    private String unit;

}

