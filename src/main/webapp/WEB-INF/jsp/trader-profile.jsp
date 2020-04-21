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

<title>${traderData.name}</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">

<script src="/webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<script src="/webjars/jquery/3.4.1/jquery.min.js"></script>

</head>
<body background="images/wall9.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>

	<div style="text-align: right;" class="container ">

		<div class="card bg-secondary text-white"
			style="width: 18rem; margin-left: 820px;">
			<div class="card-header text-white font-weight-bold text-center"
				style="color: #c4c4c4">بيانات التاجر</div>
			<ul class="list-group list-group-flush">

				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn bg-info text-white font-weight-bold text-center">
						الاسم <span style="margin-right: 25px; width: 140px;"
							class=" badge badge-light">${traderData.name}</span>
					</button>
				</li>


				<li class="bg-dark list-group-item">
					<button dir="rtl" style="text-align: right;" type="button"
						type="button"
						class="w-100 btn bg-info text-white font-weight-bold text-center">
						مشتريات <span style="margin-right: 5px; width: 140px;"
							class=" badge badge-light">${incomingsTotal} جنيه</span>
					</button>
				</li>


				<li class="bg-dark list-group-item">
					<button dir="rtl" style="text-align: right;" type="button"
						type="button"
						class="w-100 btn bg-info text-white font-weight-bold text-center">
						المدفوع <span style="margin-right: 15px; width: 140px;"
							class=" badge badge-light">${traderData.payed} جنيه</span>
					</button>
				</li>


				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						type="button"
						class=" ${incomingsTotal == traderData.payed ?  'bg-success' :'bg-danger'} 
						
						text-center w-100 btn font-weight-bold">

						الباقي <span style="margin-right: 30px; width: 140px;"
							class=" badge badge-light">${remainingTotal} جنيه</span>
					</button>
				</li>

				<li class="bg-dark list-group-item"><form:form metho="GET"
						action="pay-off-amount-for-trader" modelAttribute="traderData">


						<form:hidden path="id" />

						<input type="text" class="text-center form-control  col-xs-3"
							placeholder="ادخل المبلغ المسدد " name="moneyAmount">


						<button style="margin-top: 10px"
							${ incomingsTotal == traderData.payed ? 'disabled = "disabled"' : ''}
							type="submit" class="btn btn-info form-control font-weight-bold">

							قم بتسديد المبلغ</button>

					</form:form></li>

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
								<th>اجمالي السعر</th>
								<th>تاريخ التسجيل</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="tempItem" items="${traderIncomings}">
								<tr>
									<td>${tempItem.item}</td>
									<td>${tempItem.quantity}</td>
									<td>${tempItem.tradePrice}</td>
									<td>${tempItem.total}</td>
									<td>${tempItem.date}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		</div>

		<div
			style="display: none; margin-top: 100px; padding: 10px; box-shadow: 5px 5px 5px 5px rgba(0, 0, 0, 0.60); direction: rtl"
			class="form container-fluid col-lg-4 col-md-6 fixed-top">

			<div class="card bg-dark">


				<div class="text-right card-body bg-dark text-white">

					<h3>سداد مبلغ</h3>



				</div>
			</div>
		</div>




	</div>
</body>
</html>