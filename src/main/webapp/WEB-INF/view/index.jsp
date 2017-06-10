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

    <title>Головна</title>

    <link rel="icon" href="<c:url value="/resources/images/index.ico"/>"/>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/custom-styles.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>


</head>

<body>
<sec:authorize access="!isAuthenticated()">
    <p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Войти</a></p>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <jsp:include page="header.jsp"/>


    <div class="jumbotron" style="margin-top: 20px;">
        <h1>WORKFLOW.com</h1>
        <p class="lead">
            WORKFLOW
        </p>


        <p>Ваш логин: <sec:authentication property="principal.username"/></p>
        <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Logout</a></p>
        <p><a class="btn btn-lg btn-default" href="<c:url value="/u/" />" role="button">User dash</a></p>
        <p><a class="btn btn-lg btn-default" href="<c:url value="/u/download/1" />" role="button">download file 1</a>
        </p>
        <p><a class="btn btn-lg btn-default" href="<c:url value="/u/file/create" />" role="button">create file</a></p>
        <p><a class="btn btn-lg btn-default" href="<c:url value="/u/file/share/1" />" role="button">share file 1</a></p>
        <p><a class="btn btn-lg btn-default" href="<c:url value="/u/download/1" />" role="button">download 1</a></p>
        <p><a class="btn btn-lg btn-default" href="<c:url value="/admin/user/create/" />" role="button">admin create
            user</a></p>
        <p><a class="btn btn-lg btn-danger" href="<c:url value="/children/" />" role="button">children</a></p>
        <p><a class="btn btn-lg btn-danger" href="<c:url value="/children/child/1" />" role="button">child 1</a></p>
        <p><a class="btn btn-lg btn-danger" href="<c:url value="/children/create" />" role="button">create children</a>
        </p>


    </div>

    <div class="footer">
        <p>© WORKFLOW 2017</p>
    </div>


    <jsp:include page="footer.jsp"/>
</sec:authorize>
</body>
</html>