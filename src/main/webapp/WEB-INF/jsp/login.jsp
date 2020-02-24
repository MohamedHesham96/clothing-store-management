<%@page import="org.apache.taglibs.standard.tag.common.xml.IfTag"%>
<%@page import="com.hcoder.clothingstoremanagement.entity.Incoming"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

<title>تسجيل الدخول</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body background="images/wall10.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">



	<div  class="container ">


		<div  class="card bg-secondary text-white"
			style="width: 25rem; margin-left: 400px; margin-top: 100px">

			<div class="card-header text-white font-weight-bold text-center"
				style="color: #c4c4c4">تسجيل الدخول</div>

			<ul class="list-group list-group-flush">


				<form action="login" method="post">

					<li class="bg-dark list-group-item">

						<div class="text-center form-group">

							<label>اسم المستخدم</label> <input
								type="text" class="text-center form-control" name="username"
								placeholder="ادخل اسم المستخدم">

						</div>

					</li>
					<li class="bg-dark list-group-item">

						<div class="form-group text-center">
							<label>كلمة المرور</label> <input name="password"
								type="password" class="text-center form-control"
								>
						</div>
					</li>
					<li class="bg-dark list-group-item"><input type="submit"
						style="margin-top: 10px;"
						class="w-100 btn badge-info font-weight-bold text-center"
						value="تسجيل الدخول" /></li>
				</form>
</body>