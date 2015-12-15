<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="/js/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <link href="/js/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
        <title>Change Password</title>
        
        <style>
            .row {
                margin-left: 0px;
                margin-right: 0px;
            }
        </style>
    </head>
    <body>
        <form action="home.htm" method="GET">
            <button class="btn-link">Home</button>
        </form>
        <p>
            Password can't contain spaces.
            Number of characters must be not less than 8.
        </p>
        <form class="form-inline" action="newPassword.htm" method="POST">
            <div class="row">
                <label class="col-md-2 text-right" style="line-height: 50px">Old Password</label>
                <div class="col-md-2 text-left" style="line-height: 50px">
                    <input class="form-control" type="password" name="oldPassword" style="width: 100%">
                </div>
                <div class="col-md-3"> 
                    <c:if test="${not empty oldPassErr}">
                        <div class="alert alert-danger">${oldPassErr}</div>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <label class="col-md-2 text-right" style="line-height: 50px">New Password</label>
                <div class="col-md-2 text-left" style="line-height: 50px">
                    <input class="form-control" type="password" name="password" style="width: 100%">
                </div>
                <div class="col-md-3"> 
                    <c:if test="${not empty newPassErr}">
                        <div class="alert alert-danger">${newPassErr}</div>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <label class="col-md-2 text-right" style="line-height: 50px">Password confirmation</label>
                <div class="col-md-2 text-left" style="line-height: 50px">
                    <input class="form-control" type="password" name="confirmation" style="width: 100%">
                </div>
                <div class="col-md-3"> 
                    <c:if test="${not empty confirmErr}">
                        <div class="alert alert-danger">${confirmErr}</div>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-2 text-center">
                    <button class="btn btn-primary">Submit</button>
                </div>
            </div>
        </form>
    </body>
</html>
