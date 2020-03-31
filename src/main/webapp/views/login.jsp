<%@ include file="common/header.jspf"%>
<%@ include file="common/navbar.jspf"%>

<div class="container">
	<c:if test="${invalidInput}">
		<div style="background-color: #ffcccc; color: #ff0000; margin-bottom: 20px;">Your username or password is incorrect.</div>
	</c:if>
	<div>
	</div>
	<form class="form-horrizontal" action="login" method="POST">
		<h1 style="margin-bottom: 20px;">Log In</h1>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<div class="form-group"><label>Username:</label> <input type="text" name="username"/></div>
		<div class="form-group"><label>Password:</label> <input type="password" name="password"/></div>
		<button class="btn btn-success" type="submit">Log In</button>
	</form>
	<p style="margin-top: 20px;">Go to src/main/webApp/data/users.json to see what credentials are correct.</p>
</div>

<%@ include file="common/footer.jspf"%>