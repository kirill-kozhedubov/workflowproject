<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--Navigation-->
<div class="container">
    <div class="row">
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a href="<c:url value="/" />" class="navbar-brand">
                        Demkids
                    </a>
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#respinsive-menu">
                        <span class="sr-only">Open navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse" id="respinsive-menu">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="<c:url value="/"/>">Главная</a></li>
                        <li><a href="<c:url value="/children/"/>">Поиск детей</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#">${sessionScope.userObject.getFullName()}</a>
                        </li>
                        <li>
                            <a href="<c:url value="/logout" />">Выйти</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="page-wrap"></div>
<div class="page-wrap"></div>

<div class="container">
    <div class="row">
        <div class="content">
            <div class="col-md-12">