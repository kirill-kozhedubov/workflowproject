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

    <title>Дитина</title>

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
<div class="page-title text-center"><h3>КУ "Центр соіиально - психологічної реабілітації дітей Одеської міської ради
    Одеської області"</h3></div>

<div class="page-title text-center">
    <h1>Особиста справа № ${child.personalRecordCode}</h1>

    <div class="photo"><img src="/children/getImage/<c:out value="${child.childId}"/>" name="photo" width="300"
                            height="300" border="0" class="img-thumbnail"/></div>
</div>

<div class="text-center page-title">

    <div class="paragraph">
        <div class="paragraph-title text-center"><h3>Дані зі слів дитини</h3></div>
        <div class="paragraph-content">
            <div class="row">
                <div class="info-col info-title  col-md-2 text-right">Прізвище</div>
                <div class="info-col info-content col-md-10 text-justify"><c:out value="${child.lastName}"/></div>
            </div>
            <div class="row">
                <div class="info-col info-title   col-md-2 text-right">Ім'я</div>
                <div class="info-col info-content col-md-10 text-justify"><c:out value="${child.firstName}"/></div>
            </div>
            <div class="row">
                <div class="info-col info-title   col-md-2 text-right">По батькові</div>
                <div class="info-col info-content col-md-10 text-justify"><c:out value="${child.middleName}"/></div>
            </div>
            <div class="row">
                <div class="info-col info-title   col-md-2 text-right">Дата народження</div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${child.birthDate.toLocaleString().split(' ')[0]}"/></div>
            </div>
        </div>
    </div>


    <div class="paragraph">
        <div class="paragraph-title text-center"><h3>Уточнені дані</h3></div>
        <div class="paragraph-content">
            <div class="row">
                <div class="info-col info-title  col-md-2 text-right">Прізвище</div>
                <div class="info-col info-content col-md-10 text-justify"><c:out
                        value="${clarifiedChild.lastName}"/></div>
            </div>
            <div class="row">
                <div class="info-col info-title   col-md-2 text-right">Ім'я</div>
                <div class="info-col info-content col-md-10 text-justify"><c:out
                        value="${clarifiedChild.firstName}"/></div>
            </div>
            <div class="row">
                <div class="info-col info-title   col-md-2 text-right">По батькові</div>
                <div class="info-col info-content col-md-10 text-justify"><c:out
                        value="${clarifiedChild.middleName}"/></div>
            </div>
            <div class="row">
                <div class="info-col info-title   col-md-2 text-right">Дата народження</div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${clarifiedChild.birthDate.toLocaleString().split(' ')[0]}"/></div>
            </div>
        </div>
    </div>

    <div class="paragraph">
        <div class="paragraph-title text-center"><h3>Коли поступив та вибув</h3></div>
        <div class="paragraph-content">
            <div class="row">
                <div class="info-col info-title  col-md-2 text-right">Поступив</div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${child.entranceDate.toLocaleString().split(' ')[0]}"/></div>
            </div>
            <div class="row">
                <div class="info-col info-title   col-md-2 text-right">Вибув</div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${child.entranceDate.toLocaleString().split(' ')[0]}"/></div>
            </div>
        </div>
    </div>


    <div class="paragraph">
        <div class="paragraph-title text-center"><h3>Анкета</h3>
            <h4>дитини, влаштованої в КУ "Центр СПРД ОМР Одеської області"</h4></div>
        <div class="paragraph-content">
            <div class="row">
                <div class="info-col info-title  col-md-2 text-right">Повне ім'я</div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${clarifiedChild.fullName}"/></div>
            </div>
            <div class="row">
                <div class="info-col info-title   col-md-2 text-right">Дата народження</div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${child.entranceDate.toLocaleString().split(' ')[0]}"/></div>
            </div>
            <div class="row">
                <div class="info-col info-title  col-md-2 text-right">Міссце народження</div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${clarifiedChild.birthPlace}"/></div>
            </div>
            <div class="row">
                <div class="info-col info-title  col-md-2 text-right">Адреса</div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${clarifiedChild.address}"/></div>
            </div>
            <div class="row">
                <div class="info-col info-title  col-md-2 text-right">Місце навчання дитини</div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${clarifiedChild.occupation}"/></div>
            </div>
            <div class="text-center paragraph-title"><h4>Коли та звідки прибув до Одеси</h4></div>
            <div class="row">
                <div class="info-col info-title  col-md-2 text-right">Звідки прибув</div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${clarifiedChild.fromCame}"/></div>
            </div>
            <div class="row">
                <div class="info-col info-title  col-md-2 text-right">Коли прибув</div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${clarifiedChild.whenCame.toLocaleString().split(' ')[0]}"/></div>
            </div>
        </div>
    </div>

    <div class="paragraph">
        <div class="paragraph-title text-center"><h3>Інформація про батьків</h3></div>
        <div class="paragraph-content">
            <c:choose>
            <c:when test="${parents != null && parents.size() > 0}">
                <c:forEach items="${parents}" var="parent">
                    <div class="row">
                        <div class="info-col info-title  col-md-2 text-right">Тип</div>
                        <div class="info-col info-content col-md-10 text-justify">
                            <c:out value="${parent.parentType.russianName}"/></div>
                    </div>
                    <div class="row">
                        <div class="info-col info-title  col-md-2 text-right">Ім'я</div>
                        <div class="info-col info-content col-md-10 text-justify">
                            <c:out value="${parent.parentName}"/></div>
                    </div>
                    <div class="row">
                        <div class="info-col info-title  col-md-2 text-right">Дата народження</div>
                        <div class="info-col info-content col-md-10 text-justify">
                            <c:out value="${parent.parentBirthDate.toLocaleString().split(' ')[0]}"/></div>
                    </div>
                    <div class="row">
                        <div class="info-col info-title  col-md-2 text-right">Інформація</div>
                        <div class="info-col info-content col-md-10 text-justify">
                            <c:out value="${parent.parentInfo}"/></div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
            <div class="row">
                <div class="info-col info-title  col-md-2 text-right">Інформації</div>
                <div class="info-col info-title col-md-10 text-justify">
                    немає
                </div>
                </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <div class="paragraph">
        <div class="paragraph-title text-center"><h3>Коли, де і за що затриманий і ким доставлений до центра</h3></div>
        <div class="paragraph-content">
            <div class="row">
                <div class="info-col info-title  col-md-2 text-right">Коли затриманий</div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${detention.detentionDate.toLocaleString().split(' ')[0]}"/></div>
            </div>
            <div class="row">
                <div class="info-col info-title  col-md-2 text-right">Де затриманий</div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${detention.detentionAddress}"/></div>
            </div>
            <div class="row">
                <div class="info-col info-title  col-md-2 text-right">За що затриманий</div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${detention.gotDetainedFor}"/></div>
            </div>
            <div class="row">
                <div class="info-col info-title  col-md-2 text-right">Ким доставлений</div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${detention.detainedBy}"/></div>
            </div>
        </div>
    </div>

    <div class="paragraph">
        <div class="paragraph-title text-center"><h3>Додаткова інформація</h3></div>
        <div class="paragraph-content">
            <div class="row">
                <div class="info-col info-title  col-md-2 text-right">Затримувався лі раніше міліцією, чи був судимий
                </div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${clarifiedChild.judgedOrDetainedInfo}"/></div>
            </div>
            <div class="row">
                <div class="info-col info-title  col-md-2 text-right">Де затриманий</div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${clarifiedChild.notes}"/></div>
            </div>
            <div class="row">
                <div class="info-col info-title  col-md-2 text-right">Черговий з режиму</div>
                <div class="info-col info-content col-md-10 text-justify">
                    <c:out value="${clarifiedChild.dutyOfficer}"/></div>
            </div>
        </div>
    </div>

    <div class="paragraph">
        <div class="paragraph-title text-center"><h3>Файли</h3></div>
        <div class="paragraph-content">

            <c:forEach items="${childFiles}" var="file">
                <div class="row">
                    <div class="info-col info-title  col-md-2 text-right">Файл</div>
                    <div class="info-col info-content col-md-10 text-justify">
                        <a href="<c:out value="/children/getFile/${file.fileId}"/>"> <c:out
                                value="${file.fileName}"/></a>
                    </div>
                </div>
            </c:forEach>


        </div>


    </div>
</div>
<jsp:include page="footer.jsp"/>

</body>
</html>