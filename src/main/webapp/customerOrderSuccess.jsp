<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 결과</title>
</head>
<body>

<div><center>	
	<h2>☕결제 결과☕</h2>
	<hr><br>
</center></div>


<div><center>	
	<h1>👍👍👍 결제 완료 👍👍👍</h1>
</center></div>

<br><br>

<div><center>	
	<h4>💖 주문 번호 💖</h4>
	<h1>${requestScope.customerOrder.orderNo}</h1>
</center></div>

	<br><br>

<div><center>
	<input type="button" value="👈 처음으로 가기 👈" onClick="location.href='${pageContext.request.contextPath}/indexCustomer.html'" style="height: 50px;width: 200px;">
</center></div>
	
</body>
</html>