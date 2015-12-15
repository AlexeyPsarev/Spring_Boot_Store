<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
    <head>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="/js/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <link href="/js/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .row {
                margin-left: 0px;
                margin-right: 0px;
                margin-top: 5px;
            }
            
            p {
                margin-left: 10px;
                margin-right: 5px;
            }
        </style>
        <title>Registration</title>
    </head>
    <body>
        <a href="index.html" class="btn btn-link">Back</a><br>
        <p>
            Username must contain alphanumeric characters, underscore and dot.
            Numbers, underscore and dot can't be at the end or start of a username.
            Underscore and dot can't be next to each other.
            Underscore or dot can't be used multiple times in a row (e.g user__name / user..name).
            Number of characters must be between 4 and 20.
        </p>
        <p>
            Full name must contain alphabetic characters and spaces.
        </p>
        <p>
            Password can't contain spaces.
            Number of characters must be not less than 8.
        </p>
        <form class="form-inline" action="register.htm" method="POST">
            <div class="row">
                <label class="col-md-2 text-right" style="line-height: 50px">Username</label>
                <div class="col-md-2 text-left" style="line-height: 50px">
                    <input class="form-control" type="text" name="username" style="width: 100%" autocomplete="off" value="${username}">
                </div>
                <c:if test="${not empty badUsername}">
                    <div class="col-md-3 alert alert-danger">${badUsername}</div>
                </c:if>
                <c:if test="${not empty cannotCreate}">
                    <div class="col-md-3 alert alert-danger">${cannotCreate}</div>
                </c:if>
            </div>
            <div class="row">
                <label class="col-md-2 text-right" style="line-height: 50px">Full name</label>
                <div class="col-md-2 text-left" style="line-height: 50px">
                    <input class="form-control" type="text" name="fullname" style="width: 100%" autocomplete="off" value="${fullname}">
                </div>
                <c:if test="${not empty badFullname}">
                    <div class="col-md-3 alert alert-danger">${badFullname}</div>
                </c:if>
            </div>
            <div class="row">
                <label class="col-md-2 text-right" style="line-height: 50px">Password</label>
                <div class="col-md-2 text-left" style="line-height: 50px">
                    <input class="form-control" type="password" name="password" style="width: 100%">
                </div>
                <c:if test="${not empty badPassword}">
                    <div class="col-md-3 alert alert-danger">${badPassword}</div>
                </c:if>
            </div>
            <div class="row">
                <label class="col-md-2 text-right" style="line-height: 50px">Password confirmation</label>
                <div class="col-md-2 text-left" style="line-height: 50px">
                    <input class="form-control" type="password" name="confirmation" style="width: 100%">
                </div>
                <c:if test="${not empty confirmErr}">
                    <div class="col-md-3 alert alert-danger">${confirmErr}</div>
                </c:if>
            </div>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-2 text-center">
                    <button class="btn btn-primary">Register</button>
                </div>
            </div>
        </form>
    </body>
</html>
