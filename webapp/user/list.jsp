<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="kr">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>SLiPP Java Web Programming</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link href="../css/styles.css" rel="stylesheet">
</head>
<body>

<jsp:include page="/nav/nav.jsp" flush="false"  />

<div class="container" id="main">
    <div class="col-md-10 col-md-offset-1">
        <div class="panel panel-default">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th> <th>사용자 아이디</th> <th>이름</th> <th>이메일</th><th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user" varStatus="status">
                    <tr>
                        <th scope="row">${status.count}</th>
                        <td class="uid ${status.count}">${user.userId}</td>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td><a class="btn btn-success" role="button">수정</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- script references -->
<script src="../js/jquery-2.2.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/scripts.js"></script>

<!-- script onClickEvent -->
<script>
	let btnSuccess = document.querySelectorAll('a.btn.btn-success');
	[].forEach.call(btnSuccess, function(event){
		event.addEventListener('click', updateUser, false);
	});
	function updateUser(e){
		//console.log(this.closest('tr').querySelector('td.uid').innerHTML);
		//console.log(uid);
		let uid = this.closest('tr').querySelector('td.uid').innerHTML;
		let form = document.createElement("form");
		form.setAttribute("method", "Post");
		form.setAttribute("action", "/user/updateRequest")
		let input = document.createElement("input");
		input.setAttribute('name', 'userId');
		input.setAttribute('value', uid);
		input.setAttribute('type', "hidden");
		form.appendChild(input);
		//console.log(form);
		document.body.appendChild(form);
		form.submit();
	}
</script>
</body>
</html>
