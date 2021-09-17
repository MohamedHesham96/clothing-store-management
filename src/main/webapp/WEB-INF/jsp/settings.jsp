<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

    <title>الأعدادات</title>

    <link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
          rel="stylesheet">

</head>
<body class="align-content-center text-center" style="justify-content: center" background="images/wall4.jpg">
<%@ include file="header.jsp" %>

<div>
    <a class="btn btn-danger btn-lg p-lg-3 mt-4" style="font-size: 50px" href="/deleteAllSystem"
       onclick="return confirm('هل انت متأكد من حذف كل بيانات النظام؟')">حذف كل البيانات</a>
</div>
</body>
</html>