var formatTime = function (unixTimestamp) {
    var dt = new Date(unixTimestamp * 1000);
    var hours = dt.getHours();
    var minutes = dt.getMinutes();
    var seconds = dt.getSeconds();
    return hours + ":" + minutes + ":" + seconds;
}

(function ($) {
    var plots_num = 0;
    $.fn.rplot = function () {
        return this.each(function () {
            var el = $(this);
            $.get(el.data("source"), function (data) {
                var r = jQuery.parseJSON(data);
                var plot_el = $("<div id=\"chart_" + plots_num + "\"></div>");
                el.append(plot_el);
                var datax = [];
                r._embedded.data.forEach(function (i) {
                    datax.push([i.timestamp, i.data]);
                });
                var plot = $.jqplot(plot_el.attr('id'), [datax], {
                    title: el.data("title"),
                    axes: {
                        xaxis: {
                            label: "Czas",
                            pad: 0
                        },
                        yaxis: {
                            label: el.data("yaxis") + "[" + el.data("unit") + "]"
                        }
                    }
                });

                plots_num += 1;
                /*var ajaxRender = function (url, plot, opts) {
                    var datax = [];

                    $.ajax({
                        url: url,
                        success: function (data) {
                            data._embedded.data.forEach(function (i) {
                                datax.push([i.timestamp, i.data]);
                            });
                        },
                        async: false,
                        dataType: "json"
                    });
                    return [datax];
                };

                if (r._embedded.measurements) {
                    r._embedded.measurements.forEach(function (i) {
                        var plot_el = $("<div id=\"chart_" + plots_num + "\"></div>");
                        el.append(plot_el);

                        var plot = $.jqplot(plot_el.attr('id'), i._links.data.href, {
                            title: r.resource,
                            axes: {
                                // options for each axis are specified in seperate option objects.
                                xaxis: {
                                    label: "Czas",
                                    // Turn off "padding".  This will allow data point to lie on the
                                    // edges of the grid.  Default padding is 1.2 and will keep all
                                    // points inside the bounds of the grid.
                                    pad: 0
                                },
                                yaxis: {
                                    label: i.metric + "[" + i.unit + "]"
                                }
                            },
                            dataRenderer: ajaxRender
                        });
                        plots_num += 1;
                    });
                }*/
            });
        });
    };
}(jQuery));


$(document).ready(function () {
    $(".plotter").rplot();
});