package pl.edu.agh.dsm.monitor.core.model.measurement.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MeasurementData {

	long timestamp;
	double data;

	public MeasurementData(long timestamp, double data) {
		this.timestamp = timestamp;
		this.data = data;
	}

	public MeasurementData() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(data);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeasurementData other = (MeasurementData) obj;
		if (Double.doubleToLongBits(data) != Double
				.doubleToLongBits(other.data))
			return false;
		if (timestamp != other.timestamp)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MeasurementDataDto [timestamp=" + timestamp + ", data=" + data
				+ "]";
	}
}
