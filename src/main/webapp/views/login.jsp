<%@ include file="common/header.jspf"%>
<%@ include file="common/navbar.jspf"%>

<style>
label {
	display: inline-block;
	width: 75px;
}
</style>

<div style="display: ${invalidInput ? "block" : "none"}; background-color: #ffcccc; color: #ff0000;">Your username or password is incorrect.</div>
<form action="/login" method="POST">
	<div><label>Username:</label> <input type="text" name="username"/></div>
	<div><label>Password:</label> <input type="password" name="password"/></div>
	<button type="submit">Log In</button>
</form>
<p>Go to src/main/webApp/data/users.json to see what credentials are correct.</p>

<%@ include file="common/footer.jspf"%>