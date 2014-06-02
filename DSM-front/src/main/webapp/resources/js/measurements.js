var retrieveMeasurements = function(interval) {	
	$(".measurement_data").each(function (index, value) {
		var dataTd = $(value)
		var dataUri = $(this).attr("data-url");
		var retrieveFunction = function() {
			var traverson = require('traverson')
			var api = traverson.jsonHal.from(dataUri)
			api.newRequest()
					.follow()
					.withTemplateParameters({ limit: 'last' })
					.getResource(function(error, dataFrame) {
				data = dataFrame[0].data;
				dataTd.html(parseFloat(data).toPrecision(4));
			})
		};
		retrieveFunction()
		setInterval(retrieveFunction, interval)
	})
}

var updateDetailsUri = function(detailsUri, addUri) {
	$(".detailsUriInput").each(function (index, value) {
		value.setAttribute("value",detailsUri);
	});
	$(".addUriInput").each(function (index, value) {
		value.setAttribute("value",addUri);
	});
}