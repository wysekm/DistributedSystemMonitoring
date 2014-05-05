$(document).ready(function () {
    var plt_id = 0;

    var loader = function () {
        $(".plotter").each(function (index, value) {
            var plot_el = $(value);
            $(value).attr("id", "plt_id_num_" + plt_id);
            plt_id += 1;
            $.get(proxy_create($(value).data("source"), "GET"), function (data) {
                var main_data = jQuery.parseJSON(data);
                $(value).html("");
                $.get(proxy_create(main_data._links.data.href, "GET"), function (data) {
                    var plot_data = jQuery.parseJSON(data);
                    var datax = [];
                    plot_data._embedded.data.forEach(function (i) {
                        datax.push([i.timestamp, i.data]);
                    });

                    var plot = $.jqplot(plot_el.attr('id'), [datax], {
                        title: plot_el.data("title"),
                        axes: {
                            xaxis: {
                                label: "Czas",
                                pad: 0
                            },
                            yaxis: {
                                label: main_data.metric + "[" + main_data.unit + "]"
                            }
                        }
                    });

                })
            });
        });
    };
    loader();
    setInterval(loader, 5000);
});