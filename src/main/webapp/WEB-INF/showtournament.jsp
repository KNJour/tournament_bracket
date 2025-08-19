<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Tournament</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
        crossorigin="anonymous">
</head>

<body class="bg-light">
<div class="container-fluid py-4">
	<div class="page-header">
			    	<h1 class="h2 mb-0">Tournament Bracket Maker!</h1>
	</div>
			    
	<div>
			    <a href="/" class="btn btn-primary btn-sm">back to dashboard</a>
			    <a href="" class="btn btn-outline-secondary btn-sm">Something</a>
	 </div>
	<div class="d-flex justify-content-between align-items-center mb-1">
		<h1>
			<c:out value="${tournament.name}"/>
			(teamSize=<c:out value="${tournament.teamSize}"/>,
         	matchSize=<c:out value="${tournament.matchSize}"/>)
		</h1>
	</div>
<!-- 	delete form -->
	<div class="">
		<form action="/tournament/delete/${tournament.id}" method="post" class="d-inline">
        <button type="submit" class="btn btn-outline-danger btn-sm"
                onclick="return confirm('Delete this tournament?')">Delete</button>
      </form>
	</div>
<!-- 	Error check -->
		<c:if test="${not empty msg }">
		<div class="alert alert-into">
			${msg}
		</div>
		</c:if>
		
		
	<div class="row">
    <!-- Registered players -->
    <div class="col-md-6 mb-1">
      <div class="card">
        <div class="card-header">Registered Players</div>
        <div class="card-body">
          <c:choose>
            <c:when test="${empty registered}">
              <div class="p-3 text-muted">No one registered yet.</div>
            </c:when>
            <c:otherwise>
              <ul class="list-group list-group-flush">
                <c:forEach items="${registered}" var="player">
                  <li class="list-group-item"><c:out value="${player.name}"/></li>
                </c:forEach>
              </ul>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
    </div>
<!--     NEW PLAYERS REGISTRATION
 -->    <div class="col-md-6 mb-3">
      <div class="card">
        <div class="card-header font-weight-bold">Add Players to Tournament</div>
        <div class="card-body">
          <c:choose>
            <c:when test="${empty unassigned}">
              <div class="text-muted">All players are already registered, or you have no players.</div>
              <a class="btn btn-sm btn-primary mt-2" href="newplayer">Create Player</a>
            </c:when>
            <c:otherwise>
              <form action="/tournaments/register/${tournament.id}" method="post">
                <div class="mb-2">Select players to register:</div>
                <div class="border rounded p-2" style="max-height: 260px; overflow:auto;">
                  <c:forEach items="${unassigned}" var="player">
                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="player${player.id}"
                             name="playerIds" value="${player.id}">
                      <label class="form-check-label" for="player${player.id}">
                        <c:out value="${p.name}"/>
                      </label>
                    </div>
                  </c:forEach>
                </div>
                <button type="submit" class="btn btn-primarymt-1">Add Selected Players</button>
              </form>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>