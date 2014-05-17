var plotGraphs = function(interval) {
	$(".plot").each(function (index, value) {
		var plotDiv = $(value);
		var detailsUri = $(this).attr("details-url");
		
		var traverson = require('traverson');
		var api = traverson.jsonHal.from(detailsUri);
		api.newRequest().follow().getResource(function(error, measurement) {
			var plotFunction = function() {
				var metric =  measurement.metric;
				var resource =  measurement.resource;
				var dataUri =  measurement._links.data.href;
				var unit =  measurement.unit;

				var api = traverson.jsonHal.from(dataUri);
				api.newRequest()
						.follow()
						.withTemplateParameters({ limit: 'all' })
						.getResource(function(error, dataFrame) {
					data = [];
					var nowTime = new Date().getTime();
					dataFrame.forEach(function (i) {
						var timeSince = i.timestamp - nowTime;
						data.push([timeSince / 1000.0, i.data]);
					});
					var chartPlot = $.jqplot(plotDiv.attr("id"), [data],{
						title: 'Resource: '+resource+' | Metric: '+metric,
						axes: { 
							xaxis: { label: 'Time [s]' }, 
							yaxis: { label: metric+' ['+unit+']' }
						}
					}).replot();
				});
			};
			plotFunction();
			setInterval(plotFunction, interval);
		});
	});
}