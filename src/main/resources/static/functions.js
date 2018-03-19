
var stompClient = null;
var subs = null;

function connect() {
    var socket = new SockJS('/twitter');

    if (subs!=null){
        subs.unsubscribe();
        $("#resultsBlock").empty();
    }

    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        //setConnected(true);

        var query = $("#q").val();
        stompClient.send("/app/search", {}, query);
        subs = stompClient.subscribe("/queue/search/" + query, function(m) {
            $("#resultsBlock").append(Mustache.render(template, JSON.parse(m.body)));
        });

    });
}

function registerTemplate() {
    template = $("#template").html();
    Mustache.parse(template);
}

function registerSearch() {
    $("#search").submit(function(event){
        event.preventDefault();

        connect();

    });
}


$(document).ready(function() {
    registerTemplate()
	registerSearch();
});


