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

    <title>Додати родича</title>

    <link rel="icon" href="<c:url value="/resources/images/index.ico"/>"/>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/custom-styles.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>


</head>

<body>
<jsp:include page="header.jsp"/>
<c:url value="/children/add-parents-post" var="save"/>
<div class="page-title text-center"><h1>Додання батьків дитини ${child.fullName} ${child.personalRecordCode} до
    бази</h1></div>
<form:form class="form-horizontal" modelAttribute="parents" action="${save}" method="post">

    <fieldset>
        <div class="section-title text-center"><h3>Батьки, які будуть додані до бази</h3></div>


        <c:if test="${errorMessage!=null}">
            <br>
            <div class="alert alert-danger center-block" style="padding: 5px; width: 600px; text-align: center;"
                 role="alert">
                <p>${errorMessage}</p>
            </div>
            <br>
        </c:if>


        <c:forEach begin="0" end="2" varStatus="loop">
            <div class="form-group">
                <label class="col-sm-4 control-label">Тип та дата народження<span class="red-star">*</span></label>
                <div class="col-sm-4">
                    <form:select class="form-control" id="inputTypes"
                                 path="parentRequestList[${loop.index}].parentTypeId">
                        <c:forEach items="${parentTypes}" var="entry">
                            <form:option value="${entry.dbId}">${entry.russianName}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="col-sm-4">
                    <form:input path="parentRequestList[${loop.index}].parentDateOfBirth" type="date"
                                class="form-control"
                                placeholder="Формат: ДД.ММ.РРРР"/>
                </div>
            </div>
            <div class="form-group">
                <label for="inputName" class="col-sm-2 control-label">Ім'я<span class="red-star">*</span></label>
                <div class="col-sm-10">
                    <form:input path="parentRequestList[${loop.index}].parentName" type="text" class="form-control"
                                id="inputName"
                                placeholder="Повне ім'я"/>
                </div>
            </div>
            <div class="form-group">
                <label for="inputInfo" class="col-sm-2 control-label">Інформація*</label>
                <div class="col-sm-10">
                    <form:textarea path="parentRequestList[${loop.index}].parentInfo" type="text" class="form-control"
                                   id="inputInfo"
                                   placeholder="Інформація" rows="3"/>
                </div>
            </div>
            <form:hidden path="childId" value="${childId}"/>


            <hr>

        </c:forEach>


        <div class="form-group">
            <input class="btn btn-lg btn-success col-sm-2 col-sm-offset-5" type="submit" value="Додати"/>
        </div>


    </fieldset>
</form:form>
<jsp:include page="footer.jsp"/>


</body>
</html>