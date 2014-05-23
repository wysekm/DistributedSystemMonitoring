package pl.edu.agh.dsm.front.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MeasurementDataDto {

	long timestamp;
	double data;

	public MeasurementDataDto(long timestamp, double data) {
		this.timestamp = timestamp;
		this.data = data;
	}

	public MeasurementDataDto() {
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
	}
}
