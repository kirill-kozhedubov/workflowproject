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

    <title>Завантажити файли дитини</title>

    <link rel="icon" href="<c:url value="/resources/images/index.ico"/>"/>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/custom-styles.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>


</head>

<body>
<jsp:include page="header.jsp"/>

<form class="form-horizontal" role="form" method="POST" action="<c:out value="/children/upload-files-post"/>"
      enctype="multipart/form-data">


    <div class="form-group file-section-photo">
        <label for="inputPhoto" class="col-sm-2 control-label">Фото (якщо вже є - перезаписується)</label>
        <div class="col-sm-10">
            <input type="file" accept=".jpg, .jpeg, .png, .bmp" name="photo" id="inputPhoto"/>
        </div>
    </div>

    <div id="files-section">
        <div class="form-group file-section-item">
            <label for="inputFile" class="col-sm-2 control-label">Будь-який файл</label>
            <div class="col-sm-10">
                <input type="file" name="file" id="inputFile"/>
            </div>
        </div>
    </div>

    <div class="form-group">
        <button id="addbutton" class="btn btn-lg btn-primary col-sm-2 col-sm-offset-5" type="button">Додади файл
        </button>
    </div>

    <div class="form-group">
        <input class="btn btn-lg btn-success col-sm-2 col-sm-offset-5" type="submit" value="Next"/>
    </div>

    <input name="childId" value="${childId}" style="display: none;"/>

</form>
<jsp:include page="footer.jsp"/>

<script>
    $("#addbutton").click(
        function () {
            var cloner = $(".file-section-item:first").clone();

            cloner.find('input').val("");
            $("#files-section").append(cloner);

        });


</script>


</body>
</html>