<%@ include file="common/header.jspf"%>
<%@ include file="common/navbar.jspf"%>

<div class="container">
	<h2>${name}'s Places:</h2>
	<div class="row">
		<c:forEach items="${places}" var="place">
			<div class="col-xs-12 col-md-4">
				<div>${place.name}</div>
				<div>${place.image}</div>
				<div>${place.description}</div>
				<div>${place.beenThere}</div>
				<div><fmt:formatDate pattern="MM/dd/yyyy" value="${place.targetDate}"/></div>
				<div>
					<a class="btn btn-primary" href="/updateplace?id=${place.id}">Update</a>
					<a class="btn btn-danger" href="/deleteplace?id=${place.id}">Delete</a>
				</div>
			</div>	
		</c:forEach>
	</div>
	<div>
		<a class="btn btn-success" href="/newplace">Add Place</a>
	</div>
</div>
	
<%@ include file="common/footer.jspf"%>