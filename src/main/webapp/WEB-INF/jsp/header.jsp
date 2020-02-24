

<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:url var="warehouse" value="/warehouse"></c:url>

<c:url var="incoming" value="/incoming"></c:url>

<c:url var="bill" value="/bill"></c:url>

<c:url var="spending" value="/spending"></c:url>

<c:url var="spending" value="/spending"></c:url>

<c:url var="clients" value="/clients"></c:url>

<c:url var="traders" value="/traders"></c:url>

<c:url var="months" value="/months"></c:url>

<c:url var="today" value="/today"></c:url>


<c:url var="logout" value="/logout"></c:url>





<%
	if (session.getAttribute("username") == null) {
%>

<jsp:forward page="login.jsp" />

<%
	}
%>



<script>
	$(document).ready(function() {
		$('#showForm').click(function() {
			$('.form').toggle("slide");
		});
	});
</script>


<nav style="box-shadow: 0 6px 10px -1px rgba(0, 0, 0, 0.9);" dir="rtl"
	class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top scrolling-navbar">
	<a class="navbar-brand" href="#">مازن العليمي</a>


	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">

			<li style="margin: 5px;" class=""><a
				class="btn bg-light text-dark font-weight-bold"
				style="padding: 10px;" href="${today}">اليوم | <%=LocalDate.now().toString()%>
			</a></li>


			<li style="margin-left: 5px; margin-right: 5px">
				<h1 class="text-white ">|</h1>
			</li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-info text-white font-weight-bold"
				style="padding: 10px; width: 100px" href="${warehouse}"> المخزن
			</a></li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-info text-white font-weight-bold"
				style="padding: 10px; width: 100px" href="${bill}">الفواتير</a></li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-info text-white font-weight-bold"
				style="padding: 10px; width: 100px" href="${spending}">المصاريف</a></li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-info text-white font-weight-bold"
				style="padding: 10px; width: 100px" href="${incoming}">المشتريات</a></li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-info text-white font-weight-bold"
				style="padding: 10px; width: 100px" href="${clients}">العملاء</a></li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-info text-white font-weight-bold"
				style="padding: 10px; width: 100px" href="${traders}">التجار</a></li>

			<li style="margin-left: 5px; margin-right: 5px">
				<h1 class="text-white">|</h1>
			</li>



			<li style="margin: 5px;"><a
				class="btn bg-success text-white font-weight-bold "
				style="padding: 10px; width: 100px" href="${months}">تقرير</a></li>


			<li style="margin-left: 5px; margin-right: 5px">
				<h1 class="text-white">|</h1>
			</li>



			<li style="margin: 5px;"><a
				class="btn bg-danger text-white font-weight-bold "
				style="padding: 10px; width: 100px" href="${logout}">خروج</a></li>




		</ul>

	</div>
</nav>
<br>
<br>
<br>
<br>




