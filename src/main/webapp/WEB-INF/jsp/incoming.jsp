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

<title>المشتريات</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">


<script type="text/javascript" src="/webjars/jquery/3.4.1/jquery.min.js"></script>

</head>
<body background="images/wall7.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>


	<div style="text-align: right;" class="container">

		<div class="card bg-secondary text-white"
			style="width: 18rem; margin-left: 820px;">
			<div class="card-header text-white font-weight-bold text-center"
				style="color: #c4c4c4">الحسابات</div>
			<ul class="list-group list-group-flush">
				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn badge-info  font-weight-bold text-center">
						المشتريات <span style="margin-right: 17px;"
							class="w-50 badge badge-light"> ${incomingTotal}</span>
					</button>
				</li>
				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn badge-info  font-weight-bold text-center">
						الموجود <span style="margin-right: 35px;"
							class="w-50 badge badge-light">${warehouseTotal}</span>
					</button>
				</li>
				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn badge-success  font-weight-bold text-center">
						المباع <span style="margin-right: 50px;"
							class="w-50 badge badge-light">${soldTotal}</span>
					</button>
				</li>

				<li class="bg-dark list-group-item">

					<form method="GET" action="incoming">

						<input type="date" name="date" value="${date}"
							class="w-100 btn badge-info  font-weight-bold text-center">

						</input> <input type="submit" style="margin-top: 10px;"
							class="w-100 btn badge-light  font-weight-bold text-center"
							value="اذهب لهذا اليوم" />

					</form>
				</li>

			</ul>
		</div>

		<form:form metho="POST" action="add-incoming"
			modelAttribute="incoming">

			<div class="row  my-4">
				<div dir='rtl' class="col-lg-12 col-md-8">
					<div class="table-responsive">
						<table class=" table table-striped table-dark">
							<thead class="thead-inverse">
								<tr>
									<th>الكمية</th>
									<th>الصنف</th>
									<th>السعر تجاري</th>
									<th>سعر القطعة</th>
									<th>المحل</th>
								</tr>
							</thead>

							<tbody>

								<tr>
									<td><form:input type="text" path="quantity"
											class="text-center form-control mb-2 col-xs-3"
											placeholder="ادخل الكمية" name="pswd"></form:input></td>

									<td><form:input type="text" path="item"
											class="text-center form-control  mb-2 col-xs-3"
											placeholder="ادخل اسم الصنف" name="email"></form:input></td>


									<td><form:input type="text" path="tradePrice"
											class="text-center form-control  mb-2 col-xs-3"
											placeholder="ادخل السعر تجاري" name="pswd"></form:input></td>

									<td><form:input type="text" path="piecePrice"
											class="text-center form-control  col-xs-3"
											placeholder="ادخل سعر القطعه" name="pswd"></form:input></td>

									<td><form:select path="trader" style="width: 200px;"
											class="text-center form-control  mb-2 col-xs-3">
											<option value="-1">--- اختر المحل ---</option>

											<c:forEach var="tempItem" items="${tradersList}">

												<option value="${tempItem.name}">${tempItem.name}</option>


											</c:forEach>

										</form:select></td>
								</tr>

								<tr style="text-align: center;">
									<td colspan="5"><input type="submit" value="اضف الصنف"
										class="w-100 btn badge-info   
								 font-weight-bold text-center"
										style="width: 100%; height: 50px;"></td>

								</tr>

							</tbody>

						</table>
					</div>
				</div>
			</div>

			</form>





			<!-- TABLE -->
			<div class="row  my-4">
				<div dir='rtl' class=" col-lg-12 col-md-8">
					<div class="table-responsive">
						<table class="table table-bordered table-striped table-dark">
							<thead class="thead-inverse">
								<tr>
									<th>الصنف</th>
									<th>الكمية</th>
									<th>السعر تجاري</th>
									<th>سعر القطعه</th>
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
										<td>${tempItem.tradePrice}</td>
										<td>${tempItem.piecePrice}</td>
										<td>${tempItem.total}</td>
										<td>${tempItem.trader}</td>
										<td>${tempItem.date}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>


					<div class="container">
						<br>
					</div>
				</div>

			</div>


			<div
				style="margin-top: 75px; display: none; padding: 10px; box-shadow: 5px 5px 5px 5px rgba(0, 0, 0, 0.60); direction: rtl"
				class="form container-fluid fixed-top col-lg-3">
				<div class="card bg-dark">

					<div class="card-body bg-dark text-white">

						<h3>اضافة صنف جديد</h3>

						<!-- 
					<c:set value="34" var="num" />
					<c:out value="${num % 2 eq 0 ? 'even': 'odd'}" />
					-->





						<br>
		</form:form>

	</div>
	</div>

	</div>
	</div>
</body>
</html>