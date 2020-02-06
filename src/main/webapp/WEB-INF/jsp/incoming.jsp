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

</head>
<body background="images/wallpaper.jpg" style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>
	

	<div style="text-align: right;" class="container">

		<div class="row  my-4">
			<div dir='rtl' class=" col-lg-12 col-md-8">
				<div class="table-responsive">
					<table class="table  table-striped table-dark">
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
									<td>${tempItem.tradePrice}</td>
									<td>${tempItem.piecePrice}</td>
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


				<div class="container">
					<br>
				</div>
			</div>

		</div>


		<div style="direction: rtl" class=" container-fluid col-lg-4 col-md-6">
			<div class="card">

				<div class="card-body">

					<c:if test="${incoming != null}">
						<h3>اضافة صنف جديد</h3>
					</c:if>

					<!-- 
					<c:set value="34" var="num" />
					<c:out value="${num % 2 eq 0 ? 'even': 'odd'}" />
					-->
					
					<form:form metho="POST" action="add-incoming"
						modelAttribute="incoming">


						<label for="email2" class="mb-2 mr-sm-2"> الصنف:</label>
						<form:input type="text" path="item"
							class="form-control  mb-2 col-xs-3" placeholder="ادخل اسم الصنف"
							name="email"></form:input>

						<label for="pwd2" class="mb-2 mr-sm-2">الكمية:</label>
						<form:input type="text" path="quantity"
							class="form-control mb-2 col-xs-3" placeholder="ادخل الكمية"
							name="pswd"></form:input>

						<label for="pwd2" class="mb-2 mr-sm-2">السعر تجاري:</label>
						<form:input type="text" path="tradePrice"
							class="form-control  mb-2 col-xs-3"
							placeholder="ادخل السعر تجاري" name="pswd"></form:input>

						<label for="pwd2" class="mb-2 mr-sm-2">سعر القطعه:</label>
						<form:input type="text" path="piecePrice"
							class="form-control  col-xs-3" placeholder="ادخل سعر القطعه"
							name="pswd"></form:input>

						<label for="pwd2" class="mb-2 mr-sm-2">المحل:</label>
						<form:input type="text" path="store"
							class="form-control  col-xs-3" placeholder="ادخل اسم المحل "
							name="pswd"></form:input>
						<br>
						<button type="submit" class="btn btn-info form-control 	">اضف
							الصنف</button>

					</form:form>

				</div>
			</div>

		</div>
		</div>
</body>
</html>