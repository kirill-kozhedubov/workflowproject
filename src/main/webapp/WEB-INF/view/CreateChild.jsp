<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Додання до бази</title>

    <link rel="icon" href="<c:url value="/resources/images/index.ico"/>"/>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/custom-styles.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>


</head>

<body>
<jsp:include page="header.jsp"/>
<c:url value="/children/save" var="save"/>
<div class="page-title">Додання особової справи до бази</div>
<form:form class="form-horizontal" modelAttribute="child" action="${save}" method="post">
    <fieldset>
        <div class="section-title">Основна інформація</div>

        <div class="form-group">
            <label for="inputBasicFirstName" class="col-sm-2 control-label">Ім'я<span class="red-star">*</span></label>
            <div class="col-sm-10">
                <form:input path="basicFirstName" type="text" class="form-control" id="inputBasicFirstName"
                            placeholder="Ім'я"/>
            </div>
        </div>
        <div class="form-group">
            <label for="inputBasicLastName" class="col-sm-2 control-label">Прізвище<span
                    class="red-star">*</span></label>
            <div class="col-sm-10">
                <form:input path="basicLastName" type="text" class="form-control" id="inputBasicLastName"
                            placeholder="Прізвище"/>
            </div>
        </div>
        <div class="form-group">
            <label for="inputBasicMiddleName" class="col-sm-2 control-label">По батькові<span class="red-star">*</span></label>
            <div class="col-sm-10">
                <form:input path="basicMiddleName" type="text" class="form-control" id="inputBasicMiddleName"
                            placeholder="По батькові"/>
            </div>
        </div>
        <div class="form-group">
            <label for="inputBasicDateOfBirth" class="col-sm-2 control-label">Дата народження<span
                    class="red-star">*</span></label>
            <div class="col-sm-10">
                <form:input path="basicDateOfBirth" type="date" class="form-control" id="inputBasicDateOfBirth"
                            placeholder="Формат: ДД.ММ.РРРР"/>
            </div>
        </div>
        <div class="form-group">
            <label for="inputEnteredDate" class="col-sm-2 control-label">Поступив<span class="red-star">*</span></label>
            <div class="col-sm-10">
                <form:input path="enteredDate" type="date" class="form-control" id="inputEnteredDate"
                            placeholder="Формат: ДД.ММ.РРРР"/>
            </div>
        </div>
        <div class="form-group">
            <label for="inputRetireDate" class="col-sm-2 control-label">Вибув</label>
            <div class="col-sm-10">
                <form:input path="retireDate" type="date" class="form-control" id="inputRetireDate"
                            placeholder="Формат: ДД.ММ.РРРР, НЕ ОБОВ'ЯЗКОВО"/>
            </div>
        </div>

        <div class="section-title">Уточнені відомості</div>

        <div class="form-group">
            <label for="inputClarifiedFirstName" class="col-sm-2 control-label">Ім'я</label>
            <div class="col-sm-10">
                <form:input path="clarifiedFirstName" type="text" class="form-control" id="inputClarifiedFirstName"
                            placeholder="Ім'я"/>
            </div>
        </div>


        <div class="control-group">
            <!-- Button -->
            <div class="controls">
                <button class="btn btn-success">Додати</button>
            </div>
        </div>

    </fieldset>
</form:form>

<jsp:include page="footer.jsp"/>

</body>
</html>