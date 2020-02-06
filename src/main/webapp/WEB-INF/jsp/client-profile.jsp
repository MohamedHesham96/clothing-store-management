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

<title>المصاريف</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body background="images/wall3.jpg"
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
						الاسم <span style="margin-right: 22px;"
							class="w-50 badge badge-light">${clientData.name}</span>
					</button>
				</li>
				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn badge-info  font-weight-bold text-center">
						التليفون <span style="margin-right: 10px;"
							class="w-50 badge badge-light">${clientData.phone}</span>
					</button>
				</li>
				<li class="bg-dark list-group-item">
					<button dir="rtl" style="margin-right: 35px; text-align: right;"
						type="button"
						class="w-100 btn badge-info  font-weight-bold text-center">
						عليه <span style="margin-right: 35px;"
							class="w-50 badge badge-light">${clientData.drawee}</span>
					</button>
				</li>
			</ul>
		</div>




		<div class="row  my-4">
			<div dir='rtl' class="col-lg-12 col-md-8">
				<div class="table-responsive">
					<table class="table  table-striped table-dark">
						<thead class="thead-inverse">
							<tr>
								<th>الصنف</th>
								<th>السعر</th>
								<th>دفع</th>
								<th>باقي</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="tempItem" items="${clientData.clientRecords}">

								<tr>
									<td>${tempItem.item}</td>
									<td>${tempItem.price}</td>
									<td>${tempItem.pay}</td>
									<td>${tempItem.price - tempItem.pay}</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		</div>




	</div>
</body>
</html>