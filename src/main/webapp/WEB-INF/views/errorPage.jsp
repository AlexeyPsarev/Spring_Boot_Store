<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="/js/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <link href="/js/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Server error</title>
    </head>
    <body>
        <form action="home.htm" method="GET">
            <button class="btn btn-link" style="padding: 10px">Home</button>
        </form>
		<div style="margin-left: 10px">
			<h1>Error:</h1>
			<p>${errMsg}</p>
		</div>
    </body>
</html>
