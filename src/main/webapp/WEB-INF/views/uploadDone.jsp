<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Upload done</title>
    </head>
    <body>
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4 text-center">
                <div class="alert alert-success text-center">
                    Package uploading successfully completed
                </div>
                <form action="home.htm" method="GET">
                    <button class="btn btn-primary" style="width: 30%">OK</button>
                </form>
            </div>
            <div class="col-md-4"></div>
        </div>
    </body>
</html>
