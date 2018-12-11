<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		
		var check = false;
		
		
		$("#id").blur(function() {
			var id = $(this).val();
			$.get("./idCheck?id="+id, function(result) {
				result = result.trim();
				vart msg = ' 사용할 수 없는 ID'
				if(result==0){
					 msg = '사용 가능한  ID';
					 check=true;
				}else{
					check=false;
				}
				$("#result").html(msg);
			});
		});
		
		
		$("#btn").click(function() {
			if(check){
			$("#frm").submit();
			}else{
				alert("중복 확인 하세요");
			}
			
		});
	});
	
	
	
</script>
</head>
<body>

<h1> Member Join Form</h1>

<form id="frm" action="./join" method="post">
	<p>ID : <input type = "text" neme="id" id="id"></p>
	<p id = "result"></p>
	<p>PW : <input type = "password" neme="pw"></p>
	<p>NAME : <input type = "text" neme="name"></p>
	<p>Email : <input type = "email" neme="email"></p>
	<p>Phone : <input type = "text" neme="id"></p>
	<p>Address : <input type = "text" name="address"></p>
	<input type ="button" value="JOIN" id="btn">


</form>


</body>
</html>