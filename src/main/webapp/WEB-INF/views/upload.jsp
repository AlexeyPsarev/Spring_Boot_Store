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
        <title>Upload</title>
    </head>
    <body>
        <form action="home.htm" method="GET">
            <button class="btn btn-link">Home</button><br>
        </form>
        <c:if test="${not empty errMsg}">
            <div class="alert alert-danger">${errMsg}</div>
        </c:if>
        <form class="form-inline" action="doUpload.htm" method="POST" enctype="multipart/form-data">
            <div class="row">
                <label class="col-md-2 text-right" style="line-height: 50px">Application name</label>
                <div class="col-md-4 text-left" style="line-height: 50px">
                    <input class="form-control" name="appName" type="text" style="width: 100%" value="${appName}">
                </div>
                <c:if test="${not empty appNameErr}">
                    <div class="col-md-3 alert alert-danger">${appNameErr}</div>
                </c:if>
            </div>
            <div class="row">
                <label class="col-md-2 text-right">Application file</label>
                <div class="col-md-4 text-left">
                    <input name="pkg" type="file">
                    <p class="help-block">Choose the file to upload</p>
                </div>
                <c:if test="${not empty appFileErr}">
                    <div class="col-md-3 alert alert-danger">${appFileErr}</div>
                </c:if>
            </div>
            <div class="row">
                <label class="col-md-2 text-right" style="line-height: 50px">Category</label>
                <div class="col-md-4 text-left" style="line-height: 50px">
                    <select class="form-control" name="category" style="width: 100%">
                        <option value="Games">Games</option>
                        <option value="Multimedia">Multimedia</option>
                        <option value="Productivity">Productivity</option>
                        <option value="Tools">Tools</option>
                        <option value="Health">Health</option>
                        <option value="Lifestyle">Lifestyle</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <label class="col-md-2 text-right">Description</label>
                <div class="col-md-4 text-left">
                    <textarea class="form-control" name="description" rows="10" style="width: 100%">${description}</textarea>
                </div>
            </div>
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-4 text-center">
                    <button class="btn btn-success" style="width: 30%">Upload</button>
                </div>
            </div>
        </form>
    </body>
</html>
