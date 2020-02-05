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
		<!-- 
<div class="row ">

<div class="card text-white bg-success mb-3  mr-sm-4" style="max-width: 14rem;">
  <div class="card-header">Header</div>
  <div class="card-body">
    <h5 class="card-title">Success card title</h5>
    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
  </div>
</div>
<div class="card text-white bg-danger mb-3 mr-sm-4" style="max-width: 14rem;">
  <div class="card-header">Header</div>
  <div class="card-body">
    <h5 class="card-title">Danger card title</h5>
    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
  </div>
</div>
<div class="card text-white bg-warning mb-3 mr-sm-4" style="max-width: 14rem;">
  <div class="card-header">Header</div>
  <div class="card-body">
    <h5 class="card-title">Warning card title</h5>
    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
  </div>
</div>
<div class="card text-white bg-info mb-3 mr-sm-4" style="max-width: 14rem;">
  <div class="card-header">Header</div>
  <div class="card-body">
    <h5 class="card-title">Info card title</h5>
    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
  </div>
</div>

</div>
 -->

		<div class="row  my-4">
			<div dir='rtl' class="col-lg-12 col-md-8">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead class="thead-inverse">
							<tr>
								<th>الصنف</th>
								<th>الكمية</th>
								<th>السعر تجاري</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="tempItem" items="${items}">

								<tr>
									<td>${tempItem.item}</td>
									<td>${tempItem.quantity}</td>
									<td>${tempItem.tradePrice}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
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

					<form:form metho="POST" action="add-bill" modelAttribute="bill">

						<label for="email2" class="mb-2 mr-sm-2"> الصنف:</label>
						<form:input type="text" path="item"
							class="form-control  mb-2 col-xs-3" placeholder="ادخل اسم الصنف"
							name="email"></form:input>

						<label for="pwd2" class="mb-2 mr-sm-2">الكمية:</label>
						<form:input type="text" path="quantity"
							class="form-control mb-2 col-xs-3" placeholder="ادخل الكمية"
							name="pswd"></form:input>

						<label for="pwd2" class="mb-2 mr-sm-2">سعر القطعه:</label>
						<form:input type="text" path="piecePrice"
							class="form-control  col-xs-3" placeholder="ادخل سعر القطعه"
							name="pswd"></form:input>

						<br>
						<button type="submit" class="btn btn-info form-control 	">

						</button>
					</form:form>

						<!--
	<c:url var="showQuestion" value="/user/showq">
								<c:param name="questionId" value="hello"></c:param>
							</c:url>  -->
				

				</div>
			</div>

		</div>
</body>
</html>