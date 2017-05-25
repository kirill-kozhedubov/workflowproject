<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Spring Security</title>

    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">

    <div class="jumbotron" style="margin-top: 20px;">
        <h1>WORKFLOW.com</h1>
        <p class="lead">
            WORKFLOW
        </p>
        <sec:authorize access="!isAuthenticated()">
            <p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Войти</a></p>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <p>Ваш логин: <sec:authentication property="principal.username" /></p>
            <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>

            <p><a class="btn btn-default" href="<c:url value="/admin/add-child" />" role="button">A: add child</a></p>
            <p><a class="btn btn-default" href="<c:url value="/admin/children" />" role="button">A: view children</a></p>
            <p><a class="btn btn-default" href="<c:url value="/admin/user-create" />" role="button">A: user create</a></p>

            <p><a class="btn btn-default" href="<c:url value="/u" />" role="button">U/A: dashboard</a></p>
            <p><a class="btn btn-default" href="<c:url value="/u/share-file" />" role="button">U/A: share file</a></p>
            <p><a class="btn btn-default" href="<c:url value="/u/download-file" />" role="button">U/A: download file</a></p>
            <p><a class="btn btn-default" href="<c:url value="/u/add-file" />" role="button">U/A: add file</a></p>
        </sec:authorize>
    </div>

    <div class="footer">
        <p>© WORKFLOW 2017</p>
    </div>

</div>
</body>
</html>