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
<title>New Player</title>
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
	    	<h1 class="h2 mb-0">Add a Player!</h1>
	    </div>
	    
	    <div>
	    <a href="/" class="btn btn-primary btn-sm">Nevermind</a>
	    </div>
    
   <!-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ -->
		  <form action="/createplayer" method="post" class="card p-3">
			    <div class="form-group">
				      <label for="name">Player Name</label>
				      <input id="name" name="name" type="text" class="form-control" required value="${name}">
			    </div>
			
			    <div class="d-flex">
				      <button type="submit" class="btn btn-primary m-2">Create</button>
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
