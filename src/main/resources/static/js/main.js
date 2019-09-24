function populateTable(description, isCompleted){
    $("table").append("<tr>" + "<td> " + description + "</td>" + "<td> " + isCompleted + "</td>" + "</tr>");
}

function retrieveTasks(){
    $.getJSON( "/tasks", function( data ) {
      $.each( data, function( key, val ) {
        populateTable(val.description, val.completed);
      });
    });
}

$("#button").click(function(){
    var tasksDescription = $("#task-description").val();
    populateTable(tasksDescription, false);
});

window.onload = function() {
  retrieveTasks();
};