<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ page isErrorPage="true" %>   

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Tournament Bracket Maker</title>
  <!-- Bootstrap -->
<link rel="stylesheet" 
						href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" 
						integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" 
						crossorigin="anonymous">
						    
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>
    <div class="container-fluid"> <!-- Beginning of Container -->
	    <div class="page-header">
	    	<h1 class="h2 mb-0">Tournament Bracket Maker!</h1>
	    </div>
	    
	    <div>
	    <a href="/newtournament" class="btn btn-primary btn-sm">New Tournament</a>
	    <a href="newplayer" class="btn btn-outline-secondary btn-sm">New Player</a>
	    </div>
    
   <!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ -->
    <div class="row m-1">
    <!--  Tournament Left Side-->
    	<div class="col-md-6 mb-3">
    		<div class="card">
    			<div class="card-header d-flex justify-content-between align-items-center">
		    			<h4>
		    			<!-- count of the tournaments -->
		    				<c:out value="${fn:length(tournaments)}"/> Tournamentsa
		    				
		    			</h4>
    			</div>
    			<div class="card-body p-0">
    				<c:choose>
				              <c:when test="${empty tournaments}">
				                <div class="p-3">No tournaments yet!!</div>
				              </c:when>
              		<c:otherwise>
              				<div class="table-responsive">
              					<table class="table table-sm mb-0 table-hover">
              							<thead class="thead-light">
              								<tr>
	              									<th>Name</th>
							                        <th class="text-center">Team Size</th>
							                        <th class="text-center">Teams Per Match</th>
							                        <th>Add Players</th>
							                        <th>Delete</th>
              								</tr>
              							</thead>
              							<tbody>
              									<c:forEach items="${tournaments}" var="t">
						                        <tr>
						                          <td class="align-middle">
							                            <a href="/tournaments/${t.id}">
							                              <c:out value="${t.name}"/>
							                            </a>
						                          </td>
									                          <td class="align-middle text-center"><c:out value="${t.teamSize}"/></td>
									                          <td class="align-middle text-center"><c:out value="${t.matchSize}"/></td>
									                          <td class="text-center">
									                            		<a class="btn btn-link btn-sm" href="tournament/view/${t.id}">Open</a>
									                          </td>
									                          <td class="text-right">
									                            		<form action="tournament/delete/${t.id} " method="post" class="d-inline">
																		  <button type="submit" class="btn btn-sm btn-outline-danger"
												          					onclick="return confirm('Delete tournament?, this will delete its matches and teams)">Delete</button>
																	</form>
									                          </td>
						                        </tr>
						                      </c:forEach>
              							</tbody>
              					</table>
              				</div>
              				</c:otherwise>
           				</c:choose>
    			</div>
    		</div>
    		<!-- card end for tourneys -->
    	</div>
        <!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ -->
<!--       PLAYER CARD RIGHT SIDE START -->
		<div class="col-md-6 mb-3">
			<div class="card">
				<div class="card-header d-flex justify-content-between align-items-center">
					<h4>
		    			<!-- count of the players -->
		    				There are <c:out value="${fn:length(players)}"/> Players
		    				
		    			</h4>
				</div>
				<div class="card-body p-0">
						<c:choose>
				              <c:when test="${empty players}">
				                <div class="p-3 text-muted">No players yet!</div>
				              </c:when>
				              <c:otherwise>
					                <div class="table-responsive">
					                  <table class="table table-sm mb-0 table-hover">
					                    <thead class="thead-light">
					                      <tr>
					                        <th>Name</th>
					                        <th>Delete</th>
					                      </tr>
					                    </thead>
					                    <tbody>
					                      <c:forEach items="${players}" var="p">
					                        <tr>
					                          <td class="align-middle"><c:out value="${p.name}"/></td>
					                          <td>
						                          <form action="player/delete/${p.id }" method="post" class="d-inline">
												  		<button type="submit" class="btn btn-sm btn-outline-danger"
												          onclick="return confirm('Delete player?')">Delete</button>
												</form>
											</td>
					                        </tr>
					                      </c:forEach>
					                    </tbody>
					                  </table>
					                </div>
			              </c:otherwise>
			            </c:choose>
				</div>
			</div>
		</div>

    </div>
    </div> <!-- End of Container -->
    
    
    
<!--     BOOTSTRAP JS -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
          integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
          crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-Fy6S5H8hZlN2Qf3H0vQ1hFQhKqvQ8K/ux6l1/Mqf0kGmWLa4nU5VnN0WqN6B9I1S"
          crossorigin="anonymous"></script>
</body>
</html>
