package pl.edu.agh.dsm.monitor.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MeasurementDataDto {

    long timestamp;
    long data;

    public MeasurementDataDto(long timestamp, long data) {
        this.timestamp = timestamp;
        this.data = data;
    }

    public MeasurementDataDto() {
    }



    public long getTimestamp() {
        return this.timestamp;
    }

    public long getData() {
        return this.data;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof MeasurementDataDto)) return false;
        final MeasurementDataDto other = (MeasurementDataDto) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getTimestamp() != other.getTimestamp()) return false;
        if (this.getData() != other.getData()) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $timestamp = this.getTimestamp();
        result = result * PRIME + (int) ($timestamp >>> 32 ^ $timestamp);
        final long $data = this.getData();
        result = result * PRIME + (int) ($data >>> 32 ^ $data);
        return result;
    }

    public boolean canEqual(Object other) {
        return other instanceof MeasurementDataDto;
    }
}
