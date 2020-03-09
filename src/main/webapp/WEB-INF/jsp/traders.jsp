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

<title>التجار</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript" src="webjars/jquery/3.4.1/jquery.min.js"></script>


<script type="text/javascript">
	function myFunc(tIDVal, tNameVal, tPayedVal, tRemainingVal) {

		var idVar = document.getElementById("cIDID");
		var nameVar = document.getElementById("tNameID");
		var payedVar = document.getElementById("tPayedID");
		var remainingVar = document.getElementById("tRemainingID");
		var submitVar = document.getElementById("tSubmitID");

		idVar.value = tIDVal;
		nameVar.value = tNameVal;
		payedVar.value = tPayedVal;
		remainingVar.value = tRemainingVal;
		submitVar.value = 'تحديث بيانات التاجر';

	}
</script>


</head>

<body background="images/wall0.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover; width: 100%; height: 100%;">

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
						المدفوع <span style="margin-right: 20px;"
							class="w-50 badge badge-light">${payedTotal} جنيه </span>
					</button>
				</li>

				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn bg-info text-white  font-weight-bold text-center">
						الباقي <span style="margin-right: 30px;"
							class="w-50 badge badge-light">${remainingTotal} جنيه</span>
					</button>
				</li>


				<c:url var="withoutRemaining" value="/traders">

					<c:param name="remainingBool" value="false"></c:param>

				</c:url>

				<c:url var="withRemaining" value="/traders">

					<c:param name="remainingBool" value="true"></c:param>

				</c:url>



				<li class="bg-dark list-group-item"><a
					class="w-100 btn badge-success font-weight-bold text-center
							text-white"
					href="${withoutRemaining}"> منتهي </a></li>



				<li class="bg-dark list-group-item"><a
					class="w-100 btn badge-danger font-weight-bold text-center
							text-white"
					href="${withRemaining}"> الغير منتهي </a></li>





				<li class="bg-dark list-group-item"><form:form metho="POST"
						action="add-new-trader" modelAttribute="theTrader">

						<form:hidden id="cIDID" path="id" />

						<form:hidden id="tPayedID" path="payed" />

						<form:hidden id="tRemainingID" path="remaining" />

						<form:input id="tNameID" type="text"
							class="text-center form-control  col-xs-3"
							placeholder="ادخل اسم التاجر " path="name"></form:input>

						<input id="tSubmitID" style="margin-top: 10px" type="submit"
							value="أضافة تاجر جديد"
							class="w-100 btn badge-info font-weight-bold text-center
							onclick="
							this.disabled=true;this.parentNode.submit();">

					</form:form></li>
			</ul>
		</div>



		<!-- TABLE  -->
		<div class="row  my-4">
			<div dir='rtl' class="col-lg-12 col-md-8">
				<div class="table-responsive">
					<table class="table table-bordered table-striped table-dark">
						<thead class="thead-inverse">
							<tr>
								<th>اسم التاجر</th>
								<th>المدفوع</th>
								<th>الباقي</th>


							</tr>
						</thead>
						<tbody>
							<c:forEach var="tempItem" items="${tradersList}">
								<c:url var="traderLink" value="/traderProfile">
									<c:param name="traderId" value="${tempItem.id}"></c:param>
								</c:url>
								<tr>
									<td><a class="text-white" href="${traderLink}">${tempItem.name}</a></td>
									<td>${tempItem.payed}</td>
									<td>${tempItem.remaining}</td>
									<td style="width: 160px">

										<button style="height: 30px; font-size: 14px;" 
											class="btn btn-success text-wight
											font-weight-bold"
											onclick="myFunc('${tempItem.id}','${tempItem.name}', 
															'${tempItem.payed}', '${tempItem.remaining}');">تعديل</button>
															
										<a style="height: 30px; font-size: 14px;" 
										class="btn btn-danger text-wight
										font-weight-bold"
										href="deleteTrader?id=${tempItem.id}">حذف</a>



									</td>
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