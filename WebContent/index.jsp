<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
 <%@page import="br.usjt.web.projetopi.model.Usuario" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  
 </head>
 <body class="bg-light">
<%
Usuario user = null;
session = request.getSession();

if (session.getAttribute("user") == null) {

	response.sendRedirect("pagprincipal.html");
}

%>
 <jsp:include page="Navbar.jsp" />
<main role="main" class="container">
  <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded shadow-sm">
    <img class="mr-3" src="/docs/4.3/assets/brand/bootstrap-outline.svg" alt="" width="48" height="48">
    <div class="lh-100">
      <h6 class="mb-0 text-white lh-100">Freemon</h6>
      <small>O controle nas suas mãos.</small>
    </div>
  </div>

  <div class="my-3 p-3 bg-white rounded shadow-sm">
    <h6 class="border-bottom border-gray pb-2 mb-0">Avisos recentes</h6>
    <div class="media text-muted pt-3">

    </div>
    <div class="media text-muted pt-3">
    
    </div>
    <div class="media text-muted pt-3">
     
    </div>
    <small class="d-block text-right mt-3">
      <a href="#">Todos avisos</a>
    </small>
  </div>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Progma", "no-cache");
response.setDateHeader("Expires", 0);
%>

</main>



</body></html>