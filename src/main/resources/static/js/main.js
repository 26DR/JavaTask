$.getJSON( "/tasks", function( data ) {
  var items = [];
  $.each( data, function( key, val ) {
    $("table").append("<tr>" + "<td> " + val.description + "</td>" + "<td> " + val.completed + "</td>" + "</tr>");
  });
});