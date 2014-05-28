package pl.edu.agh.dsm.monitor.core.model.measurement.data;

public enum DataLimit {

	/**
	 * all available data
	 */
	all,

	/**
	 * last n data values
	 */
	last,

	/**
	 * all available data since n minutes
	 */
	since
}
