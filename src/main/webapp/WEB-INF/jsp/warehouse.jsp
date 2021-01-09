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

<title>المخزن</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">

<script src="/webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>


<script type="text/javascript">
	
</script>


</head>

<body background="images/wall1.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>

	<div style="text-align: right;" class="col-12">

		<div class="card bg-secondary text-white float-right"
			style="width: 18rem;">
			<div class="card-header text-white font-weight-bold text-center"
				style="color: #c4c4c4">الحسابات</div>
			<ul class="list-group list-group-flush">
				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn bg-info text-white font-weight-bold text-center">
						المشتريات <span style="margin-right: 17px;"
							class="w-50 badge badge-light"> ${incomingTotal} جنيه</span>
					</button>
				</li>

				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn bg-info text-white font-weight-bold text-center">
						المباع <span style="margin-right: 50px;"
							class="w-50 badge badge-light">${soldTotal} جنيه</span>
					</button>
				</li>

				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn bg-info text-white  font-weight-bold text-center">
						الموجود <span style="margin-right: 35px;"
							class="w-50 badge badge-light">${warehouseTotal} جنيه</span>
					</button>
				</li>


				<li class="bg-dark list-group-item">

					<form method="GET" action="search-warehouse">

						<input type="text"
							class="text-center form-control font-weight-bold col-xs-3"
							placeholder="ادخل اسم الصنف " name="itemName"> <input
							style="margin-top: 10px" type="submit" value="بحـث"
							class="btn badge-info form-control font-weight-bold">


					</form>
				</li>
			</ul>
		</div>


		<div dir="rtl" class="row">


			<div class="shadow col-12"
				style="position: relative; height: 500px; overflow: auto;">
				<table
					class="table table-bordered table-striped table-dark table-sm">

					<thead class="thead-inverse bg-secondary table-bordered shadow"
						style="position: -webkit-sticky; position: sticky; top: 0; z-index: 2;">

						<tr>
							<th>الصنف</th>
							<th>الكمية</th>
							<th>السعر تجاري</th>
							<th>المحل</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="tempItem" items="${availableIncomings}">

							<tr>
								<td>${tempItem.item}</td>
								<td>${tempItem.currentQuantity}</td>
								<td>${tempItem.tradePrice}</td>
								<td>${tempItem.trader}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>

	</div>
</body>
</html>