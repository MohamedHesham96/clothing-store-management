<%@page import="java.time.LocalDate"%>
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

<script type="text/javascript" src="webjars/jquery/3.4.1/jquery.min.js"></script>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


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
						المصاريف <span style="margin-right: 20px;"
							class="w-50 badge badge-light">${spendingTotal}</span>
					</button>

				</li>
				<li class="bg-dark list-group-item">

					<form method="GET" action="spending">

						<input type="date" name="date" value="${date}" 
							class="w-100 btn badge-info  font-weight-bold text-center">
						
						
						
						 </input> <input type="submit" style="margin-top: 10px;"
							class="w-100 btn badge-light  font-weight-bold text-center"
							value="اذهب لهذا اليوم" />

					</form>
				</li>
			</ul>
		</div>



		<div class="row  my-4">
			<div dir='rtl' class="col-lg-12 col-md-8">
				<div class="table-responsive">
					<table class="table  table-striped table-dark">
						<thead class="thead-inverse">
							<tr>
								<th>المبلغ</th>
								<th>تاريخ السحب</th>
								<th>ملاحظة</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="tempItem" items="${spendings}">

								<tr>
									<td>${tempItem.money}</td>
									<td>${tempItem.date}</td>
									<td>${tempItem.note}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		</div>

	</div>
	
	<div style="direction: rtl" class=" container-fluid col-lg-4 col-md-6">
		<div class="card bg-dark">


			<div class="text-right card-body bg-dark text-white">

				<h3>سحب مبلغ</h3>
				<form:form metho="POST" action="make-spending"
					modelAttribute="spending">

					<label style="margin-top: 10px" class="mb-2 mr-sm-2">المبلغ
						المسحوب :</label>
					<form:input type="text" class="form-control  col-xs-3"
						placeholder="ادخل المبلغ " path="money"></form:input>

					<label style="margin-top: 10px" class="mb-2 mr-sm-2">ملاحظة
						:</label>

					<form:input type="text" class="form-control  col-xs-3"
						placeholder="ادخل ملاحظتك" path="note"></form:input>

					<br>

					<button type="submit" class="btn btn-info form-control 	">
						تم سحب المبلغ</button>
				</form:form>



			</div>
		</div>

	</div>

</body>
</html>