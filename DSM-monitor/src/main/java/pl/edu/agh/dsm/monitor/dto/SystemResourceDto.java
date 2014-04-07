package pl.edu.agh.dsm.monitor.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;


@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SystemResourceDto {
    String name;
}
