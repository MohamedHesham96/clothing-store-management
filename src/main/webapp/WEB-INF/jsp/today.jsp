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

<title>اليوم | <%=LocalDate.now().toString()%></title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">

<script type="text/javascript">
	function myFunc() {

		var submitBtn = getElementById("submitBtn");

		submitBtn.disabled = false;
	}

	function showToggle() {

		var x = document.getElementById("gainTotalDiv");
		if (x.style.display === "none") {
			x.style.display = "block";
		} else {
			x.style.display = "none";
		}
	}
</script>
</head>
<body onunload="myFunc();" background="images/wall6.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>



	<div style="text-align: right;" class="container ">

		<div onclick="showToggle();"
			class=" col-xs-6 card bg-secondary text-white"
			style="width: 18rem; margin-left: 820px;">
			<div class="card-header text-white font-weight-bold text-center"
				style="color: #c4c4c4">الحسابات</div>
			<ul id="gainTotalDiv" style="display: none;"
				class="list-group list-group-flush">
				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn bg-info text-white font-weight-bold text-center">
						اجمالي الربح <span style="margin-right: 20px;"
							class="w-50 badge badge-light text-center"> ${gainTotal}
							جنيه</span>
					</button>
				</li>
				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn bg-info text-white   font-weight-bold text-center">
						المصاريف <span style="margin-right: 33px;"
							class="w-50 badge badge-light text-center">${spendingTotal}
							جنيه</span>
					</button>
				</li>
				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn bg-success  font-weight-bold text-center text-white">
						صافي الربح <span style="margin-right: 25px;"
							class="w-50 badge badge-light text-center "> ${total} جنيه</span>
					</button>
				</li>

			</ul>
		</div>

		<form action="add-bills-list" method="POST">

			<div class="row mt-4">
				<div dir='rtl' class="col-lg-12 col-md-8">
					<div class="table-responsive">
						<table class=" table table-striped table-dark ">
							<thead class="thead-inverse table-sm">
								<tr>
									<th>الكمية</th>
									<th>الصنف</th>
									<th>السعر</th>
									<th>المبلغ المدفوع</th>
									<th>العميل</th>
								</tr>
							</thead>

							<tbody>
								<%
									for (int i = 0; i < 3; i++) {
								%>
								<tr>

									<td class="col-1"><input name="quantity"
										class="form-control text-center  font-weight-bold"
										style="width: 100%; height: 100%; font-size: 18px;"></td>

									<td class="col-2"><select name="itemId"
										class=" form-control text-center font-weight-bold"
										name="testselect">
											<option value="-1">--- اختر الصنف ---</option>
											<c:forEach var="tempItem" items="${warehouseItems}">

												<c:if test="${tempItem.quantity > 0}">

													<option value="${tempItem.id}">${tempItem.item}
														(${tempItem.currentQuantity})</option>

												</c:if>


											</c:forEach>

									</select></td>

									<td class="col-1"><input name="piecePrice"
										class="text-center form-control font-weight-bold"
										style="width: 100%; height: 100%; font-size: 18px;"></td>


									<td class="col-1"><input name="payed"
										class="text-center form-control font-weight-bold"
										style="width: 100%; height: 100%; font-size: 18px;"></td>

									<td class="col-2"><select name="clientId"
										class=" form-control text-center font-weight-bold"
										name="clientId">
											<option value="-1">غير محدد</option>

											<c:forEach var="tempItem" items="${clientsList}">

												<option value="${tempItem.id}">${tempItem.name}</option>

											</c:forEach>

									</select></td>


								</tr>
								<%
									}
								%>
								<tr style="text-align: center;">
									<td colspan="5"><input id="submitBtn" type="submit"
										value="أضافة للفواتير"
										class="btn badge-info   
								 font-weight-bold text-center"
										onclick="this.parentNode.submit(); this.disabled=true; "
										style="width: 100%;"></td>

								</tr>

							</tbody>

						</table>
					</div>
				</div>
			</div>

		</form>

		<br> <br> <br> <br> <br>
		<div class="row mt-3">
			<div dir='rtl' class="col-lg-12 col-md-8">
				<div class="shadow mb-4"
					style="position: relative; height: 530px; overflow: auto;">

					<table
						class="table table-bordered table-striped table-dark table-sm">

						<thead class="thead-inverse bg-secondary table-bordered shadow"
							style="position: -webkit-sticky; position: sticky; top: 0; z-index: 2;">

							<tr>
								<th>الصنف</th>
								<th>الكيمة</th>
								<th>السعر تجاري</th>
								<th>سعر القطعه</th>
								<th>اسم العميل</th>
								<th>العملية</th>

							</tr>
						</thead>

						<tbody class="font-weight-bold">

							<c:forEach var="tempItem" items="${items}">
								<tr>
									<td class="co-1 pt-2">${tempItem.item}</td>
									<td class="co-1 pt-2">${tempItem.quantity}</td>
									<td class="co-1 pt-2">${tempItem.tradePrice}</td>
									<td class="co-1 pt-2">${tempItem.piecePrice}</td>
									<td class="co-1 pt-2">${tempItem.client.name}</td>

									<td class="co-1"><a style="height: 30px; font-size: 14px;"
										class="btn btn-danger text-wight
										font-weight-bold"
										onclick="return confirm('هل انت متأكد من حذف هذا الصنف ؟')"
										href="delete-bill?id=${tempItem.id}">حذف</a></td>
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