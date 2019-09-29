<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tasks</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body class="vh-100">
    <div class="container-fluid bg-dark h-100">
        <div class="row d-flex justify-content-center">
            <div class="col-md-6 bg-light rounded p-3">
            <table class="table table-hover mb-4">
                        <thead class="thead-dark">
                            <tr>
                                <th id="th-id">ID</th>
                                <th>Tasks</th>
                                <th id="th-completed">Completed</th>
                            </tr>
                        </thead>
                    </table>
                    <form class="form-inline mw-100">
                        <div class="input-group w-100">
                            <input type="text" id="task-description" class="form-control" name="description" placeholder="Description of task">
                            <div class="input-group-append">
                                <button type="button" id="btn-create" class="btn btn-outline-secondary">Add Task</button>
                            </div>
                        </div>
                    </form>
            </div>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/js/main.js"></script>
</body>
</html>