<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Пошук</title>

    <link rel="icon" href="<c:url value="/resources/images/index.ico"/>"/>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/custom-styles.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>


</head>

<body>
<jsp:include page="header.jsp"/>
<c:if test="${errorMessage!=null}">
    <br>
    <div class="alert alert-danger center-block" style="padding: 5px; width: 600px; text-align: center;"
         role="alert">
        <p>${errorMessage}</p>
    </div>
    <br>
</c:if>
<form class="form-inline" style="min-width: 100%;" role="form" method="GET" action="<c:out value="/children/"/>">
    <div class="row">
        <div class="col-xs-12">
            <div class="form-group text-center">
                <div class="input-group">
                    <select class="form-control" id="inputTypes" name="criteria">
                        <c:forEach items="${criterias}" var="entry">
                            <option value="${entry.id}">${entry.russianName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-group">
                    <input type="text" name="value" class="form-control" id="inputValue"
                           placeholder="Треба внести дані пошука">
                </div>
                <div class="input-group">
                    <button aria-label="Left Align" type="submit" class="btn btn-success"> Пошук
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>

<div class="row">
    <div class="child-list col-md-12">
        <c:choose>
            <c:when test="${children != null && children.size() > 0}">
                <table class="table">
                    <thread>
                        <tr>
                            <th>Фото</th>
                            <th>Ім'я</th>
                            <th>Дата народження</th>
                            <th>Дата надходження</th>
                        </tr>
                    </thread>
                    <tbody>
                    <c:forEach items="${children}" var="child">
                        <tr>
                            <td>
                                <div class="photo-min col-md-4"><img
                                        src="/children/getImage/<c:out value="${child.childId}"/>"
                                        class="img-thumbnail"/>
                                </div>
                            </td>
                            <td><a href="/children/child/<c:out value="${child.childId}"/>"><c:out
                                    value="${child.fullName}"/></a></td>
                            <td><c:out value="${child.birthDate.toLocaleString().split(' ')[0]}"/></td>
                            <td><c:out value="${child.entranceDate.toLocaleString().split(' ')[0]}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                Інформації немає
            </c:otherwise>
        </c:choose>
    </div>
</div>


<jsp:include page="footer.jsp"/>


</body>
</html>