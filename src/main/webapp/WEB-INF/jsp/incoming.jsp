<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

<title>Insert title here</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>



	<br>
	<br>
	<br>
	<br>

	<!-- Table -->
	<div style="text-align: right;" class="container">
		<div class="row  my-6">
			<div dir='rtl' class="col-lg-12 col-md-8">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead class="thead-inverse">
							<tr>
								<th>الصنف</th>
								<th>الكمية</th>
								<th>السعر تجاري</th>
								<th>سعر القطعة</th>
								<th>اجمالي السعر</th>
								<th>المحل</th>
								<th>تاريخ التسجيل</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="tempItem" items="${items}">

								<tr>
									<td>${tempItem.item}</td>
									<td>${tempItem.quantity}</td>
									<td>${tempItem.trade_price}</td>
									<td>${tempItem.piece_price}</td>
									<td>${tempItem.total}</td>
									<td>${tempItem.store}</td>
									<td>${tempItem.date}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<button type="button" class="btn btn-info btn-lg btn-block">+
					اضافة صنف</button>

			</div>

		</div>
</body>
</html>