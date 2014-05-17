var retrieveMeasurements = function(interval) {	
	$(".measurement_data").each(function (index, value) {
		var dataTd = $(value)
		var detailsUri = $(this).attr("details-url");
		var retrieveFunction = function() {
			var traverson = require('traverson')
			var api = traverson.jsonHal.from(detailsUri)
			api.newRequest()
					.follow('data')
					.withTemplateParameters({ limit: 'last' })
					.getResource(function(error, dataFrame) {
				dataTd.html(dataFrame[0].data)
			})
		};
		retrieveFunction()
		setInterval(retrieveFunction, interval)
	})
}