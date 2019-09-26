$(document).ready(function() {

    function addRow(val) {
        $("table").append("<tbody><tr>"
        + "<td> " + val.id + "</td>"
        + "<td> " + val.description + "</td>"
        + "<td> " + val.completed + "<button type='button' id='btn-delete' class='btn btn-sm btn-outline-danger float-right'>X</button>" +"</td>"
        + "</tbody></tr>");
    }

    function retrieveTasks(){
        $.getJSON( "/tasks", function( data ) {
            $.each(data, function( key, val ) {
               addRow(val);
            });
        });
    }

    retrieveTasks();

    $(document).on("click", "#btn-delete" , function(){
        var currentTr = $(this).closest('tr');
        var taskId = currentTr.children("td:first").text();
        $(this).closest('tr').remove();
            $.ajax({
              type: "DELETE",
              url: "/tasks/deleteTask/" + taskId,
              success: function(data) { alert('data: ' + data); },
              dataType: "json"
        });
    });


    $("#btn-create").click(function(){
        var tasksDescription = $("#task-description").val();
        var json;
        $.ajax({
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            url: "/tasks/createTask",
            data: '{"description":"' + tasksDescription + '","completed":false}',
            complete: function (result) {
                $.getJSON( "/tasks", function( data ) {
                    json = data;
                    var currentTasksId = json[json.length - 1].id;
                    addRow({id: currentTasksId, description:tasksDescription, completed:false});
                });
            },
            dataType: "json"
        });
    });
});