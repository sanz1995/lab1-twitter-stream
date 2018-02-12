$(document).ready(function() {
	registerSearch();
});

function registerSearch() {
	$("#search").submit(function(ev){
		event.preventDefault();
		target = $(this).attr('action')
        query = $("#q").val()
		$.get(target, { q: query } )
            .done( function(data) {
	    		$("#resultsBlock").empty().append(data);
            }).fail(function() {
                $("#resultsBlock").empty();
            });
	});
}

