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

<title>الفواتير</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">

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
						class="w-100 btn bg-info text-white  font-weight-bold text-center">
						اجمالي الربح <span style="margin-right: 20px;"
							class="w-50 badge badge-light text-center"> ${gainTotal}
							جنيه</span>
					</button>
				</li>
				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn bg-info text-white  font-weight-bold text-center">
						المصاريف <span style="margin-right: 33px;"
							class="w-50 badge badge-light text-center">${spendingTotal}
							جنيه</span>
					</button>
				</li>
				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn bg-success  font-weight-bold text-center text-center">
						صافي الربح <span style="margin-right: 25px;"
							class="w-50 badge badge-light text-center">${total} جنيه</span>
					</button>
				</li>

				<c:if test="${bank != null}">
					<li class="bg-dark list-group-item">

						<button dir="rtl" style="text-align: right;" type="button"
							class="w-100 btn bg-warning  font-weight-bold text-center text-center">
							داخل الخزنة <span style="margin-right: 25px;"
								class="w-50 badge badge-light text-center">${bank} جنيه</span>
						</button>
					</li>
				</c:if>

				<li class="bg-dark list-group-item">

					<form method="GET" action="bill">

						<input type="date" name="date" value="${date}"
							class="w-100 btn badge-info  font-weight-bold text-center">

						<input type="submit" style="margin-top: 10px;"
							class="w-100 btn badge-light  font-weight-bold text-center"
							value="اذهب لهذا اليوم" />

					</form>
				</li>

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
								<th>الصنف</th>
								<th>الكمية</th>
								<th>السعر تجاري</th>
								<th>سعر القطعه</th>
								<th>المدفوع</th>
<!-- 								<th>المتبقي</th> -->
								<th>صافي الربح</th>
								<th>اسم العميل</th>
								<th>العملية</th>
							</tr>
						</thead>

						<tbody class="font-weight-bold">

							<c:forEach var="tempItem" items="${items}">
								<tr>
									<td class="col-2 pt-2">${tempItem.item}</td>
									<td class="col-1 pt-2">${tempItem.quantity}</td>
									<td class="col-1 pt-2">${tempItem.tradePrice}</td>
									<td class="col-1 pt-2">${tempItem.piecePrice}</td>
									<td class="col-1 pt-2">${tempItem.payed}</td>
<%-- 									<td class="col-1 pt-2">${tempItem.quantity * tempItem.piecePrice - tempItem.payed}</td> --%>
									<td class="col-1 pt-2">${tempItem.gain}</td>
									<td class="col-2 pt-2">${tempItem.client.name}</td>
									<td class="col-1"><a style="font-size: 14px;"
										class="btn btn-sm btn-danger text-wight
										font-weight-bold"
										onclick="return confirm('هل انت متأكد من حذف هذا الصنف ؟')"
										href="delete-bill-from-bills?id=${tempItem.id}">حذف</a></td>
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