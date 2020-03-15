<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

<title>تسجيل الدخول</title>

<link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body background="images/wall10.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">



	<div class="container ">


		<div class="card bg-secondary text-white"
			style="width: 21rem; margin-left: 400px; margin-top: 100px">

			<!-- 		<div class="card-header text-white font-weight-bold text-center"
				style="color: #c4c4c4">تسجيل الدخول</div>
			 -->


			<form action="login" method="post">

				<ul class="list-group list-group-flush text-center">

					<li class="bg-dark list-group-item">

						<div class="text-center form-group">

							<label class="font-weight-bold">اسم المستخدم</label> <input
								type="text" class="text-center form-control font-weight-bold"
								name="username">

						</div>

					</li>

					<li class="bg-dark list-group-item">

						<div class="form-group text-center">
							<label class="font-weight-bold ">كلمة المرور</label> <input
								name="password" type="password"
								class="text-center form-control font-weight-bold">
						</div>
					</li>

					<li class="bg-dark list-group-item"><input type="submit"
						class="btn badge-info font-weight-bold form-control"
						value="تسجيل الدخول" /></li>
				</ul>

			</form>
		</div>
	</div>
</body>