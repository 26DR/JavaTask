<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tasks</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>Description</th>
            <th>Completed</th>
        </tr>
    </table>
    <form>
      <input type="text" id="task-description" name="description" placeholder="Description of task"><br>
      <button type="button" id="button">Add Task</button>
    </form>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/js/main.js"></script>
</body>
</html>