$(document).ready(function () {
    $(".measurements").each(function (index, value) {
        var vk = $(value);
        $.get(proxy_create($(this).data("url"), "GET"), function (data) {
            mdata = jQuery.parseJSON(data);

            var it = function(){
                $.get(proxy_create(mdata._links.data.href, "GET"), function (data) {
                    data = jQuery.parseJSON(data);
                    var len = data._embedded.data.length;
                    vk.html(data._embedded.data[len - 1].data);
                });
            };

            it();
            setInterval(it, 2000);
        });
    })
});