<%@ include file="common/header.jspf"%>
<%@ include file="common/navbar.jspf"%>

<div class="container">
<form:form class="form-horizontal" commandName="placeToPopulate" method="POST">
	<form:hidden path="id" />
	
	<h1>${actionWord} a Place</h1>
	<div class="form-group">
		<form:label path="name" class="col-sm-2">Name:</form:label>
		<div class="col-sm-10">
			<form:input path="name" type="text" required="required"/>
		</div>
		<form:errors path="name" cssClass="text-warning"/>
	</div>
	<div class="form-group">
		<form:label path="description" class="col-sm-2">Description:</form:label>
		<div class="col-sm-10">
			<form:input path="description" type="text" required="required"/>
		</div>
		<form:errors path="description" cssClass="text-warning"/>
	</div>
	<div class="form-group">
		<form:label path="image" class="col-sm-2">Image:</form:label>
		<div class="col-sm-10">
			<form:input type="text" path="image"/>
		</div>
	</div>
	<div class="form-group">
		<form:label path="beenThere" class="col-sm-2">I've Been There:</form:label>
		<div class="col-sm-10">
			<form:checkbox path="beenThere"/>
		</div>
	</div>
	<div class="form-group">
		<form:label path="targetDate" class="col-sm-2">Date I Went or Would Like to Go:</form:label>
		<div class="col-sm-10">
			<form:input id="targetDate" path="targetDate" type="text"/>
		</div>
	</div>
	<button class="btn btn-success" type="submit">${actionWord}</button>
</form:form>
</div>

<script>
	#('#targetDate').dataPicker({
		format: 'mm/dd/yyyy'
	});
</script>
	
<%@ include file="common/footer.jspf"%>