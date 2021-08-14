<%@page import="org.apache.taglibs.standard.tag.common.xml.IfTag" %>
<%@page import="com.hcoder.clothingstoremanagement.entity.Incoming" %>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If" %>
<%@page import="java.lang.ProcessBuilder.Redirect" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>

    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

    <title>${traderData.name}</title>
    <script src="/webjars/jquery/3.4.1/jquery.min.js"></script>

    <link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">

    <script src="/webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>


</head>
<body background="images/wall9.jpg"
      style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

<%@ include file="header.jsp" %>

<div style="text-align: right;" class="col-12">

    <div class="card bg-secondary text-white float-right"
         style="width: 18rem;">
        <div class="card-header text-white font-weight-bold text-center"
             style="color: #c4c4c4">بيانات التاجر
        </div>
        <ul class="list-group list-group-flush">

            <li class="bg-dark list-group-item">

                <button dir="rtl" style="text-align: right;" type="button"
                        class="w-100 btn bg-info text-white font-weight-bold text-center">
                    الاسم <span style="margin-right: 25px; width: 140px;"
                                class=" badge badge-light">${traderData.name}</span>
                </button>
            </li>

            <li class="bg-dark list-group-item">
                <button dir="rtl" style="text-align: right;" type="button"
                        type="button"
                        class="w-100 btn bg-success text-white font-weight-bold text-center">
                    ارباح <span style="margin-right: 32px; width: 140px;"
                                class=" badge badge-light">${benifitsTotal} جنيه</span>
                </button>
            </li>

            <li class="bg-dark list-group-item">
                <button dir="rtl" style="text-align: right;" type="button"
                        type="button"
                        class="w-100 btn bg-info text-white font-weight-bold text-center">
                    مشتريات <span style="margin-right: 5px; width: 140px;"
                                  class=" badge badge-light">${incomingsTotal} جنيه</span>
                </button>
            </li>


            <li class="bg-dark list-group-item">
                <button dir="rtl" style="text-align: right;" type="button"
                        type="button"
                        class="w-100 btn bg-info text-white font-weight-bold text-center">
                    المدفوع <span style="margin-right: 15px; width: 140px;"
                                  class=" badge badge-light">${traderData.payed} جنيه</span>
                </button>
            </li>


            <li class="bg-dark list-group-item">

                <button dir="rtl" style="text-align: right;" type="button"
                        type="button"
                        class=" ${incomingsTotal == traderData.payed ?  'bg-success' :'bg-danger'}

						text-center w-100 btn font-weight-bold">

                    الباقي <span style="margin-right: 30px; width: 140px;"
                                 class=" badge badge-light">${remainingTotal} جنيه</span>
                </button>
            </li>

            <li class="bg-dark list-group-item"><form:form metho="GET"
                                                           action="pay-off-amount-for-trader"
                                                           modelAttribute="traderData">

                <form:hidden path="id"/>

                <input type="text"
                       class="text-center form-control font-weight-bold col-xs-3"
                       placeholder="ادخل المبلغ المسدد " name="moneyAmount">

                <button style="margin-top: 10px"
                    ${ incomingsTotal == traderData.payed ? 'disabled = "disabled"' : ''}
                        type="submit" class="btn btn-info form-control font-weight-bold">
                    قم بتسديد المبلغ
                </button>

            </form:form></li>

        </ul>
    </div>

    <div class="row">
        <div dir='rtl' class="col-lg-12 col-md-8">

            <ul class="nav nav-justified">
                <li class="nav-item btn">
                    <a class="nav-link btn btn-info active font-weight-bold" href="#incoming" data-toggle="tab">المشتريات</a>
                </li>
                <li class="nav-item btn">
                    <a class="nav-link btn btn-info font-weight-bold" href="#payments" data-toggle="tab">المدفوعات</a>
                </li>
            </ul>

            <div class="tab-content mt-4">
                <div class="tab-pane active" id="incoming">
                    <div class="table-responsive shadow" style="position: relative; height: 500px; overflow: auto;">
                        <table class="table table-striped table-dark table-sm">
                            <thead class="bg-secondary  shadow"
                                   style="position: -webkit-sticky; position: sticky; top: 0; z-index: 2;">
                            <tr class="">
                                <th class="col-1">الصنف</th>
                                <th class="col-1">الكمية</th>
                                <th class="col-1">السعر تجاري</th>
                                <th class="col-1">اجمالي السعر</th>
                                <th class="col-1">تاريخ التسجيل</th>
                                <th class="col-1">العملية</th>
                            </tr>
                            </thead>
                            <tbody class="font-weight-bold">
                            <c:forEach var="tempItem" items="${traderIncomings}">
                                <tr class="">
                                    <td class="col-1">${tempItem.item}</td>
                                    <td class="col-1">${tempItem.quantity}</td>
                                    <td class="col-1">${tempItem.tradePrice}</td>
                                    <td class="col-1">${tempItem.total}</td>
                                    <td class="col-1">${tempItem.date}</td>
                                    <td class="col-1"><c:if test="${tempItem.quantity == tempItem.currentQuantity}">
                                        <a style="font-size: 14px;"
                                           class="btn btn-sm btn-danger text-wight
										font-weight-bold btn-sm"
                                           onclick="return confirm('هل انت متأكد من حذف هذا الصنف ؟')"
                                           href="delete-incoming-trader-profile?id=${tempItem.id}&traderId=${traderData.id}">حذف</a>
                                    </c:if></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="tab-pane" id="payments">
                    <div class="table-responsive shadow" style="position: relative; height: 500px; overflow: auto;">
                        <table class="table  table-striped table-dark table-sm">
                            <thead class=" bg-secondary  shadow"
                                   style="position: -webkit-sticky; position: sticky; top: 0; z-index: 2;">
                            <tr class="">
                                <th class="col-1">المبلغ</th>
                                <th class="col-1">تاريخ الدفع</th>
                                <th class="col-1">العملية</th>
                            </tr>
                            </thead>
                            <tbody class="font-weight-bold">
                            <c:forEach var="tempItem" items="${traderData.traderPayList}">
                                <tr class="">
                                    <td class="col-1">${tempItem.amount}</td>
                                    <td class="col-1">${tempItem.date}</td>
                                    <td class="col-1"><a style="font-size: 14px;"
                                                         class=" btn btn-sm btn-danger text-wight font-weight-bold"
                                                         onclick="return confirm('هل انت متأكد من حذف هذا المبلغ؟')"
                                                         href="/delete-trader-pay/${traderData.id}?id=${tempItem.id}">حذف</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>

        </div>

        <div style="display: none; margin-top: 100px; padding: 10px; box-shadow: 5px 5px 5px 5px rgba(0, 0, 0, 0.60); direction: rtl"
             class="form container-fluid col-lg-4 col-md-6 fixed-top">

            <div class="card bg-dark">

                <div class="text-right card-body bg-dark text-white">

                    <h3>سداد مبلغ</h3>

                </div>
            </div>
        </div>


    </div>
</body>
</html>