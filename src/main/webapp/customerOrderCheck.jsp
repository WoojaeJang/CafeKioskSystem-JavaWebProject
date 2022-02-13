<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 현황 확인</title>
</head>
<body>

<div><center>	
	<h3>☕ 주문 현황 확인 ☕</h3>
	<hr>
</center></div>

	
	
<div>
	<center>	
		<h4>💖 주문 현황 💖</h4>
	</center>

	<table width="70%" border="2"  bordercolor="purple" cellspacing="0" bordercolor="blue" margin="auto" align="center">
		
		<tr>
			<th>순번</th>
			<th>주문 번호</th>
			<th>고객 id</th>
			<th>음식명</th>
			<th>가격</th>
			<th>결제 방법</th>
			<th>결제 시간</th>
			<th>확인</th>
		</tr>		
		
		<c:forEach items="${requestScope.customerOrderCheck}" var="dataAll"> 
		<c:if test="${dataAll.orderCheck != 'done'}">
			<c:set var="count" value="${count + 1}" />
			<tr>
				<td align="center">${count}</td>
				<td align="center">${dataAll.orderNo}</td>
	 			<td align="center">${dataAll.cid}</td>
	 			<td align="center">${dataAll.fname}</td>
	 			<td align="center">${dataAll.payment}</td>
	 			<td align="center">${dataAll.pname}</td>
	 			<td align="center">${dataAll.orderTime}</td>	 			
	 			<td align="center">${dataAll.orderCheck}</td>
			</tr>
		</c:if>
		</c:forEach>	
	
	</table>
</div>
	
	<br>
<div><center>
	**참고 : 제조 완료된 주문(확인=done)은 현황표에서 삭제됨.
</center></div>
	
	<br><br>
	
<div><center>
	<input type="button" value="👈 처음으로 가기 👈" onClick="location.href='${pageContext.request.contextPath}/indexCustomer.html'" style="height: 50px;width: 200px;">
</center></div>
	
</body>
</html>