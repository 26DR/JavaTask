$(document).ready(function() {
    var deleteButton = "<button type='button' class='btn btn-delete float-right'><i class='fa fa-minus-square fa-lg'></i></button>";
    var editButton = "<button type='button' class='btn btn-edit float-right'><i class='fa fa-pencil-square fa-lg'></i></button>";

    function addRow(val) {
        $("table").append("<tbody><tr>"
        + "<td class='display-none'> " + val.id + "</td>"
        + "<td id='task-description-" + val.id + "'> " + "<p class='description-paragraph'>" + val.description + "</p>" + deleteButton + editButton + "</td>"
        + "<td class='display-none'>" + val.completed + "</td>"
        + "</tbody></tr>");
        if(val.completed){
            $("#task-description-" + val.id).children(".description-paragraph").addClass("strike-through");
        }
    }

    function saveTask(id, description, completed){
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

    $(document).on("click", "p" , function(){
        var idRow = $(this).closest("tr").children("td:first");
        var descriptionRow = idRow.next();
        var taskDescription = descriptionRow.children("p");
        var completedRow = descriptionRow.next();
        taskDescription.toggleClass("strike-through");
        if(completedRow.text().includes("false")){
            saveTask(idRow.text(), taskDescription.text(), true);
            completedRow.html("true ");
        } else {
            saveTask(idRow.text(), taskDescription.text(), false);
            completedRow.html("false ");
        }
    });

    $(document).on("click", ".btn-delete" , function(){
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
        if(tasksDescription != '' && tasksDescription.length != 0 && tasksDescription.match(/^[a-zA-Z0-9]+/))
        {
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
        }
    });
});