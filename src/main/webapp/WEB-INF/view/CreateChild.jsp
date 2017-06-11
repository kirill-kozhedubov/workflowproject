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

        <br>
        <c:if test="${errorMessage!=null}">
            <div class="alert alert-danger center-block" style="padding: 5px; width: 600px; text-align: center;"
                 role="alert">
                <p>${errorMessage}</p>
            </div>
        </c:if>
        <br>

        <div class="form-group">
            <label for="inputBasicFirstName" class="col-sm-2 control-label">Ім'я<span class="red-star">*</span></label>
            <div class="col-sm-10">
                <form:input path="basicFirstName" type="text" class="form-control" id="inputBasicFirstName"
                            placeholder="Ім'я" />
            </div>
        </div>
        <div class="form-group">
            <label for="inputBasicLastName" class="col-sm-2 control-label">Прізвище<span
                    class="red-star">*</span></label>
            <div class="col-sm-10">
                <form:input path="basicLastName" type="text" class="form-control" id="inputBasicLastName"
                            placeholder="Прізвище" />
            </div>
        </div>
        <div class="form-group">
            <label for="inputBasicMiddleName" class="col-sm-2 control-label">По батькові<span class="red-star">*</span></label>
            <div class="col-sm-10">
                <form:input path="basicMiddleName" type="text" class="form-control" id="inputBasicMiddleName"
                            placeholder="По батькові" />
            </div>
        </div>
        <div class="form-group">
            <label for="inputBasicDateOfBirth" class="col-sm-2 control-label">Дата народження
                <span class="red-star">*</span></label>
            <div class="col-sm-10">
                <form:input path="basicDateOfBirth" type="date" class="form-control" id="inputBasicDateOfBirth"
                            placeholder="Формат: ДД.ММ.РРРР" />
            </div>
        </div>
        <div class="form-group">
            <label for="inputPersonalRecordCode" class="col-sm-2 control-label">Номер особистої справи
                <span class="red-star">*</span></label>
            <div class="col-sm-10">
                <form:input path="personalRecordCode" type="text" class="form-control" id="inputPersonalRecordCode"
                            placeholder="Номер особистої справи" />
            </div>
        </div>
        <div class="form-group">
            <label for="inputEnteredDate" class="col-sm-2 control-label">Поступив<span class="red-star">*</span></label>
            <div class="col-sm-10">
                <form:input path="enteredDate" type="date" class="form-control" id="inputEnteredDate"
                            placeholder="Формат: ДД.ММ.РРРР" />
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
        <div class="form-group">
            <label for="inputClarifiedLastName" class="col-sm-2 control-label">Прізвище</label>
            <div class="col-sm-10">
                <form:input path="clarifiedLastName" type="text" class="form-control" id="inputClarifiedLastName"
                            placeholder="Прізвище"/>
            </div>
        </div>
        <div class="form-group">
            <label for="inputClarifiedMiddleName" class="col-sm-2 control-label">По батькові</label>
            <div class="col-sm-10">
                <form:input path="clarifiedMiddleName" type="text" class="form-control" id="inputClarifiedMiddleName"
                            placeholder="По батькові"/>
            </div>
        </div>
        <div class="form-group">
            <label for="inputClarifiedDateOfBirth" class="col-sm-2 control-label">Дата народження</label>
            <div class="col-sm-10">
                <form:input path="clarifiedDateOfBirth" type="date" class="form-control" id="inputClarifiedDateOfBirth"
                            placeholder="Формат: ДД.ММ.РРРР"/>
            </div>
        </div>
        <div class="section-title">Анкета</div>
        <div class="section-title-little">дитини, влаштованої в КУ "Центр СПРД ОМР Одеської області"</div>

        <div class="form-group">
            <label for="inputDistrict" class="col-sm-2 control-label">Район<span class="red-star">*</span></label>
            <div class="col-sm-10">
                <form:select class="form-control" id="inputDistrict" path="district">
                    <c:forEach items="${districts}" var="entry">
                        <form:option value="${entry.dbId}">${entry.russianName}</form:option>
                    </c:forEach>
                </form:select>
            </div>
        </div>
        <div class="form-group">
            <label for="inputAddress" class="col-sm-2 control-label">Адреса</label>
            <div class="col-sm-10">
                <form:input path="address" type="text" class="form-control" id="inputAddress"
                            placeholder="Адреса"/>
            </div>
        </div>
        <div class="form-group">
            <label for="inputBirthPlace" class="col-sm-2 control-label">Місто народження</label>
            <div class="col-sm-10">
                <form:input path="birthPlace" type="text" class="form-control" id="inputBirthPlace"
                            placeholder="Місто народження"/>
            </div>
        </div>
        <div class="form-group">
            <label for="inputOccupation" class="col-sm-2 control-label">Місце навчання</label>
            <div class="col-sm-10">
                <form:input path="occupation" type="text" class="form-control" id="inputOccupation"
                            placeholder="Місце навчання"/>
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-4 control-label">Коли, звідки прибув у населенмй пункт</label>
            <div class="col-sm-4">
                <form:input path="fromCame" type="text" class="form-control"
                            placeholder="Звідки"/>
            </div>
            <div class="col-sm-4">
                <form:input path="whenCame" type="date" class="form-control"
                            placeholder="Коли. Формат: ДД.ММ.РРРР"/>
            </div>
        </div>

        <div class="section-title-little">Інформація про затримання та доставлення до центру</div>

        <div class="form-group">
            <label class="col-sm-4 control-label">Коли затриманий і ким доставлений до центра<span class="red-star">*</span></label>
            <div class="col-sm-4">
                <form:input path="detainedBy" type="text" class="form-control"
                            placeholder="Ким доставлений"/>
            </div>
            <div class="col-sm-4">
                <form:input path="detentionDate" type="date" class="form-control"
                            placeholder="Коли. Формат: ДД.ММ.РРРР"/>
            </div>
        </div>

        <div class="form-group">
            <label for="inputDetentionAddress" class="col-sm-2 control-label">Де затриманий<span class="red-star">*</span></label>
            <div class="col-sm-10">
                <form:input path="detentionAddress" type="text" class="form-control" id="inputDetentionAddress"
                            placeholder="Де затриманий"/>
            </div>
        </div>

        <div class="form-group">
            <label for="inputDetainedFor" class="col-sm-2 control-label">За що затриманий<span class="red-star">*</span></label>
            <div class="col-sm-10">
                <form:textarea path="detainedFor" type="text" class="form-control" id="inputDetainedFor"
                               placeholder="За що затриманий" rows="3"/>
            </div>
        </div>

        <div class="section-title-little">Інша інформація</div>

        <div class="form-group">
            <label for="inputJudgedOrDetainedInfo" class="col-sm-2 control-label">Затримувався раніше міліцією чи був
                судимий</label>
            <div class="col-sm-10">
                <form:textarea path="judgedOrDetainedInfo" type="text" class="form-control"
                               id="inputJudgedOrDetainedInfo"
                               placeholder="Інформація про затримання/судимості. Оставити пустим, якщо таких не було."
                               rows="3"/>
            </div>
        </div>

        <div class="form-group">
            <label for="inputNotes" class="col-sm-2 control-label">Примітка</label>
            <div class="col-sm-10">
                <form:textarea path="notes" type="text" class="form-control" id="inputNotes"
                               placeholder="Примітка.  Оставити пустим, якщо немає." rows="3"/>
            </div>
        </div>

        <div class="form-group">
            <label for="inputDutyOfficer" class="col-sm-2 control-label">Черговий з режиму<span class="red-star">*</span></label>
            <div class="col-sm-10">
                <form:input path="dutyOfficer" type="text" class="form-control" id="inputDutyOfficer"
                            placeholder="Черговий з режиму" />
            </div>
        </div>


        <div class="control-group">
            <!-- Button -->
            <div class="controls">
                <button type="submit" class="btn btn-success">Додати</button>
            </div>
        </div>

    </fieldset>
</form:form>

<jsp:include page="footer.jsp"/>

</body>
</html>