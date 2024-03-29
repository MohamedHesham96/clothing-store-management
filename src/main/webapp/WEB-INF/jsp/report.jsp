<%@page import="com.hcoder.clothingstoremanagement.entity.Result" %>
<%@page import="org.apache.taglibs.standard.tag.common.xml.IfTag" %>
<%@page import="com.hcoder.clothingstoremanagement.entity.Incoming" %>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If" %>
<%@page import="java.lang.ProcessBuilder.Redirect" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" import="java.util.*,java.lang.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>

    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

    <title>التقرير</title>

    <link href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"
          rel="stylesheet">

    <script src="/webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>

    <script src="/webjars/jquery/3.4.1/jquery.min.js"></script>


    <script type="text/javascript">

    </script>


</head>

<body background="images/report.jpg"
      style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

<%@ include file="header.jsp" %>

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

        List<Result> list2022 = new ArrayList<Result>();
        List<Result> list2023 = new ArrayList<Result>();
        List<Result> list2024 = new ArrayList<Result>();

        int[] billsTotals = new int[3];
        int[] spendingsTotals = new int[3];

        for (int i = 0; i < listSize; i++) {

            if (list.get(i).getYear().intValue() == 2022) {

                list2022.add(list.get(i));

                if (list.get(i).getAmount() != null)
                    billsTotals[0] += list.get(i).getAmount().intValue();
                else
                    list.get(i).setAmount(0.0);

                if (list.get(i).getSpending() != null)
                    spendingsTotals[0] += list.get(i).getSpending().intValue();
                else
                    list.get(i).setSpending(0.0);
            } else if (list.get(i).getYear().intValue() == 2023) {

                list2023.add(list.get(i));

                if (list.get(i).getAmount() != null)
                    billsTotals[1] += list.get(i).getAmount().intValue();
                else
                    list.get(i).setAmount(0.0);

                if (list.get(i).getSpending() != null)
                    spendingsTotals[1] += list.get(i).getSpending().intValue();
                else
                    list.get(i).setSpending(0.0);

            } else if (list.get(i).getYear().intValue() == 2024) {

                list2024.add(list.get(i));

                if (list.get(i).getAmount() != null)
                    billsTotals[2] += list.get(i).getAmount().intValue();
                else
                    list.get(i).setAmount(0.0);

                if (list.get(i).getSpending() != null)
                    spendingsTotals[2] += list.get(i).getSpending().intValue();
                else
                    list.get(i).setSpending(0.0);

            }

        }

        boolean[] flags = {false, false, false};

        for (int i = 0; i < flags.length; i++) {

            if (!list2022.isEmpty())
                flags[0] = true;

            if (!list2023.isEmpty())
                flags[1] = true;

            if (!list2024.isEmpty())
                flags[2] = true;

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


    <%
        for (int i = 0; i < flags.length; i++) {

            if (flags[i] == true) {
    %>


    <div class="row  my-4">
        <div dir='rtl' class="col-lg-12 col-md-8">

            <h2><%=2022 - i%>
            </h2>

            <div>
                <table
                        class="table table-bordered table-striped table-dark table-sm">
                    <thead class=" bg-secondary">
                    <tr>
                        <th class="col-1">الشهر</th>
                        <th class="col-1">الربح</th>
                        <th class="col-1">المصاريف</th>
                        <th class="col-1">صافي الربح</th>

                    </tr>
                    </thead>
                    <tbody>

                    <%
                        if (flags[0] == true && i == 0) {
                    %>

                    <c:forEach var="temp" items="<%=list2022%>">
                        <tr>

                            <td>${temp.month.intValue()}</td>
                            <td>${temp.amount.intValue()}</td>
                            <td>${temp.spending.intValue()}</td>
                            <td>${temp.amount.intValue()- temp.spending.intValue()}</td>

                        </tr>

                    </c:forEach>

                    <%
                        }
                    %>


                    <%
                        if (flags[1] == true && i == 1) {
                    %>

                    <c:forEach var="temp" items="<%=list2023%>">
                        <tr>

                            <td>${temp.month.intValue()}</td>
                            <td>${temp.amount.intValue()}</td>
                            <td>${temp.spending.intValue()}</td>
                            <td>${temp.amount.intValue()- temp.spending.intValue()}</td>

                        </tr>
                    </c:forEach>

                    <%
                        }
                    %>

                    <%
                        if (flags[2] == true && i == 2) {
                    %>

                    <c:forEach var="temp" items="<%=list2024%>">
                        <tr>
                            <td class="font-weight-bold">${temp.month.intValue()}</td>
                            <td class="font-weight-bold">${temp.amount.intValue()}</td>
                            <td class="font-weight-bold">${temp.spending.intValue()}</td>
                            <td class="font-weight-bold">${temp.amount.intValue()- temp.spending.intValue()}</td>
                        </tr>
                    </c:forEach>

                    <%
                        }
                    %>

                    <tr class=" bg-success">

                        <td><h5 class="font-weight-bold">اجمالي السنة</h5></td>
                        <td><h5 class="font-weight-bold"><%=billsTotals[0]%>
                        </h5></td>
                        <td><h5 class="font-weight-bold"><%=spendingsTotals[0]%>
                        </h5></td>
                        <td><h5 class="font-weight-bold"><%=billsTotals[0] - spendingsTotals[0]%>
                        </h5></td>

                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

    </div>

    <%
            }
        }
    %>


</div>
</body>
</html>