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

<title>الفواتير</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">

</head>

<body background="images/cal1.jpg"
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
						class="w-100 btn bg-info text-white  font-weight-bold text-center">
						اجمالي الربح <span style="margin-right: 20px;"
							class="w-50 badge badge-light text-center"> ${gainTotal}
							جنيه</span>
					</button>
				</li>
				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn bg-info text-white  font-weight-bold text-center">
						المصاريف <span style="margin-right: 33px;"
							class="w-50 badge badge-light text-center">${spendingTotal}
							جنيه</span>
					</button>
				</li>
				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn bg-success  font-weight-bold text-center text-center">
						صافي الربح <span style="margin-right: 25px;"
							class="w-50 badge badge-light text-center">${total} جنيه</span>
					</button>
				</li>


				<li class="bg-dark list-group-item">

					<form method="GET" action="bill">

						<input type="date" name="date" value="${date}"
							class="w-100 btn badge-info  font-weight-bold text-center">



						<input type="submit" style="margin-top: 10px;"
							class="w-100 btn badge-light  font-weight-bold text-center"
							value="اذهب لهذا اليوم" />

					</form>
				</li>

			</ul>
		</div>



		<div class="row  my-4">
			<div dir='rtl' class="col-lg-12 col-md-8">
				<div class="table-responsive">
					<table class="table table-bordered table-striped table-dark">
						<thead class="thead-inverse">
							<tr>
								<th>الصنف</th>
								<th>الكمية</th>
								<th>السعر تجاري</th>
								<th>سعر القطعه</th>
								<th>صافي الربح</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="tempItem" items="${items}">
								<tr>
									<td>${tempItem.item}</td>
									<td>${tempItem.quantity}</td>
									<td>${tempItem.tradePrice}</td>
									<td>${tempItem.piecePrice}</td>
									<td>${tempItem.gain}</td>
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