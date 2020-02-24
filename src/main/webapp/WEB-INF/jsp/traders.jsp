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

<title>العملاء</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript" src="webjars/jquery/3.4.1/jquery.min.js"></script>

</head>

<body background="images/wall11.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>

	<div style="text-align: right;" class="container ">

		<div class="card bg-secondary text-white"
			style="width: 18rem; margin-left: 820px;">
			<div class="card-header text-white font-weight-bold text-center"
				style="color: #c4c4c4">الحسابات</div>
			<ul class="list-group list-group-flush">

				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn badge-info  font-weight-bold text-center">
						 المدفوع <span style="margin-right: 20px;"
							class="w-50 badge badge-light">${payedTotal} جنيه </span>
					</button>
				</li>

				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn badge-info  font-weight-bold text-center">
						 الباقي <span style="margin-right: 30px;"
							class="w-50 badge badge-light">${remainingTotal} جنيه</span>
					</button>
				</li>

				<li class="bg-dark  list-group-item">

					<button id="showForm" type="button"
						class="btn btn-light btn-lg btn-block">أضافة تاجر جديد</button>

				</li>
			</ul>
		</div>




		<div class="row  my-4">
			<div dir='rtl' class="col-lg-12 col-md-8">
				<div class="table-responsive">
					<table class="table table-bordered table-striped table-dark">
						<thead class="thead-inverse">
							<tr>
								<th>اسم التاجر</th>
								<th>المدفوع</th>
								<th>الباقي</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="tempItem" items="${tradersList}">
								<c:url var="clientLink" value="/clientProfile">
									<c:param name="clientId" value="${tempItem.id}"></c:param>
								</c:url>
								<tr>
									<td><a class="text-white" href="${clientLink}">${tempItem.name}</a></td>
									<td>${tempItem.payed}</td>
									<td>${tempItem.remaining}</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		</div>


		<div
			style="display: none; margin-top: 100px; padding: 10px; box-shadow: 5px 5px 5px 5px rgba(0, 0, 0, 0.60); direction: rtl"
			class="form fixed-top container-fluid col-lg-4">
			<div class="card bg-dark">

				<div class="text-right card-body bg-dark text-white">

					<h3>أضافة تاجر جديد</h3>
					<form:form metho="POST" action="add-new-trader"
						modelAttribute="theTrader">

						<label style="margin-top: 10px" class="mb-2 mr-sm-2"> اسم
							التاجر :</label>
						<form:input type="text" class="form-control  col-xs-3"
							placeholder="ادخل اسم التاجر " path="name"></form:input>

						<label style="margin-top: 10px" class="mb-2 mr-sm-2">المدفوع
							:</label>
						<form:input type="text" class="form-control  col-xs-3"
							placeholder="ادخل المبلغ المدفوع " path="payed"></form:input>

						<label style="margin-top: 10px" class="mb-2 mr-sm-2">الباقي
							:</label>
						<form:input type="text" class="form-control  col-xs-3"
							placeholder="ادخل الملف المتبقي " path="remaining"></form:input>

						<br>

						<button type="submit" class="btn btn-info form-control 	">
							قم بأضافة تاجر جديد</button>
					</form:form>


				</div>
			</div>
		</div>
	</div>
</body>
</html>