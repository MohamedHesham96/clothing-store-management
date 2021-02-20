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


</head>
<body background="images/cal1.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>

	<div style="text-align: right;" class="col-12">

		<div class="card bg-secondary text-white float-right"
			style="width: 18rem;">

			<div class="card-header text-white font-weight-bold text-center"
				style="color: #c4c4c4">الحسابات</div>

			<ul class="list-group list-group-flush">

				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn badge-info  font-weight-bold text-center">
						المصاريف <span style="margin-right: 20px;"
							class="w-50 badge badge-light">${spendingTotal} جنيه</span>
					</button>

				</li>

				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn badge-warning font-weight-bold text-center">
						داخل الخزنة <span style="margin-right: 15px;"
							class="w-50 badge badge-light">${bank} جنيه</span>
					</button>

				</li>



				<li class="bg-dark list-group-item">

					<form method="GET" action="spending">

						<input type="date" name="date" value="${date}"
							class=" btn badge-info  font-weight-bold text-center form-control  col-xs-3 ">

						<input type="submit" style="margin-top: 10px;"
							class=" btn badge-light font-weight-bold text-center form-control  col-xs-3"
							value="اذهب لهذا اليوم" />

					</form>
				</li>


				<li class="bg-dark list-group-item"><form:form id="formId"
						method="POST" action="make-spending" modelAttribute="spending">

						<form:input type="text" class="font-weight-bold text-center form-control col-xs-3"
							placeholder="ادخل المبلغ" name="money" path="money"></form:input>

						<form:input style="margin-top: 10px" type="text"
							class="font-weight-bold text-center form-control  col-xs-3"
							placeholder="ادخل ملاحظتك" path="note"></form:input>

						<input name="date" value="2020-01-01" type="hidden" />

						<input style="margin-top: 10px" type="submit" value="سحب المبلغ"
							onclick="this.disabled=true; this.parentNode.submit();"
							class="btn badge-info form-control font-weight-bold">


					</form:form></li>
			</ul>
		</div>



		<div class="row">
			<div dir='rtl' class="col-lg-12 col-md-8">
				<div class="shadow"
					style="position: relative; height: 500px; overflow: auto;">
					<table
						class="table table-bordered table-striped table-dark table-sm">

						<thead class="thead-inverse bg-secondary table-bordered shadow"
							style="position: -webkit-sticky; position: sticky; top: 0; z-index: 2;">
							<tr>
								<th>المبلغ</th>
								<th>تاريخ السحب</th>
								<th>ملاحظة</th>
								<th>العملية</th>

							</tr>
						</thead>
						
						<tbody class="font-weight-bold">
						
							<c:forEach var="tempItem" items="${spendings}">

								<tr>
									<td class="col-1 pt-2">${tempItem.money}</td>
									<td class="col-1 pt-2">${tempItem.date}</td>
									<td class="col-2 pt-2">${tempItem.note}</td>

									<td class="col-1"><a style="font-size: 14px;"
										class=" btn btn-sm btn-danger text-wight
										font-weight-bold"
										onclick="return confirm('هل انت متأكد من حذف هذا المبلغ؟')"
										href="delete-spending?id=${tempItem.id}">حذف</a></td>

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