$(document).ready(function() {
    var deleteButton = "<button type='button' class='btn btn-delete'><i class='fa fa-trash-o' aria-hidden='true'></i></button>";
    var editButton = "<button type='button' class='btn btn-edit'><i class='fa fa-pencil' aria-hidden='true'></i></i></button>";
    var dropDown =  "<div class='dropdown float-right'>"
                    + "<button class='btn dropdown-toggle' type='button' id='dropdownMenuButton' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"
                    + "<i class='fa fa-ellipsis-h' aria-hidden='true'></i>"
                    + "</button>"
                    + "<div class='dropdown-menu' aria-labelledby='dropdownMenuButton'>"
                    + "<button type='button' class='btn btn-edit dropdown-item'><i class='fa fa-pencil' aria-hidden='true'></i></i> edit</button>"
                    + "<button type='button' class='btn btn-delete dropdown-item'><i class='fa fa-trash-o' aria-hidden='true'></i> delete</button>"
                    + "</div>"
                    + "</div>";

    function addRow(val) {
        $("table").append("<tbody><tr>"
        + "<td class='display-none'> " + val.id + "</td>"
        + "<td id='task-description-" + val.id + "'> "
        + "<input class='form-check-input' type='checkbox' value='' id='completed-check-" + val.id + "'>"
        + "<label class='description-paragraph ml-2' for='completed-check-" + val.id + "'>" + val.description + "</label>"
        + dropDown + "</td>"
        + "<td class='display-none'>" + val.completed + "</td>"
        + "</tbody></tr>");
        if(val.completed){
            $("#task-description-" + val.id).children(".form-check-input").prop('checked', true);
        }
    }

    function saveTask(id, description, completed){
        $.ajax({
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            url: "/tasks",
            data: '{"id":"' + id.replace(/\s+/g, '') + '","description":"' + description + '","completed":"' + completed + '"}',
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

    $(document).on("click", "label" , function(){
        var idRow = $(this).closest("tr").children("td:first");
        var descriptionRow = idRow.next();
        var taskDescription = descriptionRow.children("label");
        var completedRow = descriptionRow.next();
        if(completedRow.text().includes("false")){
            saveTask(idRow.text(), taskDescription.text(), true);
            completedRow.html("true ");
        } else {
            saveTask(idRow.text(), taskDescription.text(), false);
            completedRow.html("false ");
        }
    });

    $(document).on("focusin", ".dropdown-toggle" , function(){
        $(this).siblings('.dropdown-menu').toggle("slow");
    });

    $(document).on("focusout", ".dropdown-toggle" , function(){
        $(this).siblings('.dropdown-menu').toggle("slow");
    });

    $(document).on("click", ".btn-edit" , function(){
        var currentTr = $(this).closest('tr');
        var taskId = currentTr.children("td:first");
        var description = taskId.next();
        description.find("label").replaceWith( function(){
        return "<form class='form-inline display-inline-block ml-2'>"
               + "<div class='input-group'>"
               + "<input type='text' id='task-description-edit' class='form-control' name='description' placeholder='" + $( this ).html() + "'>"
               + "<div class='input-group-append'>"
               + "<button type='button' id='btn-create' class='btn btn-outline-secondary btn-create-edit'>"
               + "<i class='fa fa-check' aria-hidden='true'></i>"
               + "</button>"
               + "</div>"
               + "</div>"
               + "</form>"
        });

    $(document).on("click", "#btn-create" , function(){
        var form = description.find("form");
        var textBox = description.find("form").find("input");
        var completedRow = description.next();
        form.replaceWith( function(){
            return "<label class='description-paragraph ml-2' for='completed-check-" + taskId.text() + "'>" + textBox.val() + "</label>"
        });
        saveTask(taskId.text(), textBox.val(), completedRow.html());
        });
    });

    $(document).on("click", ".btn-delete" , function(){
        var currentTr = $(this).closest('tr');
        var taskId = currentTr.children("td:first").text();
        $(this).closest('tr').remove();
            $.ajax({
              type: "DELETE",
              url: "/tasks/" + taskId,
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
                url: "/tasks",
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