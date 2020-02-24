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

<body background="images/wall6.jpg"
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
						المشتريات <span style="margin-right: 17px;"
							class="w-50 badge badge-light"> ${incomingTotal}</span>
					</button>
				</li>

				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn badge-info  font-weight-bold text-center">
						المباع <span style="margin-right: 50px;"
							class="w-50 badge badge-light">${soldTotal}</span>
					</button>
				</li>

				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn badge-info  font-weight-bold text-center">
						الموجود <span style="margin-right: 35px;"
							class="w-50 badge badge-light">${warehouseTotal}</span>
					</button>
				</li>

				<li class="bg-dark  list-group-item">

					<button id="showForm" type="button"
						class="btn btn-light btn-lg btn-block">بيع صنف</button>
				</li>
			</ul>
		</div>


		<div class="row  my-4">

			<div dir='rtl' class="col-lg-12 col-md-8">
				<div style="margin-top:" class="table-responsive">
					<table class="table table-bordered table-striped table-dark">
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

		<div
			style="display: none; margin-top: 75px; padding: 10px;
			 box-shadow: 5px 5px 5px 5px rgba(0, 0, 0, 0.60); direction: rtl"
			class="form fixed-top container-fluid col-lg-3">

			<div class="card bg-dark">


				<div class="card-body bg-dark text-white">

					<c:if test="${incoming != null}">
						<h3>بيع صنف</h3>
					</c:if>

					<!-- 
					<c:set value="34" var="num" />
					<c:out value="${num % 2 eq 0 ? 'even': 'odd'}" />
					-->


					<form:form onsubmit="return validate(this)" metho="POST"
						action="add-bill" modelAttribute="bill">
						<label for="pwd2" class="mb-2 mr-sm-2">الصنف:</label>

						<form:select path="id" class="form-control  mb-2 col-xs-3"
							name="testselect">
							<c:forEach var="tempItem" items="${items}">


								<c:if test="${tempItem.quantity > 0}">

									<option value="${tempItem.id}">${tempItem.item}</option>

								</c:if>


							</c:forEach>

						</form:select>

						<label for="pwd2" class="mb-2 mr-sm-2">الكمية:</label>
						<form:input id="quantity" type="text" path="quantity"
							class="form-control mb-2 col-xs-3" placeholder="ادخل الكمية"
							name="pswd"></form:input>

						<label for="pwd2" class="mb-2 mr-sm-2">سعر القطعه:</label>
						<form:input id="piecePrice" type="text" path="piecePrice"
							class="form-control  col-xs-3" placeholder="ادخل سعر القطعه"
							name="pswd"></form:input>


						<label style="margin-top: 8px" for="pwd2" class="mb-2 mr-sm-2">اسم
							العميل:</label>

						<select path="clientId" class="form-control  mb-2 col-xs-3"
							name="clientId">
							<c:forEach var="tempItem" items="${clientsList}">

								<option value="${tempItem.id}">${tempItem.name}</option>


							</c:forEach>

						</select>

						<label for="pwd2" class="mb-2 mr-sm-2">المبلغ المدفوع:</label>

						<input id="payed" type="text" class="form-control  col-xs-3"
							placeholder="ادخل المبلغ المدفوع" name="payed">


						<br>

						<button type="submit" class="btn btn-info form-control">تمت
							العملية</button>
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