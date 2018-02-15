function registerSearch() {
    $("#search").submit(function(event){
        event.preventDefault();
        var target = $(this).attr('action');
        var query = $("#q").val();
        $.get(target, { q: query } )
            .done( function(data) {
                $("#resultsBlock").empty().append(data);
            }).fail(function() {
            $("#resultsBlock").empty();
        });
    });
}

$(document).ready(function() {
	registerSearch();
});


