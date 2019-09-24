function retrieveTasks(){
    $.getJSON( "/tasks", function( data ) {
      $.each( data, function( key, val ) {
        $("table").append("<tr>" + "<td> " + val.description + "</td>" + "<td> " + val.completed + "</td>" + "</tr>");
      });
    });
}

window.onload = function() {
  retrieveTasks();
};