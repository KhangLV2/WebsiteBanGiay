
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check Out</title>
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <%-- Menu   --%>
       <jsp:include page="menu.jsp"></jsp:include>
    <%-- End menu   --%>

    <div class="container">
        <div class="alert alert-info" role="alert">
            Bạn đã đặt hàng thành công
        </div>
    </div>

   <%-- Footer    --%>
      <jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
