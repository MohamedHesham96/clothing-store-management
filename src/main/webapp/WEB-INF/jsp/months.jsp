<%@page import="com.hcoder.clothingstoremanagement.entity.Result"%>
<%@page import="org.apache.taglibs.standard.tag.common.xml.IfTag"%>
<%@page import="com.hcoder.clothingstoremanagement.entity.Incoming"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" import="java.util.*,java.lang.*"%>

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


		<!-- 
		<div class="card bg-secondary text-white"
			style="width: 18rem; margin-left: 820px;">
			<div class="card-header text-white font-weight-bold text-center"
				style="color: #c4c4c4">الحسابات</div>
			<ul class="list-group list-group-flush">
				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn bg-info text-white font-weight-bold text-center">
						المشتريات <span style="margin-right: 17px;"
							class="w-50 badge badge-light">0 جنيه</span>
					</button>
				</li>

				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn bg-info text-white font-weight-bold text-center">
						المباع <span style="margin-right: 50px;"
							class="w-50 badge badge-light">0 جنيه</span>
					</button>
				</li>

				<li class="bg-dark list-group-item">

					<button dir="rtl" style="text-align: right;" type="button"
						class="w-100 btn bg-info text-white  font-weight-bold text-center">
						الموجود <span style="margin-right: 35px;"
							class="w-50 badge badge-light">0 جنيه</span>
					</button>
				</li>



			</ul>
		</div>
 -->
		<%
			List<Result> list = (List<Result>) request.getAttribute("monthsList");
			int listSize = list.size();
			boolean firstTime = true;

			List<Result> list2020 = new ArrayList<Result>();
			List<Result> list2019 = new ArrayList<Result>();

			int[] billsTotals = new int[2];
			int[] spendingsTotals = new int[2];

			for (int i = 0; i < listSize; i++) {

				if (list.get(i).getYear().intValue() == 2020) {

					list2020.add(list.get(i));

					if (list.get(i).getAmount() != null)
						billsTotals[0] += list.get(i).getAmount().intValue();

					if (list.get(i).getSpending() != null)
						spendingsTotals[0] += list.get(i).getSpending().intValue();
				}

				if (list.get(i).getYear().intValue() == 2019) {

					list2019.add(list.get(i));

					if (list.get(i).getAmount() != null)
						billsTotals[1] += list.get(i).getAmount().intValue();

					if (list.get(i).getSpending() != null)
						spendingsTotals[1] += list.get(i).getSpending().intValue();

				}

			}

			List<String> monthsNames = new ArrayList<String>();

			monthsNames.add("يناير");
			monthsNames.add("فبراير");
			monthsNames.add("مارس");
			monthsNames.add("أبريل");
			monthsNames.add("مايو");
			monthsNames.add("يونيو");
			monthsNames.add("أغسطس");
			monthsNames.add("سبتمبر");
			monthsNames.add("نوفمبر");
			monthsNames.add("ديسمبر");
		%>


		<div class="row  my-4">
			<div dir='rtl' class="col-lg-12 col-md-8">

				<h2>2020</h2>

				<div style="margin-top:" class="table-responsive">
					<table class="table table-bordered table-striped table-dark">
						<thead class="thead-inverse">

							<tr>
								<th>الشهر</th>
								<th>الربح</th>
								<th>المصاريف</th>
								<th>صافي الربح</th>

							</tr>
						</thead>
						<tbody>


							<c:forEach var="temp" items="<%=list2020%>">


								<tr>

									<td>${temp.month.intValue()}</td>
									<td>${temp.amount.intValue()}</td>
									<td>${temp.spending.intValue()}</td>
									<td>${temp.amount.intValue()- temp.spending.intValue()}</td>

								</tr>



							</c:forEach>


							<tr class=" bg-success text-light">

								<td><h5 class="font-weight-bold">اجمالي السنة</h5></td>
								<td><h5 class="font-weight-bold"><%=billsTotals[0]%></h5></td>
								<td><h5 class="font-weight-bold"><%=spendingsTotals[0]%></h5></td>
								<td><h5 class="font-weight-bold"><%=billsTotals[0] - spendingsTotals[0]%></h5></td>

							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>


		<div class="row  my-4">

			<div dir='rtl' class="col-lg-12 col-md-8">
				<h2>2019</h2>
				<div style="margin-top:" class="table-responsive">
					<table class="table table-bordered table-striped table-dark">
						<thead class="thead-inverse">
							<tr>
								<th>الشهر</th>
								<th>الربح</th>
								<th>المصاريف</th>
								<th>صافي الربح</th>

							</tr>
						</thead>
						<tbody>


							<c:forEach var="temp" items="<%=list2019%>">


								<tr>

									<td>${temp.month.intValue()}</td>
									<td>${temp.amount.intValue()}</td>
									<td>${temp.spending.intValue()}</td>
									<td>${temp.amount.intValue()- temp.spending.intValue()}</td>

								</tr>

							</c:forEach>


							<tr class=" bg-success text-light">

								<td><h5 class="font-weight-bold">اجمالي السنة</h5></td>
								<td><h5 class="font-weight-bold"><%=billsTotals[1]%></h5></td>
								<td><h5 class="font-weight-bold"><%=spendingsTotals[1]%></h5></td>
								<td><h5 class="font-weight-bold"><%=billsTotals[1] - spendingsTotals[1]%></h5></td>

							</tr>


						</tbody>
					</table>
				</div>
			</div>

		</div>






	</div>
</body>
</html>