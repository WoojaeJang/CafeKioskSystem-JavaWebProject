<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
	
<form action="coffeeOrder?command=customerSignUp" method="post" >
<div><center>
	<h3>☕회원 가입☕</h3>
	<hr>
</center></div>

<div>
	<center>	
		<h4>💖 정보 입력 💖</h4>
	</center>

	<table width="70%" border="2" bordercolor="red" cellspacing="0" bordercolor="blue" margin="auto" align="center">
		<tr>
			<td align="center" width="20%" height="30">아이디</td>
			<td align="center" height="30">
				<input type="text" style="width:96%;height:70%;" name="cid" placeholder="아이디를 입력하세요">
			</td>
		</tr>
		
		<tr>
			<td align="center" width="20%" height="30">비밀번호</td>
			<td align="center" height="30">
				<input type="password" style="width:96%;height:70%;" name="cpw" placeholder="비밀번호를 입력하세요">
			</td>
		</tr>
		
		<tr>
			<td align="center" height="30">이름</td>
			<td align="center" height="30">
				<input type="text" style="width:96%;height:70%;" name="cname" placeholder="이름을 입력하세요">
			</td>
		</tr>
		
		<tr>
			<td align="center" height="30">휴대폰번호</td>
			<td align="center" height="30">
				<input type="text" style="width:96%;height:70%;" name="phone_number" placeholder="휴대폰 번호를 입력하세요">
			</td>
		</tr>
		
	</table>
</div>		

	<br><br>

<div><center>    
    <input type="submit" value="👉 회원 가입 👉" style="height: 50px;width: 200px;">
</center></div>   
    <br><br>	
	
<div><center>
	<input type="button" value="👈 처음으로 가기 👈" onClick="location.href='${pageContext.request.contextPath}/indexCustomer.html'" style="height: 50px;width: 200px;">
</center></div>

</form>	
</body>
</html>