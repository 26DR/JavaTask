$(document).ready(function() {
    var deleteButton = "<button type='button' id='btn-delete' class='btn float-right'><i class='fa fa-minus-square fa-lg'></i></button>";
    var editButton = "<button type='button' id='btn-warning' class='btn float-right'><i class='fa fa-pencil-square fa-lg'></i></button>";
    var doneButton = "<button type='button' id='btn-done' class='btn float-right'><i class='fa fa-check-square fa-lg'></i></button>";

    function addRow(val) {
        $("table").append("<tbody><tr>"
        + "<td id='td-id'> " + val.id + "</td>"
        + "<td id='td-description-" + val.id + "'> " + val.description + deleteButton + editButton + "</td>"
        + "<td id='td-completed'>" + val.completed + "</td>"
        + "</tbody></tr>");
        if(val.completed){
            $("#td-description-" + val.id).addClass("strike");
        }
    }

    function doTaskPostRequest(id, description, completed){
        $.ajax({
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            url: "/tasks/createTask",
            data: '{"id":"' + id + '","description":"' + description + '","completed":"' + completed + '"}',
            dataType: "json"
        });
    }

    function retrieveTasks(){
        $.getJSON( "/tasks", function( data ) {
            $.each(data, function( key, val ) {
               addRow(val);
            });
        });
    }

    retrieveTasks();

    $(document).on("click", "tr" , function(){
        var idRow = $(this).children("td:first");
        var descriptionRow = $(this).children("td:first").next();
        var completedRow = descriptionRow.next();
        descriptionRow.toggleClass("strike");
        if(completedRow.text().includes("false")){
            doTaskPostRequest(idRow.text(), descriptionRow.text(), true);
            completedRow.html("true ");
        } else {
            doTaskPostRequest(idRow.text(), descriptionRow.text(), false);
            completedRow.html("false ");
        }
    });

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