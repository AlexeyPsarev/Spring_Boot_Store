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
        </style>
        <title>Login page</title>
    </head>
    <body>
        <a href="index.html" class="btn btn-link">Back</a><br>
        <div class="row">
            <c:if test="${not empty errMsg}">
                <div class="col-md-4 alert alert-danger text-center">${errMsg}</div>
            </c:if>
        </div>
        <form class="form-inline" action="login.htm" method="POST">
            <div class="row">
                <label class="col-md-2 text-right" style="line-height: 50px">Username</label>
                <div class="col-md-2 text-left" style="line-height: 50px">
                    <input class="form-control" type="text" style="width: 100%" name="username" autocomplete="off" value="${username}">
                </div>
            </div>
            <div class="row">
                <label class="col-md-2 text-right" style="line-height: 50px">Password</label>
                <div class="col-md-2 text-left" style="line-height: 50px">
                    <input class="form-control" type="password" name="password" style="width: 100%">
                </div>
            </div>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-2 text-center">
                    <button class="btn btn-primary" style="width: 30%">Submit</button>
                </div>
            </div>
        </form>
    </body>
</html>
