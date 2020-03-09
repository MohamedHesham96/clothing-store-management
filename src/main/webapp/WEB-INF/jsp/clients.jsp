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

<title>العملاء</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript" src="webjars/jquery/3.4.1/jquery.min.js"></script>


<script type="text/javascript">
	function myFunc(cIDVal, cNameVal, cPhoneVal, cDraweeVal) {

		var idVar = document.getElementById("cIDID");
		var nameVar = document.getElementById("cNameID");
		var draweeVar = document.getElementById("cDraweeID");
		var phoneVar = document.getElementById("cPhoneID");
		var submitVar = document.getElementById("cSubmitID");

		idVar.value = cIDVal;
		nameVar.value = cNameVal;
		draweeVar.value = cDraweeVal;
		phoneVar.value = cPhoneVal;
		submitVar.value = 'تحديث بيانات العميل';

	}
</script>

</head>

<body background="images/wall8.jpg"
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
						class="w-100 btn bg-info text-white  font-weight-bold text-center">
						اجمالي الدين <span style="margin-right: 20px;"
							class="w-50 badge badge-light">${draweeTotal} جنيه</span>
					</button>
				</li>

				<li class="bg-dark list-group-item"><form:form metho="POST"
						action="add-new-client" modelAttribute="theClient">

						<form:hidden id="cIDID" path="id" />

						<form:hidden id="cDraweeID" path="drawee" />

						<form:input id="cNameID" type="text"
							class="text-center form-control  col-xs-3"
							placeholder="ادخل اسم العميل " path="name"></form:input>

						<form:input id="cPhoneID" cssStyle="margin-top: 10px" type="text"
							class="text-center form-control  col-xs-3"
							placeholder="ادخل تيليفون العميل" path="phone"></form:input>


						<input id="cSubmitID" style="margin-top: 10px" type="submit"
							value="أضافة عميل جديد"
							class="btn btn-info form-control font-weight-bold"
							onclick="this.disabled=true; this.parentNode.submit();">
					</form:form></li>
			</ul>
		</div>




		<div class="row  my-4">
			<div dir='rtl' class="col-lg-12 col-md-8">
				<div class="table-responsive">
					<table class="table table-bordered table-striped table-dark">
						<thead class="thead-inverse">
							<tr>
								<th>اسم العميل</th>
								<th>التيليفون</th>
								<th>عليه</th>
								<th></th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="tempItem" items="${clientsList}">
								<c:url var="clientLink" value="/clientProfile">
									<c:param name="clientId" value="${tempItem.id}"></c:param>
								</c:url>
								<tr>
									<td><a class="text-white" href="${clientLink}">${tempItem.name}</a></td>
									<td>${tempItem.phone}</td>
									<td>${tempItem.drawee}</td>
									<td style="width: 160px">

										<button style="height: 30px; font-size: 14px;"
											class="btn btn-success text-wight
											font-weight-bold"
											onclick="myFunc('${tempItem.id}','${tempItem.name}', '${tempItem.phone}', '${tempItem.drawee}');">تعديل</button>
										<a style="height: 30px; font-size: 14px;"
										class="btn btn-danger  text-wight
											font-weight-bold"
										onclick="return confirm('هل انت متأكد من حذف هذا العميل ؟?')"
										href="deleteClient?id=${tempItem.id}">حذف</a>
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