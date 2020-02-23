
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:url var="warehouse" value="/warehouse"></c:url>


<c:url var="incoming" value="/incoming">
</c:url>

<c:url var="bill" value="/bill">
</c:url>

<c:url var="spending" value="/spending">

</c:url>


<c:url var="clients" value="/clients"></c:url>


<c:url var="today" value="/today"></c:url>



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
				class="btn bg-light text-dark" style="padding: 10px;"
				href="${today}"> اليوم </a></li>
		
			<li style="margin-left: 5px; margin-right: 5px">
				<h1 class="text-white">|</h1>
			</li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-info text-white" style="padding: 10px;"
				href="${warehouse}"> المخزن </a></li>

			<li style="margin: 5px;" class=""><a
				class="btn bg-info text-white" style="padding: 10px;" href="${bill}">الفواتير</a></li>

			<li style="margin: 5px;" class=""><a
				class="btn bg-info text-white" style="padding: 10px;"
				href="${spending}">المصاريف</a></li>

			<li style="margin: 5px;" class=""><a
				class="btn bg-info text-white" style="padding: 10px;"
				href="${incoming}">المشتريات</a></li>

			<li style="margin: 5px;" class=""><a
				class="btn bg-info text-white" style="padding: 10px;"
				href="${clients}">العملاء</a></li>

			<li style="margin-left: 5px; margin-right: 5px">
				<h1 class="text-white">|</h1>
			</li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-success text-white" style="padding: 10px;"
				href="${clients}">اسبوعي</a></li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-warning text-white" style="padding: 10px;"
				href="${clients}">شهري</a></li>


			<li style="margin: 5px;" class=""><a
				class="btn bg-danger text-white" style="padding: 10px;"
				href="${clients}">سنوي</a></li>

		</ul>

	</div>
</nav>
<br>
<br>
<br>
<br>

</div>


