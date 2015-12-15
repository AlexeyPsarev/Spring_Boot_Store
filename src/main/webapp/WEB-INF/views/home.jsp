<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="/js/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <link href="/js/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
        <title>Application Store</title>
        
        <c:choose>
            <c:when test="${empty category}">
                <c:set var="category" value="Games"/>
            </c:when>
            <c:when test="${empty page}">
                <c:set var="page" value="1"/>
            </c:when>
        </c:choose>
        
        <script charset="utf-8">
            var category = '${category}',
                    page = ${page};
        </script>
        <script src="${pageContext.request.contextPath}/js/HomePageActions.js"></script>
        
        <style>
            .row {
                margin-left: 0px;
                margin-right: 0px;
            }
            button {
                margin-top: 5px;
            }
        </style>
    </head>
    <body>
        <div>
            <p>Most popular</p>
            <div style="min-height: 128px">
                <c:forEach var="item" items="${popularApps}">
                    <a href="${pageContext.request.contextPath}/details.htm?app=${item.id}"><img src="${item.pic128}" height="128" width="128" style="margin-left: 10px"></a>
                </c:forEach>
            </div>
        </div>
        <div class="row" style="margin-top: 20px;">
            <div class="col-md-3 text-center">
                <ul class="nav nav-pills nav-stacked">
                    <li><a href="${pageContext.request.contextPath}/home.htm?category=Games&page=1" id="Games">Games</a></li>
                    <li><a href="${pageContext.request.contextPath}/home.htm?category=Multimedia&page=1" id="Multimedia">Multimedia</a></li>
                    <li><a href="${pageContext.request.contextPath}/home.htm?category=Productivity&page=1" id="Productivity">Productivity</a></li>
                    <li><a href="${pageContext.request.contextPath}/home.htm?category=Tools&page=1" id="Tools">Tools</a></li>
                    <li><a href="${pageContext.request.contextPath}/home.htm?category=Health&page=1" id="Health">Health</a></li>
                    <li><a href="${pageContext.request.contextPath}/home.htm?category=Lifestyle&page=1" id="Lifestyle">Lifestyle</a></li>
                </ul>
                <hr>
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <form action="upload.htm" method="GET">
                            <button class="btn btn-primary btn-block text-center">Upload</button>
                        </form>
                        <form action="changePassword.htm" method="GET">
                            <button class="btn btn-primary btn-block text-center">Change Password</button>
                        </form>
                        <form action="logout.htm" method="GET">
                            <button class="btn btn-primary btn-block text-center">Log out</button>
                        </form>
                    </div>
                    <div class="col-md-2"></div>
                </div>
            </div>
            <div class="col-md-9" style="border-left: 2px solid; min-height: 650px">
                <div style="min-height: 650px">
                    <c:forEach var="item" items="${apps}">
                        <a href="${pageContext.request.contextPath}/details.htm?app=${item.id}"><img src="${item.pic128}" height="128" width="128"></a>
                        <a href="${pageContext.request.contextPath}/details.htm?app=${item.id}" style="line-height: 128px; margin-left: 50px">${item.appName}</a>
                        <hr>
                    </c:forEach>
                </div>
                <nav>
                    <ul class="pagination">
                        <c:forEach var="cur" begin="1" end="${pageCount}">
                            <li><a href="${pageContext.request.contextPath}/home.htm?category=${category}&page=${cur}" id="p${cur}">${cur}</a></li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>
    </body>
</html>
