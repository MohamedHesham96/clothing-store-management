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


<script type="text/javascript" src="/webjars/jquery/3.4.1/jquery.min.js">
	function myFunc() {

		var submitBtn = getElementById("submitBtn");

		if (submitBtn.disabled === false) {

			submitBtn.disabled = true;

		} else {

			submitBtn.disabled = false;

		}
	}
</script>

</head>
<body onunload="myFunc();" background="images/wall5.jpg"
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
						class="w-100 btn bg-info text-white font-weight-bold text-center">
						المشتريات <span style="margin-right: 17px;"
							class="w-50 badge badge-light"> ${incomingTotal} جنيه</span>
					</button>
				</li>
				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn bg-info text-white  font-weight-bold text-center">
						الموجود <span style="margin-right: 35px;"
							class="w-50 badge badge-light">${warehouseTotal} جنيه</span>
					</button>
				</li>
				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn bg-success text-white font-weight-bold text-center">
						المباع <span style="margin-right: 50px;"
							class="w-50 badge badge-light">${soldTotal} جنيه</span>
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

			<div class="row">
				<div dir='rtl' class="col-lg-12 col-md-8">

					<div class="table-responsive">

						<table class="table table-striped table-dark table-sm">
							<thead class="thead-inverse bg-secondary shadow"
								style="position: -webkit-sticky; position: sticky; top: 0; z-index: 2;">
								<tr>
									<th>الكمية</th>
									<th>الصنف</th>
									<th>السعر تجاري</th>
									<th>سعر القطعة</th>
									<th>المدفوع</th>
									<th>المحل</th>
								</tr>
							</thead>

							<tbody>

								<tr>
									<td class="col-1"><form:input path="quantity"
											class="font-weight-bold text-center form-control mb-2 mt-2 col-xs-3"
											placeholder="الكمية"></form:input></td>

									<td class="col-3"><form:input path="item"
											class="font-weight-bold text-center form-control mb-2 mt-2 col-xs-3"
											placeholder="اسم الصنف"></form:input></td>

									<td class="col-1"><form:input path="tradePrice"
											class="font-weight-bold text-center form-control mb-2 mt-2 col-xs-3"
											placeholder="السعر تجاري"></form:input></td>

									<td class="col-1"><form:input path="piecePrice"
											class="font-weight-bold text-center form-control mb-2 mt-2 col-xs-3"
											placeholder="سعر القطعه"></form:input></td>

									<td class="col-1"><form:input path="payed"
											class="font-weight-bold text-center form-control mb-2  mt-2 col-xs-3"
											placeholder="المدفوع"></form:input></td>

									<td class="col-2"><form:select path="trader"
											class="font-weight-bold text-center form-control mb-2  mt-2 col-xs-3">
											<option value="غير محدد">غير محدد</option>

											<c:forEach var="tempItem" items="${tradersList}">

												<option value="${tempItem.name}">${tempItem.name}</option>

											</c:forEach>

										</form:select></td>
								</tr>

								<tr style="text-align: center;">
									<td colspan="6"><input type="submit" value="اضف الصنف"
										class="btn badge-info   
								 font-weight-bold text-center"
										style="width: 100%;"
										onclick="this.parentNode.submit(); this.disabled=true; ">
									</td>

								</tr>

							</tbody>

						</table>
					</div>
				</div>
			</div>

		</form:form>





		<!-- TABLE -->
		<div class="row ">
			<div dir='rtl' class=" col-lg-12 col-md-8">
				<div class="shadow"
					style="position: relative; height: 340px; overflow: auto;">
					<table
						class="table  table-bordered table-striped table-dark table-sm">

						<thead class="thead-inverse bg-secondary shadow"
							style="position: -webkit-sticky; position: sticky; top: 0; z-index: 2;">

							<tr>
								<th>الصنف</th>
								<th>الكمية</th>
								<th>س.تجاري</th>
								<th>س.القطعه</th>
								<th>الاجمالي</th>
								<th>المدفوع</th>
								<th>المحل</th>
								<th>التاريخ</th>
								<th>العملية</th>
							</tr>

						</thead>
						<tbody class="font-weight-bold ">
							<c:forEach var="tempItem" items="${incomings}">

								<tr>
									<td class="col-3 pt-2">${tempItem.item}</td>
									<td class="col-1 pt-2">${tempItem.quantity}</td>
									<td class="col-1 pt-2">${tempItem.tradePrice}</td>
									<td class="col-1 pt-2">${tempItem.piecePrice}</td>
									<td class="col-1 pt-2">${tempItem.total}</td>
									<td class="col-1 pt-2">${tempItem.payed}</td>
									<td class="col-2 pt-2">${tempItem.trader}</td>
									<td class="col-1 pt-2">${tempItem.date}</td>

									<td class="col-1"><c:if
											test="${tempItem.quantity == tempItem.currentQuantity}">

											<a style="font-size: 14px;"
												class="btn btn-sm btn-danger text-wight
										font-weight-bold btn-sm"
												onclick="return confirm('هل انت متأكد من حذف هذا الصنف ؟')"
												href="delete-incoming?id=${tempItem.id}">حذف</a>

										</c:if></td>
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