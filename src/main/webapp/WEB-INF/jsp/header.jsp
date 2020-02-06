
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:url var="warehouse" value="/warehouse"></c:url>
<c:url var="incoming" value="/incoming"></c:url>
<c:url var="bill" value="/bill"></c:url>
<c:url var="spending" value="/spending"></c:url>
<c:url var="clients" value="/clients"></c:url>




<nav style="box-shadow: 0 6px 10px -1px rgba(0, 0, 0, 0.9);" dir="rtl"
	class="navbar navbar-expand-lg navbar-dark  bg-dark fixed-top scrolling-navbar">
	<a class="navbar-brand" href="#">مازن العليمي</a>


	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">

			<li style="margin: 5px;" class="btn bg-info active mb-2 "><a
				class="nav-link" href="${warehouse}">المخزن </a></li>

			<li style="margin: 5px;" class="btn bg-info active mb-2"><a
				class="nav-link" href="${incoming}">المشتريات</a></li>

			<li style="margin: 5px;" class="btn bg-info active mb-2"><a
				class="nav-link" href="${bill}">الفواتير</a></li>

			<li style="margin: 5px;" class="btn bg-info active mb-2 "><a
				class="nav-link" href="${spending}">المصاريف</a></li>
			
			<li style="margin: 5px;" class="btn bg-info active mb-2 "><a
				class="nav-link" href="${clients}">العملاء</a></li>

		</ul>

	</div>
</nav>
<br>
<br>
<br>
<br>

</div>


