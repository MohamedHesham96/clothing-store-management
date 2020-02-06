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

</head>
<body background="images/wall3.jpg" style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>

	<div  style="text-align: right;" class="container ">
	
		<div  class="row  my-4">
			<div dir='rtl' class="col-lg-12 col-md-8">
				<div class="table-responsive">
					<table class="table  table-striped table-dark">
						<thead class="thead-inverse">
							<tr>
								<th>الصنف</th>
								<th>الكمية</th>
								<th>السعر تجاري</th>
								<th>المحل</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="tempItem" items="${items}">

								<tr>
									<td>${tempItem.item}</td>
									<td>${tempItem.quantity}</td>
									<td>${tempItem.tradePrice}</td>
									<td>${tempItem.store}</td>
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
						<h3>بيع صنف</h3>
					</c:if>

					<!-- 
					<c:set value="34" var="num" />
					<c:out value="${num % 2 eq 0 ? 'even': 'odd'}" />
					-->

					<form:form metho="POST" action="add-bill" modelAttribute="bill">
						<label for="pwd2" class="mb-2 mr-sm-2">الصنف:</label>

						<form:select path="id" class="form-control  mb-2 col-xs-3"
							name="testselect">
							<c:forEach var="tempItem" items="${items}">

								<option value="${tempItem.id}">${tempItem.item}</option>

							</c:forEach>

						</form:select>

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
						تمت العملية
						</button>
					</form:form>

					<!--
	<c:url var="showQuestion" value="/user/showq">
								<c:param name="questionId" value="hello"></c:param>
							</c:url>  -->


				</div>
			</div>

		</div>
		</div>
</body>
</html>