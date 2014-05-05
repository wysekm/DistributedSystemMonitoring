$(document).ready(function(){
    var loader = function(){
        $(".plotter").each(function(index, value){
            $.get(proxy_create($(value).data("source"), "GET"), function(data){
                var main_data = jQuery.parseJSON(data);
                $(value).html("");

            });
        });
    };
    loader();
    setInterval(loader, 5000);
});