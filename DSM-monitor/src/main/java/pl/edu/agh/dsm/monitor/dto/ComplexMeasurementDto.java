package pl.edu.agh.dsm.monitor.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ComplexMeasurementDto {

    //TODO change to URL http://127.0.0.1:8081/measurements/359a2051-ab94-40f6-b89a-f4ffec4da2a7",
    UUID measurement;
    String type;
    List<ComplexMeasurementParamDto> params;


    @java.beans.ConstructorProperties({"measurement", "type", "params"})
    public ComplexMeasurementDto(UUID measurement, String type, List<ComplexMeasurementParamDto> params) {
        this.measurement = measurement;
        this.type = type;
        this.params = params;
    }

    public ComplexMeasurementDto() {
    }

    public UUID getMeasurement() {
        return this.measurement;
    }

    public String getType() {
        return this.type;
    }

    public List<ComplexMeasurementParamDto> getParams() {
        return this.params;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ComplexMeasurementDto)) return false;
        final ComplexMeasurementDto other = (ComplexMeasurementDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$measurement = this.getMeasurement();
        final Object other$measurement = other.getMeasurement();
        if (this$measurement == null ? other$measurement != null : !this$measurement.equals(other$measurement))
            return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$params = this.getParams();
        final Object other$params = other.getParams();
        if (this$params == null ? other$params != null : !this$params.equals(other$params)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $measurement = this.getMeasurement();
        result = result * PRIME + ($measurement == null ? 0 : $measurement.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 0 : $type.hashCode());
        final Object $params = this.getParams();
        result = result * PRIME + ($params == null ? 0 : $params.hashCode());
        return result;
    }

    public boolean canEqual(Object other) {
        return other instanceof ComplexMeasurementDto;
    }
}
