<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tasks</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body class="vh-100">
    <div class="container-fluid h-100">
        <div class="row d-flex justify-content-center">
            <div class="col-md-6 bg-dark rounded p-5">
            <h2 class="text-center text-white mb-5">Task List</h2>
            <table class="table table-borderless table-dark">
                <form class="form-inline mw-100">
                    <div class="input-group">
                        <input type="text" id="task-description" class="form-control" name="description" placeholder="Add a new task...">
                        <div class="input-group-append">
                            <button type="button" id="btn-create" class="btn"><i class="fa fa-check" aria-hidden="true"></i></button>
                        </div>
                    </div>
                </form>
                <div class="alert alert-dark alert-dismissible mt-3 fade show display-none" role="alert">
                  Tasks description can't be empty
                  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <hr>
                <thead class="thead-dark display-none">
                    <tr>
                        <th class="display-none">ID</th>
                        <th>Tasks</th>
                        <th class="display-none">Completed</th>
                    </tr>
                </thead>
            </table>
            </div>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/js/main.js"></script>
</body>
</html>