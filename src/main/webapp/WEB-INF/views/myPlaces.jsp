<%@ include file="common/header.jspf"%>
<%@ include file="common/navbar.jspf"%>

<style>
.place {
	background-color: rgba(0,0,255,0.4);
	border-radius: 15px;
	box-shadow: 5px 5px 2px rgba(80,80,80,0.2);
	padding: 10px;
	margin: 5px;
}
<</style>

<div class="container">
	<h2>${username}'s Places:</h2>
	<div class="row" style="margin-bottom: 20px;">
		<c:forEach items="${places}" var="place">
			<div class="col-xs-12 col-md-3 place">
				<div><strong>Name:</strong> ${place.name}</div>
				<div>${place.description}</div>
				<c:if test="${place.beenThere}">
					<div style="color: green;"><strong>I've been there</strong> <span class="glyphicon glyphicon-ok"></span></div>
				</c:if>
				<c:if test="${not place.beenThere}">
					<div style="color: #996600;"><strong>I can't wait to go.</strong></div>
				</c:if>
				<div><strong>Date:</strong> <fmt:formatDate pattern="MM/dd/yyyy" value="${place.targetDate}"/></div>
				<div><strong>Image:</strong></div>
				<div><img class="myPlacesImage center" src="/resources/images/${place.imageName}"></img></div>
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