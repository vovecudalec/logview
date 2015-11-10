<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Maven + Spring MVC</title>
    <spring:url value="/resources/core/css/hello.css" var="coreCss"/>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${coreCss}" rel="stylesheet"/>
</head>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container" style="width: 100%">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">LogReader</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="get100" role="button" title="100 последних строк">100</a></li>
                <li><a href="get500" role="button" title="500 последних строк">500</a></li>
                <li title="Здесь будет кнопка для скачивания всего файла"><a href="#" class="btn disabled" role="button">Download</a></li>
            </ul>
            <ul class="nav navbar-nav" style="float:right">
                <li><a href="/" role="button" title="К списку логов">Selector</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="jumbotron" style="height: 100%">
    <input type="text" readonly value="${title}" style="width: 100%"/>
                    <textarea class="form-control"
                              style="width:100%; height: 100%; font-size: 1em; resize:none;"
                            >${fileContent}</textarea>
</div>

<div class="navbar-inverse navbar-fixed-bottom">
    <div class="nav" style="width: 100%; color: white">
        Корусконсалтинг ДМ - 2015
    </div>
</div>

<spring:url value="/resources/core/css/hello.js" var="coreJs"/>
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs"/>

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>