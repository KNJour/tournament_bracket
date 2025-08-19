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
<title>New Tournament</title>
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
			    <a href="/" class="btn btn-primary btn-sm">back to dashboard</a>
			    <a href="" class="btn btn-outline-secondary btn-sm">Something</a>
	    </div>
	    <form action="${pageContext.request.contextPath}/createtournament" method="post" class="card p-3">
  
		    <div class="form-group">
		    
			      <label for="name">Tournament Name</label>
			      <input id="name" name="name" type="text" class="form-control" required>
			    </div>
			
			    <div class="form-group">
			      <label for="teamSize">Team Size</label>
			     <select id="teamSize" name="teamSize" class="form-control" required>
					    <option value="1" <c:if test="${teamSize == 1}">selected</c:if>>1 - (1 player per team)</option>
					    <option value="2" <c:if test="${teamSize == 2}">selected</c:if>>2 - (2 players per team)</option>
					    <option value="3" <c:if test="${teamSize == 3}">selected</c:if>>3 - (3 players per team)</option>
					    <option value="4" <c:if test="${teamSize == 4}">selected</c:if>>4 - (4 players per team)</option>
				</select>
					  <small class="form-text text-muted">
					    How many players make up a single team (e.g., 2 = 2v2).
					  </small>
			    </div>
			    <div class="form-group">
			      <label for="matchSize">Teams per Match</label>
			      <select id="matchSize" name="matchSize" class="form-control" required>
						    <option value="2" <c:if test="${matchSize == 2}">selected</c:if>>2  Head-to-head (two teams)</option>
						    <option value="3" <c:if test="${matchSize == 3}">selected</c:if>>3  Triple threat (three teams)</option>
						    <option value="4" <c:if test="${matchSize == 4}">selected</c:if>>4 Free-for-all (four teams)</option>
				  </select>
  <small class="form-text text-muted">
    How many teams appear in one match (e.g., 4 = four-team FFA).
  </small>
			    </div>
			
				    <div class="d-flex">
				      <button type="submit" class="btn btn-primary mr-2">Create</button>
				      <a class="btn btn-secondary" href="${pageContext.request.contextPath}/">Cancel</a>
			   </div>
 		 </form>
    

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
