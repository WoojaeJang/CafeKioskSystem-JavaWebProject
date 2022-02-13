<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문하기</title>
</head>
<body>

<form action="coffeeOrder?command=customerOrderAdd" method="post">

<div><center>	
	<h2>☕주문하기☕</h2>
	<hr>
</center></div>

<div>
	<center>	
		<h4>💖 고객 id 💖</h4>
	</center>
	
	<table width="70%" border="2" bordercolor="orange" cellspacing="0" bordercolor="blue" margin="auto" align="center">
		<tr>
			<td align="center" height="30">고객 id 입력</td>
		</tr>
		
		<tr>
			<td align="center" height="30">				
				<input type="text" style="width:98%;height:70%;" name="cid" value="${customer.cid}" placeholder="회원이신 경우 id를 입력하세요" >
			</td>
		</tr>
	</table>
</div>

<br>
	
<div>
	<center>	
		<h4>💖 메뉴 선택 💖</h4>
	</center>

	<table width="70%" border="2" bordercolor="yellowgreen" cellspacing="0" bordercolor="blue" margin="auto" align="center">
					
			<tr>
				<td align="center" width="20%" height="30">선택</td>
			    <td align="center">상품</td>
			    <td align="center">가격(원)</td>
			</tr>
				
		<c:forEach items="${requestScope.customerOrder.foodData}" var="foodAll">
			<tr>
				<td align="center" height="30">
					<input type="radio" name="fid" value="${foodAll.fid}" ondblclick="this.checked=false">
				</td>
				<td align="center">${foodAll.fname}</td>
				<td align="center">${foodAll.price}</td>	
			</tr>
		</c:forEach>
		
	</table>
</div>

<br>

<div>  
	<center>	
		<h4>💖 결제 방식 선택 💖</h4>
	</center>	
	
    <table width="70%" border="2"  bordercolor="blue" cellspacing="0" bordercolor="blue" margin="auto" align="center">
			
			<tr>
				<td align="center" width="20%" height="30">선택</td>
				<td align="center">결제 방식</td>
			</tr>
			
		<c:forEach items="${requestScope.customerOrder.payData}" var="payAll"> 
			<tr>
				<td align="center" height="30">
					<input type="radio" name="pid" value="${payAll.pid}" ondblclick="this.checked=false">
				</td>
				<td align="center">${payAll.pname}</td>
			</tr>
		</c:forEach>
		
    </table>
</div>

    <br><br>
    
<div><center>    
    <input type="submit" value="👉 결제 👉" style="height: 50px;width: 200px;">
</center></div>   
    
    <br><br>
    
<div><center>
	<input type="button" value="👈 처음으로 가기 👈" onClick="location.href='${pageContext.request.contextPath}/indexCustomer.html'" style="height: 50px;width: 200px;">
</center></div>
</form>   
</body>
</html>